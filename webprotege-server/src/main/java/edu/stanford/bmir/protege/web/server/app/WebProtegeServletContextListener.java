package edu.stanford.bmir.protege.web.server.app;

import edu.stanford.bmir.protege.web.server.filter.WebProtegeWebAppFilter;
import edu.stanford.bmir.protege.web.server.init.WebProtegeConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import static edu.stanford.bmir.protege.web.server.logging.WebProtegeLogger.WebProtegeMarker;

public class WebProtegeServletContextListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(WebProtegeServletContextListener.class);

    public WebProtegeServletContextListener() {
    }

    public void contextInitialized(ServletContextEvent sce) {
        logger.info(WebProtegeMarker, "Initializing WebProtege");
        try {
            ServerComponent serverComponent = DaggerServerComponent.create();

            ServletContext servletContext = sce.getServletContext();

            servletContext.setAttribute(ServerComponent.class.getName(), serverComponent);


            servletContext.addServlet("DispatchService", serverComponent.getDispatchServlet())
                          .addMapping("/webprotege/dispatchservice");

            servletContext.addServlet("ProjectDownloadServlet", serverComponent.getProjectDownloadServlet())
                          .addMapping("/download");

            servletContext.addServlet("FileUploadServlet", serverComponent.getFileUploadServlet())
                          .addMapping("/webprotege/submitfile");

            servletContext.addListener(serverComponent.getSessionListener());

            serverComponent.getWebProtegeConfigurationChecker().performConfiguration();

            Runtime runtime = Runtime.getRuntime();
            logger.info("Max  Memory: {} MB", (runtime.maxMemory() / (1024 * 1024)));
            logger.info(WebProtegeMarker, "Initialized WebProtege");
        }
        catch (WebProtegeConfigurationException e) {
            logger.error(WebProtegeMarker, "Encountered a configuration error during initialization: {}", e.getMessage(), e);
            WebProtegeWebAppFilter.setConfigError(e);
        } catch (Throwable error) {
            logger.error(WebProtegeMarker, "Encountered an error during initialization: {}", error.getMessage(), error);
            WebProtegeWebAppFilter.setError(error);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info(WebProtegeMarker, "Shutting down WebProtege");
        ServletContext servletContext = servletContextEvent.getServletContext();
        ServerComponent serverComponent = (ServerComponent) servletContext.getAttribute(ServerComponent.class.getName());
        if(serverComponent != null) {
            logger.info(WebProtegeMarker, "Disposing of objects");
            serverComponent.getDisposableObjectManager().dispose();
        }
        logger.info(WebProtegeMarker, "WebProtege shutdown complete");
    }
}
