package com.exeter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class PlayerDeckMediator implements BasePlayerDeckMediator{
    private Thread t;
    private Queue<List<String>> buffer = new LinkedList<>();
    private List<Player> players;
    private List<Deck> decks;
    private boolean finished = false;

    public PlayerDeckMediator(){
        players = new ArrayList<>();
        decks = new ArrayList<>();
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void addDeck(Deck deck){
        decks.add(deck);
    }

    public void run() {
        String fileName;
        String input;
        List<String> current;
        BufferedWriter writer;

        try {
            Thread.sleep(100);
            while((!this.finished) || buffer.peek() != null){
                if(buffer.peek() != null){
                    current = buffer.remove();
                    fileName = current.get(0);
                    input = current.get(1);

                    try{
                        writer = new BufferedWriter(new FileWriter(fileName, true));
                        writer.append(input);
                        writer.newLine();
                        writer.flush();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    Thread.sleep(100);
                }
            }
            t.interrupt();
        }catch (Exception e){
            System.out.println("Output Queue Interrupted!");
        }
    }

    public void start(){
        System.out.println("Starting thread for PlayerDeckMediator");
        if(t==null){
            t = new Thread(this, "PlayerDeckMediator");
            t.start();
            try{
                t.join();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void output(String fileName, String input){
        buffer.add(Arrays.asList(fileName, input));
    }

    public void outputDecks(){
        for (Deck deck : decks) {
            deck.output();
        }
    }

    public void interrupt(){
        //the game has finished
        this.outputDecks();
        //stopping condition for buffer loop
        this.finished = true;
    }
}
