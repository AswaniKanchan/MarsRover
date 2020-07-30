package main.java.MarsRovers.rover;

import main.java.MarsRovers.domain.Coordinate;
import main.java.MarsRovers.domain.Direction;
import main.java.MarsRovers.domain.Position;
import main.java.MarsRovers.exception.ExplorationException;

public class MarsRover implements Rover {
    private Position position;

    public MarsRover(Position position) {
        this.position = position;
    }

    @Override
    public void turnLeft() throws ExplorationException {
        int distinctDirections = Direction.values().length;
        int minIndex = 1;
        int maxIndex = distinctDirections;
        int leftIndex = (position.getDirection().getOrder() - 1) % (maxIndex+1);
        if(leftIndex < minIndex) leftIndex = maxIndex;
        position.setDirection(Direction.getDirection(leftIndex));
    }

    @Override
    public void turnRight() throws ExplorationException {
        int distinctDirections = Direction.values().length;
        int minIndex = 1;
        int maxIndex = distinctDirections;
        int rightIndex = (position.getDirection().getOrder() + 1) % (maxIndex+1);
        if(rightIndex<minIndex) rightIndex = minIndex;
        position.setDirection(Direction.getDirection(rightIndex));
    }

    @Override
    public void move(Coordinate gridMinCoordinates, Coordinate gridMaxCoordinates, int step) throws ExplorationException{
        int coordinateValue;
        switch (position.getDirection()) {
            case WEST:
                coordinateValue = trySteppingBack(position.getCoordinate().getX(), gridMinCoordinates.getX(), step);
                position.getCoordinate().setX(coordinateValue);
                break;
            case NORTH:
                coordinateValue = trySteppingAhead(position.getCoordinate().getY(), gridMaxCoordinates.getY(), step);
                position.getCoordinate().setY(coordinateValue);
                break;
            case EAST:
                coordinateValue = trySteppingAhead(position.getCoordinate().getX(), gridMaxCoordinates.getX(), step);
                position.getCoordinate().setX(coordinateValue);
                break;
            case SOUTH:
                coordinateValue = trySteppingBack(position.getCoordinate().getY(), gridMinCoordinates.getY(), step);
                position.getCoordinate().setY(coordinateValue);
                break;
        }
    }

    public int trySteppingBack(int currentValue, int minValuePossible, int step) throws ExplorationException {
        if(currentValue-step >= minValuePossible) return currentValue-step;
        throw new ExplorationException("Incorrect Move");
    }

    public int trySteppingAhead(int currentValue, int maxValuePossible, int step) throws ExplorationException{
        if(currentValue+step <= maxValuePossible) return currentValue+step;
        throw new ExplorationException("Incorrect Move");
    }

    @Override
    public Position getPosition() {
        return position;
    }
}

