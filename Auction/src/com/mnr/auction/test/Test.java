package com.mnr.auction.test;

import com.mnr.auction.Dispatcher;
import com.mnr.auction.card.CardType;
import com.mnr.auction.item.Item;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Test {
    private static final String FIRST_EMAIL = "email@email.email";
    private static final String FIRST_USERNAME = "first";
    private static final String FIRST_PASSWORD = "qwerty";

    private static final String SECOND_EMAIL = "mail@mail.mail";
    private static final String SECOND_USERNAME = "second";
    private static final String SECOND_PASSWORD = "123456";

    private static String winnerToken;

    public static void main(String[] args) {
        /**
         * Create System
         */

        int itemTypeId = addItemType();
        addItem(itemTypeId);
        addItem(-1);
        final ArrayList<Item> items = searchItems(itemTypeId);

        register(FIRST_EMAIL, FIRST_USERNAME, FIRST_PASSWORD);
        register(SECOND_EMAIL, SECOND_USERNAME, SECOND_PASSWORD);
        register(SECOND_EMAIL, SECOND_USERNAME, SECOND_PASSWORD);

        final String firstToken = login(FIRST_USERNAME, FIRST_PASSWORD);
        final String secondToken = login(SECOND_USERNAME, SECOND_PASSWORD);

        placeBid(items.get(0).getId(), secondToken, 510000);

        addAuction(items.get(0).getId());

        placeBid(items.get(0).getId(), firstToken, 600000);
        placeBid(items.get(0).getId(), secondToken, 510000);

        addCard(firstToken);

        bidDelayed(items.get(0).getId(), firstToken, 700000);
        bidDelayed(items.get(0).getId(), secondToken, 700000);

        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(1500);

                    logout(firstToken);

                    placeBid(items.get(0).getId(), firstToken, 600000);

                    logout(secondToken);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private static int addItemType() {
        int itemTypeId = Dispatcher.sharedDispatcher().addItemType("Picture").getId();
        Dispatcher.sharedDispatcher().addItemType("Picture");

        return itemTypeId;
    }

    private static void addItem(int itemTypeId) {
        Dispatcher.sharedDispatcher().addItem(itemTypeId, "Night", "Van Gog", 500000);
    }

    private static ArrayList<Item> searchItems(int itemTypeId) {
        return Dispatcher.sharedDispatcher().searchItems(itemTypeId, "Gog");
    }

    private static boolean register(String email, String username, String password) {
        return Dispatcher.sharedDispatcher().register(email, username, password, "Yerevan");
    }

    private static String login(String username, String password) {
        return Dispatcher.sharedDispatcher().logIn(username, password);
    }

    private static void placeBid(int itemId, String token, int amount) {
        Dispatcher.sharedDispatcher().placeBid(itemId, token, amount);
    }

    private static void bidDelayed(final int itemId, final String token, final int amount) {
        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(1000);
                    placeBid(itemId, token, amount);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private static void addAuction(int itemId) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 5);

        Dispatcher.sharedDispatcher().startAuction(new Date(), calendar.getTime(), itemId);
    }

    private static void addCard(String token) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 3);

        Dispatcher.sharedDispatcher().addCard(calendar.getTime(), 134, CardType.Master, token);
    }

    private static void logout(String token) {
        Dispatcher.sharedDispatcher().logOut(token);
    }
}
