package cz.polacek.school.model;

public class Person {

    private String name;
    private PersonType type;
    private int age;

    public Person(String name, PersonType type, int old) {
        this.name = name;
        this.type = type;
        this.age = old;
    }

}
