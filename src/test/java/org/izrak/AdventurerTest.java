package org.izrak;

import org.izrak.exception.adventurer.AdventurerException;
import org.izrak.exception.adventurer.InvalidAdventurerNameException;
import org.izrak.exception.adventurer.InvalidAdventurerStartingPositionException;
import org.izrak.exception.command.CommandException;
import org.izrak.exception.command.InvalidCommandException;
import org.izrak.orientation.Orientation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class AdventurerTest {

    @DisplayName("Should initialize adventurer with a valid position, north orientation and name")
    @Test
    void should_initialize_adventurer_with_a_valid_position_north_orientation_and_name() throws AdventurerException {
        // Given
        Position position = new Position(1, 1);
        Orientation orientation = Orientation.N;
        Map map = new Map(10, 10);
        // When
        Adventurer adventurer = new Adventurer("Lara", position, orientation, map);

        // Then
        Assertions.assertEquals("Lara", adventurer.getName());
        Assertions.assertEquals(position, adventurer.getPosition());
    }

    @DisplayName("Should initialize adventurer with a valid position, east orientation and name")
    @Test
    void should_initialize_adventurer_with_a_valid_position_east_orientation_and_name() throws AdventurerException {
        // Given
        Position position = new Position(1, 1);
        Orientation orientation = Orientation.E;
        Map map = new Map(10, 10);

        // When
        Adventurer adventurer = new Adventurer("Lara", position, orientation, map);

        // Then
        Assertions.assertEquals("Lara", adventurer.getName());
        Assertions.assertEquals(position, adventurer.getPosition());
        Assertions.assertEquals(orientation, adventurer.getOrientation());
    }

    @DisplayName("Should throws exception when name is empty")
    @Test
    void should_throw_invalidAdventurerNameException_when_name_is_null() {
        // Given
        Position position = new Position(1, 1);
        Orientation orientation = Orientation.S;
        String no_name = null;
        Map map = new Map(10, 10);

        // When
        Assertions.assertThrows(InvalidAdventurerNameException.class,
                () -> new Adventurer(no_name, position, orientation, map),
                "Le nom d'un aventurier ne peut pas être nul");
    }

    @DisplayName("Should throws exception when starting position is outside the map")
    @Test
    void should_throw_invalidAdventurerStartingPositionException_when_starting_position_is_outside_the_map() {
        // Given
        Position position = new Position(11, 11);
        Orientation orientation = Orientation.S;
        Map map = new Map(10, 10);

        // When
        // Then
        Assertions.assertThrows(InvalidAdventurerStartingPositionException.class,
                () -> new Adventurer("Lara", position, orientation, map),
                "La position de départ d'un aventurier doit être dans la carte");
    }

    @DisplayName("Should throws exception when starting position (negative) is outside the map")
    @Test
    void should_throw_invalidAdventurerStartingPositionException_when_starting_negative_position_is_outside_the_map() {
        // Given
        Position position = new Position(5, 5);
        Orientation orientation = Orientation.S;
        Map map = new Map(-1, -1);

        // When
        // Then
        Assertions.assertThrows(InvalidAdventurerStartingPositionException.class,
                () -> new Adventurer("Lara", position, orientation, map),
                "La position de départ d'un aventurier doit être dans la carte");
    }

    @DisplayName("Should be at the same position when given an empty command list")
    @Test
    void should_be_at_same_position_given_an_empty_command_list() throws AdventurerException, CommandException{
        //Given
        Position position = new Position(0, 0);
        Orientation orientation = Orientation.N;
        Map map = new Map(10, 10);

        //When
        Adventurer adventurer = new Adventurer("Bob", position, orientation, map);
        String result = adventurer.executeCommands("");

        //Then
        Assertions.assertEquals("0 - 0 - N", result);
    }

    @DisplayName("Should be at the same position and facing east when given 'D' command")
    @Test
    void should_be_at_same_position_and_facing_east_given_D_command() throws AdventurerException, CommandException{
        //Given
        Position position = new Position(0, 0);
        Orientation orientation = Orientation.N;
        Map map = new Map(10, 10);

        //When
        Adventurer adventurer = new Adventurer("Bob", position, orientation, map);
        String result = adventurer.executeCommands("D");

        //Then
        Assertions.assertEquals("0 - 0 - E", result);
    }

    @DisplayName("Should be at the same position and facing west when given 'G' command")
    @Test
    void should_be_at_same_position_and_facing_west_given_G_command() throws AdventurerException, CommandException {
        //Given
        Position position = new Position(0, 0);
        Orientation orientation = Orientation.N;
        Map map = new Map(10, 10);

        //When
        Adventurer adventurer = new Adventurer("Bob", position, orientation, map);
        String result = adventurer.executeCommands("G");

        //Then
        Assertions.assertEquals("0 - 0 - W", result);
    }


    @DisplayName("Should throw an exception when given an invalid command")
    @Test
    void should_throw_an_exception_when_given_an_invalid_command() throws AdventurerException {
        //Given
        Position position = new Position(0, 0);
        Orientation orientation = Orientation.N;
        Map map = new Map(10, 10);

        //When
        Adventurer adventurer = new Adventurer("Bob", position, orientation, map);

        //Then
        InvalidCommandException thrownError = Assertions.assertThrows(InvalidCommandException.class,
                () -> adventurer.executeCommands("Z"),
                "Commande incorrecte");

        Assertions.assertTrue(thrownError.getMessage().contentEquals("Commande incorrecte"));
    }
}
