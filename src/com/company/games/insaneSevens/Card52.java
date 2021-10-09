package com.company.games.insaneSevens;

import com.company.Color;
import com.company.decks.Card;

import java.util.List;

public class Card52 extends Card {
    public static List<String> ranks = List.of(
            "A",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "J",
            "Q",
            "K"
    );
    //    public static List<String> suits = List.of(
//            "C",
//            "D",
//            "H",
//            "S"
//    );
    public static List<String> suits = List.of(
            "\u2664",
            "\u2665",
            "\u2666",
            "\u2667"
    );

    public Card52(String rank, String suit) {
        super(rank, suit);
    }

    protected int getValue(String rank) {
        switch (rank) {
            case "7":
                return 50;
            case "J":
            case "Q":
            case "K":
                return 10;
            case "A":
                return 1;
            default:
                return Integer.parseInt(rank);
        }
    }

    protected String getColor(String suit) {
        switch (suit) {
            case "C":
            case "\u2664":
                return "Green";
            case "S":
            case "\u2665":
                return "Yellow";
//                return "Black";
            case "H":
            case "\u2666":
                return "Red";
            case "D":
            case "\u2667":
            default:
                return "Blue";
//                return "White";
        }
    }

    @Override
    public String toString() {
        return String.format("%s%s of %s%s\t",
                Color.getColor(color), rank, suit, Color.RESET);
    }
}
