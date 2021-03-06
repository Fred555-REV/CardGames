package com.company.games.insaneSevens;

import com.company.Console;
import com.company.actors.AutoPlayer;
import com.company.actors.Player;
import com.company.decks.Card;
import com.company.decks.DeckI;
import com.company.decks.StandardDeck;
import com.company.games.Hand;

import java.util.ArrayList;
import java.util.List;

public class InsaneSevens {
    private List<Hand> hands = new ArrayList<>();
    private DeckI deck;
    private List<Card> discard = new ArrayList<>();
    public static final String WILD = "7";
    private int turnCounter;

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
        //Auto players vvvvv
//        hands.add(new Hand(new AutoPlayer("Fred", "Cyan")));
//        hands.add(new Hand(new AutoPlayer("AutoBot", "Yellow")));
    }

    public void playGame() {
        setup();
        while (round()) ;
        determineWinner();
    }

    private boolean round() {
        reshuffle();
        deal();
        while (turn(activeHand())) {
            passTurn();
        }
        endRound();
        return Console.getInt(1, 2, "(1) Quit\t(2) Start next round", "Invalid Selection") != 1;
    }

    private void reshuffle() {
        discard.clear();
        do {
            deck = new StandardDeck();
            deck.shuffle();
            discard.add(deck.deal());
        } while (discard.get(0).rank.equals(WILD));
    }

    private void deal() {
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
            draw();
        } else {
            Card card = activeHand.takeCard(choice - 1);
            playCard(card);
        }
        return activeHand.getHandValue() != 0;

    }

    private void playCard(Card card) {

        if (card.rank.equals(WILD)) {
            discard.remove(discard.size() - 1);
            discard.add(new Card52(WILD,
                    Card52.suits.get(activeHand().getSuit("52"))));
        } else {
            discard.add(card);
        }
    }

    private void draw() {
        if (deck.size() == 0) {
            Card activeCard52 = discard.remove(discard.size() - 1);
            deck.addDeck(discard);
            discard.add(activeCard52);
        }
        if (deck.size() == 0) {
            System.out.println("No more cards in the deck");
            return;
        }
        Card card = deck.deal();

        if (card.rank.equals(discard.get(discard.size() - 1).rank)
                || card.suit.equals(discard.get(discard.size() - 1).suit)) {
            if (activeHand().playDrawn(card)) {
                discard.add(card);
                return;
            }
        }
        activeHand().draw(card);
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
