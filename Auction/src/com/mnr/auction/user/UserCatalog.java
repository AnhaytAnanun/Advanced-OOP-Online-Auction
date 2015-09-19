package com.mnr.auction.user;

import java.util.HashSet;

/**
 * Created by anhaytananun on 19.09.15.
 */
public class UserCatalog {
    private HashSet<User> users;

    public boolean addUser(String email, String username, String password, String address) {
        User user = new User(email, username, password, address);
        return users.add(user);
    }
}
