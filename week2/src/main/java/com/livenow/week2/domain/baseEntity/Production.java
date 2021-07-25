package com.livenow.week2.domain.baseEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "production_id"))
public class Production extends BaseEntity {

    private String name;

    @Builder
    public Production(Long id, String name, LocalDateTime createdAt, LocalDateTime lastModifiedAt) {
        super(id, createdAt, lastModifiedAt);
        this.name = name;
    }
}
