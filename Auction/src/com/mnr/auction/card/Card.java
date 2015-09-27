package com.mnr.auction.card;

import java.util.Date;

public class Card {

    private int id;
    private Date date;
    private int number;
    private CardType type;

    public Card(Date date, int number, CardType type) {
        this.date = date;
        this.number = number;
        this.type = type;
    }

    public void makePayement (){


    }
}
