package com.att.tdp.bisbis10.repository;

import com.att.tdp.bisbis10.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is a repository interface for the Order entity
 * It extends JpaRepository to provide CRUD operations for the Order entity
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}