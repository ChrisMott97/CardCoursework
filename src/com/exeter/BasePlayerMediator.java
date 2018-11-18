package com.exeter;

public interface BasePlayerMediator {
    void setWinner(Player winner);

    Player getWinner();

    void interrupt(Player player);

    void interruptAll();

    void add(Player player);
}
