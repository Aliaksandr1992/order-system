package com.epam.orderingsystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class GiftOrder implements Cloneable{

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @Getter
    @Setter
    private Child child;

    @Getter
    @Setter
    @Column
    private String text;

    @Getter
    @Setter
    @Column
    private Long datetime;

    public GiftOrder(Child child, String text, Long datetime)
    {
        this.child = child;
        this.text = text;
        this.datetime = datetime;
    }

    public GiftOrder(Long id, Child child, String text, Long datetime)
    {
        this.id = id;
        this.child = child;
        this.text = text;
        this.datetime = datetime;
    }
}
