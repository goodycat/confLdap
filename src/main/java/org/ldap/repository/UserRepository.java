package org.ldap.repository;

import org.ldap.entity.User;
import org.springframework.data.ldap.repository.LdapRepository;

public interface UserRepository extends LdapRepository<User> {
    User findBySn(String name);
}
