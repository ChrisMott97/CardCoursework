package com.exeter;

public interface BasePlayerDeckMediator extends Runnable{
    void output(String fileName, String input);

    void addPlayer(Player player);

    void addDeck(Deck deck);

    void start();

    void outputDecks();

    void interrupt();
}
