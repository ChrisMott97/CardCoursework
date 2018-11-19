package com.exeter;

import java.util.List;

public interface BasePlayer extends Runnable{
    // declare more methods here
    int getId();

    List<Card> getHand();

    Player getWinner();

    void deal(Card card);

    void interrupt(Player source);

    void start();
}