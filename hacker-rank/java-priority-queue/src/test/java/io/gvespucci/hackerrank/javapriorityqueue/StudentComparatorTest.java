package io.gvespucci.hackerrank.javapriorityqueue;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class StudentComparatorTest {

    /**
     *
     The student having the highest Cumulative Grade Point Average (CGPA)
     is served first.
     */
    @Test
    void shouldServeBeforeTheHighestCgpa() {
        final Student john = new Student("John", 3.75, 50);
        final Student mark = new Student("Mark", 3.8, 24);
        assertTrue(mark.isServedBefore(john));
    }

    /**
     *
     Any students having the same CGPA will be served by name
     in ascending case-sensitive alphabetical order.
     */
    @Test
    void shouldServeBeforeByNameIfCgpaIsTheSame() {
        final Student john = new Student("John", 3.20, 50);
        final Student mark = new Student("Mark", 3.20, 24);
        assertTrue(john.isServedBefore(mark));
    }

    /**
     *
     Any students having the same CGPA and name will be served
     in ascending order of the id.
     */
    @Test
    void shouldServeBeforeByIdIfNameAndCgpaAreTheSame() {
        final Student alice50 = new Student("Alice", 3.20, 50);
        final Student alice24 = new Student("Alice", 3.20, 24);
        assertTrue(alice24.isServedBefore(alice50));
    }

}
