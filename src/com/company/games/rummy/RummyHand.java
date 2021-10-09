package com.company.games.rummy;

import com.company.decks.Card;
import com.company.games.Actor;

import java.util.ArrayList;
import java.util.List;

public class RummyHand {
    private Actor rummer;
    List<Card> cards = new ArrayList<>();

    public RummyHand(Actor rummer) {
        this.rummer = rummer;
    }

    public int size(){
        return cards.size();
    }
}
