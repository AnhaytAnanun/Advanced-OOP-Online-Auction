package com.mnr.auction.item;

import java.util.ArrayList;

public class ItemType extends Object {
    private static int idCounter = 0;

    private String name;
    private int id;
    private ArrayList<Item> items;

    public ItemType(String name) {
        this.name = name;
        this.id = idCounter++;
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public ArrayList<Item> getItemsByQuery(String query) {
        ArrayList<Item> items = new ArrayList<>();

        for (Item item : this.items) {
            if (item.matchesQuery(query)) {
                items.add(item);
            }
        }

        return items;
    }

    public Item getItemById(int itemId) {
        for (Item item : this.items) {
            if (item.getId() == itemId) {
                return item;
            }
        }

        return null;
    }

    public int getId() {
        return this.id;
    }
}
