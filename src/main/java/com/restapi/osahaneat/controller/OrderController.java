package com.restapi.osahaneat.controller;

import com.restapi.osahaneat.payload.request.OrderRequest;
import com.restapi.osahaneat.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderServiceImpl orderService;

    @PostMapping("/")
    public ResponseEntity<?> getAllUser(@RequestBody OrderRequest orderRequest) {

        return new ResponseEntity<>(orderService.insertOrder(orderRequest), HttpStatus.OK);
    }
}
