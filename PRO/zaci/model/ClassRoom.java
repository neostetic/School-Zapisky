package cz.polacek.zaci.model;

import java.util.Arrays;

public class ClassRoom {

    private String name;
    private Student[] students;

    public ClassRoom(String name, Student[] students) {
        this.name = name;
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "ClassRoom{" +
                "name='" + name + '\'' +
                ", students=" + Arrays.toString(students) +
                '}';
    }
}
