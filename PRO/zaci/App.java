package cz.polacek.zaci;

import cz.polacek.zaci.model.Student;
import cz.polacek.zaci.model.ClassRoom;

import java.util.Arrays;

public class App {

    public static void calculateAvg(Student student) {
        int sum = 0;
        for (int i: student.getGrades()) {
            sum++;
        }

        float avg = sum / (float) student.getGrades().length;
        System.out.printf("%s has %f%n", student.getName(), avg);
    }

    public static void main(String[] args) {
        Student student = new Student("karel", new int[]{1,2,3,4,5});
        student.setName("Vladan");
        student.setGrades(new int[]{1,2,5});

        ClassRoom class1 = new ClassRoom("Class1", new Student[]{
                new Student("karel", new int[]{1,2}),
                new Student("petr", new int[]{1,2,3,4,5}),
                new Student("pavel", new int[]{1,2,3,4,5})
        });
        System.out.println(class1);
    }

}
