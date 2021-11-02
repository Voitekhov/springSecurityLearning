package com.voitekhov.springsecurityproject.model;


import org.hibernate.cfg.AccessType;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = 100000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
//  See https://hibernate.atlassian.net/browse/HHH-3718 and https://hibernate.atlassian.net/browse/HHH-12034
//  Proxy initialization when accessing its identifier managed now by JPA_PROXY_COMPLIANCE setting
    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
