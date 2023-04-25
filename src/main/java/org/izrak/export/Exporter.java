package org.izrak.export;

import org.izrak.map.Map;

import java.io.FileWriter;
import java.io.IOException;

public class Exporter implements Export {

    @Override
    public void export(String fileName, String adventurer, String commands, Map map) throws IOException {

        try (FileWriter fileWriter = new FileWriter(fileName)) {

            fileWriter.write("C - " + map.getWidth() + " - " + map.getHeight() + "\n");

            map.getMountains().forEach(mountain -> {
                try {
                    fileWriter.write("M - " + mountain.getX() + " - " + mountain.getY() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            map.getTreasures().forEach(treasure -> {
                try {
                    fileWriter.write("T - " + treasure.getX() + " - " + treasure.getY() + " - " + map.getTreasures().size() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            fileWriter.write("A - " + adventurer+
                    " - " + commands);
        }
    }

}

