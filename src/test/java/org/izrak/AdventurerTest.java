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

import java.util.*;

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
    @CsvSource({"NORTH, 1 - 2 - N",
            "SOUTH, 1 - 0 - S",
            "EAST, 2 - 1 - E",
            "WEST, 0 - 1 - W"})
    void should_move_forward_when_given_a_forward_command(String adventurerOrientation, String newPosition) throws CommandException, AdventurerException {
        //Given
        Position adventurerPosition = new Position(1, 1);
        this.adventurer = new Adventurer("Bob", adventurerPosition, Orientation.valueOf(adventurerOrientation), map);

        //When
        String result = adventurer.executeCommands("A");

        //Then
        Assertions.assertEquals(newPosition, result);
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


    @DisplayName("Should add treasure to adventurer when stepping on treasure")
    @Test
    void should_add_treasure_to_adventurer_when_stepping_on_treasure() throws AdventurerException, CommandException {
        //Given
        List<Position> treasures = new ArrayList<>(List.of(new Position(0, 1)));
        Map mapWithTreasures = new Map(10, 10, Collections.emptySet(), treasures);

        //When
        this.adventurer = new Adventurer("Bob", position, Orientation.NORTH, mapWithTreasures);
        String result = adventurer.executeCommands("A");

        //Then
        Assertions.assertAll(
                () -> Assertions.assertEquals("0 - 1 - N", result),
                () -> Assertions.assertEquals(1, adventurer.getTreasures()),
                () -> Assertions.assertEquals(0, mapWithTreasures.getTreasures().size())
        );
    }

    @DisplayName("Should add two treasures to adventurer when stepping on two treasures")
    @Test
    void should_add_two_treasures_to_adventurer_when_stepping_on_two_treasures() throws AdventurerException, CommandException {
        //Given
        List<Position> treasures = new ArrayList<>(List.of(new Position(0, 1), new Position(0, 2)));
        Map mapWithTreasures = new Map(10, 10, Collections.emptySet(), treasures);

        //When
        this.adventurer = new Adventurer("Bob", position, Orientation.NORTH, mapWithTreasures);
        String result = adventurer.executeCommands("AA");

        //Then
        Assertions.assertAll(
                () -> Assertions.assertEquals("0 - 2 - N", result),
                () -> Assertions.assertEquals(2, adventurer.getTreasures()),
                () -> Assertions.assertEquals(0, mapWithTreasures.getTreasures().size())
        );

    }

    @DisplayName("Should add two treasure to adventurer when stepping on two treasures on the same position")
    @Test
    void should_add_two_treasures_to_adventure_when_stepping_on_two_treasures_on_the_same_position() throws CommandException, AdventurerException {
        //Given
        List<Position> treasures = new ArrayList<>(List.of(new Position(0, 1), new Position(0, 1)));
        Map mapWithTreasures = new Map(10, 10, Collections.emptySet(), treasures);

        //When
        this.adventurer = new Adventurer("Bob", position, Orientation.NORTH, mapWithTreasures);
        String result = adventurer.executeCommands("AADDA");

        //Then
        Assertions.assertAll(
                () -> Assertions.assertEquals("0 - 1 - S", result),
                () -> Assertions.assertEquals(2, adventurer.getTreasures()),
                () -> Assertions.assertEquals(0, mapWithTreasures.getTreasures().size())
        );
    }

    @DisplayName("Sample test")
    @Test
    void sampleTest() throws AdventurerException, CommandException {

        Position intialPosition = new Position(1, 1);

        Set<Position> mountains = Set.of(new Position(1, 0), new Position(2, 1));

        List<Position> treasures = new ArrayList<>(List.of(new Position(0, 3),
                new Position(0, 3), new Position(1, 3), new Position(1, 3), new Position(1, 3)));


        Map map = new Map(3, 4, mountains, treasures);

        Adventurer adventurer = new Adventurer("Lara", intialPosition, Orientation.SOUTH, map);

        Assertions.assertEquals("0 - 3 - S - 3", adventurer.executeCommands("AADADAGGA") + " - " + adventurer.getTreasures());

    }
}
