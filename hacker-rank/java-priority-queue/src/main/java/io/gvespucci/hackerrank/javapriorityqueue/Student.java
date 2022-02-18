package io.gvespucci.hackerrank.javapriorityqueue;

public class Student {

    private final int id;
    private final String name;
    private final double cgpa;

    Student(int id, String name, double cgpa) {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
    }

    static Student of(String event) {

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
