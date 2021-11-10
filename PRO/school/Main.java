package cz.polacek.school;

import cz.polacek.school.model.Person;
import cz.polacek.school.model.Student;
import cz.polacek.school.model.Teacher;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Person> personList = new LinkedList<>();
        personList.add(new Student("Nanda", 19, "3B"));
        personList.add(new Student("Mikka", 17, "3B"));
        personList.add(new Student("Renda", 16, "3A"));
        personList.add(new Teacher("Karel", 68, new String[] {"3B", "3A"}));
    }

}
