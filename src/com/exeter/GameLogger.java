package com.exeter;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class GameLogger implements BaseGameLogger {
    List<List<String>> logs = new ArrayList<>();


    GameLogger (int numPlayers){
        for (int i = 0; i < numPlayers; i++) {
            logs.add(new ArrayList<>());
        }
        Path filePath;
        for (int i = 0; i < numPlayers; i++) {
            filePath = Paths.get("player"+(i+1)+"_output.txt");
            try{
                Files.deleteIfExists(filePath);
                Files.createFile(filePath);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public void logInitialPlayerState(Player player){
        logs.get(player.getId()-1).add(
                String.format("%s initial hand: %s\n", player.toString(), player.getHand().toString())
        );
    }

    public void logPlayerState(Player player){
        logs.get(player.getId()-1).add(
            String.format("%s hand: %s\n", player.toString(), player.getHand().toString())
        );
    }

    public void logFinalPlayerState(Player player){
        logs.get(player.getId()-1).add(
            String.format("%s told %s that it has won\n", player.getWinner().toString(), player.toString())
        );
        logs.get(player.getId()-1).add(
            String.format("%s final hand: %s\n", player.toString(), player.getHand().toString())
        );
        write(player);
        System.out.println(logs.get(player.getId()-1));
    }

    public void logPlayerDraw(Player player, Deck deck, Card card){
        logs.get(player.getId()-1).add(
            String.format("%s drew Card %s from %s\n", player.toString(), card.toString(), deck.toString())
        );
    }
    public void logPlayerDiscard(Player player, Deck deck, Card card){
        logs.get(player.getId()-1).add(
            String.format("%s discards Card %s to %s\n", player.toString(), card.toString(), deck.toString())
        );
    }

    public void logDecks(List<Deck> decks){
        for (Deck deck : decks){
            System.out.printf("%s final state %s\n", deck, deck.getCards());
        }

    }

    public void write(Player player){
        Path path = Paths.get("player"+player.getId()+"_output.txt");
        for (String line :
                logs.get(player.getId() - 1)) {
            try{
                Files.write(path, line.getBytes(), StandardOpenOption.APPEND);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
