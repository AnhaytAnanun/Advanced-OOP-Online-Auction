package com.mnr.auction.archive;

import com.mnr.auction.item.Item;
import com.mnr.auction.user.User;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

public class Archive {
    private Logger LOGGER = Logger.getLogger(Archive.class.getName());

    private ArrayList<ArchiveRecord> archiveRecords;

    public Archive() {
        archiveRecords = new ArrayList<>();
    }

    public void addRecord(int itemId, int userId, float price) {
        LOGGER.info("ARCHIVE RECORD ADDED");

        ArchiveRecord archiveRecord = new ArchiveRecord(itemId, userId, price);

        archiveRecords.add(archiveRecord);
    }
}