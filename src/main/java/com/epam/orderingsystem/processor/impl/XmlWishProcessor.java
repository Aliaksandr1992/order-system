package com.epam.orderingsystem.processor.impl;

import com.epam.orderingsystem.model.Child;
import com.epam.orderingsystem.model.GiftOrder;
import com.epam.orderingsystem.model.Wish;
import com.epam.orderingsystem.parser.StaxXmlParser;
import com.epam.orderingsystem.processor.WishProcessor;
import com.epam.orderingsystem.service.ChildService;
import com.epam.orderingsystem.service.GiftOrderService;
import com.epam.orderingsystem.service.WishBuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.FileReader;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Component
public class XmlWishProcessor implements WishProcessor {

    public static final String FIRST_NAME_ELEMENT = "first-name";
    public static final String LAST_NAME_ELEMENT = "last-name";
    public static final String TEXT_ELEMENT = "text";
    public static final String DATETIME_ELEMENT = "datetime";
    public static final String WISH_ELEMENT = "wish";

    @Autowired
    private ChildService childService;
    @Autowired
    private GiftOrderService giftOrderService;
    @Autowired
    private WishBuilderService wishBuilderService;


    @Override
    public void process(String path) throws Exception
    {
        try (StaxXmlParser staxXmlParser = new StaxXmlParser(new FileReader(
                ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + path)
        ))) {
            XMLStreamReader reader = staxXmlParser.getReader();
            List<Child> children = new ArrayList<>();
            List<GiftOrder> orders = new ArrayList<>();

            getParsedWishes(reader).forEach(wish -> {
                Child child = new Child(wish.getFirstName(), wish.getLastName());
                children.add(child);
                orders.add(new GiftOrder(child, wish.getText(), wish.getDatetime()));
            });

            giftOrderService.saveAllOrders(orders);
            childService.saveAllChildren(children);
        }
    }

    /**
     * Parse xml file and separate each wish
     *
     * @param reader is xml reader
     * @return list of wishes
     * @throws Exception when exception is thrown
     */
    private List<Wish> getParsedWishes(XMLStreamReader reader) throws Exception
    {
        List<Wish> result = new ArrayList<>();
        List<Wish> savedWishes = wishBuilderService.build(childService.findAllChildren(), giftOrderService.findAllOrders());
        String firstName = null, lastName = null, text = null;
        Long datetime = null;
        while (reader.hasNext()) {
            int event = reader.next();
            if (event == XMLStreamConstants.START_ELEMENT && FIRST_NAME_ELEMENT.equals(reader.getLocalName())) {
                firstName = reader.getElementText();
            } else if (event == XMLStreamConstants.START_ELEMENT && LAST_NAME_ELEMENT.equals(reader.getLocalName())) {
                lastName = reader.getElementText();
            } else if (event == XMLStreamConstants.START_ELEMENT && TEXT_ELEMENT.equals(reader.getLocalName())) {
                text = reader.getElementText();
            } else if (event == XMLStreamConstants.START_ELEMENT && DATETIME_ELEMENT.equals(reader.getLocalName())) {
                LocalDate localDate = LocalDate.parse(reader.getElementText());
                Instant deliveryDate = localDate.plusDays(3).atTime(LocalTime.NOON).atZone(ZoneId.of("UTC")).toInstant();
                datetime = deliveryDate.toEpochMilli();
            } else if (event == XMLStreamConstants.END_ELEMENT && WISH_ELEMENT.equals(reader.getLocalName())) {
                Wish wish = new Wish(firstName, lastName, text, datetime);
                if (!savedWishes.contains(wish))
                    result.add(new Wish(firstName, lastName, text, datetime));
            }
        }
        return result;
    }
}
