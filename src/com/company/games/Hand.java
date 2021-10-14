package com.company.games;

import com.company.Color;
import com.company.decks.Card;
import com.company.games.insaneSevens.Card52;
import com.company.games.insaneSevens.InsaneSevens;
import com.company.games.uno.UnoCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private final Actor player;
    private final List<Card> cards = new ArrayList<>();

    public Hand(Actor player) {
        this.player = player;
    }


    public int getHandValue() {
        int handValue = 0;
        for (Card card : cards) {
            handValue += card.value;
        }
        return handValue;
    }

    public boolean hasPlayableCard(Card activeCard, String color) {
        for (Card card : cards) {
            if (validateCard(color, activeCard.rank, card)) {
                return true;
            }
        }
        return false;
    }

    public int getSelection(Card activeCard) {
        int selection = -1;

        do {
            if (selection != -1) {
                System.out.println("Invalid Card");
            }
            selection = player.getSelection(activeCard.suit, activeCard.rank,
                    Collections.unmodifiableList(cards));
            if (selection == 0) {
                break;
            }
            if (cards.get(selection - 1).rank.equals(InsaneSevens.WILD)) {
                break;
            }
            if (cards.get(selection - 1).suit.equals(activeCard.suit) || cards.get(selection - 1).rank.equals(activeCard.rank)) {
                break;
            }
        } while (true);

        return selection;
    }

    public int getSelection(Card activeCard, String color) {
        int selection = -1;

        do {
            if (selection != -1) {
                System.out.println("Invalid Card");
            }
            selection = player.getSelection(color, activeCard.rank,
                    Collections.unmodifiableList(cards));
            if (selection == 0) {
                break;
            }
            Card card = cards.get(selection - 1);
            if (validateCard(color, activeCard.rank, card)) {
                break;
            }

        } while (true);

        return selection;
    }

    private boolean validateCard(String color, String rank, Card card) {
        if (card.rank.equals("+2") && rank.equals("+2") || rank.equals("2+")) {
            return true;
        }
        if (rank.equals("+2")) {
            System.out.println("Select a +2 or draw");
            return false;
        }
        if (card.suit.equals("Wild")) {
            return true;
        }
        return card.suit.equals(color) || card.rank.equals(rank);
    }

    public void draw(Card card) {
        cards.add(card);
    }

    public String getName() {
        return player.getName();
    }

    public int getScore() {
        return player.getScore();
    }

    public Card takeCard(int index) {
        return cards.remove(index);
    }

    public void displayHand() {
        StringBuilder output = new StringBuilder();
        output.append(Color.getColor(player)).append(player.getName()).append("\n");
        cards.forEach(card -> output.append(card).append("|"));
        output.append("\n");
        output.append(Color.RESET);
        System.out.println(output);
    }

    public int getSuit(String suit) {
        List<String> suits;
        if (suit.equals("Uno")) {
            suits = UnoCard.suits;
        } else {
            suits = Card52.suits;
        }
        return player.getSuit(
                Collections.unmodifiableList(cards),
                suits);
    }

    private void displayCards() {
        //TODO make a display card for better visuals
        StringBuilder output = new StringBuilder();
        StringBuilder Top = new StringBuilder();
        StringBuilder Mid = new StringBuilder();
        StringBuilder Bottom = new StringBuilder();
    }

    public void win(int points) {
        player.win(points);
    }

    public void clear() {
        cards.clear();
    }
}
