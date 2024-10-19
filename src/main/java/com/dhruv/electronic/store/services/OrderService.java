package com.dhruv.electronic.store.services;

import com.dhruv.electronic.store.dtos.CreateOrderRequest;
import com.dhruv.electronic.store.dtos.OrderDto;
import com.dhruv.electronic.store.dtos.OrderUpdateRequest;
import com.dhruv.electronic.store.dtos.PageableResponse;

import java.util.List;

public interface OrderService {

    //create order
    OrderDto createOrder(CreateOrderRequest orderDto);

    //remove order
    void removeOrder(String orderId);

    //get orders of user
    List<OrderDto> getOrdersOfUser(String userId);

    //get orders
    PageableResponse<OrderDto> getOrders(int pageNumber, int pageSize, String sortBy, String sortDir);

    OrderDto updateOrder(String orderId, OrderUpdateRequest request);

    //order methods(logic) related to order

}
