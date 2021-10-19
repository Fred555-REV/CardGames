package com.company.actors;

import com.company.decks.Card;
import com.company.games.Actor;
import com.company.games.insaneSevens.InsaneSevens;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AutoPlayer implements Actor {
    private final String name;
    private final String color;
    int score;

    public AutoPlayer(String name, String color) {
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
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            if (card.suit.equals(suit) || card.rank.equals(rank) || card.rank.equals(InsaneSevens.WILD)) {
                return i + 1;
            }
        }
        return 0;
    }

    @Override
    public int getSuit(List<Card> cards, List<String> suits) {
        Map<String, Integer> suitFreq = new HashMap<>();
        for (Card card : cards) {
            if (suitFreq.containsKey(card.suit)) {
                suitFreq.put(card.suit, (1 + suitFreq.get(card.suit)));
            } else {
                suitFreq.put(card.suit, 1);
            }
        }
        String suitMax = null;
        int max = 0;
        for (String key : suitFreq.keySet()) {
            if (suitFreq.get(key) > max) {
                suitMax = key;
                max = suitFreq.get(suitMax);
            }
        }
        assert suitMax != null;
        switch (suitMax) {
            case "\u2664":
                return 0;
            case "\u2665":
                return 1;
            case "\u2666":
                return 2;
            case "\u2667":
                return 3;
            default:
                return -1;
        }
    }


    @Override
    public void win(int points) {
        score += points;
    }
}
