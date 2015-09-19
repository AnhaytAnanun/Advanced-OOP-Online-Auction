package com.mnr.auction.user;

import com.mnr.auction.card.Card;
import com.mnr.auction.card.CardType;

import java.util.Date;
import java.util.HashSet;
import java.util.UUID;

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
    private HashSet<Card> cards;

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

    public String logIn(String username, String password) {
        if (!this.username.equals(username)
                || !this.password.equals(password)) {
            return null;
        }

        this.token = UUID.randomUUID().toString();
        this.status = true;

        return this.token;
    }

    public void logOut() {
        this.status = false;
        this.token = null;
    }

    public void addCard(Date date, int number, CardType type) {
        Card card = new Card(date, number, type);

        cards.add(card);
    }

    public void getCarts() {
    }

    public boolean canAuth(String token) {
        return this.token.equals(token)
                && this.status;
    }
}
