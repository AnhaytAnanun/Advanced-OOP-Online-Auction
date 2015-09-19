package com.mnr.auction.item;

public class Item {
    private int idCounter = 0;

    private int id;
    private String name;
    private String description;
    private int startPrice;

    public Item(String name, String description, int startPrice) {
        this.name = name;
        this.description = description;
        this.startPrice = startPrice;
        this.id = idCounter++;
    }

    public int getId() {
        return id;
    }
}