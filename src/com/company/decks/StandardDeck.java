package com.company.decks;

import com.company.games.insaneSevens.Card52;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StandardDeck implements DeckI {
    private List<Card> card52s = new ArrayList<>();

    public StandardDeck() {
        addCards();
    }

    @Override
    public void addCards() {
        for (String suit : Card52.suits) {
            for (String rank : Card52.ranks) {
                card52s.add(new Card52(rank, suit));
            }
        }
    }

    @Override
    public int size() {
        return card52s.size();
    }

    @Override
    public void addDeck(List<Card> discard) {
        card52s.addAll(discard);
        discard.clear();
    }

    @Override
    public Card deal() {
        return card52s.remove(card52s.size() - 1);
    }

    @Override
    public void shuffle() {
        Collections.shuffle(card52s);
    }
}
