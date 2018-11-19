package com.exeter;

public interface BaseMediator {
    void add(Player player);

    void add(Deck deck);

    void start();

    void interrupt(Player source);

    boolean checkWin(Player player);

    void takeTurn(Player player);

    void finalPlayerState(Player player);
}
