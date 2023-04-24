package org.izrak.map;

import org.izrak.orientation.Orientation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class MapTest {

    private Map map;
    private Position position;

    @BeforeEach
    void setUp(){
        position = new Position(0, 0);
        Set<Position> mountains = Set.of(new Position(0, 1));
        map = new Map(10, 10, mountains);
    }

    @DisplayName("Should return the same position if there is a mountain on the next position")
    @Test
    void should_return_the_same_position_if_there_is_a_mountain_on_the_next_position() {
        // When
        Position nextPosition = map.getNextPosition(position, Orientation.NORTH);

        // Then
        assertEquals(position, nextPosition);
    }

    @DisplayName("Should return the new position if there is not a mountain on the next position")
    @Test
    void should_return_the_newPosition_if_there_is_not_a_mountain_on_the_next_position() {
        // Given
        this.position = new Position(3,4);

        // When
        Position nextPosition = map.getNextPosition(position, Orientation.NORTH);

        // Then
        assertNotEquals(position, nextPosition);
        assertEquals(new Position(3,5), nextPosition);

    }

    @DisplayName("Should return the same position if there is no mountain and the next position is outside the map")
    @Test
    void should_return_the_same_position_if_there_is_no_mountain_and_the_next_position_is_outside_the_map() {
        // When
        Position nextPosition = map.getNextPosition(position, Orientation.SOUTH);

        // Then
        assertEquals(position, nextPosition);
    }

}
