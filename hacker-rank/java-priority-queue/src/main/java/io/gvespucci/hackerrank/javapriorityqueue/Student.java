package io.gvespucci.hackerrank.javapriorityqueue;

import lombok.Data;

@Data
public class Student implements Comparable<Student> {

    private final String name;
    private final double cgpa;
    private final int id;

    static Student of(final String event) {
        final String[] items = event.split(" ");
        return new Student(items[1], Double.parseDouble(items[2]), Integer.parseInt(items[3]));
    }

    @Override
    public int compareTo(final Student that) {
        if (this.cgpa < that.cgpa) {
            return -1;
        }
        if (this.cgpa == that.cgpa) {
            return this.name.compareTo(that.name);
        }
        return 0;
    }

    boolean isServedBefore(final Student that) {
        return this.compareTo(that) < 0;
    }
}
