package com.exeter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CardGame {
    public static void main(String[] args) {
        int numPlayers = 0;
        File packFile = new File("");
        List<Card> cards = new ArrayList<Card>();

        Scanner sc = new Scanner(System.in);

        System.out.println("How many players? ");
        boolean playersValidated = false;

        while(!playersValidated){
            numPlayers = sc.nextInt();

            if(validatePlayers(numPlayers))
                playersValidated = true;
            else
                System.out.println("Players must be greater than 0. Try again!:");
        }

        System.out.println("Please choose a valid location for a pack:");
        boolean packValidated = false;

        while(!packValidated){
            String packLocation = sc.next();
            packFile = new File(packLocation);
            cards = new ArrayList<Card>();

            try (BufferedReader br = new BufferedReader(new FileReader(packFile))) {
                String line = br.readLine();

                while (line != null) {
                    cards.add(new Card(Integer.parseInt(line)));
                    line = br.readLine();
                }

                if (validatePack(cards, numPlayers))
                    packValidated = true;
                else
                    System.out.println("Not enough cards in pack. Try again!:");
            } catch (Exception e) {
                System.out.println("Invalid pack file. Try again!:");
            }
        }
        Game game = new Game(numPlayers, packFile);
        game.start();
    }

    private static boolean validatePlayers(int playerAmount){
        return playerAmount > 0;
    }

    private static boolean validatePack(List<Card> cards, int numplayers){
        return cards.size() >= 8 * numplayers;
    }
}
