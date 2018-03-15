package ex02fred.pyrmont;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Response implements ServletResponse {
  private static final Logger logger = LoggerFactory.getLogger(Response.class);
  private static final int BUFFER_SIZE = 1024;

  Request request;
  OutputStream output;
  PrintWriter writer;

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
      File file = new File(Constants.WEB_ROOT, request.getUri());
      //
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
    } catch (FileNotFoundException e) {
      String errorMessage =
          "HTTP/1.1 404 File Not Found\r\n"
              + "Content-Type: text/html\r\n"
              + "Content-Length: 23\r\n"
              + "\r\n"
              + "<h1>File Not Found</h1>";
      output.write(errorMessage.getBytes());
      //
      //
      //
    } finally {
      if (fis != null) fis.close();
    }
  }

  /** implementation of ServletResponse */
  public void flushBuffer() throws IOException {}

  public int getBufferSize() {
    return 0;
  }

  public String getCharacterEncoding() {
    return null;
  }

  public Locale getLocale() {
    return null;
  }

  public ServletOutputStream getOutputStream() throws IOException {
    return null;
  }

  public PrintWriter getWriter() throws IOException {
    // autoflush is true, println() will flush,
    // but print() will not.
    writer = new PrintWriter(output, true);
    return writer;
  }

  public boolean isCommitted() {
    return false;
  }

  public void reset() {}

  public void resetBuffer() {}

  public void setBufferSize(int size) {}

  public void setContentLength(int length) {}

  public void setContentType(String type) {}

  public void setLocale(Locale locale) {}

  // added by fred
  /**
   * The default behavior of this method is to call setCharacterEncoding(String charset) on the
   * wrapped response object.
   *
   * @since 2.4
   */
  public void setCharacterEncoding(String charset) {}

  /**
   * The default behavior of this method is to return getContentType() on the wrapped response
   * object.
   *
   * @since 2.4
   */
  public String getContentType() {
    return null;
  }
}








