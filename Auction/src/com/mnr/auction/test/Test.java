package com.mnr.auction.test;

import com.mnr.auction.Dispatcher;

public class Test {
    public static void main(String[] args) {
        /**
         * Create System
         */

    }

    public static void addItemType() {
        Dispatcher.sharedDispatcher().addItemType("Picture");
        Dispatcher.sharedDispatcher().addItemType("Picture");
    }
}