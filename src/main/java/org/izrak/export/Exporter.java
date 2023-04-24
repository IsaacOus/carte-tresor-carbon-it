package org.izrak.export;

import org.izrak.map.Map;

import java.io.FileWriter;
import java.io.IOException;

public class Exporter implements Export {

    @Override
    public void export(String fileName, String adventurer, String commands, Map map) throws IOException {

        try (FileWriter fileWriter = new FileWriter(fileName)) {

            fileWriter.write("C - " + map.getWidth() + " - " + map.getHeight());
            fileWriter.write("M - " + map.getMountains());
            fileWriter.write("T - " + map.getTreasures());

            fileWriter.write("A - "  + adventurer +
                    " - " + commands);

        }
    }

}

