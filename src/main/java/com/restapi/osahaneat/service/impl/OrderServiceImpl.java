package com.restapi.osahaneat.service.impl;

import com.restapi.osahaneat.payload.request.OrderRequest;

public interface OrderServiceImpl {
    boolean insertOrder(OrderRequest orderRequest);
}
