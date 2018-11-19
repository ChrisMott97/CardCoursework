package com.exeter;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private List<Card> cards = new ArrayList<>();
    private BaseGame mediator;
    private int id;

    /**
     * Constructor for Decks.
     *
     * @param id        the given id of the Deck.
     * @param mediator  the mediator reference to allow communication.
     */
    public Deck(int id, BaseGame mediator){
        this.id = id;
        this.mediator = mediator;
    }

    /**
     * Getter for the Deck's id.
     * @return      the Deck's id.
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for the Deck's cards.
     *
     * @return       the Deck's cards.
     */
    public List<Card> getCards() { return cards; }

    /**
     * Adds the given card to the top of the pile.
     *
     * @param card  the {@link Card} to be added to the pile
     */
    public void deal(Card card) { cards.add(0, card); }

    /**
     * Removes a card from the top of the pile and returns it.
     *
     * @return      the {@link Card} at the top of the pile.
     */
    public Card pickFromTop(){ return (!cards.isEmpty()) ? cards.remove(0) : null; }

    /**
     * Places a given {@link Card} at the bottom of the pile.
     * @param current   the card to add to the bottom.
     */
    public void discardToBottom(Card current){ cards.add(current); }

    /**
     * Allows Decks to be included in strings nicely using "this".
     *
     * @return      String in format "Deck n" where n > 0.
     */
    @Override
    public String toString() { return "Deck " + Integer.toString(this.id); }
}
