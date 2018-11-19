//package com.exeter;
//
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.Writer;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Game {
//    private List<Player> players = new ArrayList<>();
//    private List<Deck> decks = new ArrayList<>();
//    private BaseGame mediator;
//    private GameLogger logger;
//
//    public Game(int numPlayers, List<Card> cards){
//        logger = new GameLogger(numPlayers);
//        mediator = new Mediator(logger);
//
//        initialise(numPlayers);
//        dealCards(cards);
//    }
//
//    public void start(){ mediator.start(); }
//}

package com.exeter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game implements BaseGame {
    private List<Player> players = new ArrayList<>();
    private List<Deck> decks = new ArrayList<>();
    private BaseGameLogger logger;

    Game (int numPlayers, List<Card> cards){
        this.logger = new GameLogger(numPlayers);
        initialise(numPlayers);
        dealCards(cards);
    }

    public void start() {
        for (Player player : players){
            logger.initialPlayerState(player);
            player.start();
        }
    }

    public synchronized void interrupt(Player source) {
        for (Player player : players) {
            player.interrupt(source);
        }
        logger.decks(decks);
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
            logger.playerState(player);
        }
    }

    public void finalPlayerState(Player player){
        logger.finalise(player);
    }

    private boolean drawCard(Player player){
        int sourceDeckId = (player.getId()-1 == 0) ? players.size()-1 : player.getId()-1;
        Deck source = decks.get(sourceDeckId);

        Card card = source.pickFromTop();

        if(card == null){
            return false;
        }
        player.getHand().add(card);
        logger.playerDraw(player, source, card);
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

                logger.playerDiscard(player, burner, chosen);
            }
        }
    }

    private void initialise(int numPlayers){
        for (int i = 1; i < numPlayers + 1; i++) {
            Player player = new Player(i, this);
            Deck deck = new Deck(i, this);

            players.add(player);
            decks.add(deck);
        }
    }

    private void dealCards(List<Card> cards){
        Player currentPlayer = players.get(0);

        while (currentPlayer.getHand().size() < 4){
            currentPlayer.deal(cards.remove(0));

            int i = players.indexOf(currentPlayer);
            if (i == players.size() - 1)
                currentPlayer = players.get(0);
            else
                currentPlayer = players.get(i+1);
        }

        Deck currentDeck = decks.get(0);

        while (cards.size() > 0){
            currentDeck.deal(cards.remove(0));

            int i = decks.indexOf(currentDeck);
            if (i == decks.size() - 1)
                currentDeck = decks.get(0);
            else
                currentDeck = decks.get(i+1);
        }

    }

}