package com.ufc.app.model;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Employee {

    @EmbeddedId
    private Long id;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @MapsId("empid") 
    @OneToOne
    private Address address;

    private String name;
    private String gender;
    private Date birthday;
    private BigDecimal salary;
}
