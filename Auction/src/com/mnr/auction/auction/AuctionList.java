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

    public String placeBid (int itemId, User user, float bid){
        if (user.getCard() == null) {
            LOGGER.warning("USER DOES NOT HAVE PAYMENT METHOD");

            return "NO PAYMENT METHOD";
        }

        for (Auction auction : auctions) {
            if (auction.getItemId() == itemId) {
                return auction.placeBid(user, bid);
            }
        }

        LOGGER.warning("NO AUCTION FOR THIS ITEM");

        return "NO OPEN AUCTION FOR THIS ITEM";
    }

    public void startAuction(Date startDate, Date endDate, Item item) {
        Auction auction = new Auction(startDate, endDate, item);

        LOGGER.info("AUCTION CREATED");

        auctions.add(auction);
    }

    public synchronized ArrayList<Auction> closeAuction() {
        ArrayList<Auction> closedAuctions = new ArrayList<>();

        for (Auction auction : auctions) {
            if (auction.getStatus() == AuctionStatus.Ended) {
                LOGGER.info("AUCTION CLOSED");

                User user = auction.getUser();
                user.getCard().makePayment();

                closedAuctions.add(auction);
                auctions.remove(auction);
            }
        }

        return closedAuctions;
    }
}
