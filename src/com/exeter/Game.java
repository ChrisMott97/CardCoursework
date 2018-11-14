package com.exeter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Game {
    int numPlayers;
    String packLocation;

    public Game(int numPlayers, String packLocation){

        File f = new File(packLocation);
        if(f.exists() && !f.isDirectory()){
            System.out.println("Found pack!");
            try(BufferedReader br = new BufferedReader(new FileReader(f))){
                String line = br.readLine();

                while (line != null){

                    line = br.readLine();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            System.out.println("Cannot find pack!");
        }
        this.numPlayers = numPlayers;
        this.packLocation = packLocation;
    }

//    private void validate()
}
