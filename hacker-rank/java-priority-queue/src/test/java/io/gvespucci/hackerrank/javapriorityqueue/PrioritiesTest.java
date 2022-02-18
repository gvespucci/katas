package io.gvespucci.hackerrank.javapriorityqueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class PrioritiesTest {

    private final static String JOHN = "ENTER John 3.75 50";
    private final static String MARK = "ENTER Mark 3.8 24";
    private final static String SHAFAET = "ENTER Shafaet 3.7 35";
    private final static String SERVED = "SERVED";
    public static final List<String> THREE_STUDENTS = Arrays.asList(JOHN, SERVED, MARK, SERVED, SHAFAET);

    @Test
    void shouldAddStudentForEachEnterEvent() {
        assertEquals(3, new Priorities().getStudents(THREE_STUDENTS).size());
    }
}
