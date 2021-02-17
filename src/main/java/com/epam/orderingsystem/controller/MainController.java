package com.epam.orderingsystem.controller;

import com.epam.orderingsystem.model.Wish;
import com.epam.orderingsystem.service.ChildService;
import com.epam.orderingsystem.service.GiftOrderService;
import com.epam.orderingsystem.service.WishBuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private WishBuilderService wishBuilderService;
    @Autowired
    private ChildService childService;
    @Autowired
    private GiftOrderService giftOrderService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showAllWishes(Model model)
    {
        List<Wish> wishes = wishBuilderService.build(childService.findAllChildren(), giftOrderService.findAllOrders());
        model.addAttribute("wishes", wishes);
        return "index";
    }
}
