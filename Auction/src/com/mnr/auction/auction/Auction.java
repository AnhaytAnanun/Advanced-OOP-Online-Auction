package com.mnr.auction.auction;

import com.mnr.auction.item.Item;
import com.mnr.auction.user.User;

import java.util.Date;
import java.util.logging.Logger;

public class Auction {
    Logger LOGGER = Logger.getLogger(Auction.class.getName());

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

    synchronized public boolean placeBid (User user, float bid) {
        if (bid > this.bid && getStatus() == AuctionStatus.Started) {
            this.user = user;
            this.bid = bid;

            LOGGER.info("BID PLACED");

            return true;
        }

        LOGGER.warning("INVALID BID");

        return false;
    }

    public AuctionStatus getStatus () {
        Date date = new Date();
        AuctionStatus status;

        if(date.before(startDate)) {
            status = AuctionStatus.NotStarted;
        } else if (date.after(endDate)) {
            status = AuctionStatus.Ended;
        } else {
            status = AuctionStatus.Started;
        }

        return status;
    }

    public int getItemId() {
        return this.item.getId();
    }
}
