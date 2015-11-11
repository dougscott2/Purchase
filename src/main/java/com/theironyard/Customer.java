package com.theironyard;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by DrScott on 11/11/15.
 */
@Entity
public class Customer {
    @Id
    @GeneratedValue
    Integer id;

    String name;
    String email;
}
