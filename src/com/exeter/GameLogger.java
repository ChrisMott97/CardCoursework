package com.exeter;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class GameLogger implements BaseGameLogger {
    List<List<String>> logs = new ArrayList<>();

    /**
     * Constructor for the GameLogger
     *
     * @param numPlayers    number of players used to set up the logger.
     */
    GameLogger (int numPlayers){
        for (int i = 0; i < numPlayers; i++) {
            logs.add(new ArrayList<>());
        }
        for (int i = 0; i < numPlayers; i++) {
            Path deckFilePath = Paths.get("deck"+(i+1)+"_output.txt");
            Path playerFilePath = Paths.get("player"+(i+1)+"_output.txt");
            try{
                Files.deleteIfExists(deckFilePath);
                Files.createFile(deckFilePath);
                Files.deleteIfExists(playerFilePath);
                Files.createFile(playerFilePath);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Adds an initial hand entry to the logs list (to be outputted).
     *
     * @param player    the {@link Player} who sent the request.
     */
    public void initialPlayerState(Player player){
        logs.get(player.getId()-1).add(
                String.format("%s initial hand: %s\n", player.toString(), player.getHand().toString())
        );
    }

    /**
     * Adds a current hand entry to the logs list (to be outputted).
     *
     * @param player    the {@link Player} who sent the request.
     */
    public void playerState(Player player){
        logs.get(player.getId()-1).add(
            String.format("%s hand: %s\n", player.toString(), player.getHand().toString())
        );
    }

    /**
     * Adds final moves entries to the logs list then outputs all {@link Player}s logs to file.
     *
     * @param player    the {@link Player} who sent the request.
     */
    public void finalise(Player player){
        if (player.equals(player.getWinner())){
            logs.get(player.getId()-1).add(
                    String.format("%s has won\n", player.toString())
            );
        } else {
            logs.get(player.getId()-1).add(
                    String.format("%s told %s that it has won\n", player.getWinner().toString(), player.toString())
            );
        }
        logs.get(player.getId()-1).add(
            String.format("%s final hand: %s\n", player.toString(), player.getHand().toString())
        );
        write(player);
    }

    /**
     * Adds player draw entries to the logs list (to be outputted).
     *
     * @param player    the {@link Player} who drew the {@link Card}.
     * @param deck      the {@link Deck} that was drawn from.
     * @param card      the {@link Card} that was drawn.
     */
    public void playerDraw(Player player, Deck deck, Card card){
        logs.get(player.getId()-1).add(
            String.format("%s drew Card %s from %s\n", player.toString(), card.toString(), deck.toString())
        );
    }

    /**
     * Adds player discard entries to the logs list (to be outputted).
     *
     * @param player    the {@link Player} who discarded the {@link Card}.
     * @param deck      the {@link Deck} that was discarded to.
     * @param card      the {@link Card} that was discarded.
     */
    public void playerDiscard(Player player, Deck deck, Card card){
        logs.get(player.getId()-1).add(
            String.format("%s discards Card %s to %s\n", player.toString(), card.toString(), deck.toString())
        );
    }

    /**
     * Writes all the final {@link Deck}s to their corresponding files.
     *
     * @param decks     the {@link Deck}s that exist in the {@link Game}.
     */
    public void decks(List<Deck> decks){
        for (Deck deck : decks){
            Path path = Paths.get("deck"+deck.getId()+"_output.txt");
            String line = String.format("%s final state %s\n", deck, deck.getCards());
            try{
                Files.write(path, line.getBytes(), StandardOpenOption.APPEND);
            }catch(IOException e){
                e.printStackTrace();
            }
        }

    }

    /**
     * Writes all the logs from the list for a given {@link Player} into their corresponding output files.
     *
     * @param player    the {@link Player}s logs that need to be outputted.
     */
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
