package com.hendisantika.springbootehcache.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-ehcache
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 30/01/18
 * Time: 06.17
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "DEPARTMENT")
@Getter
@Setter
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<Employee> employees = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<Office> offices = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Company company;

    public void setEmployees(Set<Employee> employees) {
        this.employees.clear();
        if (employees != null) {
            this.employees.addAll(employees);
        }
    }

    public void setOffices(Set<Office> offices) {
        this.offices.clear();
        if (offices != null) {
            this.offices.addAll(offices);
        }
    }
}
