package MarsRovers;

import MarsRovers.domain.Coordinate;
import MarsRovers.domain.Direction;
import MarsRovers.domain.Position;
import MarsRovers.exception.ExplorationException;
import MarsRovers.rover.MarsRover;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

public class MarsRoverExplorationTest {
    private Coordinate gridMinCoordinates = new Coordinate(0,0);

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testExplorationWithProvidedSample1() throws ExplorationException {
        Coordinate gridMaxCoordinate = new Coordinate(5,5);
        MarsRoverExploration marsRoverExploration = new MarsRoverExploration(gridMinCoordinates, gridMaxCoordinate);

        Position position = new Position(new Coordinate(1,2), Direction.NORTH);
        MarsRover rover = new MarsRover(position);
        String operationString = "LMLMLMLMM";
        marsRoverExploration.explore(rover, operationString);

        assert(rover.getPosition().getCoordinate().getX() == 1);
        assert(rover.getPosition().getCoordinate().getY() == 3);
        assert(rover.getPosition().getDirection().toString().charAt(0) == 'N');
    }

    @Test
    public void testExplorationWithProvidedSample2() throws ExplorationException{
        Coordinate gridMaxCoordinate = new Coordinate(5,5);
        MarsRoverExploration marsRoverExploration = new MarsRoverExploration(gridMinCoordinates, gridMaxCoordinate);

        Position position = new Position(new Coordinate(3,3), Direction.EAST);
        MarsRover rover = new MarsRover(position);
        String operationString = "MMRMMRMRRM";
        System.out.print("Input: " + getPositionString(rover.getPosition()) + ", Operation String: " + operationString);

        marsRoverExploration.explore(rover, operationString);

        assert(rover.getPosition().getCoordinate().getX() == 5);
        assert(rover.getPosition().getCoordinate().getY() == 1);
        assert(rover.getPosition().getDirection().toString().charAt(0) == 'E');
        System.out.println(", Output: " + getPositionString(rover.getPosition()));
    }

    @Test
    public void testRoverWhenOperationsStringAsNull() {
        Coordinate gridMaxCoordinate = new Coordinate(5,5);
        MarsRoverExploration marsRoverExploration = new MarsRoverExploration(gridMinCoordinates, gridMaxCoordinate);

        Position position = new Position(new Coordinate(3,3), Direction.EAST);
        MarsRover rover = new MarsRover(position);
        String operationString = null;

        Throwable exception = Assertions.assertThrows(ExplorationException.class,
                ()->{
                    marsRoverExploration.explore(rover, operationString);
                });

        assert(exception.getMessage().equals("Unable to parse exploration commands from 'null'"));
    }

    @Test
    public void testRoverWhenMoveCommandFallsOutsideGridXAxis() {
        Coordinate gridMaxCoordinate = new Coordinate(5,5);
        MarsRoverExploration marsRoverExploration = new MarsRoverExploration(gridMinCoordinates, gridMaxCoordinate);

        Coordinate coordinate = new Coordinate(5,5);
        Direction direction = Direction.EAST;
        Position position = new Position();
        position.setDirection(direction);
        position.setCoordinate(coordinate);
        MarsRover rover = new MarsRover(position);
        String operationString = "M";

        Throwable exceptionThatWasThrown = Assertions.assertThrows(ExplorationException.class,
                ()->{
                    marsRoverExploration.explore(rover, operationString);
                });
        assert(exceptionThatWasThrown.getMessage().equals("Incorrect Move"));
    }

    @Test
    public void testRoverWhenGridMaxCoordinatesNotGiven() {
        MarsRoverExploration marsRoverExploration = new MarsRoverExploration();

        Position position = new Position(new Coordinate(0,0), Direction.SOUTH);
        MarsRover rover = new MarsRover(position);
        String operationString = "M";

        Throwable exceptionThatWasThrown = Assertions.assertThrows(ExplorationException.class,
                ()->{
                    marsRoverExploration.explore(rover, operationString);
                });
        assert(exceptionThatWasThrown.getMessage().equals("Please set grid max coordinates to proceed"));
    }

    @Test
    public void testRoverWhenGridMaxCoordinatesNotGivenFlavor2() {
        MarsRoverExploration marsRoverExploration = new MarsRoverExploration();

        Position position = new Position(new Coordinate(0,0), Direction.SOUTH);
        MarsRover rover = new MarsRover(position);
        List<Character> commands = new ArrayList<>();

        Throwable exceptionThatWasThrown = Assertions.assertThrows(ExplorationException.class,
                ()->{
                    marsRoverExploration.explore(rover, commands);
                });
        assert(exceptionThatWasThrown.getMessage().equals("Please set grid max coordinates to proceed"));
    }

    @Test
    public void testRoverWhenMoveCommandFallsOutsideGridYAxis() {
        Coordinate gridMaxCoordinate = new Coordinate(5,5);
        MarsRoverExploration marsRoverExploration = new MarsRoverExploration();
        marsRoverExploration.setGridMaxCoordinates(gridMaxCoordinate);
        marsRoverExploration.setGridMinCoordinates(gridMinCoordinates);

        Position position = new Position(new Coordinate(0,0), Direction.SOUTH);
        MarsRover rover = new MarsRover(position);
        String operationString = "M";

        Throwable exceptionThatWasThrown =  Assertions.assertThrows(ExplorationException.class,
                ()->{
                    marsRoverExploration.explore(rover, operationString);
                });
        assert(exceptionThatWasThrown.getMessage().equals("Incorrect Move"));
    }

    private String getPositionString(Position position) {
        return position.getCoordinate().getX() + " " + position.getCoordinate().getY() + " " + position.getDirection().toString().charAt(0);
    }
}

