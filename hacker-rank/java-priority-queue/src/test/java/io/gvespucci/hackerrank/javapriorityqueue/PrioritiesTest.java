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
    private static final List<String> WHOLE_SEQUENCE = Arrays.asList(
        "ENTER John 3.75 50",
        "ENTER Mark 3.8 24",
        "ENTER Shafaet 3.7 35",
        "SERVED",
        "SERVED",
        "ENTER Samiha 3.85 36",
        "SERVED",
        "ENTER Ashley 3.9 42",
        "ENTER Maria 3.6 46",
        "ENTER Anik 3.95 49",
        "ENTER Dan 3.95 50",
        "SERVED"
    );

    @Test
    void shouldAddStudentForEachEnterEvent() {
        assertEquals(3, new Priorities().getStudents(Arrays.asList(JOHN, MARK, SHAFAET)).size());
    }

    @Test
    void shouldRemainFourToServe() {
        assertEquals(4, new Priorities().getStudents(WHOLE_SEQUENCE).size());
    }

    @Test
    void shouldRemainFourExactToServe() {
        final List<Student> stillToServe = new Priorities().getStudents(WHOLE_SEQUENCE);
        assertEquals("Dan", stillToServe.get(0).getName());
        assertEquals("Ashley", stillToServe.get(1).getName());
        assertEquals("Shafaet", stillToServe.get(2).getName());
        assertEquals("Maria", stillToServe.get(3).getName());
    }
}
