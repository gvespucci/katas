package io.gvespucci.hackerrank.javapriorityqueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class PrioritiesTest {

    private final static String JOHN = "ENTER John 3.75 50";
    private final static String MARK = "ENTER Mark 3.8 24";
    private final static String SHAFAET = "ENTER Shafaet 3.7 35";

    @Test
    void shouldAddStudentForEachEnterEvent() {
        assertEquals(3, new Priorities().getStudents(Arrays.asList(JOHN, MARK, SHAFAET)).size());
    }
}
