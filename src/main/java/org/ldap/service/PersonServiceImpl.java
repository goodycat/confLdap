package org.ldap.service;

import com.google.common.collect.Lists;
import org.ldap.entity.Person;
import org.ldap.entity.User;
import org.ldap.repository.PersonRepository;
import org.ldap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис позволяет осуществить доступ к репозиторию.
 */
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return Lists.newArrayList(userRepository.findAll());
    }

    @Override
    public User findByName(String name) {
        return userRepository.findBySn(name);
    }
}