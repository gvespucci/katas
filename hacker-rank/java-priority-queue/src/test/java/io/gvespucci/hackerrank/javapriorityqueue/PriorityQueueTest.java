package io.gvespucci.hackerrank.javapriorityqueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.PriorityQueue;

import org.junit.jupiter.api.Test;

public class PriorityQueueTest {

    private final PriorityQueue<Student> queue =
        new PriorityQueue<>(new StudentComparator());

    @Test
    void shouldPutStudentsInOrder() {
        this.queue.clear();
        showQueue();

        this.queue.add(Student.of("ENTER Maria 3.6 46"));
        showQueue();

        this.queue.add(Student.of("ENTER Shafaet 3.7 35"));
        showQueue();

        this.queue.add(Student.of("ENTER Ashley 3.9 42"));
        showQueue();

        final Student[] students = this.queue.toArray(new Student[0]);
        assertEquals("Ashley", students[0].getName());
        assertEquals("Shafaet", students[1].getName());
        assertEquals("Maria", students[2].getName());

        this.queue.add(Student.of("ENTER Anik 3.95 49"));

        showQueue();
    }

    private void showQueue() {
        System.out.println("QUEUE : " + Arrays.toString(this.queue.toArray()));
    }
}
