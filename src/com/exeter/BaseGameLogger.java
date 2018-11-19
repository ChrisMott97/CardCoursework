package com.exeter;

import java.util.List;

public interface BaseGameLogger {
    void logInitialPlayerState(Player player);

    void logPlayerState(Player player);

    void logFinalPlayerState(Player player);

    void logPlayerDraw(Player player, Deck deck, Card card);

    void logPlayerDiscard(Player player, Deck deck, Card card);

    void logDecks(List<Deck> decks);
}
