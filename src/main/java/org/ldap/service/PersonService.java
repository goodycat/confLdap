package org.ldap.service;

import org.ldap.entity.Person;

import java.util.List;

public interface PersonService {
    List<Person> findAll();
    Person findByName(String name);
}
