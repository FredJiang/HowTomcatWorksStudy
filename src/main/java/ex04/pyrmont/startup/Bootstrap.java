/* explains Tomcat's default container */
package ex04.pyrmont.startup;

import ex04.pyrmont.core.SimpleContainer;
import org.apache.catalina.connector.http.HttpConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Bootstrap {
  private static final Logger logger = LoggerFactory.getLogger(Bootstrap.class);

  public static void main(String[] args) {
    logger.info("start main");
    HttpConnector connector = new HttpConnector();
    SimpleContainer container = new SimpleContainer();
    connector.setContainer(container);
    try {
      connector.initialize();
      connector.start();

      // make the application wait until we press any key.
      System.in.read();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
