package org.ldap.controller;

import org.ldap.entity.Person;
import org.ldap.repository.PersonRepository;
import org.ldap.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Контроллер обеспечивает доступ к ресурсу посредством репозитория.
 */
@RestController
public class LdapController {

    private static Logger log = LoggerFactory.getLogger(LdapController.class);

    @Autowired
    private PersonService personService;

    @GetMapping("/")
    public String index() {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken)
                        SecurityContextHolder.getContext().getAuthentication();
        LdapUserDetailsImpl principal = (LdapUserDetailsImpl) authentication.getPrincipal();

        log.info("authentication: " + authentication);
        log.info("principal: " + principal);

        return "Good authenticated!";
    }

    @GetMapping("/managers")
    public List<Person> managers() {
        return personService.findAll();
    }

    @GetMapping("/employees")
    public String employees() {
        return "employees";
    }

    @GetMapping("/developers/{name}")
    public Person developers(@PathVariable("name") String name) {
        return personService.findByName(name);
    }
}