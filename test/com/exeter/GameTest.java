package com.exeter;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class GameTest {
    private Game SUT;
    private GameLoggerMock gameLoggerMock;

    @Before
    public void setup(){
        int[] data = {3, 2, 1, 2, 1, 4, 2, 3, 1, 3, 2, 3, 3, 3, 2, 1, 1, 4, 1, 4, 3, 4, 1, 2, 1, 4, 1, 4, 4, 4, 1, 2};
        List<Card>cards = new ArrayList<>();
        for (int val : data){
            cards.add(new Card(val));
        }

        gameLoggerMock = new GameLoggerMock();

        SUT = new Game(4, cards, gameLoggerMock);
    }

    @Test
    public void start() {
        SUT.start();

        assertEquals(4, Collections.frequency(gameLoggerMock.calledMethods, "initialPlayerState"));
    }

    @Test
    public void interrupt(){
        Player player = SUT.getPlayers().get(1);

        SUT.interrupt(player);

        assertTrue(Collections.frequency(gameLoggerMock.calledMethods, "decks") == 1);
    }
}
