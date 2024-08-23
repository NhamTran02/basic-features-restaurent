package com.restapi.osahaneat.service;

import com.restapi.osahaneat.entity.*;
import com.restapi.osahaneat.entity.key.KeyOrderItem;
import com.restapi.osahaneat.payload.request.OrderRequest;
import com.restapi.osahaneat.responsitory.OrderItemRepository;
import com.restapi.osahaneat.responsitory.OrderRepository;
import com.restapi.osahaneat.service.impl.OrderServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderService implements OrderServiceImpl {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public boolean insertOrder(OrderRequest orderRequest) {
        try {
            Users user = new Users();
            user.setId(orderRequest.getUserId());

            Restaurent restaurent=new Restaurent();
            restaurent.setId(orderRequest.getResId());

            Orders orders = new Orders();
            orders.setUsers(user);
            orders.setRestaurent(restaurent);

            orderRepository.save(orders);

            List<OrderItem> orderItems=new ArrayList<>();
            for (int idFood:orderRequest.getFoodIds()){
                Food food=new Food();
                food.setId(idFood);

                OrderItem orderItem=new OrderItem();
                KeyOrderItem keyOrderItem=new KeyOrderItem(orders.getId(),idFood);
                orderItem.setKeyOrderItem(keyOrderItem);

                orderItems.add(orderItem);
            }

            orderItemRepository.saveAll(orderItems);

            return true;
        }
        catch (Exception e) {
            System.out.println("Error inserting order: "+e.getMessage());
            return false;
        }
    }
}
