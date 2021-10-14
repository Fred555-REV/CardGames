package com.company.games.uno;

import com.company.Color;
import com.company.Console;
import com.company.actors.Player;
import com.company.decks.Card;
import com.company.decks.DeckI;
import com.company.games.Hand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Uno {

    List<Hand> hands = new ArrayList<>();
    DeckI deck;
    List<Card> discard = new ArrayList<>();
    String color;
    String previousColor;
    int turnCounter;
    boolean isReversed = false;
    int cardsToDraw = 1;

    public Uno() {
    }


    private Hand activeHand() {
        return hands.get(turnCounter);
    }

    private void setup() {
        addPlayers();
        deck = new UnoDeck();
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
    }

    private boolean round() {
        do {
            deck.addDeck(discard);
            discard.add(deck.deal());
            deck.shuffle();
        } while (discard.get(0).suit.equals("Wild"));
        color = discard.get(0).suit;
        deal();
        while (turn(activeHand())) ;
        endRound();
        return Console.getInt(1, 2, "(1) Quit\t(2) Start next round", "Invalid Selection") != 1;

    }

    private void deal() {
        System.out.println("Dealing...");
        for (int i = 0; i < 7; i++) {
            hands.forEach(hand -> hand.draw(deck.deal()));
        }
    }

    private boolean turn(Hand activeHand) {
        activeHand.displayHand();
        Card activeCard = discard.get(discard.size() - 1);
        System.out.printf("Active Card is %s\n", activeCard);
        if (activeCard.suit.equals("Wild")) {
            System.out.printf("Color is %s%s%s\n", Color.getColor(activeCard.color), color, Color.RESET);
        }
        int choice = getChoice(activeHand, activeCard);
        if (choice == 0) {
            draw();
            passTurn();
        } else {
            previousColor = color;
            Card card = activeHand.takeCard(choice - 1);
            playCard(card);
            passTurn();
        }
        return activeHand.getHandValue() != 0;

    }

    private int getChoice(Hand activeHand, Card activeCard) {
        if (activeCard.rank.equals("+2") && cardsToDraw == 1) {
            return activeHand.getSelection(new UnoCard("2+", "Wild"), color);
        } else if (!activeCard.rank.equals("+4") || cardsToDraw == 1) {
            return activeHand.getSelection(activeCard, color);
        } else {
            if (Console.getInt(1, 2, "Challenge? (1) Y | (2) N", "Just pick one...") == 1) {
                if (challenge()) {
                    return activeHand.getSelection(activeCard, color);
                }
            }
        }
        return 0;
    }

    private boolean challenge() {
        //Goes to previous player
        // -1 0 1
        //  X
        //Go back previous
        reverse();
        passTurn();
        Hand previousHand = activeHand();

        //Checks if previous player had playable card
        //The placements of reverse has to change depending on who has to draw
        if (previousHand.hasPlayableCard(discard.get(discard.size() - 2), previousColor)) {
            //display outcome and then changes turn back to the current player
            System.out.printf("%s did have a playable card, they draw 4\n", previousHand.getName());
            cardsToDraw = 4;
            draw();
            //go Forward current
            // -1 0 1
            //    X
            reverse();
            passTurn();
            return true;
        } else {
            //display outcome and then changes turn back to the current player
            System.out.printf("%s did not have a playable card, ", previousHand.getName());
            //go Forward current
            // -1 0 1
            //    X
            reverse();
            passTurn();
            System.out.printf("%s draws 6\n", activeHand().getName());
            cardsToDraw = 6;
            return false;
        }
    }

    private void reverse() {
        isReversed = !isReversed;
    }

    private void playCard(Card card) {
        if (card.suit.equals("Wild")) {
            if (card.rank.equals("+4")) {
                cardsToDraw = 4;
            }
            discard.add(card);
            color = UnoCard.suits.get(activeHand().getSuit("Uno"));
        } else if (card.rank.equals("Re")) {
            reverse();
            color = card.suit;
        } else if (card.rank.equals("Sk")) {
            passTurn();
            color = card.suit;
        } else if (card.rank.equals("+2")) {
            if (cardsToDraw == 1) {
                cardsToDraw = 0;
            }
            cardsToDraw += 2;
            color = card.suit;
        } else {
            color = card.suit;
        }
        discard.add(card);
    }

    private void draw() {
        System.out.println("Drawing " + cardsToDraw);
        for (int i = 0; i < cardsToDraw; i++) {
            if (deck.size() == 0) {
                Card activeCard = discard.remove(discard.size() - 1);
                Collections.shuffle(discard);
                deck.addDeck(discard);
                discard.add(activeCard);
            }
            Card card = deck.deal();
            if (cardsToDraw == 1) {
                if (card.rank.equals(discard.get(discard.size() - 1).rank)
                        || card.suit.equals(color)) {
                    System.out.println("Drew " + card);

                    if (Console.getInt(1, 2, "(1) Play? (2) Keep?", "Invalid Input") == 1) {
                        playCard(card);
                        return;
                    }
                }
            }
            activeHand().draw(card);
        }

        cardsToDraw = 1;
    }

    private void passTurn() {
        if (isReversed) {
//            turnCounter--;
            if (--turnCounter == -1) {
                turnCounter = hands.size() - 1;
            }
        } else {
//            turnCounter++;
            if (++turnCounter == hands.size()) {
                turnCounter = 0;
            }
        }
    }

    private void endRound() {
        Hand winner = null;
        for (Hand hand : hands) {
            hand.displayHand();
            hand.clear();
            if (hand.getHandValue() == 0) {
                winner = hand;
                winner.win(1);
                break;
            }
        }
        assert winner != null;
        System.out.printf("The round winner is: %s\tTotal wins: %s\n",
                winner.getName(), winner.getScore());
    }

}
