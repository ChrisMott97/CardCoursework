package com.exeter;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MediatorTest {
    private Mediator SUT;
    private List<Player> players = new ArrayList<>();
    private List<Deck> decks = new ArrayList<>();

    private void initialise(int numPlayers){
        for (int i = 1; i < numPlayers + 1; i++) {
            Player player = new Player(i, SUT);
            Deck deck = new Deck(i, SUT);

            SUT.add(player);
            SUT.add(deck);

            players.add(player);
            decks.add(deck);
        }
    }

    @Before
    void setup(){
        BaseGameLogger logger = new GameLogger(4);
        SUT = new Mediator(logger);


    }

    @Test
    void getId() {

    }
}
