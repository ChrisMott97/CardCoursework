package com.exeter;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class PlayerDeckMediator implements Runnable, BasePlayerDeckMediator{
    private Thread t;
    private Queue<List<String>> buffer = new LinkedList<>();
//    private List<BufferedWriter> writers = new ArrayList<>();
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

    @Override
    public void run() {
        String fileName;
        int playerId;
        Path filePath;
        String input;
        List<String> current;
        BufferedWriter writer;

//        try{
//            for (Player player : players) {
//                writers.add(new BufferedWriter(new FileWriter("player"+player.getId()+"_output.txt", true)));
//            }
//        }catch(IOException e){
//            e.printStackTrace();
//        }


        try {
            Thread.sleep(100);
            while((!this.finished) || buffer.peek() != null){
                if(buffer.peek() != null){
                    current = buffer.remove();
//                    playerId = Integer.parseInt(current.get(0));
                    fileName = current.get(0);
                    input = current.get(1);

//                    filePath = Paths.get(fileName);
//                    System.out.println(input);
                    try{
//                        Files.write(filePath, Collections.singleton(input), Charset.forName("UTF-8"), StandardOpenOption.APPEND);
                        writer = new BufferedWriter(new FileWriter(fileName, true));
//                        writer = writers.get(playerId-1);
//                        System.out.println(input);
//                        System.out.println(input);
                        writer.append(input);
                        writer.newLine();
                        writer.flush();
//                        Thread.sleep(200);
//                        writer.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    Thread.sleep(100);
                }
            }
//            try{
//                for (BufferedWriter bWriter : writers) {
//                    bWriter.close();
//                }
//            }catch (IOException e){
//                e.printStackTrace();
//            }
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
        for (Deck deck :
                decks) {
            deck.output();
        }
    }



    public void interrupt(){
        this.outputDecks();
        this.finished = true;
    }
}
