package com.example.springboot.gcp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private String id;
    private String firstName;
    private String lastName;
    private String customerId;
    private Date orderDate;

}
