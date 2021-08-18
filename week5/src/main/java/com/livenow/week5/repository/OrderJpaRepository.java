package com.livenow.week5.repository;
import com.livenow.week5.domain.Member;
import com.livenow.week5.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {

    List<Order> findByMember(Member member);

}
