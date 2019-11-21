package com.airhacks.data;

import javax.persistence.Entity;


public class Customer {

    private int id ;
    private String name;


    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Customer() {

    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
