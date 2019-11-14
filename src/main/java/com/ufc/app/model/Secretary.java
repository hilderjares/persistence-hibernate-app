package com.ufc.app.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "secretary")
public class Secretary extends Employee {

    private String educationLevel;
    private int workload;
}