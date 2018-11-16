package com.exeter;

public interface Mediator {

    void interrupt(Player player);

    void interruptAll();

    void add(Player player);

}
