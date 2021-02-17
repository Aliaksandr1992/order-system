package com.epam.orderingsystem.service;

import com.epam.orderingsystem.model.Child;
import com.epam.orderingsystem.model.GiftOrder;
import com.epam.orderingsystem.model.Wish;

import java.util.List;

public interface WishBuilderService {
    List<Wish> build(List<Child> children, List<GiftOrder> orders);
}
