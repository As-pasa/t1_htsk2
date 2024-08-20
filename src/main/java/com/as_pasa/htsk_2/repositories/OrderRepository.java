package com.as_pasa.htsk_2.repositories;

import com.as_pasa.htsk_2.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
