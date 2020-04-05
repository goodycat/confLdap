package org.ldap.entity;

public class Person {
    private String name;
    private String lastName;

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "fullName='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
