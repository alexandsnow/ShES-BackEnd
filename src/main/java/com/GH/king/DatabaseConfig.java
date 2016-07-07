package com.GH.king;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by alex on 2016/1/22.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.GH.king.repository",
        entityManagerFactoryRef = "mEntityManagerFactory",
        transactionManagerRef = "mTransactionManager"
)
public class DatabaseConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
    @Bean(name = "mEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mEntityManagerFactory() {
        Map<String, Object> properties = new Hashtable<>();
        properties.put("javax.persistence.schema-generation.database.action", "none");

        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabasePlatform("org.hibernate.dialect.SQLServer2008Dialect");

        LocalContainerEntityManagerFactoryBean factory =
                new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(adapter);
        factory.setDataSource(this.dataSource());
        factory.setPackagesToScan("com.GH.king.domain");
        factory.setJpaPropertyMap(properties);
        return factory;
    }

    @Bean(name = "mTransactionManager")

    public PlatformTransactionManager springTransactionManager() {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(mEntityManagerFactory().getObject());
        return manager;
    }
}
