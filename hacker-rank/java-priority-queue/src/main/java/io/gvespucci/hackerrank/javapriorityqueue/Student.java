package io.gvespucci.hackerrank.javapriorityqueue;

import java.nio.DoubleBuffer;

public class Student {

    private final int id;
    private final String name;
    private final double cgpa;

    Student(String name, double cgpa, int id) {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
    }

    static Student of(String event) {
        String[] items = event.split(" ");
        return new Student(items[0], Double.parseDouble(items[1]), Integer.parseInt(items[2]));
    }

    int getID() {
        return this.id;
    }
    String getName() {
        return this.name;
    }
    double getCGPA() {
        return this.cgpa;
    }

}
