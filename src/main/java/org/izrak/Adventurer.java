package org.izrak;

import org.izrak.exception.adventurer.AdventurerException;
import org.izrak.exception.adventurer.InvalidAdventurerNameException;
import org.izrak.exception.adventurer.InvalidAdventurerStartingPositionException;
import org.izrak.exception.command.CommandException;
import org.izrak.exception.command.InvalidCommandException;
import org.izrak.orientation.Orientation;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

public class Adventurer implements Command {

    private final String name;
    private final Position position;
    private Orientation orientation;
    private static final Logger logger = LoggerFactory.getLogger(Adventurer.class);

    public Adventurer(String name, Position position, Orientation orientation, Map map) throws AdventurerException {
        if (name == null || name.isEmpty()) {
            throw new InvalidAdventurerNameException("Le nom d'un aventurier ne peut pas être nul");
        }
        this.name = name;
        this.position = position;
        this.orientation = orientation;
        if (!map.isPositionInsideMap(position)) {
            throw new InvalidAdventurerStartingPositionException("La position de départ d'un aventurier doit être dans la carte");
        }
        logger.info(() -> "Aventurier " + name + " créé");
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

    public String executeCommands(String commands) {
        for (char command : commands.toCharArray()) {
            try {
                executeCommand(command);
            } catch (CommandException error) {
                logger.error(error, () -> "Erreur lors de l'exécution de la commande " + command);
            }
        }
        return position.getX() + " - " + position.getY() + " - " + orientation;
    }

    private void executeCommand(char command) throws CommandException {
        switch (command) {
            case 'A':
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

}
