package org.izrak;

import org.izrak.exception.InvalidAdventurerNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class AdventurerTest {

    @DisplayName("Should initialize adventurer with a valid position, north orientation and name")
    @Test
    void should_initialize_adventurer_with_a_valid_position_north_orientation_and_name() throws InvalidAdventurerNameException {
        // Given
        Position position = new Position(1, 1);
        Orientation orientation = Orientation.NORTH;

        // When
        Adventurer adventurer = new Adventurer("Lara",position, orientation);

        // Then
        Assertions.assertEquals("Lara", adventurer.getName());
        Assertions.assertEquals(position, adventurer.getPosition());
    }

    @DisplayName("Should initialize adventurer with a valid position, east orientation and name")
    @Test
    void should_initialize_adventurer_with_a_valid_position_east_orientation_and_name() throws InvalidAdventurerNameException {
        // Given
        Position position = new Position(1, 1);
        Orientation orientation = Orientation.EAST;

        // When
        Adventurer adventurer = new Adventurer("Lara",position, orientation);

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

        // When
        Assertions.assertThrows(InvalidAdventurerNameException.class,
                () -> new Adventurer(no_name, position, orientation), "Le nom d'un aventurier ne peut pas être nul");
    }


}
