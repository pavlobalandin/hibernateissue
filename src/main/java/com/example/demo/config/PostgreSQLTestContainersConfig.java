package com.example.demo.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;


@TestConfiguration(proxyBeanMethods = false)
public class PostgreSQLTestContainersConfig {

    private static final Logger log = LogManager.getLogger(PostgreSQLTestContainersConfig.class);

    private static final String POSTGRES_IMAGE = "postgres:18-alpine";

    @Bean
    @ServiceConnection
    public PostgreSQLContainer<?> postgresContainer() {
        log.info("Starting PostgreSQL Testcontainer with image: {}", POSTGRES_IMAGE);
        return new PostgreSQLContainer<>(POSTGRES_IMAGE)
                .withLogConsumer(
                        outputFrame ->
                                log.debug(
                                        "[TESTCONTAINER-POSTGRES] {}",
                                        outputFrame.getUtf8String().strip()));
    }
}
