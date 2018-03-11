package ex01.pyrmont;

import java.net.Socket;
import java.net.ServerSocket;
import java.net.InetAddress;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpServer {
  private static final Logger logger = LoggerFactory.getLogger(HttpServer.class);
  private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
  private boolean shutdown = false;

  public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";

  public static void main(String[] args) {
    logger.info("start main");
    HttpServer server = new HttpServer();
    server.await();
  }

  public void await() {
    ServerSocket serverSocket = null;
    int port = 8080;
    try {
      serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
      logger.info("start server");
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }

    while (!shutdown) {
      Socket socket = null;
      InputStream input = null;
      OutputStream output = null;
      try {
        socket = serverSocket.accept();
        logger.info("wait client");
        input = socket.getInputStream();
        output = socket.getOutputStream();

        // create Request object and parse
        Request request = new Request(input);
        request.parse();

        // create Response object
        Response response = new Response(output);
        response.setRequest(request);
        response.sendStaticResource();

        // Close the socket
        socket.close();

        // check if the previous URI is a shutdown command
        shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
      } catch (Exception e) {
        e.printStackTrace();
        continue;
      }
    }
  }
}
