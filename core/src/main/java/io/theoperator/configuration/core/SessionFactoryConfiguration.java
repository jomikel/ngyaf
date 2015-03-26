package io.theoperator.configuration.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by andreas on 2/27/15.
 */

@Configuration
@PropertySource("classpath:hibernate.properties")
public class SessionFactoryConfiguration {

    private Environment environment;

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Bean
    @Autowired
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("io.theoperator.model");

        Properties props = new Properties();
        props.setProperty("hibernate.dialect", this.environment.getProperty("hibernate.dialect"));
        props.setProperty("hibernate.show_sql", this.environment.getProperty("hibernate.show_sql"));

        sessionFactory.setHibernateProperties(props);

        return sessionFactory;
    }

}
