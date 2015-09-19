package com.mnr.auction;

import com.mnr.auction.archive.Archive;
import com.mnr.auction.auction.AuctionList;
import com.mnr.auction.card.CardType;
import com.mnr.auction.item.Item;
import com.mnr.auction.store.Store;
import com.mnr.auction.user.User;
import com.mnr.auction.user.UserCatalog;

import java.util.Date;

public class Dispatcher {
    private static Dispatcher sharedDispatcher;

    private Archive archive;
    private AuctionList auctionList;
    private UserCatalog userCatalog;
    private Store store;

    /**
     * Constructor Methods
     */

    private Dispatcher() {
        archive = new Archive();
        auctionList = new AuctionList();
        userCatalog = new UserCatalog();
        store = new Store();
    }

    /**
     * Singletone Methods
     */

    public static Dispatcher sharedDispatcher() {
        if (sharedDispatcher == null) {
            sharedDispatcher = new Dispatcher();
        }

        return sharedDispatcher;
    }

    /**
     * Store Methods
     */

    public void addItemType(String name) {
        store.addItemType(name);
    }

    public void addItem(int itemTypeId, String name, String description, int startPrice) {
        store.addItem(itemTypeId, name, description, startPrice);
    }


    /**
     * User Methods
     */

    public boolean register(String email, String username, String password, String address) {
        return userCatalog.addUser(email, username, password, address);
    }

    public String logIn(String username, String password) {
        return userCatalog.logIn(username, password);
    }

    public void logOut(String token) {
        userCatalog.logOut(token);
    }

    public void addCard(Date date, int number, CardType type, String token) {
        userCatalog.addCard(date, number, type, token);
    }

    public void placeBid (int itemId, String token, float bid){
        User user = userCatalog.getUser(token);

        if (user != null) {
            auctionList.placeBid(itemId, user, bid);
        }
    }


    /**
     * Auction Methods
     */

    public void startAuction(Date startDate, Date endDate, int itemId) {
        Item item = store.getItemById(itemId);

        auctionList.startAuction(startDate, endDate, item);
    }
}