package com.exeter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CardGame {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int numPlayers = getNumPlayers();
        List<Card>cards = getCards(numPlayers);

        Game game = new Game(numPlayers, cards);
        game.start();
    }

    private static int getNumPlayers(){
        int num = 0;
        boolean firstTry = true;

        System.out.println("How many players?:");

        while(num <= 0) {
            try {
                num = sc.nextInt();
            } catch (Exception e){
                System.out.println("Input is not an integer");
                sc.nextLine();
                continue;
            }

            if(!firstTry)
                System.out.println("Players must be greater than 0. Try again!:");

            firstTry = false;
        }
        return num;
    }

    //should numplayers be private static var?
    private static List<Card> getCards(int numPlayers){
        List<Card> cards = new ArrayList<>();
        boolean firstTry = true;

        System.out.println("Please choose a valid location for a pack:");

        while(cards.size() < 8 * numPlayers){
            cards = parseCardsFromFile(new File(sc.next()));

            if (cards != null){
                if (!firstTry)
                    System.out.println("Not enough cards in pack. Try again!:");

                firstTry = false;
            } else {
                System.out.println("Invalid pack file. Try again!:");
                cards = new ArrayList<>();
            }
        }

        return cards;
    }

    private static List<Card> parseCardsFromFile(File file){
        List<Card> cards = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line = br.readLine();

            while (line != null) {
                cards.add(new Card(Integer.parseInt(line)));
                line = br.readLine();
            }
        } catch (Exception e){
            return null;
        }

        return cards;
    }
}
