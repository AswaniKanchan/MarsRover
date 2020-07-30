package main.java.MarsRovers.rover;

import main.java.MarsRovers.domain.Coordinate;
import main.java.MarsRovers.domain.Position;
import main.java.MarsRovers.exception.ExplorationException;

public interface Rover {
    void turnLeft() throws ExplorationException;

    void turnRight() throws ExplorationException;

    void move(Coordinate gridMinCoordinates, Coordinate gridMaxCoordinates, int step) throws ExplorationException;

    Position getPosition();
}

