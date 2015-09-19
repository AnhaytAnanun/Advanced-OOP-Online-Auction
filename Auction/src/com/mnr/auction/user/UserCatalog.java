package com.mnr.auction.user;

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

    public void logOut(String token) {
        for (User user : users) {
            if (user.canAuth(token)) {
                user.logOut();
            }
        }
    }
}
