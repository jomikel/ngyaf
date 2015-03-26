package io.theoperator.configuration.web;

import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;
import ro.isdc.wro.http.WroFilter;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * Created by andreas on 2/28/15.
 */
public class WebApplicationInitializer implements org.springframework.web.WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) {

        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(WebApplicationConfiguration.class);

        container.addListener(new ContextLoaderListener(rootContext));

        container.addFilter("sitemesh", new ConfigurableSiteMeshFilter()).addMappingForUrlPatterns(null, false, "/*");
        container.addFilter("wro", new WroFilter()).addMappingForUrlPatterns(null, false, "/wro/*");
        container.addFilter("urlRewrite", new UrlRewriteFilter()).addMappingForUrlPatterns(null, false, "/*");

        ServletRegistration.Dynamic dispatcher = container.addServlet("webapplication", new DispatcherServlet(rootContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/app/*");

    }

}
