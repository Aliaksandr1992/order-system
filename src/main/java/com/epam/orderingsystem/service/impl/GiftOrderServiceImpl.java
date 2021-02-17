package com.epam.orderingsystem.service.impl;

import com.epam.orderingsystem.model.GiftOrder;
import com.epam.orderingsystem.repository.GiftOrderRepository;
import com.epam.orderingsystem.service.GiftOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiftOrderServiceImpl implements GiftOrderService {
    @Autowired
    private GiftOrderRepository giftOrderRepository;

    @Override
    public void saveOrder(GiftOrder giftOrder)
    {
        giftOrderRepository.save(giftOrder);
    }

    @Override
    public void saveAllOrders(List<GiftOrder> giftOrders)
    {
        giftOrderRepository.saveAll(giftOrders);
    }

    @Override
    public List<GiftOrder> findAllOrders()
    {
        return giftOrderRepository.findAll();
    }
}
