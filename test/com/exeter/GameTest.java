package com.exeter;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GameTest {
    private Game SUT;
    private List<Player> players = new ArrayList<>();
    private List<Deck> decks = new ArrayList<>();

    @Before
    void setup(){
        BaseGameLogger logger = new GameLogger(4);
//        SUT = new Game();


    }

    @Test
    void getId() {

    }
}
