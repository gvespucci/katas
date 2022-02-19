package io.gvespucci.hackerrank.javapriorityqueue;

import java.util.Objects;

public class Student implements Comparable<Student> {

    private final String name;
    private final double cgpa;
    private final int id;

    public Student(final String name, final double cgpa, final int id) {
        this.name = name;
        this.cgpa = cgpa;
        this.id = id;
    }

    static Student of(final String event) {
        final String[] items = event.split(" ");
        return new Student(items[1], Double.parseDouble(items[2]), Integer.parseInt(items[3]));
    }

    @Override
    public int compareTo(final Student that) {
        if (this.cgpa > that.cgpa) {
            return 1;
        }
        if (this.cgpa == that.cgpa) {
            if (this.name.equals(that.name)) {
                return Integer.compare(that.id, this.id);
            } else {
                return that.name.compareTo(this.name);
            }
        }
        return 0;
    }

    boolean isServedBefore(final Student that) {
        return new StudentComparator().compare(this, that) > 0;
//        return this.compareTo(that) > 0;
    }

    public String getName() {
        return this.name;
    }

    public double getCgpa() {
        return this.cgpa;
    }

    public int getId() {
        return this.id;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Student)) return false;
        final Student other = (Student) o;
        if (!Student.canEqual(this)) return false;
        final Object thisName = this.getName();
        final Object otherName = other.getName();
        if (!Objects.equals(thisName, otherName)) return false;
        if (Double.compare(this.getCgpa(), other.getCgpa()) != 0) return false;
        return this.getId() == other.getId();
    }

    protected static boolean canEqual(final Object other) {
        return other instanceof Student;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final long $cgpa = Double.doubleToLongBits(this.getCgpa());
        result = result * PRIME + (int) ($cgpa >>> 32 ^ $cgpa);
        result = result * PRIME + this.getId();
        return result;
    }

    public String toString() {
        return "Student(name=" + this.getName() + ", cgpa=" + this.getCgpa() + ", id=" + this.getId() + ")";
    }
}
