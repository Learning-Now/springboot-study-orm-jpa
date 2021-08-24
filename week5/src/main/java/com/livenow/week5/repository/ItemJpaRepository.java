package com.livenow.week5.repository;

import com.livenow.week5.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemJpaRepository extends JpaRepository<Long, Item> {
}
