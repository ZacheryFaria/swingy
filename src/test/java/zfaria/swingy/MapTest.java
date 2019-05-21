package zfaria.swingy;

import org.junit.Test;

import static org.junit.Assert.*;

public class MapTest {

    @Test
    public void checkMapSize() {
        Map map = new Map(7);
        assertEquals(39, map.getSize());
    }

}