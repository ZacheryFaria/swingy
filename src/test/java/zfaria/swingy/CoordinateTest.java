package zfaria.swingy;

import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinateTest {

    @Test
    public void equalsTest() {
        Coordinate c1 = new Coordinate(5, 2);
        Coordinate c2 = new Coordinate(5, 2);
        assertEquals(true, c1.equals(c2));

        Coordinate c3 = new Coordinate();
        c3.addCoordinate(5, 2);
        assertEquals(true, c1.equals(c3));

        Coordinate c4 = new Coordinate();
        assertNotEquals(true, c1.equals(c4));
    }

}