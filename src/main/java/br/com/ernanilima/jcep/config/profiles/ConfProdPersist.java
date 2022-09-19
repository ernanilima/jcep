package br.com.ernanilima.jcep.config.profiles;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Profile("prod")
public class ConfProdPersist extends ConfProfilesDevProd {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(System.getenv("ENV_DRIVER_CLASS_NAME"));
        dataSource.setUrl(System.getenv("ENV_JDBC_CONNECTION"));
        dataSource.setUsername(System.getenv("ENV_USER"));
        dataSource.setPassword(System.getenv("ENV_PASSWORD"));

        return super.setDataSource(dataSource);
    }
}