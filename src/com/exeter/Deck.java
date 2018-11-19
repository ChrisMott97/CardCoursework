package com.exeter;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private List<Card> cards = new ArrayList<>();
    private BaseGame mediator;
    private int id;

    public Deck(int id, BaseGame mediator){
        this.id = id;
        this.mediator = mediator;
    }

    public int getId() {
        return id;
    }

    public List<Card> getCards() { return cards; }

    public void deal(Card card) { cards.add(0, card); }

    public Card pickFromTop(){ return (!cards.isEmpty()) ? cards.remove(0) : null; }

    public void discardToBottom(Card current){ cards.add(current); }

    @Override
    public String toString() { return "Deck " + Integer.toString(this.id); }
}
