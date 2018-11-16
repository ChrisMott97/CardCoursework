package com.exeter;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private List<Card> cards = new ArrayList<>();
    private int id;

    public Deck(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Card takeTop(){
        Card current = cards.get(0);
        cards.remove(0);
        return current;
    }

    public void giveBottom(Card current){
        cards.add(current);
    }

    public List<Card> getCards() { return cards; }

    public void addCard(Card card) { this.cards.add(card); }

    @Override
    public String toString() {
        return "Deck " + Integer.toString(this.id);
    }
}
