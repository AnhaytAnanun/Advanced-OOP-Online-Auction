package com.mnr.auction.auction;

import com.mnr.auction.item.Item;
import com.mnr.auction.user.User;

import java.util.ArrayList;
import java.util.Date;

public class AuctionList {
    private ArrayList<Auction> auctions;

    public AuctionList() {
        auctions = new ArrayList<>();
    }

    public void placeBid (int itemId, User user, float bid){
        for (Auction auction : auctions) {
            if (auction.getItemId() == itemId) {
                auction.placeBid(user, bid);
            }
        }
    }

    public void startAuction(Date startDate, Date endDate, Item item) {
        Auction auction = new Auction(startDate, endDate, item);

        auctions.add(auction);
    }

    public void closeAuction() {
    }
}
