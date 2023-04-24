package org.izrak;

import org.izrak.exception.adventurer.AdventurerException;
import org.izrak.exception.adventurer.InvalidAdventurerNameException;
import org.izrak.exception.adventurer.InvalidAdventurerStartingPositionException;
import org.izrak.exception.command.CommandException;
import org.izrak.exception.command.InvalidCommandException;
import org.izrak.map.Map;
import org.izrak.map.Position;
import org.izrak.orientation.Orientation;

public class Adventurer implements Command {

    private final String name;
    private Position position;
    private Orientation orientation;
    private final Map map;

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
    }

    private boolean isAdventurerAtEdgeOfMap() {
        return (this.position.getX() == 0 && orientation == Orientation.WEST) ||
                (this.position.getX() == map.getWidth() - 1 && orientation == Orientation.EAST) ||
                (this.position.getY() == 0 && orientation == Orientation.SOUTH) ||
                (this.position.getY() == map.getHeight() - 1 && orientation == Orientation.NORTH);
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
}
