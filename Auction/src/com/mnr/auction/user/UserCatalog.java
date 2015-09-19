package com.mnr.auction.user;

import com.mnr.auction.card.Card;
import com.mnr.auction.card.CardType;

import java.util.Date;
import java.util.HashSet;

public class UserCatalog {
    private HashSet<User> users;

    public boolean addUser(String email, String username, String password, String address) {
        User user = new User(email, username, password, address);
        return users.add(user);
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

        if (user != null) {
            user.addCard(date, number, type);
        }
    }

    public void logOut(String token) {
        User user = getUser(token);
        
        if (user != null) {
            user.logOut();
        }
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
