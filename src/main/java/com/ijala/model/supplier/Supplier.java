package com.ijala.model.supplier;

public class Supplier {
    private int id;
    private String name;
    private String contact;

    public Supplier(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }

    public Supplier(int id, String name, String contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}