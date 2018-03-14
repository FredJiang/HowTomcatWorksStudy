package ex02.pyrmont;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Request implements ServletRequest {
  private static final Logger logger = LoggerFactory.getLogger(Request.class);
  private InputStream input;
  private String uri;

  public Request(InputStream input) {
    this.input = input;
  }

  public String getUri() {
    return uri;
  }

  private String parseUri(String requestString) {
    logger.info(requestString);
    int index1, index2;
    index1 = requestString.indexOf(' ');
    if (index1 != -1) {
      index2 = requestString.indexOf(' ', index1 + 1);
      if (index2 > index1) return requestString.substring(index1 + 1, index2);
    }
    return null;
  }

  public void parse() {
    StringBuffer request = new StringBuffer(2048);
    int i;
    byte[] buffer = new byte[2048];
    try {
      i = input.read(buffer);
    } catch (IOException e) {
      e.printStackTrace();
      i = -1;
    }
    for (int j = 0; j < i; j++) {
      request.append((char) buffer[j]);
    }
    uri = parseUri(request.toString());
  }

  /* implementation of the ServletRequest*/
  public Object getAttribute(String attribute) {
    return null;
  }

  public Enumeration getAttributeNames() {
    return null;
  }

  public String getRealPath(String path) {
    return null;
  }

  public RequestDispatcher getRequestDispatcher(String path) {
    return null;
  }

  public boolean isSecure() {
    return false;
  }

  public String getCharacterEncoding() {
    return null;
  }

  public int getContentLength() {
    return 0;
  }

  public String getContentType() {
    return null;
  }

  public ServletInputStream getInputStream() throws IOException {
    return null;
  }

  public Locale getLocale() {
    return null;
  }

  public Enumeration getLocales() {
    return null;
  }

  public String getParameter(String name) {
    return null;
  }

  public Map getParameterMap() {
    return null;
  }

  public Enumeration getParameterNames() {
    return null;
  }

  public String[] getParameterValues(String parameter) {
    return null;
  }

  public String getProtocol() {
    return null;
  }

  public BufferedReader getReader() throws IOException {
    return null;
  }

  public String getRemoteAddr() {
    return null;
  }

  public String getRemoteHost() {
    return null;
  }

  public String getScheme() {
    return null;
  }

  public String getServerName() {
    return null;
  }

  public int getServerPort() {
    return 0;
  }

  public void removeAttribute(String attribute) {}

  public void setAttribute(String key, Object value) {}

  public void setCharacterEncoding(String encoding) throws UnsupportedEncodingException {}

  // added by fred
  /**
   * Returns the Internet Protocol (IP) source port of the client or last proxy that sent the
   * request.
   *
   * @return an integer specifying the port number
   * @since 2.4
   */
  public int getRemotePort() {
    return 0;
  }

  /**
   * Returns the host name of the Internet Protocol (IP) interface on which the request was
   * received.
   *
   * @return a <code>String</code> containing the host name of the IP on which the request was
   *     received.
   * @since 2.4
   */
  public String getLocalName() {
    return "";
  }

  /**
   * Returns the Internet Protocol (IP) address of the interface on which the request was received.
   *
   * @return a <code>String</code> containing the IP address on which the request was received.
   * @since 2.4
   */
  public String getLocalAddr() {
    return "";
  }

  /**
   * Returns the Internet Protocol (IP) port number of the interface on which the request was
   * received.
   *
   * @return an integer specifying the port number
   * @since 2.4
   */
  public int getLocalPort() {
    return 0;
  }
}
