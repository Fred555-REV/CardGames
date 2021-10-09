package com.company.decks;

import com.company.Color;


public abstract class Card {
    public final String name;
    public final String rank;
    public final String suit;
    public final String color;
    public final int value;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
        value = getValue(rank);
        color = getColor(suit);
        name = String.format("%s%s of %s%s",
                Color.getColor(color), rank, suit, Color.RESET);

    }

    protected abstract int getValue(String rank);

    protected abstract String getColor(String suit);

    public String toString() {
        return String.format(" %s%s of %s%s ",
                Color.getColor(color), rank, suit, Color.RESET);
    }
}
