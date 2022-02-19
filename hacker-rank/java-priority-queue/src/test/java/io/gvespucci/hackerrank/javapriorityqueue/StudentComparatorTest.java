package io.gvespucci.hackerrank.javapriorityqueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.PriorityQueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentComparatorTest {

    private PriorityQueue<Student> queue;
    private StudentComparator comparator;

    @BeforeEach
    void beforeEach() {
        this.comparator = new StudentComparator();
        this.queue = new PriorityQueue<>(this.comparator);
    }

    /**
     * The student having the highest Cumulative Grade Point Average (CGPA)
     * is served first.
     */
    @Test
    void shouldServeBeforeTheHighestCgpa() {
        final Student john = new Student("John", 3.75, 50);
        final Student mark = new Student("Mark", 3.8, 24);
        assertTrue(this.comparator.compare(mark, john) < 0);

        this.queue.add(john);
        this.queue.add(mark);
        assertEquals(mark, this.queue.peek());
    }

    /**
     * Any students having the same CGPA will be served by name
     * in ascending (A to Z) case-sensitive alphabetical order.
     */
    @Test
    void shouldServeBeforeByNameIfCgpaIsTheSame() {
        final Student alice = new Student("Alice", 3.20, 50);
        final Student bob = new Student("Bob", 3.20, 24);
        assertTrue(this.comparator.compare(alice, bob) < 0);

        this.queue.add(bob);
        this.queue.add(alice);
        assertEquals(alice, this.queue.peek());
    }

    /**
     * Any students having the same CGPA and name will be served
     * in ascending order of the id.
     */
    @Test
    void shouldServeBeforeByIdIfNameAndCgpaAreTheSame() {
        final Student alice50 = new Student("Alice", 3.20, 50);
        final Student alice24 = new Student("Alice", 3.20, 24);
        assertTrue(this.comparator.compare(alice24, alice50) < 0);

        this.queue.add(alice50);
        this.queue.add(alice24);
        assertEquals(alice24, this.queue.peek());
    }
}
