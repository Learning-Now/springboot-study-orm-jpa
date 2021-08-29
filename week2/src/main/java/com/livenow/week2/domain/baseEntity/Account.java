package com.livenow.week2.domain.baseEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "account_id"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account extends BaseEntity {

    private String name;

    @Builder
    public Account(Long id, String name, LocalDateTime createdAt, LocalDateTime lastModifiedAt) {
        super(id, createdAt, lastModifiedAt);
        this.name = name;
    }
}
