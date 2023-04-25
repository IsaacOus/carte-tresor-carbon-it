package org.izrak.parsing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputParser {
    private final String[] map;

    private final ArrayList<String[]> adventurers;
    private final  ArrayList<String[]> treasures;
    private final ArrayList<String[]> mountains;


    public InputParser(String filename) {
        map = new String[2];
        adventurers = new ArrayList<>();
        treasures = new ArrayList<>();
        mountains = new ArrayList<>();

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

    public List<String[]> getAdventurers() {
        return adventurers;
    }

    public List<String[]> getTreasures() {
        return treasures;
    }

    public List<String[]> getMountains() {
        return mountains;
    }
}
