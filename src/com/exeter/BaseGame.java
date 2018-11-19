package com.exeter;

import java.util.List;

public interface BaseGame {
    void start();

    void interrupt(Player source);

    boolean checkWin(Player player);

    void takeTurn(Player player);

    void finalPlayerState(Player player);
}
