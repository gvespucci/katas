package io.gvespucci.hackerrank.javapriorityqueue;

import lombok.Data;

@Data
public class Student implements Comparable<Student> {

    private final String name;
    private final double cgpa;
    private final int id;

//    Student(final String name, final double cgpa, final int id) {
//        this.id = id;
//        this.name = name;
//        this.cgpa = cgpa;
//    }

    static Student of(final String event) {
        final String[] items = event.split(" ");
        return new Student(items[1], Double.parseDouble(items[2]), Integer.parseInt(items[3]));
    }

//    int getID() {
//        return this.id;
//    }
//    String getName() {
//        return this.name;
//    }
//    double getCGPA() {
//        return this.cgpa;
//    }

    @Override
    public int compareTo(final Student o) {
        return 0;
    }
}
