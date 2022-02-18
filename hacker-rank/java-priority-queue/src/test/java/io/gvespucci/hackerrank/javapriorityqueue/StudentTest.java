package io.gvespucci.hackerrank.javapriorityqueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class StudentTest {

    public static final String EVENT = "ENTER John 3.75 50";

    @Test
    void shouldParseTheName() {
        assertEquals("John", Student.of(EVENT).getName());
    }

    @Test
    void shouldParseTheCumulativeGradePointAverage() {
        assertEquals(3.75, Student.of(EVENT).getCgpa());
    }

    @Test
    void shouldParseTheId() {
        assertEquals(50, Student.of(EVENT).getId());
    }

    @Test
    void shouldServeBeforeTheLowestCgpa() {
        final Student john = new Student("John", 3.75, 50);
        final Student mark = new Student("Mark", 3.8, 24);
        assertTrue(john.compareTo(mark) < 0);
        assertTrue(john.isServedBefore(mark));
    }

    @Test
    void shouldServeBeforeByNameIfCgpaIsEqual() {
        final Student john = new Student("John", 3.20, 50);
        final Student mark = new Student("Mark", 3.20, 24);
        assertTrue(john.compareTo(mark) < 0);
        assertTrue(john.isServedBefore(mark));
    }
}
