package com.example.ecommerce.DAO;

import com.example.ecommerce.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
