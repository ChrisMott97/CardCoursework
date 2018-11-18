package com.exeter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player implements BasePlayer{
    private Thread t;
    private BasePlayerMediator playerMediator;
    private BasePlayerDeckMediator playerDeckMediator;
    private List<Card> hand = new ArrayList<>();
    private Deck fromDeck;
    private Deck toDeck;
    private int id;

    public Player(int id, BasePlayerMediator playerMediator, BasePlayerDeckMediator playerDeckMediator){
        this.id = id;
        this.playerMediator = playerMediator;
        this.playerDeckMediator = playerDeckMediator;
    }

    public void setFromDeck(Deck fromDeck) {
        this.fromDeck = fromDeck;
    }

    public void setToDeck(Deck toDeck) {
        this.toDeck = toDeck;
    }
    
    public void interrupt(){
        this.t.interrupt();
    }

    public int getId() {
        return id;
    }

    public List<Card> getHand() { return hand; }

    public void deal(Card card) { this.hand.add(card); }

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
                    System.out.println(this + " hand: " + hand);
                    pick();
                    discard();
                    playerDeckMediator.output(
                            String.format("player%d_output.txt", this.id),
                            String.format("Player %d current hand is %s", this.id, this.hand));
                }
                Thread.sleep(50);
            }

        }catch(InterruptedException e){
            //after any player wins
            if(playerMediator.getWinner() == this){
                playerDeckMediator.output(
                        String.format("player%d_output.txt", this.id),
                        String.format("Player %d exits", this.id));
                playerDeckMediator.output(
                        String.format("player%d_output.txt", this.id),
                        String.format("Player %d final hand is %s", this.id, this.hand));
            }else{
                //not the winner, who's the winner?
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
                        String.format("Player %d final hand is %s", this.id, this.hand));
            }
            System.out.println(this + "'s Thread interrupted");
        }
        System.out.println(this + "'s Thread exiting");
    }

    public void start(){
        playerDeckMediator.output(
                String.format("player%d_output.txt", this.id),
                String.format("Player %d initial hand is %s", this.id, this.hand));
        if(t==null){
            t = new Thread(this, Integer.toString(id));
            t.start();
        }
    }

    public void pick(){
        System.out.println(this + " took from " + fromDeck);
        Card card = fromDeck.pickFromTop();
        if(card != null){
            playerDeckMediator.output(
                    String.format("player%d_output.txt", this.id),
                    String.format("Player %d draws a %d from Deck %d.", this.id, card.getValue(), fromDeck.getId()));
            hand.add(card);
        }
    }

    public void discard(){
        Random rand = new Random();
        boolean ownCard = true;
        int chosenCard;
        Card current;

        while(ownCard){
            chosenCard = rand.nextInt(4);
            current = hand.get(chosenCard);
            if(current.getValue() != this.id){
                ownCard = false;
                toDeck.discardToBottom(current);
                playerDeckMediator.output(
                        String.format("player%d_output.txt", this.id),
                        String.format("Player %d discards a %d to Deck %d.", this.id, current.getValue(), toDeck.getId()));
                hand.remove(current);
            }
        }


    }

    public boolean checkWin(){
        if(hand.size() == 4){
            int v = hand.get(0).getValue();
            for (Card card : hand) {
                if(card.getValue() != v)
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
