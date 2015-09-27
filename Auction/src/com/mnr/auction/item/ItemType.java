package com.mnr.auction.item;

import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Logger;

public class ItemType extends Object {
    private final Logger LOGGER = Logger.getLogger(ItemType.class.getName());

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

        LOGGER.info("ITEM CREATED");
    }

    public ArrayList<Item> getItemsByQuery(String query) {
        ArrayList<Item> items = new ArrayList<>();

        for (Item item : this.items) {
            if (item.matchesQuery(query)) {
                LOGGER.info("ITEM FOUND");

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

    @Override
    public boolean equals(Object object) {
        if (object instanceof ItemType) {
            ItemType itemType = (ItemType) object;
            return itemType.name.equals(this.name);
        }

        return false;
    }
}