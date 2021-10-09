package com.company.games.insaneSevens;

import com.company.Console;
import com.company.actors.Player;
import com.company.decks.Card;
import com.company.decks.DeckI;
import com.company.decks.StandardDeck;
import com.company.games.Hand;

import java.util.ArrayList;
import java.util.List;

public class InsaneSevens {
    List<Hand> hands = new ArrayList<>();
    DeckI deck;
    List<Card> discard = new ArrayList<>();
    Card activeCard;
    int turnCounter;

    public InsaneSevens() {
    }


    private Hand activeHand() {
        return hands.get(turnCounter);
    }

    private void setup() {
        addPlayers();
        deck = new StandardDeck();
        deck.shuffle();
    }

    private void addPlayers() {
        int playerCount = Console.getInt(1, 5,
                "Enter amount of players 1-5", "Invalid Selection");
        for (int i = 0; i < playerCount; i++) {
            hands.add(new Hand(
                    new Player(Console.getString("Enter Name", true),
                            Console.getString("Enter Color", true))));
        }
    }

    public void playGame() {
        setup();
        while (round()) ;
        determineWinner();
    }

    private boolean round() {
        do {
            deck.addDeck(discard);
            discard.add(deck.deal());
            deck.shuffle();
        } while (discard.get(0).rank.equals("8"));
        draw();
        while (turn(activeHand())) {
            passTurn();
        }
        endRound();
        return Console.getInt(1, 2, "(1) Quit\t(2) Start next round", "Invalid Selection") != 1;

    }

    private void draw() {
        System.out.println("Drawing...");
        for (int i = 0; i < 5; i++) {
            hands.forEach(hand -> hand.draw(deck.deal()));
        }
    }

    private boolean turn(Hand activeHand) {
        activeHand.displayHand();
        System.out.printf("ActiveCard %s\n", discard.get(discard.size() - 1));

        int choice = activeHand.getSelection(discard.get(discard.size() - 1));
        if (choice == 0) {
            if (deck.size() == 0) {
                Card activeCard52 = discard.remove(discard.size() - 1);
                deck.addDeck(discard);
                discard.add(activeCard52);
            }
            activeHand.draw(deck.deal());
        } else {
            Card card = activeHand.takeCard(choice - 1);
            if (card.rank.equals("7")) {
                discard.remove(discard.size()-1);
                discard.add(new Card52("7",
                        Card52.suits.get(
                                Console.getInt(1, 4, "Select suit 1-4 " + Card52.suits, "Invalid Selection") - 1)));
            } else {
                discard.add(card);
            }
        }
        return activeHand.getHandValue() != 0;

    }

    private void passTurn() {
        turnCounter++;
        if (turnCounter == hands.size()) {
            turnCounter = 0;
        }
    }

    private void endRound() {
        Hand winner = null;
        int points = 0;
        for (Hand hand : hands) {
            hand.displayHand();
            if (hand.getHandValue() == 0) {
                winner = hand;
            }
            points += hand.getHandValue();
            hand.clear();
        }
        assert winner != null;
        winner.win(points);
        System.out.printf("The round winner is: %s\tpoints earned this round: %s\ttotal score: %s\n",
                winner.getName(), points, winner.getScore());
    }

    private void determineWinner() {
        Hand winner = hands.get(0);
        for (Hand hand : hands) {
            if (hand.getHandValue() > winner.getHandValue()) {
                winner = hand;
            }
        }
        System.out.printf("%s Wins with a total of %s points.", winner.getName(), winner.getScore());
    }

}
