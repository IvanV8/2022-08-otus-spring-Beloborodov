package ru.otus.spring082022.Beloborodov03.domain;

public class Student {
    private final String firstName;
    private final String lastName;


    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public String getFullName() {
        return firstName + " " + lastName;
    }
}
