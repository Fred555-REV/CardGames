package com.company.games;

public interface Actor {
    int getScore();

    String getName();

    String getColor();

    int getSelection(String suit, String rank, int handSize); //return 0 for draw or the index+1 of card that you want to play

    void win(int points);

}
