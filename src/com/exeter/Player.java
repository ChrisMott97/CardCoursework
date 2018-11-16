package com.exeter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player implements Runnable, BasePlayer{
    private Thread t;
    private Mediator playerMediator;
    private List<Card> cards = new ArrayList<>();
    private Deck fromDeck;
    private Deck toDeck;
    private int id;

    public Player(int id){
        this.id = id;
        System.out.println("Constructing "+this);
    }

    public void setPlayerMediator(Mediator playerMediator){
        this.playerMediator = playerMediator;
    }

    public void interrupt(){
        this.t.interrupt();
    }

    public void run() {
        System.out.println("Thread for "+this+" running!");
        try{
            while(true){
                if(checkWin()) {
                    System.out.println("    "+this + " won!");
                    t.interrupt();
                    playerMediator.interruptAll();
                }else{
                    System.out.println(this + " cards: " + cards);
                    takeFrom();
                    giveTo();
                }

                Thread.sleep(50);
            }

        }catch(InterruptedException e){
            System.out.println(this + "'s Thread interrupted");
        }
        System.out.println(this + "'s Thread exiting");
    }

    public void start(){
        System.out.println("Starting thread for "+this);
        if(t==null){
            t = new Thread(this, Integer.toString(id));
            t.start();
        }
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

    public void takeFrom(){
        System.out.println(this + " took from " + fromDeck);
        cards.add(fromDeck.takeTop());
    }

    public void giveTo(){
        Random rand = new Random();
        boolean ownCard = true;
        int chosenCard;
        Card current;

        while(ownCard){
            chosenCard = rand.nextInt(4)+1;
            if(chosenCard != this.id){
                ownCard = false;
                current = cards.get(1);
                toDeck.giveBottom(current);
                cards.remove(current);
                System.out.println(this + " gave to " + toDeck);
            }
        }


    }

    public int getId() {
        return id;
    }

    public List<Card> getCards() { return cards; }

    public void addCard(Card card) { this.cards.add(card); }

    public boolean checkWin(){
        if(cards.size() == 4){
            for (Card card : cards) {
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
