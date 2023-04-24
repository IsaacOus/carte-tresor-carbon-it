package org.izrak;

import org.izrak.exception.adventurer.AdventurerException;
import org.izrak.exception.adventurer.InvalidAdventurerNameException;
import org.izrak.exception.adventurer.InvalidAdventurerStartingPositionException;
import org.izrak.exception.command.CommandException;
import org.izrak.exception.command.InvalidCommandException;
import org.izrak.map.Map;
import org.izrak.map.Position;
import org.izrak.orientation.Orientation;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Set;

class AdventurerTest {

    private Adventurer adventurer;
    private Position position;

    private Orientation orientation;
    private Map map;

    @BeforeEach
    void setUp() throws AdventurerException {
        this.position = new Position(0, 0);
        this.orientation = Orientation.NORTH;
        this.map = new Map(10, 10);
        this.adventurer = new Adventurer("Lara", position, orientation, map);
    }

    @DisplayName("Should initialize adventurer with a valid position, north orientation and name")
    @Test
    void should_initialize_adventurer_with_a_valid_position_north_orientation_and_name() {
        Assertions.assertEquals("Lara", adventurer.getName());
        Assertions.assertEquals(position, adventurer.getPosition());
    }

    @DisplayName("Should initialize adventurer with a valid position, east orientation and name")
    @Test
    void should_initialize_adventurer_with_a_valid_position_east_orientation_and_name() {
        Assertions.assertEquals("Lara", adventurer.getName());
        Assertions.assertEquals(position, adventurer.getPosition());
        Assertions.assertEquals(orientation, adventurer.getOrientation());
    }

    @DisplayName("Should throws exception when name is empty")
    @Test
    void should_throw_invalidAdventurerNameException_when_name_is_null() {
        //Given
        String no_name = null;

        // When
        Assertions.assertThrows(InvalidAdventurerNameException.class,
                () -> new Adventurer(no_name, position, orientation, map), "Le nom d'un aventurier ne peut pas être nul");
    }

    @DisplayName("Should throws exception when starting position is outside the map")
    @Test
    void should_throw_invalidAdventurerStartingPositionException_when_starting_position_is_outside_the_map() {
        // Given
        Position positionOutSideMap = new Position(11, 11);

        // When
        // Then
        Assertions.assertThrows(InvalidAdventurerStartingPositionException.class,
                () -> new Adventurer("Lara", positionOutSideMap, orientation, map), "La position de départ d'un aventurier doit être dans la carte");
    }

    @DisplayName("Should throws exception when starting position (negative) is outside the map")
    @Test
    void should_throw_invalidAdventurerStartingPositionException_when_starting_negative_position_is_outside_the_map() {
        // Given
        Position positionOutSideMap = new Position(-1, -1);

        // When
        // Then
        Assertions.assertThrows(InvalidAdventurerStartingPositionException.class,
                () -> new Adventurer("Lara", positionOutSideMap, orientation, map), "La position de départ d'un aventurier doit être dans la carte");
    }

    @DisplayName("Should be at the same position when given an empty command list")
    @Test
    void should_be_at_same_position_given_an_empty_command_list() throws CommandException {
        // When
        String result = adventurer.executeCommands("");

        //Then
        Assertions.assertEquals("0 - 0 - N", result);
    }

    @ParameterizedTest
    @CsvSource({"D, 0 - 0 - E", "DD, 0 - 0 - S", "DDD, 0 - 0 - W", "DDDD, 0 - 0 - N",})
    void should_be_at_same_position_and_correct_orientation_when_turn_right(String command, String newPosition) throws CommandException {
        // When
        String result = adventurer.executeCommands(command);

        //Then
        Assertions.assertEquals(newPosition, result);
    }

    @ParameterizedTest
    @CsvSource({"G, 0 - 0 - W", "GG, 0 - 0 - S", "GGG, 0 - 0 - E", "GGGG, 0 - 0 - N",})
    void should_be_at_same_position_and_facing_correct_orientation_when_given_turn_left_command(String command, String newPosition) throws CommandException {
        //When
        String result = adventurer.executeCommands(command);

        //Then
        Assertions.assertEquals(newPosition, result);
    }


    @DisplayName("Should throw an exception when given an invalid command")
    @Test
    void should_throw_an_exception_when_given_an_invalid_command() {
        //Then
        InvalidCommandException thrownError = Assertions.assertThrows(InvalidCommandException.class,
                () -> adventurer.executeCommands("Z"), "Commande incorrecte");

        Assertions.assertTrue(thrownError.getMessage().contentEquals("Commande incorrecte"));
    }

    @DisplayName("Should move forward when given a forward command")
    @ParameterizedTest
    @CsvSource({"N, 0 - 1 - N", "S, 0 - -1 - S", "E, 1 - 0 - E", "W, -1 - 0 - W",})
    void should_move_forward_when_given_a_forward_command() throws CommandException {
        //When
        String result = adventurer.executeCommands("A");

        //Then
        Assertions.assertEquals("0 - 1 - N", result);
    }

    @DisplayName("Should not move when given a forward command when the adventurer is at the edge of the map and facing north")
    @Test
    void should_not_move_when_given_a_forward_command_and_the_adventurer_is_at_the_edge_of_the_map_facing_north() throws AdventurerException, CommandException {
        //Given
        Position positionAtEdge = new Position(0, 9);

        //When
        Adventurer adventurer = new Adventurer("Bob", positionAtEdge, orientation, map);
        String result = adventurer.executeCommands("A");

        //Then
        Assertions.assertEquals("0 - 9 - N", result);
    }

    @DisplayName("Should not move when given a forward command and the adventurer is at the edge of the map and facing east")
    @Test
    void should_not_move_when_given_a_forward_command_and_the_adventurer_is_at_the_edge_of_the_map_facing_east() throws AdventurerException, CommandException {
        //Given
        Position positionAtEdge = new Position(9, 0);

        //When
        Adventurer adventurer = new Adventurer("Bob", positionAtEdge, Orientation.EAST, map);
        String result = adventurer.executeCommands("A");

        //Then
        Assertions.assertEquals("9 - 0 - E", result);
    }

    @DisplayName("Should not move when given a forward command when the adventurer is at the edge of the map and facing south")
    @Test
    void should_not_move_when_given_a_forward_command_and_the_adventurer_is_at_the_edge_of_the_map_facing_south() throws AdventurerException, CommandException {
        //When
        Adventurer adventurer = new Adventurer("Bob", position, Orientation.SOUTH, map);
        String result = adventurer.executeCommands("A");

        //Then
        Assertions.assertEquals("0 - 0 - S", result);
    }

    @DisplayName("Should not move when given a forward command when the adventurer is at the edge of the map and facing west")
    @Test
    void should_not_move_when_give_a_forward_command_and_the_adventurer_is_at_the_edge_of_the_map_facing_west() throws AdventurerException, CommandException {
        //When
        Adventurer adventurer = new Adventurer("Bob", position, Orientation.WEST, map);
        String result = adventurer.executeCommands("A");

        //Then
        Assertions.assertEquals("0 - 0 - W", result);
    }

    @DisplayName("Should not move when given a forward command and the adventurer is facing a mountain")
    @Test
    void should_not_move_when_given_a_forward_command_and_the_adventurer_is_facing_a_mountain() throws AdventurerException, CommandException {
        //Given
        Set<Position> mountains = Set.of(new Position(0, 1));
        Map mapWithMoutains = new Map(10, 10, mountains);

        //When
        this.adventurer = new Adventurer("Bob", position, Orientation.NORTH, mapWithMoutains);
        String result = adventurer.executeCommands("A");

        //Then
        Assertions.assertEquals("0 - 0 - N", result);
    }

}
