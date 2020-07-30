package MarsRovers.domain;

import MarsRovers.exception.ExplorationException;

public enum Direction {
    WEST(0),
    NORTH(1),
    EAST(2),
    SOUTH(3);

    //Increasing order means direction to the right else to the left
    private int order;

    Direction(int order) {
        this.order = order;
    }

    public static Direction getDirection(int value) throws ExplorationException {
        for (Direction direction : Direction.values()) {
            if (direction.order == value) return direction;
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

