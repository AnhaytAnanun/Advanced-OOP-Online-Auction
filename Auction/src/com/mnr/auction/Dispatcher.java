package com.mnr.auction;

import com.mnr.auction.archive.Archive;
import com.mnr.auction.auction.Auction;
import com.mnr.auction.auction.AuctionList;
import com.mnr.auction.card.CardType;
import com.mnr.auction.item.Item;
import com.mnr.auction.item.ItemType;
import com.mnr.auction.store.Store;
import com.mnr.auction.user.User;
import com.mnr.auction.user.UserCatalog;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

public class Dispatcher {
    private final Logger LOGGER = Logger.getLogger(UserCatalog.class.getName());

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

        new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        sleep(10000);
                        ArrayList<Auction> closedAuctions = auctionList.closeAuction();

                        for (Auction auction : closedAuctions) {
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
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

    public int addItemType(String name) {
        return store.addItemType(name);
    }

    public void addItem(int itemTypeId, String name, String description, int startPrice) {
        store.addItem(itemTypeId, name, description, startPrice);
    }

    public ArrayList<Item> searchItems(int itemTypeId, String query) {
        return store.searchItems(itemTypeId, query);
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

    public boolean placeBid (int itemId, String token, float bid){
        User user = userCatalog.getUser(token);

        if (user == null) {
            LOGGER.warning("NO USER FOUND");

            return false;
        }

        return auctionList.placeBid(itemId, user, bid);
    }


    /**
     * Auction Methods
     */

    public void startAuction(Date startDate, Date endDate, int itemId) {
        Item item = store.getItemById(itemId);

        auctionList.startAuction(startDate, endDate, item);
    }
}