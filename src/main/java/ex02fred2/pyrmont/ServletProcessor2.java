package ex02fred2.pyrmont;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServletProcessor2 {
  private static final Logger logger = LoggerFactory.getLogger(ServletProcessor2.class);

  public void process(Request request, Response response) {
    String uri = request.getUri();
    String servletName = uri.substring(uri.lastIndexOf("/") + 1);
    URLClassLoader loader = null;
    logger.info(servletName);

    try {
      // create a URLClassLoader
      URL[] urls = new URL[1];
      URLStreamHandler streamHandler = null;
      // File classPath = new File(Constants.WEB_ROOT);
      File classPath = new File(Constants.WEB_ROOT + "/../target/classes/");
      // the forming of repository is taken from the createClassLoader method in
      // org.apache.catalina.startup.ClassLoaderFactory
      String repository =
          (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
      logger.info(repository);
      // the code for forming the URL is taken from the addRepository method in
      // org.apache.catalina.loader.StandardClassLoader class.
      urls[0] = new URL(null, repository, streamHandler);
      loader = new URLClassLoader(urls);

      ClassLoader clNew = loader;
      logger.info(clNew.getClass().getName());
      URL[] newClassLoaderUrls = ((URLClassLoader) clNew).getURLs();
      for (URL url : newClassLoaderUrls) {
        logger.info(url.getFile());
      }

      ClassLoader clSystem = ClassLoader.getSystemClassLoader();
      logger.info(clSystem.getClass().getName());
      URL[] systemClassLoaderUrls = ((URLClassLoader) clSystem).getURLs();
      for (URL url : systemClassLoaderUrls) {
        logger.info(url.getFile());
      }

      ClassLoader clCurrent = Thread.currentThread().getContextClassLoader();
      logger.info(clCurrent.getClass().getName());
      URL[] currentClassLoaderUrls = ((URLClassLoader) clCurrent).getURLs();
      for (URL url : currentClassLoaderUrls) {
        logger.info(url.getFile());
      }

      // use current maven ClassLoader
      loader = (URLClassLoader) clCurrent;

    } catch (IOException e) {
      System.out.println(e.toString());
    }
    Class myClass = null;
    try {
      myClass = loader.loadClass(servletName);
    } catch (ClassNotFoundException e) {
      System.out.println(e.toString());
    }

    Servlet servlet = null;

    RequestFacade requestFacade = new RequestFacade(request);
    ResponseFacade responseFacade = new ResponseFacade(response);

    try {
      logger.info(myClass.getName());
      logger.info(myClass.getSuperclass().getName());
      //
      logger.info("before casting");
      servlet = (Servlet) myClass.newInstance();
      logger.info("after casting");
      servlet.service((ServletRequest) requestFacade, (ServletResponse) responseFacade);
    } catch (Exception e) {
      System.out.println(e.toString());
    } catch (Throwable e) {
      System.out.println(e.toString());
    }
  }
}
