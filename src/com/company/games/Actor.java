package com.company.games;

import com.company.decks.Card;

import java.util.List;

public interface Actor {
    int getScore();

    String getName();

    String getColor();

    int getSelection(String suit, String rank, List<Card> cards); //return 0 for draw or the index+1 of card that you want to play

    int getSuit(List<Card> list, List<String> suits);

    void win(int points);

}
