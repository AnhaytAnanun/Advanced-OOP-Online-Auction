package com.mnr.auction.archive;

import com.mnr.auction.item.Item;
import com.mnr.auction.user.User;

import java.util.Date;

public class ArchiveRecord {
    private static int idGenerator = 0;

    private int id;
    private Date date;
    private int userId;
    private float price;
    private int itemId;

    public ArchiveRecord(int itemId, int userId, float price) {
        this.itemId = itemId;
        this.userId = userId;
        this.price = price;
        this.date = new Date();
        this.id = idGenerator++;
    }
}
