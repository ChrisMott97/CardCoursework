package com.exeter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mediator implements BaseMediator {
    private List<Player> players = new ArrayList<>();
    private List<Deck> decks = new ArrayList<>();
    private BaseGameLogger logger;
    
    Mediator (BaseGameLogger logger){
        this.logger = logger;
    }

    public void add(Player player) { players.add(player); }

    public void add(Deck deck) { decks.add(deck); }

    public void start() {
        for (Player player : players){
            logger.logInitialPlayerState(player);
            player.start();
        }
    }

    public synchronized void interrupt(Player source) {
        for (Player player : players) {
            player.interrupt(source);
        }
        logger.logDecks(decks);
    }

    public synchronized boolean checkWin(Player player){
        List<Card> hand = player.getHand();

        if(hand.size() == 4){
            int v = hand.get(0).getValue();
            for (Card card : hand) {
                if(card.getValue() != v)
                    return false;
            }
            return true;
        }
        //is there opportunity for a hand that is not size 4? (atomic action?)
        return false;
    }

    public synchronized void takeTurn(Player player){
        if(drawCard(player)){
            discardCard(player);
            logger.logPlayerState(player);
        }
    }

    public void finalPlayerState(Player player){
        logger.logFinalPlayerState(player);
    }

    private boolean drawCard(Player player){
        int sourceDeckId = (player.getId()-1 == 0) ? players.size()-1 : player.getId()-1;
        Deck source = decks.get(sourceDeckId);

        Card card = source.pickFromTop();

        if(card == null){
            return false;
        }
        player.getHand().add(card);
        logger.logPlayerDraw(player, source, card);
        return true;

    }

    private void discardCard(Player player){
        int burnerDeckId = (player.getId() == players.size()) ? 0 : player.getId();
        Deck burner = decks.get(burnerDeckId);

        Random rand = new Random();
        boolean chosenCardWanted = true;
        Card chosen;

        while(chosenCardWanted){
            chosen = player.getHand().get(rand.nextInt(5));
            if(chosen.getValue() != player.getId()){
                chosenCardWanted = false;
                burner.discardToBottom(chosen);
                player.getHand().remove(chosen);

                logger.logPlayerDiscard(player, burner, chosen);
            }
        }
    }

}
