package com.exeter;

import java.util.ArrayList;
import java.util.List;

public class PlayerMediator implements BasePlayerMediator {
    private List<Player> players;
    private Player winner;

    public PlayerMediator(){
        this.players = new ArrayList<>();
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Player getWinner() {
        return winner;
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
