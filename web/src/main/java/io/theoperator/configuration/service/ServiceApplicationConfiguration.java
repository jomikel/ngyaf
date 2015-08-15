package io.theoperator.configuration.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by andreas on 2/28/15.
 */

@EnableWebMvc
@ComponentScan(basePackages = {
        "io.theoperator.service",
        "io.theoperator.repository",
        "io.theoperator.configuration.service",
        "io.theoperator.restservice"
        //"io.theoperator.configuration.core"
})
public class ServiceApplicationConfiguration extends WebMvcConfigurerAdapter {

    /* seems to be unneeded...
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
        configurer.mediaTypes(new HashMap<String, MediaType>() {{
            put("json", MediaType.APPLICATION_JSON);
            put("xml", MediaType.APPLICATION_XML);
        }});
    }
    */

}
