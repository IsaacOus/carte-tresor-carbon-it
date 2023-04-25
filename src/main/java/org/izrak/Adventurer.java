package org.izrak;

import org.izrak.exception.adventurer.AdventurerException;
import org.izrak.exception.adventurer.InvalidAdventurerNameException;
import org.izrak.exception.adventurer.InvalidAdventurerStartingPositionException;
import org.izrak.exception.command.CommandException;
import org.izrak.exception.command.InvalidCommandException;
import org.izrak.map.Map;
import org.izrak.map.Position;
import org.izrak.orientation.Orientation;

import java.util.Objects;

public class Adventurer implements Command {

    private final String name;
    private Position position;
    private Orientation orientation;
    private final Map map;
    private int treasures;

    public Adventurer(String name, Position position, Orientation orientation, Map map) throws AdventurerException {
        if (name == null || name.isEmpty()) {
            throw new InvalidAdventurerNameException("Le nom d'un aventurier ne peut pas être nul");
        }
        this.name = name;
        this.position = position;
        this.orientation = orientation;
        if (map.isPositionNotInsideMap(position)) {
            throw new InvalidAdventurerStartingPositionException("La position de départ d'un aventurier doit être dans la carte");
        }
        this.map = map;
        this.treasures = 0;
    }


    public String executeCommands(String commands) throws CommandException {
        for (char command : commands.toCharArray()) {
            executeCommand(command);
        }
        return position.getX() + " - " + position.getY() + " - " + orientation;
    }

    private void executeCommand(char command) throws CommandException {
        switch (command) {
            case 'A':
                moveForward();
                break;
            case 'D':
                this.orientation = orientation.turnRight();
                break;
            case 'G':
                this.orientation = orientation.turnLeft();
                break;
            default:
                throw new InvalidCommandException("Commande incorrecte");
        }
    }

    private void moveForward() {
        if (isAdventurerAtEdgeOfMap()) {
            return;
        }
        this.position = map.getNextPosition(position, orientation);

        if (map.isTreasureAtPosition(position)) {
            treasures++;
        }
    }

    private boolean isAdventurerAtEdgeOfMap() {
        return (this.position.getX() == 0 && orientation == Orientation.EAST) ||
                (this.position.getX() == map.getWidth() - 1 && orientation == Orientation.WEST) ||
                (this.position.getY() == 0 && orientation == Orientation.NORTH) ||
                (this.position.getY() == map.getHeight() - 1 && orientation == Orientation.SOUTH);
    }

    @Override
    public String toString() {
        return "A - " + name + " - " + position.getX() + " - " + position.getY() + " - " + orientation + " - " + treasures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adventurer that = (Adventurer) o;
        return treasures == that.treasures && Objects.equals(name, that.name) && Objects.equals(position, that.position) && orientation == that.orientation && Objects.equals(map, that.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position, orientation, map, treasures);
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public int getTreasures() {
        return treasures;
    }
}
