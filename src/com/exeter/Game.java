package com.exeter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players = new ArrayList<>();
    private List<Deck> decks = new ArrayList<>();
    private PlayerMediator playerMediator = new PlayerMediator();
    private PlayerDeckMediator playerDeckMediator = new PlayerDeckMediator();

    public Game(int numPlayers, List<Card> cards){
        clearFiles();
        generateGameObjs(numPlayers);
        assignDecks();
        dealCards(cards);
    }

    private void generateGameObjs(int numPlayers){
        for (int i = 1; i < numPlayers + 1; i++) {
            Player player = new Player(i, playerMediator, playerDeckMediator);
            Deck deck = new Deck(i, playerDeckMediator);

            playerMediator.add(player);
            playerDeckMediator.addPlayer(player);
            playerDeckMediator.addDeck(deck);

            players.add(player);
            decks.add(deck);
        }
    }

    //encapsulate file writing in player loggers
    private void clearFiles(){
        Path filePath;
        for (Player player : players) {
            filePath = Paths.get("player"+player.getId()+"_output.txt");
            try{
                Files.deleteIfExists(filePath);
                Files.createFile(filePath);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    private void assignDecks(){
        for (Player player : players) {
            if(player.getId() == players.size()){
                for (Deck deck :
                        decks) {
                    if(deck.getId() == 1)
                        player.setToDeck(deck);
                    if(deck.getId() == players.size())
                        player.setFromDeck(deck);
                }
            }else{
                for (Deck deck : decks) {
                    if(deck.getId() == player.getId())
                        player.setFromDeck(deck);
                    if(deck.getId() == player.getId() + 1)
                        player.setToDeck(deck);
                }
            }
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

    public void start(){
        for (Player player : players) {
            player.start();
        }
        playerDeckMediator.start();
    }
}
