package br.com.ernanilima.jcep.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "br.com.ernanilima.jcep.repository")
@Profile("prod")
public class ConfProdPersist {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(System.getenv("ENV_JDBC_CONNECTION"));
        dataSource.setUsername(System.getenv("ENV_USER"));
        dataSource.setPassword(System.getenv("ENV_PASSWORD"));
        return dataSource;
    }

    private Properties getAdditionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.show_sql", "false");
        properties.setProperty("hibernate.format_sql", "false");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.connection.charSet", "UTF-8");
        return properties;
    }

    private String[] getPackagesToScan() {
        ArrayList<String> packages = new ArrayList<>();
        packages.add("br.com.ernanilima.jcep.domain");
        return packages.toArray(new String[packages.size()]);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(this.getPackagesToScan());

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(getAdditionalProperties());
        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
}