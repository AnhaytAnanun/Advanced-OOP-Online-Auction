package com.mnr.auction.item;

import java.io.Serializable;

public class Item implements Serializable {
    private int idCounter = 0;

    private int id;
    private String name;
    private String description;
    private float startPrice;

    public Item(String name, String description, int startPrice) {
        this.name = name;
        this.description = description;
        this.startPrice = startPrice;
        this.id = idCounter++;
    }

    public int getId() {
        return id;
    }

    public boolean matchesQuery(String query) {
        return description.contains(query)
                || name.matches(query);
    }

    public float getStartPrice() {
        return startPrice;
    }
}
