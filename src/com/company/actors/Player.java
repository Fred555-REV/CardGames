package com.company.actors;

import com.company.Color;
import com.company.Console;
import com.company.games.Actor;

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
    public int getSelection(String activeSuit, String activeRank, int handSize) {

        return Console.getInt(0, handSize,
                Color.getColor(color) + "Select card to play or enter 0 to draw",
                "Invalid Selection");
    }

    private void displayAvailableActions() {
//        System.out.println("(0) Draw");
    }


    @Override
    public void win(int points) {
        score += points;
    }
}
