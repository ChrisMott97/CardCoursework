package com.exeter;

import java.util.ArrayList;
import java.util.List;

public class Player implements BasePlayer{
    private int id;
    private Player winner;
    private Thread t;
    private BaseGame mediator;
    private List<Card> hand = new ArrayList<>();

    /**
     * Constructor for Player
     *
     * @param id        the assigned id of the player.
     * @param mediator  the mediator to communicate between players and decks.
     */
    public Player(int id, BaseGame mediator){
        this.id = id;
        this.mediator = mediator;
    }

    /**
     * Getter for Player id.
     *
     * @return      the id of the player.
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for the Player hand.
     *
     * @return      the hand of the player.
     */
    public List<Card> getHand() { return hand; }

    /**
     * Getter for the winner of the game.
     *
     * @return      the winner of the game.
     */
    public Player getWinner() { return winner; }

    /**
     * Adds a card to the player's hand.
     *
     * @param card  a Card object from the initial pile.
     */
    public void deal(Card card) { hand.add(card); }

    /**
     * Creates and starts a new thread for the Player.
     */
    public void start(){
        if(t==null){
            t = new Thread(this, Integer.toString(id));
            t.start();
        }
    }

    /**
     * Overridden method for Threads that runs when the Thread starts.
     */
    public void run() {
        while(!t.isInterrupted()){
            if (mediator.checkWin(this)) {
                mediator.interrupt(this);
            } else {
                mediator.takeTurn(this);
            }
        }

        mediator.finalPlayerState(this);
    }

    /**
     * Allows external sources to interrupt a given player includes reference to caller.
     *
     * @param source    the original caller of the function (usually the winner of the game).
     */
    public void interrupt(Player source){
        winner = source;
        if (t!=null)
            this.t.interrupt();
    }

    /**
     * Allows players to be included in strings nicely using "this".
     *
     * @return      String in format "Player n" where n > 0.
     */
    @Override
    public String toString() {
        return "Player " + Integer.toString(this.id);
    }
}
