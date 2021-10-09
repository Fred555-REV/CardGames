package com.company.decks;

import java.util.List;

public interface DeckI {

    Card deal();
    int size();
    void addCards();
    void addDeck(List<Card> discard);
    void shuffle();
}
