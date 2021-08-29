package com.livenow.week5.repository;
import com.livenow.week5.domain.Member;
import com.livenow.week5.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface OrderJpaRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    List<Order> findByMember(Member member);

}
