package io.gvespucci.hackerrank.javapriorityqueue;

import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {

    @Override
    public int compare(final Student o1, final Student o2) {
        if (o1.getCgpa() > o2.getCgpa()) {
            return 1;
        }
        if (o1.getCgpa() == o2.getCgpa()) {
            if (o1.getName().equals(o2.getName())) {
                return Integer.compare(o2.getId(), o1.getId());
            } else {
                return o2.getName().compareTo(o1.getName());
            }
        }
        return 0;
    }
}
