package com.epam.orderingsystem.processor.impl;

import com.epam.orderingsystem.model.Child;
import com.epam.orderingsystem.model.GiftOrder;
import com.epam.orderingsystem.parser.StaxXmlProcessor;
import com.epam.orderingsystem.processor.WishProcessor;
import com.epam.orderingsystem.service.ChildService;
import com.epam.orderingsystem.service.GiftOrderService;
import org.apache.commons.lang3.builder.HashCodeBuilder;
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

    @Override
    public void process(String path) throws Exception
    {
        try (StaxXmlProcessor staxXmlProcessor = new StaxXmlProcessor(new FileReader(
                ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + path)
        ))) {
            XMLStreamReader reader = staxXmlProcessor.getReader();
            List<Child> children = new ArrayList<>();
            List<GiftOrder> orders = new ArrayList<>();

            getParsedWishes(reader).forEach(wish -> {
                Child child = new Child(wish.firstName, wish.lastName);
                children.add(child);
                orders.add(new GiftOrder(child, wish.getText(), wish.getDatetime()));
            });

            giftOrderService.saveAllOrders(orders);
            childService.saveAllChildren(children);
        }
    }

    public List<Wish> getParsedWishes(XMLStreamReader reader) throws Exception
    {
        List<Wish> result = new ArrayList<>();
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
                if (!result.contains(wish))
                    result.add(new Wish(firstName, lastName, text, datetime));
            }
        }
        return result;
    }

    public class Wish {
        private String firstName;
        private String lastName;
        private String text;
        private Long datetime;

        public Wish(String firstName, String lastName, String text, Long datetime)
        {
            this.firstName = firstName;
            this.lastName = lastName;
            this.text = text;
            this.datetime = datetime;
        }

        public String getFirstName()
        {
            return firstName;
        }

        public void setFirstName(String firstName)
        {
            this.firstName = firstName;
        }

        public String getLastName()
        {
            return lastName;
        }

        public void setLastName(String lastName)
        {
            this.lastName = lastName;
        }

        public String getText()
        {
            return text;
        }

        public void setText(String text)
        {
            this.text = text;
        }

        public Long getDatetime()
        {
            return datetime;
        }

        public void setDatetime(Long datetime)
        {
            this.datetime = datetime;
        }

        @Override
        public int hashCode()
        {
            HashCodeBuilder builder = new HashCodeBuilder();
            builder.append(this.firstName);
            builder.append(this.lastName);
            builder.append(this.text);
            builder.append(this.datetime);
            return builder.toHashCode();
        }

        @Override
        public boolean equals(Object obj)
        {
            Wish wish = (Wish) obj;
            return this.firstName.equals(wish.getFirstName()) &&
                    this.lastName.equals(wish.getLastName()) &&
                    this.text.equals(wish.getText()) &&
                    this.datetime.equals(wish.getDatetime());
        }
    }

}
