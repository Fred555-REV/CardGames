package com.company;

import com.company.decks.DeckI;
import com.company.decks.StandardDeck;
import com.company.games.insaneSevens.Card52;
import com.company.games.insaneSevens.InsaneSevens;
import com.company.games.uno.Uno;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // write your code here
        System.out.println(Card52.suits.toString());
        System.out.println(Card52.suits.toString());
        System.out.println(Card52.suits.toString());
        System.out.println(Card52.suits.toString());
        System.out.println(Card52.suits.toString());
        System.out.println("Welcome to CardGames Select a Game");
        if (Console.getInt(1, 2, "(1)Uno\n(2)InsaneSevens", "Invalid Number") == 1) {
            Uno uno = new Uno();
            uno.playGame();
        } else {
            InsaneSevens insaneSevens = new InsaneSevens();
            insaneSevens.playGame();
        }
    }
}
// Notes/Plan of Attack
/*
Cards
suit and rank
display Card

DeckI
 StandardDeck
 cheatDeck
addCards()
deal()
shuffle()





Actor
getScore()
getName()
getAction()

Player
AutoPlayer


Table/Game/InsaneSevens
List<Actor> players
DeckI deck
Card activeCard


setup()
    addPlayers()
    addDeck()


startRound()
    draw()
    while(gameIsNotOver())
        turn()
    endRound()
        counts the score to the winner

roundIsNotOver()
    checks if anyone's card count is zero

Turn //Thinking about having it as a field for table
turnCount
turnPass()

Console STATIC methods
getInt()
getInt(min,max,prompt,errmsg)
getString()






 */
