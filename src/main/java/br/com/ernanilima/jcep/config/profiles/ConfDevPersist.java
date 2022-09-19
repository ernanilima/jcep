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
@Profile("dev")
public class ConfDevPersist extends ConfProfilesDevProd {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://127.0.0.1:5432/jcep");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");

        return super.setDataSource(dataSource);
    }
}