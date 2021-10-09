package com.company.games.uno;

import com.company.decks.Card;

import java.util.List;

public class UnoCard extends Card {
    public static List<String> ranks = List.of(
            "0",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "Re",
            "Sk",
            "Wi",
            "+2",
            "+4"
    );
    public static List<String> suits = List.of(
            "Blue",
            "Red",
            "Yellow",
            "Green"
    );

    public UnoCard(String rank, String suit) {
        super(rank, suit);
    }

    @Override
    protected int getValue(String rank) {
        return 1;
    }

    @Override
    protected String getColor(String suit) {
        if (suit.equals("Wild")) {
            return "Black";
        }
        return suit;
    }
}
