package com.exeter;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private List<Card> cards = new ArrayList<>();
    private PlayerDeckMediator playerDeckMediator;
    private int id;

    public Deck(int id, PlayerDeckMediator playerDeckMediator){
        this.id = id;
        this.playerDeckMediator = playerDeckMediator;
    }

    public int getId() {
        return id;
    }

    public void deal(Card card) { this.cards.add(0, card); }

    public Card pickFromTop(){ return (!this.cards.isEmpty()) ? cards.remove(0) : null; }

    public void discardToBottom(Card current){ cards.add(current); }

    //maybe do something about this
    public void output(){
        playerDeckMediator.output(
                String.format("deck%d_output.txt", this.id),
                String.format("Deck %d contents: %s", this.id, this.cards));
    }

    @Override
    public String toString() { return "Deck " + Integer.toString(this.id); }
}
