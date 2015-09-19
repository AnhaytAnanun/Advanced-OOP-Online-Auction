package com.mnr.auction;

import com.mnr.auction.archive.Archive;
import com.mnr.auction.auction.AuctionList;
import com.mnr.auction.store.Store;
import com.mnr.auction.user.UserCatalog;

/**
 * Created by anhaytananun on 19.09.15.
 */
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
}