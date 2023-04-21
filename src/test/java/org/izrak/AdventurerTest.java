package org.izrak;

import org.izrak.exception.adventurer.AdventurerException;
import org.izrak.exception.adventurer.InvalidAdventurerNameException;
import org.izrak.exception.adventurer.InvalidAdventurerStartingPositionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class AdventurerTest {

    @DisplayName("Should initialize adventurer with a valid position, north orientation and name")
    @Test
    void should_initialize_adventurer_with_a_valid_position_north_orientation_and_name() throws AdventurerException {
        // Given
        Position position = new Position(1, 1);
        Orientation orientation = Orientation.NORTH;
        Map map = new Map(10, 10);
        // When
        Adventurer adventurer = new Adventurer("Lara",position, orientation, map);

        // Then
        Assertions.assertEquals("Lara", adventurer.getName());
        Assertions.assertEquals(position, adventurer.getPosition());
    }

    @DisplayName("Should initialize adventurer with a valid position, east orientation and name")
    @Test
    void should_initialize_adventurer_with_a_valid_position_east_orientation_and_name() throws AdventurerException {
        // Given
        Position position = new Position(1, 1);
        Orientation orientation = Orientation.EAST;
        Map map = new Map(10, 10);

        // When
        Adventurer adventurer = new Adventurer("Lara",position, orientation, map);

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
        Orientation orientation = Orientation.SOUTH;
        String no_name = null;
        Map map = new Map(10, 10);

        // When
        Assertions.assertThrows(InvalidAdventurerNameException.class,
                () -> new Adventurer(no_name, position, orientation, map), "Le nom d'un aventurier ne peut pas être nul");
    }

@DisplayName("Should throws exception when starting position is outside the map")
    @Test
    void should_throw_invalidAdventurerNameException_when_starting_position_is_outside_the_map() {
        // Given
        Position position = new Position(11, 11);
        Orientation orientation = Orientation.SOUTH;
        Map map = new Map(10, 10);

        // When
        // Then
        Assertions.assertThrows(InvalidAdventurerStartingPositionException.class,
                () -> new Adventurer("Lara", position, orientation, map), "La position de départ d'un aventurier doit être dans la carte");
    }
}
