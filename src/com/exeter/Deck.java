package com.exeter;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private List<Card> cards = new ArrayList<>();
    private PlayerDeckMediator playerDeckMediator = new PlayerDeckMediator();
    private int id;

    public Deck(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setPlayerDeckMediator(PlayerDeckMediator playerDeckMediator) {
        this.playerDeckMediator = playerDeckMediator;
    }

    public void output(){
        playerDeckMediator.output(
                String.format("deck%d_output.txt", this.id),
                String.format("Deck %d contents: %s", this.id, this.cards));
    }

    public Card takeTop(){
        if(!this.cards.isEmpty()){
            Card current = cards.get(0);
            cards.remove(0);
            return current;
        }return null;
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
