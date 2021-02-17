package com.epam.orderingsystem.service;

import com.epam.orderingsystem.model.GiftOrder;

import java.util.List;

public interface GiftOrderService {
    void saveOrder(GiftOrder giftOrder);
    void saveAllOrders(List<GiftOrder> giftOrders);
    List<GiftOrder> findAllOders();
}
