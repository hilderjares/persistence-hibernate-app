package com.ufc.app.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "cleaner")
public class Cleaner extends Employee {

    private String companyPosition;
}