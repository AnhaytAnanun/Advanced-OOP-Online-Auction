package com.mnr.auction.archive;

import com.mnr.auction.item.Item;
import com.mnr.auction.user.User;

import java.util.ArrayList;
import java.util.Date;

public class Archive {
    private ArrayList<ArchiveRecord> archiveRecords;

    public Archive() {
        archiveRecords = new ArrayList<>();
    }

    public void addRecord(int itemId, int userId, float price) {
        ArchiveRecord archiveRecord = new ArchiveRecord(itemId, userId, price);
    }
}