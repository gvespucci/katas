package io.gvespucci.hackerrank.javapriorityqueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Priorities {

    private final PriorityQueue<Student> queue =
        new PriorityQueue<>(new StudentComparator());

    public List<Student> getStudents(final List<String> events) {
        for (final String event : events) {
            final String[] eventItems = event.split(" ");
            if ("ENTER".equals(eventItems[0])) {
                this.queue.offer(Student.of(event));
                System.out.println("ENTER : " + Arrays.toString(this.queue.toArray()));
            } else if ("SERVED".equals(eventItems[0])) {
                this.queue.poll();
                System.out.println("SERVED: " + Arrays.toString(this.queue.toArray()));
            }
        }
        return new ArrayList<>(this.queue);
    }
}
