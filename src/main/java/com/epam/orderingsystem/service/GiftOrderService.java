package com.epam.orderingsystem.service;

import com.epam.orderingsystem.model.GiftOrder;

import java.util.List;

public interface GiftOrderService {
    /**
     * Save order
     * @param giftOrder is order which need to save
     */
    void saveOrder(GiftOrder giftOrder);

    /**
     * Save many orders
     * @param giftOrders is a list of orders
     */
    void saveAllOrders(List<GiftOrder> giftOrders);

    /**
     * Find all saved orders
     * @return list of orders
     */
    List<GiftOrder> findAllOders();
}
