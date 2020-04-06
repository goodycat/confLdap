package org.ldap.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ldap.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class PersonServiceTest {
    @Autowired
    PersonService personService;

    @Test
    public void findAll() {
        List<User> users = personService.findAll();
        assertEquals(3, users.size());
    }

    @Test
    public void findByName() {
        User user = personService.findByName("John");
        assertEquals("John Doe", user.getCn());
    }
}