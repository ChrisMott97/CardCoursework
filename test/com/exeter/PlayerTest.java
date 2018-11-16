package com.exeter;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Before
    void setup(){
        Player SUT = new Player(1);
    }

    @Test
    void setFromDeck() {

        SUT.setFromDeck(new Deck(1));
        fail();
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
        //arrange
        Player SUT = new Player(1);

        //act
        int result = SUT.getId();

        //assert
        assertEquals(1, result);
    }

    @Test
    void getCards() {
        //arrange
        Player SUT = new Player(1);
        SUT.addCard(new Card(3));
        SUT.addCard(new Card(4));

        //act
        List<Card> result = SUT.getCards();

        //assert
        assertEquals(2, result.size());
        assertEquals(4, result.get(1).getValue());
    }

    @Test
    void addCard() {
        //arrange
        Player SUT = new Player(1);

        //act
        SUT.addCard(new Card(1));
        SUT.addCard(new Card(2));

        //assert
        assertEquals(1, SUT.getCards().get(0).getValue());
        assertEquals(2, SUT.getCards().get(1).getValue());
    }

    @Test
    void checkWin_whenWinningHand_returnsTrue() {
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
    void checkWin_whenNotWinningHand_returnsFalse() {
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
        assertThat();
    }

    @Test
    void setPlayerMediator() {
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

    @Test
    void checkWin() {
    }
}