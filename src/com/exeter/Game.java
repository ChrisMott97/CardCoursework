package com.exeter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players = new ArrayList<>();
    private List<Deck> decks = new ArrayList<>();
    private BaseMediator mediator;
    private GameLogger logger;

    public Game(int numPlayers, List<Card> cards){
        logger = new GameLogger(numPlayers);
        mediator = new Mediator(logger);

        initialise(numPlayers);
        dealCards(cards);
    }

    private void initialise(int numPlayers){
        for (int i = 1; i < numPlayers + 1; i++) {
            Player player = new Player(i, mediator);
            Deck deck = new Deck(i, mediator);

            mediator.add(player);
            mediator.add(deck);

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

    public void start(){ mediator.start(); }
}
