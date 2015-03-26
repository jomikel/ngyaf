package io.theoperator.configuration.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Created by andreas on 2/27/15.
 */
@Configuration
@PropertySource("classpath:database.properties")
public class DataSourceConfiguration {

    private Environment environment;

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(environment.getProperty("database.url"));
        dataSource.setUsername(environment.getProperty("database.user"));
        dataSource.setPassword(environment.getProperty("database.password"));
        dataSource.setDriverClassName(environment.getProperty("database.driver"));
        return dataSource;
    }

}
