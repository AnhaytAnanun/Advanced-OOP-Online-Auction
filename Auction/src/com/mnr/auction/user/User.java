package com.mnr.auction.user;

/**
 * Created by anhaytananun on 19.09.15.
 */
public class User {
    private static int idCounter = 0;

    private int id;
    private String email;
    private String address;
    private String username;
    private String password;
    private boolean status;
    private boolean canParticipate;
    private String token;

    public User(String email, String username, String password, String address) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.address = address;
        this.status = false;
        this.canParticipate = true;
        this.token = null;

        this.id = idCounter;
        idCounter++;
    }

    public void logIn() {
    }
    public void logOut() {
    }
    public void addCard() {
    }
    public void getCarts() {
    }
}
