package com.company.actors;

import com.company.decks.Card;
import com.company.games.Actor;

import java.util.List;

public class Rummer implements Actor {
    public final String name;
    public final String color;
    private int score;

    public Rummer(String name, String color) {
        this.name = name;
        this.color = color;
        score = 0;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public int getSelection(String suit, String rank, List<Card> cards) {

        return 0;
    }

    @Override
    public int getSuit(List<Card> list, List<String> suits) {
        return 0;
    }

    @Override
    public void win(int points) {
        score += points;
    }
}
