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
        this.bid = item.getStartPrice();
        this.user = null;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void placeBid (User user, float bid) {
        if (bid > this.bid) {
            this.user = user;
            this.bid = bid;
        }
    }

    public void getStatus () {

        Date date = new Date();
        if(startDate.before(new Date()) && endDate.after(new Date()))
        {
            status = AuctionStatus.Ended;
        }
        return status;
    }

    public int getItemId() {
        return this.item.getId();
    }
}
