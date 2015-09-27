package com.mnr.auction.store;

import com.mnr.auction.item.Item;
import com.mnr.auction.item.ItemType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Logger;

public class Store {
    private final Logger LOGGER = Logger.getLogger(Store.class.getName());

    private HashSet<ItemType> itemTypes;

    public Store() {
        itemTypes = new HashSet<>();
    }

    public int addItemType(String name) {
        ItemType itemType = new ItemType(name);

        if (itemTypes.add(itemType)) {
            LOGGER.info("ITEM TYPE CREATED");

            return itemType.getId();
        }

        LOGGER.warning("ITEM TYPE ALREADY EXIST");

        return -1;
    }

    public void addItem(int itemTypeId, String name, String description, int startPrice) {
        for (ItemType itemType : itemTypes) {
            if (itemType.getId() == itemTypeId) {
                Item item = new Item(name, description, startPrice);

                itemType.addItem(item);
            }
        }
    }

    public ArrayList<Item> searchItems(int itemTypeId, String query) {
        ArrayList<Item> items = new ArrayList<>();

        for (ItemType itemType : itemTypes) {
            if (itemType.getId() == itemTypeId) {
                items.addAll(itemType.getItemsByQuery(query));
            }
        }

        return items;
    }

    public Item getItemById(int itemId) {
        for (ItemType itemType : itemTypes) {
            Item item = itemType.getItemById(itemId);

            if (item != null) {
                return item;
            }
        }

        return null;
    }
}
