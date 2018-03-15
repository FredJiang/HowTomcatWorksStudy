package ex02fred.pyrmont;

import java.io.File;
import java.io.IOException;
import java.lang.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;
import java.util.*;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServletProcessor1 {
  private static final Logger logger = LoggerFactory.getLogger(ServletProcessor1.class);

  public void process(Request request, Response response) {
    String uri = request.getUri();
    String servletName = uri.substring(uri.lastIndexOf("/") + 1);
    URLClassLoader loader = null;
    logger.info(servletName);

    try {
      // create a URLClassLoader
      URL[] urls = new URL[1];
      URLStreamHandler streamHandler = null;
      File classPath = new File(Constants.WEB_ROOT);
      // the forming of repository is taken from the createClassLoader method in
      // org.apache.catalina.startup.ClassLoaderFactory
      String repository =
          (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
      logger.info(repository);
      // the code for forming the URL is taken from the addRepository method in
      // org.apache.catalina.loader.StandardClassLoader class.
      urls[0] = new URL(null, repository, streamHandler);
      loader = new URLClassLoader(urls);
    } catch (IOException e) {
      logger.error(e.toString());
    }
    Class myClass = null;
    try {
      myClass = loader.loadClass(servletName);
    } catch (ClassNotFoundException e) {
      logger.error(e.toString());
    }

    Servlet servlet = null;

    //
    //

    try {
      logger.info(myClass.getName());
      logger.info(myClass.getSuperclass().getName());
      Class[] classes = myClass.getInterfaces();
      logger.info("" + Arrays.asList(classes));
      logger.info("before casting");
      servlet = (Servlet) myClass.newInstance();
      logger.info("after casting");
      servlet.service((ServletRequest) request, (ServletResponse) response);
    } catch (Exception e) {
      logger.error(e.toString());
    } catch (Throwable e) {
      logger.error(e.toString());
    }
  }
}
