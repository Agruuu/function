package com.vampire.demo.test;
import lombok.Data;

import	java.time.LocalDate;

@Data
public class Book {

    private int id;
    private String name;
    private double price;
    private String type;
    private LocalDate publishDate;

    public Book(int id, String name, double price, String type, LocalDate publishDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.publishDate = publishDate;
    }
}
