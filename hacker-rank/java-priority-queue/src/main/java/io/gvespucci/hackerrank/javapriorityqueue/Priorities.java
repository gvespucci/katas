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
            final String command = eventItems[0];
            if (command.equals("ENTER")) {
                System.out.println("(+) " + event);
                this.queue.add(Student.of(event));
            } else if (command.equals("SERVED")) {
                System.out.println("(-) SERVING: " + this.queue.remove());
            }
            System.out.println("QUEUE: " + Arrays.toString(this.queue.toArray()));
        }
        return new ArrayList<>(this.queue);
    }
}
