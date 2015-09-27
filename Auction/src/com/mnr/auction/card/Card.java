package com.mnr.auction.card;

import java.util.Date;
import java.util.logging.Logger;

public class Card {
    private static Logger LOGGER = Logger.getLogger(Card.class.getName());

    private int id;
    private Date date;
    private int number;
    private CardType type;

    public Card(Date date, int number, CardType type) {
        this.date = date;
        this.number = number;
        this.type = type;
    }

    public void makePayment() {
        LOGGER.info("PAYMENT MADE");
    }
}
