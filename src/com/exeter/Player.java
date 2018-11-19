package com.exeter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player implements BasePlayer{
    private int id;
    private Player winner;
    private Thread t;
    private BaseMediator mediator;
    private List<Card> hand = new ArrayList<>();

    public Player(int id, BaseMediator mediator){
        this.id = id;
        this.mediator = mediator;
    }

    public int getId() {
        return id;
    }

    public List<Card> getHand() { return hand; }

    public Player getWinner() { return winner; }

    public void deal(Card card) { hand.add(card); }

    public void start(){
        if(t==null){
            t = new Thread(this, Integer.toString(id));
            t.start();
        }
    }

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

    public void interrupt(Player source){
        winner = source;
        if (t!=null)
            this.t.interrupt();
    }

    @Override
    public String toString() {
        return "Player " + Integer.toString(this.id);
    }
}
