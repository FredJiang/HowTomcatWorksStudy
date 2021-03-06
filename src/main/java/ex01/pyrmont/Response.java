package ex01.pyrmont;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//
//
//
//
//

public class Response {
  private static final Logger logger = LoggerFactory.getLogger(Response.class);
  private static final int BUFFER_SIZE = 1024;

  Request request;
  OutputStream output;
  //

  public Response(OutputStream output) {
    this.output = output;
  }

  public void setRequest(Request request) {
    this.request = request;
  }

  public void sendStaticResource() throws IOException {
    byte[] bytes = new byte[BUFFER_SIZE];
    FileInputStream fis = null;
    try {
      logger.info(request.getUri());
      File file = new File(HttpServer.WEB_ROOT, request.getUri());
      if (file.exists()) {
        fis = new FileInputStream(file);

        logger.info("find file");
        // 不加头的话，浏览器会报错
        String httpInfo = "HTTP/1.1 200 \r\n" + "Content-Type: text/html\r\n" + "\r\n";
        output.write(httpInfo.getBytes());

        int ch = fis.read(bytes, 0, BUFFER_SIZE);
        while (ch != -1) {
          output.write(bytes, 0, ch);
          ch = fis.read(bytes, 0, BUFFER_SIZE);
        }
      } else {
        String errorMessage =
            "HTTP/1.1 404 File Not Found\r\n"
                + "Content-Type: text/html\r\n"
                + "Content-Length: 23\r\n"
                + "\r\n"
                + "<h1>File Not Found</h1>";
        output.write(errorMessage.getBytes());
      }
    } catch (Exception e) {
      System.out.println(e.toString());
    } finally {
      if (fis != null) fis.close();
    }
  }
}
