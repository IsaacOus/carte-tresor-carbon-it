package org.izrak.export;

import org.izrak.map.Map;

import java.io.IOException;

public interface Export {
    void export(String fileName, String adventurer, String commands, Map map) throws IOException;
}
