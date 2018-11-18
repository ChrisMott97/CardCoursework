package com.exeter;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

class PlayerTest {
    Player SUT;
    BasePlayerMediator playerMediator;
    BasePlayerDeckMediator playerDeckMediator;

    @Before
    void setup(){
        playerMediator = new MockPlayerMediator();
        playerDeckMediator = new MockPlayerDeckMediator();
        SUT = new Player(1, playerMediator, playerDeckMediator);
    }

    @Test
    void getId() {
        int result = SUT.getId();

        assertEquals(1, result);
    }

    @Test
    void addCard() {
        SUT.deal(new Card(1));
        SUT.deal(new Card(2));

        assertEquals(1, SUT.getHand().get(0).getValue());
        assertEquals(2, SUT.getHand().get(1).getValue());
    }

    @Test
    void checkWin_whenWinningHand_returnsTrue() {
        SUT.deal(new Card(1));
        SUT.deal(new Card(1));
        SUT.deal(new Card(1));
        SUT.deal(new Card(1));

        boolean result = SUT.checkWin();

        assertTrue(result);
    }

    @Test
    void checkWin_whenNotWinningHand_returnsFalse() {
        SUT.deal(new Card(2));
        SUT.deal(new Card(2));
        SUT.deal(new Card(2));
        SUT.deal(new Card(2));

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