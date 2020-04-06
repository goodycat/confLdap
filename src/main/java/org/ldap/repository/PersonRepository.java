package org.ldap.repository;

import org.ldap.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.SearchScope;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Repository;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

/**
 * Репозиторий формирует запросы к ресурсу.
 */
@Repository
public class PersonRepository {
    private static final Integer THREE_SECONDS = 3000;

    @Autowired
    private LdapTemplate ldapTemplate;

    public List<Person> getAllPersons() {
        return ldapTemplate.search(query()
                .where("objectclass").is("person"), new PersonAttributesMapper());
    }

    public Person findPerson(String dn) {
        return ldapTemplate.lookup(dn, new PersonAttributesMapper());
    }

    public List<Person> getAnyPersonsLikeName(String name) {
        LdapQuery query = query()
                .searchScope(SearchScope.SUBTREE)
                .timeLimit(THREE_SECONDS)
                .countLimit(10)
                .attributes("cn")
                .base(LdapUtils.emptyLdapName())
                .where("objectclass").is("person")
                .and("sn").like(name)
                .and("uid").isPresent();

        return ldapTemplate.search(query, new PersonAttributesMapper());
    }

    public Person getPersonsByName(String name) {
        SearchControls sc = new SearchControls();
        sc.setSearchScope(SearchControls.SUBTREE_SCOPE);
        sc.setTimeLimit(THREE_SECONDS);
        sc.setCountLimit(10);
        sc.setReturningAttributes(new String[]{"cn"});

        AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter("objectclass", "person"));
        filter.and(new EqualsFilter("sn", name));
        return ldapTemplate
                .search(LdapUtils.emptyLdapName(), filter.encode(), sc, new PersonAttributesMapper())
                .get(0);
    }

    /**
     * Формирование POJO-объекта.
     */
    private static class PersonAttributesMapper implements AttributesMapper<Person> {
        @Override
        public Person mapFromAttributes(Attributes attrs) throws NamingException {
            Person person = new Person();
            String[] name = ((String) attrs.get("cn").get()).split(" ");
            person.setName(name[0]);
            person.setLastName(name[1]);
            return person;
        }
    }
}