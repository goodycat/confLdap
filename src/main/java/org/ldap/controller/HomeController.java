package org.ldap.controller;

import org.ldap.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private static Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/")
    public String index() {

        log.info("Getting UsernamePasswordAuthenticationToken from SecurityContextHolder");
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken)
                        SecurityContextHolder.getContext().getAuthentication();

        log.info("Getting principal from UsernamePasswordAuthenticationToken");
        LdapUserDetailsImpl principal = (LdapUserDetailsImpl) authentication.getPrincipal();

        log.info("authentication: " + authentication);
        log.info("principal: " + principal);

        return "Spring Security + Spring LDAP Authentication Configuration Example";
    }

    @GetMapping("/managers")
    public String managers(){
        return String.valueOf(personRepository.getPersonNamesByLastName2("John"));
    }

    @GetMapping("/employees")
    public String employees(){
        return "Hello employees";
    }
}