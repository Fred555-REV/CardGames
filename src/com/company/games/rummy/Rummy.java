package com.company.games.rummy;

import com.company.Console;
import com.company.actors.Rummer;
import com.company.decks.Card;
import com.company.decks.DeckI;
import com.company.decks.StandardDeck;

import java.util.ArrayList;
import java.util.List;

public class Rummy {
    DeckI deck;
    List<RummyHand> hands = new ArrayList<>();
    List<List<Card>> runs = new ArrayList<>();
    List<List<Card>> sets = new ArrayList<>();
    List<Card> discard = new ArrayList<>();
    int turnCounter = 0;

    public Rummy() {
    }

    private void setup() {
        addRummers();
        deck = new StandardDeck();
        deck.addCards();
        deck.shuffle();
    }

    private void addRummers() {
        for (int i = 0; i < Console.getInt(1, 5, "Enter Player Count 1-5", "Invalid Amount"); i++) {
            hands.add(new RummyHand(
                    new Rummer(Console.getString("Enter Name", true),
                            Console.getString("Enter Color", true))));
        }
    }

    private RummyHand activeHand() {
        return hands.get(turnCounter);
    }

    public void playGame() {
        while (round());
    }

    private boolean round() {
        deck.addDeck(discard);
        deck.shuffle();
        discard.add(deck.deal());
        while (turn()) {
            turnPass();
        }
        endRound();
        return Console.getInt(1, 2, "(1) Quit\t(2) Start next round", "Invalid Selection") != 1;
    }

    private boolean turn() {
        return activeHand().size() != 0;
    }

    private void endRound() {

    }

    private void turnPass() {
        turnCounter++;
        if (turnCounter >= hands.size()) {
            turnCounter = 0;
        }
    }
}
