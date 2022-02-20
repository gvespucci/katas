package io.gvespucci.hackerrank.javapriorityqueue;

import java.util.Comparator;

/**
 * 1. The student having the highest Cumulative Grade Point Average (CGPA) is served first.
 * 2. Any students having the same CGPA will be served by name in ascending case-sensitive alphabetical order.
 * 3. Any students having the same CGPA and name will be served in ascending order of the id.

 */
public class StudentComparator implements Comparator<Student> {

    @Override
    public int compare(final Student o1, final Student o2) {
        System.out.printf("Comparing %s with %s%n", o1, o2);
        if (o1.getCgpa() > o2.getCgpa()) {
            System.out.printf("%s goes first, %s goes after%n", o1, o2);
            return -1;
        }
        if (o1.getCgpa() == o2.getCgpa()) {
            if (o1.getName().equals(o2.getName())) {
                return Integer.compare(o1.getId(), o2.getId());
            } else {
                return o1.getName().compareTo(o2.getName());
            }
        }
        return 0;
    }
}
