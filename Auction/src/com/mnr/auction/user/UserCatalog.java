package com.mnr.auction.user;

import com.mnr.auction.card.CardType;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

public class UserCatalog {
    private final Logger LOGGER = Logger.getLogger(UserCatalog.class.getName());

    private ArrayList<User> users;

    public UserCatalog() {
        users = new ArrayList<>();
    }

    public boolean addUser(String email, String username, String password, String address) {
        User user = new User(email, username, password, address);

        for (User existingUser : users) {
            if (user.equals(existingUser)) {
                LOGGER.warning("USER ALREADY EXISTS");

                return false;
            }
        }

        LOGGER.warning("USER REGISTERED");

        users.add(user);

        return true;
    }

    public String logIn(String username, String password) {
        for (User user : users) {
            String token = user.logIn(username, password);

            if (token != null) {
                return token;
            }
        }

        return null;
    }

    public void addCard(Date date, int number, CardType type, String token){
        User user = getUser(token);

        if (user == null) {
            LOGGER.warning("NO USER FOUND");

            return;
        }

        user.addCard(date, number, type);
    }

    public void logOut(String token) {
        User user = getUser(token);
        
        if (user == null) {
            LOGGER.warning("NO USER FOUND");

            return;
        }

        user.logOut();
    }

    public User getUser(String token) {
        for (User user : users) {
            if (user.canAuth(token)) {
                return user;
            }
        }

        return null;
    }
}