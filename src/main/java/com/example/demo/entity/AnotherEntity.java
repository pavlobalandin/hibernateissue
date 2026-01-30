package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.dialect.type.PostgreSQLEnumJdbcType;
import org.hibernate.type.SqlTypes;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "ANOTHER_ENTITY")
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
public class AnotherEntity {

    @Nullable private Long id;

    private Set<ItemsEnum> items = new HashSet<>();


    public AnotherEntity(Long id, Set<ItemsEnum> items) {
        this.id = id;
        this.items = items;
    }

    public AnotherEntity() {
    }

    @Nullable @Id
    @Column(name = "ID", updatable = false)
    public Long getId() {
        return id;
    }

    public void setId(@Nullable final Long id) {
        this.id = id;
    }

    // not working
//    @JdbcTypeCode(SqlTypes.ARRAY)
//    @JdbcType(PostgreSQLEnumJdbcType.class)
    // end

    // working
    @JdbcTypeCode(SqlTypes.ARRAY)
    @Enumerated(EnumType.STRING)
    @ColumnTransformer(write = "?::\"SOME\".\"ITEMS_TYPE\"[]")
    // end
    @Column(name = "ITEMS")
    public Set<ItemsEnum> getItems() {
        return items;
    }

    public void setItems(final Set<ItemsEnum> completedJobs) {
        this.items = completedJobs;
    }

}
