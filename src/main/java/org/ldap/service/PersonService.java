package org.ldap.service;

import org.ldap.entity.Person;
import org.ldap.entity.User;

import java.util.List;

public interface PersonService {
    List<User> findAll();
    User findByName(String name);
}
