package com.exeter;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void setFromDeck() {
    }

    @Test
    void setToDeck() {
    }

    @Test
    void getFromDeck() {
    }

    @Test
    void getToDeck() {
    }

    @Test
    void getId() {
    }

    @Test
    void getCards() {
    }

    @Test
    void addCard() {
    }

    @Test
    void initialWinCheck_whenWinningHand_returnsTrue() {
        //arrange
        Player SUT = new Player(1);
        SUT.addCard(new Card(1));
        SUT.addCard(new Card(1));
        SUT.addCard(new Card(1));
        SUT.addCard(new Card(1));

        //act
        boolean result = SUT.checkWin();

        //assert
        assertTrue(result);
    }

    @Test
    void initialWinCheck_whenNotWinningHand_returnsFalse() {
        //arrange
        Player SUT = new Player(1);
        SUT.addCard(new Card(2));
        SUT.addCard(new Card(2));
        SUT.addCard(new Card(2));
        SUT.addCard(new Card(2));

        //act
        boolean result = SUT.checkWin();

        //assert
        assertFalse(result);
    }
}