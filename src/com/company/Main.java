package com.company;

import com.company.games.insaneSevens.InsaneSevens;
import com.company.games.uno.Uno;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // write your code here
//        Uno uno = new Uno();
//        uno.playGame();
        InsaneSevens insaneSevens = new InsaneSevens();
        insaneSevens.playGame();
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
