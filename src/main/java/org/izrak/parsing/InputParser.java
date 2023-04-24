package org.izrak.parsing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputParser {
    private String[] map;

    private ArrayList<String[]> adventurers;
    private ArrayList<String[]> treasures;
    private ArrayList<String[]> mountains;


    public InputParser(String filename) {
        map = new String[2];
        adventurers = new ArrayList<String[]>();
        treasures = new ArrayList<String[]>();
        mountains = new ArrayList<String[]>();

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" - ");

                if (parts[0].equals("C")) {
                    map[0] = parts[1];
                    map[1] = parts[2];
                } else if (parts[0].equals("M")) {
                    String[] mountain = {parts[0], parts[1], parts[2]};
                    mountains.add(mountain);
                } else if (parts[0].equals("T")) {
                    String[] treasure = {parts[0], parts[1], parts[2], parts[3]};
                    treasures.add(treasure);
                } else if (parts[0].equals("A")) {
                    String[] adventurer = {parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]};
                    adventurers.add(adventurer);
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    public String[] getMapSize() {
        return map;
    }

    public String[] getMap() {
        return map;
    }

    public ArrayList<String[]> getAdventurers() {
        return adventurers;
    }

    public ArrayList<String[]> getTreasures() {
        return treasures;
    }

    public ArrayList<String[]> getMountains() {
        return mountains;
    }
}
