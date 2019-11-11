package br.com.project;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@EnableJpaRepositories(basePackages = {"br.com.project.*.repository"})
@EnableTransactionManagement
@Configuration
public class TestConfiguration {

    @Bean
    public PostgreSQLContainer<?> postgreSQLContainer() {
        PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:11-alpine");
        postgreSQLContainer.start();
        return postgreSQLContainer;
    }


    @Primary
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.mrocto.datasource")
    public DataSource dataSource(PostgreSQLContainer<?> postgreSQLContainer) {
        String url = String.format("jdbc:postgresql://%s:%s/%s", postgreSQLContainer.getContainerIpAddress(),
                postgreSQLContainer.getMappedPort(PostgreSQLContainer.POSTGRESQL_PORT),
                postgreSQLContainer.getDatabaseName());
        return DataSourceBuilder.create()
                .url(url)
                .username(postgreSQLContainer.getUsername())
                .password(postgreSQLContainer.getPassword())
                .driverClassName("org.postgresql.Driver")
                .build();
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(PostgreSQLContainer<?> postgreSQLContainer) throws SQLException {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource(postgreSQLContainer));
        bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        bean.setPackagesToScan("br.com.project.*.entity");
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.default_schema", "public");
        bean.setJpaProperties(jpaProperties);
        return bean;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(PostgreSQLContainer<?> postgreSQLContainer) throws SQLException {
        return new JpaTransactionManager(entityManagerFactory(postgreSQLContainer).getObject());
    }

}
