package com.mnr.auction.user;

import com.mnr.auction.card.Card;
import com.mnr.auction.card.CardType;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.UUID;
import java.util.logging.Logger;

public class User {
    private Logger LOGGER = Logger.getLogger(User.class.getName());

    private static int idCounter = 0;

    private int id;
    private String email;
    private String address;
    private String username;
    private String password;
    private boolean status;
    private boolean canParticipate;
    private String token;
    private ArrayList<Card> cards;

    public User(String email, String username, String password, String address) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.address = address;
        this.status = false;
        this.canParticipate = true;
        this.token = null;
        this.cards = new ArrayList<>();

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

        LOGGER.info("USER LOG OUT");
    }

    public void addCard(Date date, int number, CardType type) {
        Card card = new Card(date, number, type);

        LOGGER.info("CARD ADDED");

        cards.add(card);
    }

    public boolean canAuth(String token) {
        return this.token != null && this.token.equals(token)
                && this.status;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof User) {
            User user = (User) object;

            return user.username.equals(this.username)
                    || user.email.equals(this.email);
        }

        return false;
    }
}
