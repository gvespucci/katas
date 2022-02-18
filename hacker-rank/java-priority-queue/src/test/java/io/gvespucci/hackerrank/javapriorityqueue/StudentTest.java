package io.gvespucci.hackerrank.javapriorityqueue;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StudentTest {

    @Test
    void shouldParseTheName() {
        assertEquals("John", Student.of("John 3.75 50").getName());
    }

    @Test
    void shouldParseTheCumulativeGradePointAverage() {
        assertEquals(3.75, Student.of("John 3.75 50").getCGPA());
    }

    @Test
    void shouldParseTheId() {
        assertEquals(50, Student.of("John 3.75 50").getID());
    }
}
