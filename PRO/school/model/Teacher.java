package cz.polacek.school.model;

public class Teacher extends Person {

    private String[] clazz;

    public Teacher(String name, int old, String[] clazz) {
        super(name, PersonType.TEACHER, old);
        this.clazz = clazz;
    }
}
