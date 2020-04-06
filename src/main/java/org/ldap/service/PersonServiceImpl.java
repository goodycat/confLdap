package org.ldap.service;

import org.ldap.entity.Person;
import org.ldap.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис позволяет осуществить доступ к репозиторию.
 */
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepository personRepository;

    @Override
    public List<Person> findAll() {
        return personRepository.getAllPersons();
    }

    @Override
    public Person findByName(String name) {
        return personRepository.getPersonsByName(name);
    }
}