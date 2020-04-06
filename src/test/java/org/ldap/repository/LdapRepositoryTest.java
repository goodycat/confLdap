package org.ldap.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ldap.entity.Person;
import org.ldap.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Тестирование репозитория.
 */
@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class LdapRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testFindAllPersons() {
        List<Person> persons = personRepository.getAllPersons();
        assertNotNull(persons);
        assertEquals(persons.size(), 3);
    }

    @Test
    public void testFindLikePersons() {
        List<Person> list = personRepository.getAnyPersonsLikeName("J*hn");
        assertEquals(2, list.size());
    }

    @Test
    public void testFindPersonsByName() {
        Person person = personRepository.getPersonsByName("John");
        assertEquals("John", person.getName());
    }

    @Test
    public void testFindPerson() {
        Person person = personRepository.findPerson("uid=john,ou=people");
        assertNotNull(person);
        assertEquals(person.getName(), "John");
    }
}