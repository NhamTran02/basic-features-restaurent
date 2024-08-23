package com.restapi.osahaneat.responsitory;

import com.restapi.osahaneat.entity.OrderItem;
import com.restapi.osahaneat.entity.key.KeyOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, KeyOrderItem> {
}
