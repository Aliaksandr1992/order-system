package com.epam.orderingsystem.model;

import org.apache.commons.lang3.builder.HashCodeBuilder;

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