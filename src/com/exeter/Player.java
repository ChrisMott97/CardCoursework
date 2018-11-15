package com.exeter;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> cards = new ArrayList<>();
    private int id;

    public Player(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public List<Card> getCards() { return cards; }

    public void addCard(Card card) { this.cards.add(card); }

    @Override
    public String toString() {
        return "Player " + Integer.toString(this.id);
    }
}
