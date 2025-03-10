package ru.spbstu.telematics.java;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Iterator;

public class MyCopyOnWriteArrayListTest {

    private MyCopyOnWriteArrayList<Integer> myList;
    private CopyOnWriteArrayList<Integer> javaList;

    @Before
    public void setUp() {
        myList = new MyCopyOnWriteArrayList<>();
        javaList = new CopyOnWriteArrayList<>();
    }

    @Test
    public void testSize() {
        assertEquals(javaList.size(), myList.size());

        myList.add(0, 1);
        javaList.add(0, 1);

        assertEquals(javaList.size(), myList.size());

        myList.add(1, 52);
        javaList.add(1, 52);

        myList.add(2, 15);
        javaList.add(2, 15);

        myList.add(3, 5);
        javaList.add(3, 5);

        myList.add(4, 7);
        javaList.add(4, 7);

        assertEquals(javaList.size(), myList.size());
    }

    @Test
    public void testContains() {
        assertEquals(javaList.contains(52), myList.contains(52));

        myList.add(0, 1);
        javaList.add(0, 1);

        assertEquals(javaList.contains(1), myList.contains(1));
        assertEquals(javaList.contains(10), myList.contains(10));
    }

    @Test
    public void testAdd() {
        myList.add(0, 1);
        javaList.add(0, 1);

        myList.add(1, 2);
        javaList.add(1, 2);

        assertEquals(javaList.size(), myList.size());
        assertEquals(javaList.get(0), myList.get(0));
        assertEquals(javaList.get(1), myList.get(1));
    }

    @Test
    public void testRemove() {
        myList.add(0, 1);
        myList.add(1, 2);
        javaList.add(0, 1);
        javaList.add(1, 2);

        assertEquals(javaList.size(), myList.size());
        assertEquals(javaList.get(0), myList.get(0));

        myList.remove(0);
        javaList.remove(0);

        assertEquals(javaList.size(), myList.size());
        assertEquals(javaList.get(0), myList.get(0));
    }

    @Test
    public void testClear() {
        myList.add(0, 1);
        myList.add(1, 2);
        javaList.add(0, 1);
        javaList.add(1, 2);

        assertEquals(javaList.size(), myList.size());
        assertFalse(myList.isEmpty());
        assertFalse(javaList.isEmpty());

        myList.clear();
        javaList.clear();

        assertEquals(javaList.size(), myList.size());
        assertTrue(myList.isEmpty());
        assertTrue(javaList.isEmpty());
    }

    @Test
    public void testToString() {
        assertEquals(javaList.toString(), myList.toString());

        myList.add(0, 1);
        myList.add(1, 2);
        javaList.add(0, 1);
        javaList.add(1, 2);

        assertEquals(javaList.toString(), myList.toString());
    }

    @Test
    public void testIterator() {
        myList.add(0, 1);
        myList.add(1, 2);
        myList.add(2, 3);
        myList.add(3, -67);
        myList.add(4, 24);
        myList.add(5, 80);
        myList.add(6, 6);
        javaList.add(0, 1);
        javaList.add(1, 2);
        javaList.add(2, 3);
        javaList.add(3, -67);
        javaList.add(4, 24);
        javaList.add(5, 80);
        javaList.add(6, 6);

        Iterator<Integer> myIterator = myList.iterator();
        Iterator<Integer> javaIterator = javaList.iterator();

        while (myIterator.hasNext() && javaIterator.hasNext()) {
            assertEquals(javaIterator.next(), myIterator.next());
        }

        assertFalse(myIterator.hasNext());
        assertFalse(javaIterator.hasNext());
    }
}
