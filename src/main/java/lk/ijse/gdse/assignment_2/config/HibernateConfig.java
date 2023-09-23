package lk.ijse.gdse.assignment_2.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@EnableJpaRepositories("lk.ijse.gdse.assignment_2.repo")
public class HibernateConfig {

    private Environment environment;

    @Autowired
    public HibernateConfig(Environment environment) {
        this.environment = environment;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf =
                new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("lk.ijse.gdse.assignment_2.entity");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emf.setJpaVendorAdapter(vendorAdapter);
        emf.setJpaPropertyMap(hibernateProperties()); // Hibernate property map
        return emf;
    }

    // Data Source create
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dmd = new DriverManagerDataSource();
        dmd.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        dmd.setUrl(environment.getProperty("spring.datasource.url"));
        dmd.setUsername(environment.getProperty("spring.datasource.username"));
        dmd.setPassword(environment.getProperty("spring.datasource.password"));
        return dmd;
    }

    // Transaction
    @Bean
    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf.getObject());
        return transactionManager;
    }

    // Load Hibernate Properties
    private Map<String, String> hibernateProperties() {
        Map<String, String> hibernateProperties = new HashMap<>();
        hibernateProperties.put("hibernate.hbm2ddl.auto", environment.getProperty("spring.jpa.hibernate.ddl-auto"));
        hibernateProperties.put("hibernate.show_sql", environment.getProperty("spring.jpa.show-sql"));
        hibernateProperties.put("hibernate.dialect", environment.getProperty("spring.jpa.properties.hibernate.dialect"));
        hibernateProperties.put("hibernate.format_sql", environment.getProperty("spring.jpa.properties.hibernate.format_sql"));
        return hibernateProperties;
    }

}
