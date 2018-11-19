package com.exeter;

import java.util.List;

public interface BaseGameLogger {
    void initialPlayerState(Player player);

    void playerState(Player player);

    void finalise(Player player);

    void playerDraw(Player player, Deck deck, Card card);

    void playerDiscard(Player player, Deck deck, Card card);

    void decks(List<Deck> decks);
}
