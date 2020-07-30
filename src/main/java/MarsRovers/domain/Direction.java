package main.java.MarsRovers.domain;

import main.java.MarsRovers.exception.ExplorationException;

public enum Direction {
    WEST(1),
    NORTH(2),
    EAST(3),
    SOUTH(4);

    //Increasing order means direction to the right else to the left
    private int order;

    Direction(int order) {
        this.order = order;
    }

    public static Direction getDirection(int order) throws ExplorationException {
        for (Direction direction : Direction.values()) {
            if (direction.order == order) return direction;
        }
        throw new ExplorationException("Direction not found. Please check");
    }

    public static Direction getDirection(char value) throws ExplorationException {
        for (Direction direction : Direction.values()) {
            if (direction.toString().charAt(0) == value) return direction;
        }
        throw new ExplorationException("Direction not found. Please check");
    }

    public int getOrder() {
        return order;
    }
}

