package com.exeter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Game {
    List<Card> cards = new ArrayList<>();
    List<Player> players = new ArrayList<>();
    List<Deck> decks = new ArrayList<>();

    public Game(int numPlayers, File packFile){
        
        try(BufferedReader br = new BufferedReader(new FileReader(packFile))){
            String line = br.readLine();

            while (line != null){
                cards.add(new Card(Integer.parseInt(line)));
                line = br.readLine();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        for (int i = 1; i < numPlayers + 1; i++) {
            players.add(new Player(i));
            decks.add(new Deck(i));
        }

        for (Player player :
                players) {
            if(player.getId() == numPlayers){
                for (Deck deck :
                        decks) {
                    if(deck.getId() == 1)
                        player.setToDeck(deck);
                    if(deck.getId() == numPlayers)
                        player.setFromDeck(deck);
                }
            }else{
                for (Deck deck :
                        decks) {
                    if(deck.getId() == player.getId())
                        player.setFromDeck(deck);
                    if(deck.getId() == player.getId() + 1)
                        player.setToDeck(deck);
                }
            }
        }
    }
}
