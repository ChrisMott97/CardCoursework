package com.exeter;

import java.util.ArrayList;
import java.util.List;

public class PlayerMediator implements BasePlayerMediator {
    private List<Player> players = new ArrayList<>();
    private Player winner;

    public PlayerMediator(){ }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Player getWinner() {
        return winner;
    }

    public void interrupt(Player player) {
        Player currentPlayer = players.get(players.indexOf(player));
        currentPlayer.interrupt();
    }

    public void interruptAll() {
        for (Player player : players) {
            player.interrupt();
        }
    }

    public void add(Player player) {
        this.players.add(player);
    }
}
