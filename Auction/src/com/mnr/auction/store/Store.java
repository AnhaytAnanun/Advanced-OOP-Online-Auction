package com.mnr.auction.store;

import com.mnr.auction.item.Item;
import com.mnr.auction.item.ItemType;

import java.util.HashSet;

/**
 * Created by Melkon on 19/09/2015.
 */
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
}
