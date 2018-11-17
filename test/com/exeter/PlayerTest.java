package com.exeter;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

class PlayerTest {
    Player SUT;

    @Before
    void setup(){
        SUT = new Player(1);
    }

    @Test
    void getId() {
        int result = SUT.getId();

        assertEquals(1, result);
    }

    @Test
    void addCard() {
        SUT.addCard(new Card(1));
        SUT.addCard(new Card(2));

        assertEquals(1, SUT.getCards().get(0).getValue());
        assertEquals(2, SUT.getCards().get(1).getValue());
    }

    @Test
    void checkWin_whenWinningHand_returnsTrue() {
        SUT.addCard(new Card(1));
        SUT.addCard(new Card(1));
        SUT.addCard(new Card(1));
        SUT.addCard(new Card(1));

        boolean result = SUT.checkWin();

        assertTrue(result);
    }

    @Test
    void checkWin_whenNotWinningHand_returnsFalse() {
        SUT.addCard(new Card(2));
        SUT.addCard(new Card(2));
        SUT.addCard(new Card(2));
        SUT.addCard(new Card(2));

        boolean result = SUT.checkWin();

        assertFalse(result);
    }

    @Test
    void interrupt() {
    }

    @Test
    void run() {
    }

    @Test
    void start() {
    }

    @Test
    void takeFrom() {
    }

    @Test
    void giveTo() {
    }
}