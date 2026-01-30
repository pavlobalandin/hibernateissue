package com.example.demo.entity;

import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;

import com.example.demo.config.PostgreSQLTestContainersConfig;
import com.example.demo.config.SomeRepoConfiguration;

@DataJpaTest
@ContextConfiguration(
        classes = {
            PostgreSQLTestContainersConfig.class,
            SomeRepoConfiguration.class,
            TestConfiguration.class
        })
@EnableJpaAuditing
@DependsOn("mainFlyway")
class SomeRepositoryITest {

    @Autowired private SomeRepository repository;

    @Test
    @Commit
    void testEntitySave() {
        final var entity =
                new SomeEntity(
                        1L,
                        Set.of(ItemsEnum.SOME_ONE, ItemsEnum.ANOTHER_ONE));

        final SomeEntity saved = repository.save(entity);

        assertThat(saved.getItems()).isEqualTo(Set.of(ItemsEnum.SOME_ONE, ItemsEnum.ANOTHER_ONE));
    }
}
