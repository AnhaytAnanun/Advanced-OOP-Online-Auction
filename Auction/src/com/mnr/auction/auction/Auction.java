package com.mnr.auction.auction;

import com.mnr.auction.item.Item;
import com.mnr.auction.user.User;

import java.util.Date;
import java.util.logging.Logger;

public class Auction {
    Logger LOGGER = Logger.getLogger(Auction.class.getName());

    private static int idGenerator = 0;

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
        this.id = idGenerator++;
    }

    synchronized public String placeBid (User user, float bid) {
        if (bid > this.bid && getStatus() == AuctionStatus.Started) {
            this.user = user;
            this.bid = bid;

            LOGGER.info("BID PLACED");

            return "BID PLACED";
        }

        LOGGER.warning("INVALID BID");

        return "INVALID BID";
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

    public User getUser() {
        return user;
    }

    public int getItemId() {
        return this.item.getId();
    }

    public float getBid() {
        return bid;
    }
}
