package com.theironyard;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by DrScott on 11/11/15.
 */
@Entity
public class Purchase {
    @Id
    @GeneratedValue
    Integer id;

    String date;
    String creditCard;
    String cvv;
    String category;

    @ManyToOne
    Customer customer;

}
