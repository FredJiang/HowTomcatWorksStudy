package ex03.pyrmont.connector.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpConnector implements Runnable {
  private static final Logger logger = LoggerFactory.getLogger(HttpConnector.class);

  boolean stopped;
  private String scheme = "http";

  public String getScheme() {
    logger.info(scheme);
    return scheme;
  }

  public void run() {
    logger.info("start run");
    ServerSocket serverSocket = null;
    int port = 8080;
    try {
      logger.info("create serverSocket");
      serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }
    while (!stopped) {
      // Accept the next incoming connection from the server socket
      Socket socket = null;
      try {
        socket = serverSocket.accept();
      } catch (Exception e) {
        continue;
      }
      // Hand this socket off to an HttpProcessor
      HttpProcessor processor = new HttpProcessor(this);
      processor.process(socket);
    }
  }

  public void start() {
    Thread thread = new Thread(this);
    thread.start();
  }
}
