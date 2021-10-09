package com.company.games.uno;

import com.company.decks.Card;
import com.company.decks.DeckI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UnoDeck implements DeckI {
    List<Card> cards = new ArrayList<>();

    public UnoDeck() {
        addCards();
    }

    @Override
    public void addCards() {
        for (String rank : UnoCard.ranks) {
            switch (rank) {
                case "0":
                case "Re":
                case "Sk":
                case "+2":
                    for (String suit : UnoCard.suits) {
                        //one of each 0 and action card
                        cards.add(new UnoCard(rank, suit));
                    }
                    break;
                case "Wi":
                case "+4":
                    for (int i = 0; i < 2; i++) {
                        //2 of each wild
                        cards.add(new UnoCard(rank, "Wild"));
                    }
                    break;
                default:
                    for (int i = 0; i < 2; i++) {
                        for (String suit : UnoCard.suits) {
                            //8 of each rank
                            //2 for each color
                            cards.add(new UnoCard(rank, suit));
                        }
                    }
            }
        }
    }

    @Override
    public Card deal() {
        return cards.remove(cards.size() - 1);
    }

    @Override
    public int size() {
        return cards.size();
    }

    @Override
    public void addDeck(List<Card> discard) {
        cards.addAll(discard);
    }

    @Override
    public void shuffle() {
        Collections.shuffle(cards);
    }
}
