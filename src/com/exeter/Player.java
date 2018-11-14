package com.exeter;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> cards = new ArrayList<>();
    private Deck fromDeck;
    private Deck toDeck;
    private int id;

    public Player(int id){
        this.id = id;
    }

    public void setFromDeck(Deck fromDeck) {
        this.fromDeck = fromDeck;
    }

    public void setToDeck(Deck toDeck) {
        this.toDeck = toDeck;
    }

    public Deck getFromDeck() {
        return fromDeck;
    }

    public Deck getToDeck() {
        return toDeck;
    }

    public int getId() {
        return id;
    }

    //UNTESTED
    public boolean initialWinCheck(){
        if(cards.size() == 4){
            for (Card card :
                    cards) {
                if(card.getValue() != this.id)
                    return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Player " + Integer.toString(this.id);
    }

}
