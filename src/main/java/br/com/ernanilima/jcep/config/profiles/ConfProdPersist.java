package br.com.ernanilima.jcep.config.profiles;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "br.com.ernanilima.jcep.repository")
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