package com.java.config;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableJpaRepositories(basePackages = "com.java.domain")
public class RootConfig {

  @Bean
  public BasicDataSource dataSource() {
    // org.apache.commons.dbcp.BasicDataSource
    BasicDataSource basicDataSource = new BasicDataSource();
    basicDataSource.setDriverClassName("org.postgresql.Driver");
    basicDataSource.setUrl("jdbc:postgresql://127.0.0.1:5432/users");
    basicDataSource.setUsername("postgres");
    basicDataSource.setPassword("root1to2gross");
    return basicDataSource;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
    LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
//    entityManagerFactory.setPersistenceUnitName("hibernate-persistence");
    entityManagerFactory.setDataSource(dataSource);
    entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    entityManagerFactory.setJpaDialect(new HibernateJpaDialect());
    entityManagerFactory.setPackagesToScan("com.java.domain");
    
    entityManagerFactory.setJpaPropertyMap(hibernateJpaProperties());
    return entityManagerFactory;
  }

  private Map<String, ?> hibernateJpaProperties() {
    HashMap<String, String> properties = new HashMap<>();
    properties.put("hibernate.hbm2ddl.auto", "update");
    properties.put("hibernate.show_sql", "false");
    properties.put("hibernate.format_sql", "false");
    properties.put("hibernate.hbm2ddl.import_files", "insert-data.sql");
    properties.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
    
    properties.put("hibernate.c3p0.min_size", "2");
    properties.put("hibernate.c3p0.max_size", "5");
    properties.put("hibernate.c3p0.timeout", "300"); // 5mins
    
    return properties;
  }

  @Bean
  public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
    //org.springframework.orm.jpa.JpaTransactionManager
    JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
    jpaTransactionManager.setEntityManagerFactory(emf);
    return jpaTransactionManager;
  }

}
