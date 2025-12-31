package flik;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestFlik {
    @Test
    public void testNumberBound() {
        int i = 127;
        int j = 128;
        System.out.println(i);
        System.out.println(j);
        System.out.println(j + 1);
        assertEquals(j, 128);
    }

    @Test
    public void testInteger() {
        Integer a = 127;
        Integer b = 128;
        Integer c = 129;
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        Integer f = 129;
        System.out.println(Flik.isSameNumber(c, f));
        int g = 128;
        System.out.println(Flik.isSameNumber(g, 128));
        assertTrue(Flik.isSameNumber(a, 127));
        assertEquals(g, 128);
        assertEquals(c, f);
    }
}
