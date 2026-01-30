package com.example.demo.config;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.flyway.autoconfigure.FlywayProperties;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(
        basePackages =
                "com.example.demo")
@EnableJpaRepositories(
        basePackages =
                "com.example.demo.entity")
@EntityScan(
        basePackages =
                "com.example.demo.entity")
@EnableTransactionManagement(proxyTargetClass = true)
@EnableConfigurationProperties(FlywayProperties.class)
public class SomeRepoConfiguration {

    @Bean(name = "mainFlyway", initMethod = "migrate")
    public Flyway mainFlyway(
            final DataSource dataSource, final FlywayProperties flywayProperties) {
        return Flyway.configure()
                .dataSource(dataSource)
                .schemas("SOME")
                .defaultSchema("SOME")
                .locations("classpath:migration")
                .baselineOnMigrate(flywayProperties.isBaselineOnMigrate())
                .createSchemas(true)
                .load();
    }
}
