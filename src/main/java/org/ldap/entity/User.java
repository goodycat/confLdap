package org.ldap.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

@Entry(
        base = "ou=people",
        objectClasses = { "top","person"})

public final class User {
    private
    @Id
    @JsonIgnore
    Name id;
    private @Attribute(name = "cn")
    String cn;
    private @Attribute(name = "sn")
    String sn;


    public Name getId() {
        return id;
    }

    public void setId(Name id) {
        this.id = id;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}