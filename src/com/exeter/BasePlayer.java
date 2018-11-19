package com.exeter;

public interface BasePlayer extends Runnable{
    // declare more methods here
    void interrupt(Player source);

    void start();
}