package com.livenow.week2.domain.baseEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseEntity {

    @Id
    @GeneratedValue
    protected Long id;

    protected LocalDateTime createdAt;
    protected LocalDateTime lastModifiedAt;

    public BaseEntity(Long id, LocalDateTime createdAt, LocalDateTime lastModifiedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
    }
}
