package org.ldap.repository;

import org.junit.Before;
import org.junit.Test;
import org.ldap.Application;
import org.ldap.config.WebSecurityConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.ldap.core.LdapTemplate;

import static org.junit.Assert.*;

public class PersonRepositoryTest {
    private GenericApplicationContext genericApplicationContext;
    PersonRepository personRepository;

    @Before
    public void setUp() {
        genericApplicationContext = new AnnotationConfigApplicationContext(WebSecurityConfig.class, Application.class);
        PersonRepository personRepository = genericApplicationContext.getBean(PersonRepository.class);
        assertNotNull(personRepository);
        assertNotNull(genericApplicationContext.getBean(LdapTemplate.class));
    }

    @Test
    public void getPersonNamesByLastName() {
        System.out.println(personRepository.getPersonNamesByLastName("John"));
    }

    @Test
    public void getPersonNamesByLastName3(){
        System.out.println(personRepository.getPersonNamesByLastName3("Jahn"));
    }


}