package com.exeter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class CardGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numPlayers = 0;
        String packLocation = "";


        System.out.println("How many players? ");
        boolean numPlayersValidated = false;
        while(!numPlayersValidated){
            numPlayers = sc.nextInt();
            if(validateNumPlayers(numPlayers)){
                numPlayersValidated = true;
            }else{
                System.out.println("Players must be greater than 0. Try again!:");
            }
        }

        System.out.println("Please choose a valid location for a pack:");
        boolean packLocationValidated = false;
        while(!packLocationValidated){
            packLocation = sc.next();
            if(validatePackLocation(packLocation)){
                packLocationValidated = true;
            }else{
                System.out.println("Invalid pack location. Try again!:");
            }
        }

        Game game = new Game(numPlayers, packLocation);

    }

    private static boolean validateNumPlayers(int playerAmount){
        return playerAmount > 0;
    }

    private static boolean validatePackLocation(String packLocation){
        return true;
    }
}
