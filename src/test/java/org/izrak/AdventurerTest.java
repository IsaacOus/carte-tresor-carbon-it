package org.izrak;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class AdventurerTest {

    @Test
    void should_initialize_adventurer_with_a_valid_position_and_orientation() {
        // Given
        Position position = new Position(1, 1);
        Orientation orientation = Orientation.NORTH;

        // When
        Adventurer adventurer = new Adventurer(position, orientation);

        // Then
        Assertions.assertEquals(position, adventurer.getPosition());
    }

}
