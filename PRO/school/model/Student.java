package cz.polacek.school.model;

public class Student extends Person {

    private String clazz;

    public Student(String name, int old, String clazz) {
        super(name, PersonType.STUDENT, old);
        this.clazz = clazz;
    }


}
