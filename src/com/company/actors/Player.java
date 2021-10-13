package com.company.actors;

import com.company.Color;
import com.company.Console;
import com.company.decks.Card;
import com.company.games.Actor;
import com.company.games.insaneSevens.Card52;

import java.util.Collections;
import java.util.List;

public class Player implements Actor {
    private final String name;
    private final String color;
    private int score;

    public Player(String name, String color) {
        this.name = name;
        this.color = color;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public String getName() {
        return Color.getColor(color) + name;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public int getSelection(String suit, String rank, List<Card> cards) {

        return Console.getInt(0, cards.size(),
                Color.getColor(color) + "Select card to play or enter 0 to draw",
                "Invalid Selection");
    }

    @Override
    public int getSuit(List<Card> cards, List<String> suits) {
        return Console.getInt(1, 4, "Select suit 1-4 " + suits, "Invalid Selection") - 1;
    }

    private void displayAvailableActions() {
//        System.out.println("(0) Draw");
    }


    @Override
    public void win(int points) {
        score += points;
    }
}
