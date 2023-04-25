package org.izrak;

import org.izrak.exception.adventurer.AdventurerException;
import org.izrak.exception.command.CommandException;
import org.izrak.export.Exporter;
import org.izrak.map.Map;
import org.izrak.map.Position;
import org.izrak.orientation.Orientation;
import org.izrak.parsing.InputParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws AdventurerException, CommandException, IOException {
        InputParser reader = new InputParser("src/main/resources/input.txt");

        String[] mapSize = reader.getMapSize();
        List<String[]> adventurers = reader.getAdventurers();
        List<String[]> mountains = reader.getMountains();
        List<String[]> treasures = reader.getTreasures();

        Set<Position> mountainsPosition = new HashSet<>();
        for (String[] mountain : mountains) {
            mountainsPosition.add(new Position(Integer.parseInt(mountain[1]), Integer.parseInt(mountain[2])));
        }

        List<Position> treasuresPosition = new ArrayList<>();

        for (String[] treasure : treasures) {
            for (int i = 0; i < Integer.parseInt(treasure[3]); i++) {
                treasuresPosition.add(new Position(Integer.parseInt(treasure[1]), Integer.parseInt(treasure[2])));
            }
        }

        Map map = new Map(Integer.parseInt(mapSize[0]), Integer.parseInt(mapSize[1]), mountainsPosition, treasuresPosition);

        Adventurer adventurer = new Adventurer(
                adventurers.get(0)[1],
                new Position(Integer.parseInt(adventurers.get(0)[2]), Integer.parseInt(adventurers.get(0)[3])),
                Orientation.SOUTH,
                map
        );

        String adventurerAfter = adventurer.executeCommands(adventurers.get(0)[5]);

        Exporter exporter = new Exporter();

        exporter.export("src/main/resources/output.txt", adventurerAfter, adventurers.get(0)[5], map);
    }
}
