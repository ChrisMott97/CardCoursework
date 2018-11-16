package com.exeter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player implements Runnable, BasePlayer{
    private Thread t;
    private PlayerMediator playerMediator;
    private PlayerDeckMediator playerDeckMediator;
    private List<Card> cards = new ArrayList<>();
    private Deck fromDeck;
    private Deck toDeck;
    private int id;

    public Player(int id){
        this.id = id;
        System.out.println("Constructing "+this);
    }

    public void setPlayerMediator(PlayerMediator playerMediator){
        this.playerMediator = playerMediator;
    }

    public void setPlayerDeckMediator(PlayerDeckMediator playerDeckMediator) {
        this.playerDeckMediator = playerDeckMediator;
    }

    public void interrupt(){
        this.t.interrupt();
    }

    public void run() {
        System.out.println("Thread for "+this+" running!");
        try{
            while(true){
                if(checkWin()) {
                    playerDeckMediator.output(
                            String.format("player%d_output.txt", this.id),
                            String.format("Player %d win!", this.id));

                    playerMediator.setWinner(this);
                    t.interrupt();
                    playerMediator.interruptAll();
                    playerDeckMediator.interrupt();
                }else{
                    System.out.println(this + " cards: " + cards);
                    takeFrom();
                    giveTo();
                    playerDeckMediator.output(
                            String.format("player%d_output.txt", this.id),
                            String.format("Player %d current hand is %s", this.id, this.cards));
                }
                Thread.sleep(50);
            }

        }catch(InterruptedException e){
            if(playerMediator.getWinner() == this){
                playerDeckMediator.output(
                        String.format("player%d_output.txt", this.id),
                        String.format("Player %d exits", this.id));
                playerDeckMediator.output(
                        String.format("player%d_output.txt", this.id),
                        String.format("Player %d final hand is %s", this.id, this.cards));
            }else{
                playerDeckMediator.output(
                        String.format("player%d_output.txt", this.id),
                        String.format("Player %d has informed Player %d that Player %d has won",
                                playerMediator.getWinner().getId(),
                                this.id,
                                playerMediator.getWinner().getId()));
                playerDeckMediator.output(
                        String.format("player%d_output.txt", this.id),
                        String.format("Player %d exits", this.id));
                playerDeckMediator.output(
                        String.format("player%d_output.txt", this.id),
                        String.format("Player %d final hand is %s", this.id, this.cards));
            }
            System.out.println(this + "'s Thread interrupted");
        }
        System.out.println(this + "'s Thread exiting");
    }

    public void start(){
        playerDeckMediator.output(
                String.format("player%d_output.txt", this.id),
                String.format("Player %d initial hand is %s", this.id, this.cards));
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
        Card newTop;
        if((newTop = fromDeck.takeTop()) != null){
            playerDeckMediator.output(
                    String.format("player%d_output.txt", this.id),
                    String.format("Player %d draws a %d from Deck %d.", this.id, newTop.getValue(), fromDeck.getId()));
            cards.add(newTop);
        }
    }

    public void giveTo(){
        Random rand = new Random();
        boolean ownCard = true;
        int chosenCard;
        Card current;

        while(ownCard){
            chosenCard = rand.nextInt(4);
            current = cards.get(chosenCard);
            if(current.getValue() != this.id){
                ownCard = false;
                toDeck.giveBottom(current);
                playerDeckMediator.output(
                        String.format("player%d_output.txt", this.id),
                        String.format("Player %d discards a %d to Deck %d.", this.id, current.getValue(), toDeck.getId()));
                cards.remove(current);
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
