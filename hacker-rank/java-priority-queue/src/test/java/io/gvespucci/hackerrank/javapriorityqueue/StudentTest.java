package io.gvespucci.hackerrank.javapriorityqueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
