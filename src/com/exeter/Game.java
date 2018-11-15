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
    }

    public void dealCards(){
        Player currentPlayer = players.get(0);
        while (currentPlayer.getCards().size() < 4){
            currentPlayer.addCard(cards.remove(0));

            int i = players.indexOf(currentPlayer);
            if (i == players.size() - 1)
                currentPlayer = players.get(0);
            else
                currentPlayer = players.get(i+1);
        }

        Deck currentDeck = decks.get(0);
        while (cards.size() > 0){
            currentDeck.addCard(cards.remove(0));

            int i = decks.indexOf(currentDeck);
            if (i == decks.size() - 1)
                currentDeck = decks.get(0);
            else
                currentDeck = decks.get(i+1);
        }
        System.out.println(cards.size());
    }
}
