package com.exeter;

import java.util.ArrayList;
import java.util.List;

public class PlayerMock implements BasePlayer {
    private int id;
    public List<String> calledMethods = new ArrayList<>();

    public int getId() {
        calledMethods.add("getId");
        return id;
    }

    public List<Card> getHand() {
        calledMethods.add("getHand");
        return null;
    }

    public Player getWinner() {
        calledMethods.add("getWinner");
        return null;
    }

    public void deal(Card card) {
        calledMethods.add("deal");
    }

    public void interrupt(Player source) {
        calledMethods.add("interrupt");
    }

    public void start() {
        calledMethods.add("start");
    }

    public void run() {
        calledMethods.add("run");
    }
}
