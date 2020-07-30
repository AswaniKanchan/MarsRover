package test.java.MarsRovers.domain;

import main.java.MarsRovers.domain.Direction;
import main.java.MarsRovers.exception.ExplorationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DirectionTest {

    @Test
    public void testDirectionForValidOrder() throws ExplorationException {
        assert(Direction.getDirection(1).equals(Direction.WEST));
    }

    @Test
    public void testDirectionForValidCharacter() throws ExplorationException {
        assert(Direction.getDirection('W').equals(Direction.WEST));
    }

    @Test
    public void testDirectionForInvalidOrder() {
        Assertions.assertThrows(ExplorationException.class,
                ()->Direction.getDirection(9),
                "Direction not found. Please check");
    }

    @Test
    public void testDirectionForInvalidCharacter() {
        Assertions.assertThrows(ExplorationException.class,
                ()->Direction.getDirection('K'),
                "Direction not found. Please check");
    }
}
