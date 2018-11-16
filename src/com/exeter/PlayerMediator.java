package com.exeter;

import java.util.ArrayList;
import java.util.List;

public class PlayerMediator implements Mediator {
    private List<Player> players;

    public PlayerMediator(){
        this.players = new ArrayList<>();
    }

    @Override
    public void interrupt(Player player) {
        Player currentPlayer = players.get(players.indexOf(player));
        currentPlayer.interrupt();
    }

    @Override
    public void interruptAll() {
        for (Player player : players) {
            player.interrupt();
        }
    }

    @Override
    public void add(Player player) {
        this.players.add(player);
    }
}
