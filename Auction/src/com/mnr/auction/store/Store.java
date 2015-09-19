package com.mnr.auction.store;

import com.mnr.auction.item.Item;
import com.mnr.auction.item.ItemType;

import java.util.HashSet;

public class Store {
    private HashSet<ItemType> itemTypes;

    public Store() {
        itemTypes = new HashSet<>();
    }

    public void addItemType(String name) {
        ItemType itemType = new ItemType(name);
        itemTypes.add(itemType);
    }

    public void addItem(int itemTypeId, String name, String description, int startPrice) {
        for (ItemType itemType : itemTypes) {
            if (itemType.getId() == itemTypeId) {
                Item item = new Item(name, description, startPrice);

                itemType.addItem(item);
            }
        }
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
