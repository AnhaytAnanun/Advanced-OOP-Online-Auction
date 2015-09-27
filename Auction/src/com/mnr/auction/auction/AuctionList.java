package com.mnr.auction.auction;

import com.mnr.auction.item.Item;
import com.mnr.auction.user.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

public class AuctionList {
    Logger LOGGER = Logger.getLogger(Auction.class.getName());

    private ArrayList<Auction> auctions;

    public AuctionList() {
        auctions = new ArrayList<>();
    }

    public boolean placeBid (int itemId, User user, float bid){
        for (Auction auction : auctions) {
            if (auction.getItemId() == itemId) {
                return auction.placeBid(user, bid);
            }
        }

        LOGGER.warning("NO AUCTION FOR THIS ITEM");

        return false;
    }

    public void startAuction(Date startDate, Date endDate, Item item) {
        Auction auction = new Auction(startDate, endDate, item);

        LOGGER.info("AUCTION CREATED");

        auctions.add(auction);
    }

    public void closeAuction() {
    }
}
