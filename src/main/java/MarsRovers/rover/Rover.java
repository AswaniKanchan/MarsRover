package MarsRovers.rover;

import MarsRovers.domain.Coordinate;
import MarsRovers.domain.Position;
import MarsRovers.exception.ExplorationException;

public interface Rover {
    void turnLeft() throws ExplorationException;

    void turnRight() throws ExplorationException;

    void move(Coordinate gridMinCoordinates, Coordinate gridMaxCoordinates, int step) throws ExplorationException;

    Position getPosition();
}

