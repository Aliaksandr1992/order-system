package com.epam.orderingsystem.service.impl;

import com.epam.orderingsystem.model.Child;
import com.epam.orderingsystem.model.GiftOrder;
import com.epam.orderingsystem.model.Wish;
import com.epam.orderingsystem.service.WishBuilderService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WishBuilderServiceImpl implements WishBuilderService {
    @Override
    public List<Wish> build(List<Child> children, List<GiftOrder> orders)
    {
        List<Wish> wishes = new ArrayList<>();
        Map<Long, Child> collectedChildren = children.stream().collect(Collectors
                .toMap(Child::getId, child -> new Child(child.getFirstName(), child.getLastName())));
        orders.forEach(giftOrder -> {
            String firstName = collectedChildren.get(giftOrder.getId()).getFirstName();
            String lastName = collectedChildren.get(giftOrder.getId()).getLastName();
            wishes.add(new Wish(firstName, lastName, giftOrder.getText(), giftOrder.getDatetime()));
        });
        return wishes;
    }
}
