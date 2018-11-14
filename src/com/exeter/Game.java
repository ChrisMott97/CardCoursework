package com.exeter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Game {
    public int numPlayers;
    public File packLocation;

    public Game(int numPlayers, File packLocation){

        System.out.println("Found pack!");
        try(BufferedReader br = new BufferedReader(new FileReader(packLocation))){
            String line = br.readLine();

            while (line != null){

                line = br.readLine();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        this.numPlayers = numPlayers;
        this.packLocation = packLocation;
    }

//    private void validate()
}
