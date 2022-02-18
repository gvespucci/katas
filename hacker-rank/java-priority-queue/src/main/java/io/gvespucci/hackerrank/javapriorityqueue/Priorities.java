package io.gvespucci.hackerrank.javapriorityqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Priorities {

    private final PriorityQueue<Student> queue = new PriorityQueue<>();

    public List<Student> getStudents(final List<String> events) {
        for (final String event : events) {
            if ("ENTER".equals(event.split(" ")[0])) {
                this.queue.add(Student.of(event));
                System.out.println("ENTER: " + this.queue);
            }
        }
        return new ArrayList<>(this.queue);
    }
}
