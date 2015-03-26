package io.theoperator.configuration.websocket;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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
public class WebSocketApplicationConfiguration extends WebMvcConfigurerAdapter {



}
