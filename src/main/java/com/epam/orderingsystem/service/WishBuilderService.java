package com.epam.orderingsystem.service;

import com.epam.orderingsystem.model.Child;
import com.epam.orderingsystem.model.GiftOrder;
import com.epam.orderingsystem.model.Wish;

import java.util.List;

public interface WishBuilderService {
    /**
     * Build wish from children and order
     * @param children is list of all children
     * @param orders is list of all orders
     * @return list of wishes
     */
    List<Wish> build(List<Child> children, List<GiftOrder> orders);
}
