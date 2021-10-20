package cz.polacek.zaci;

import cz.polacek.zaci.model.Student;

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
        student.getGrades();
    }

}
