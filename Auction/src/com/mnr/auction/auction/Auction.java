package com.mnr.auction.auction;

import com.mnr.auction.item.Item;
import com.mnr.auction.user.User;

import java.util.Date;

public class Auction {
    private int id;
    private float bid;
    private User user;
    private Date startDate;
    private Date endDate;
    private Item item;

    public Auction(Date startDate, Date endDate, Item item) {
        this.item = item;
        this.bid = 0;
        this.user = null;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void placeBid (User user) {

    }

    public void getStatus () {

    }
}
