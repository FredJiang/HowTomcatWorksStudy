package ex03.pyrmont.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ex03.pyrmont.connector.http.HttpConnector;

public final class Bootstrap {
  private static final Logger logger = LoggerFactory.getLogger(Bootstrap.class);

  public static void main(String[] args) {
    logger.info("start server");
    HttpConnector connector = new HttpConnector();
    connector.start();
  }
}
