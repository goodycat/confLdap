package org.ldap.controller;

import org.ldap.entity.Person;
import org.ldap.entity.User;
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
    public List<User> managers() {
        return personService.findAll();
    }

    @GetMapping("/managers/{name}")
    public User managers(@PathVariable("name") String name) {
        return personService.findByName(name);
    }

    @GetMapping("/employees/{name}")
    public String employees(@PathVariable("name") String name) {
        return name;
    }

    @GetMapping("/developers/{name}")
    public String developers(@PathVariable("name") String name) {
        return name;
    }
}