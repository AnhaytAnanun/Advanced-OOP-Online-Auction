package com.mnr.auction.store;

import com.mnr.auction.item.Item;
import com.mnr.auction.item.ItemType;

import java.util.ArrayList;
import java.util.logging.Logger;

public class Store {
    private final Logger LOGGER = Logger.getLogger(Store.class.getName());

    private ArrayList<ItemType> itemTypes;

    public Store() {
        itemTypes = new ArrayList<>();
    }

    public ItemType addItemType(String name) {
        ItemType itemType = new ItemType(name);

        for (ItemType existingItemType : itemTypes) {
            if (itemType.equals(existingItemType)) {
                LOGGER.warning("ITEM TYPE ALREADY EXIST");

                return null;
            }
        }

        LOGGER.info("ITEM TYPE CREATED");

        itemTypes.add(itemType);

        return itemType;
    }

    public Item addItem(int itemTypeId, String name, String description, int startPrice) {
        for (ItemType itemType : itemTypes) {
            if (itemType.getId() == itemTypeId) {
                Item item = new Item(name, description, startPrice);

                itemType.addItem(item);

                return item;
            }
        }

        LOGGER.warning("CAN'T CREATE ITEM. INVALID ITEM TYPE ID");

        return null;
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
