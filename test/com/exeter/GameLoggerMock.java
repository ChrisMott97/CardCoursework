package com.exeter;

import java.util.ArrayList;
import java.util.List;

public class GameLoggerMock implements BaseGameLogger {
    public List<String> calledMethods = new ArrayList<>();

    public void initialPlayerState(Player player) {
        calledMethods.add("initialPlayerState");
    }

    public void playerState(Player player) {
        calledMethods.add("playerState");
    }

    public void finalise(Player player) {
        calledMethods.add("finalise");
    }

    public void playerDraw(Player player, Deck deck, Card card) {
        calledMethods.add("playerDraw");
    }

    public void playerDiscard(Player player, Deck deck, Card card) {
        calledMethods.add("playerDiscard");
    }

    public void decks(List<Deck> decks) {
        calledMethods.add("decks");
    }
}
