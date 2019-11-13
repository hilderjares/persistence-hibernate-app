package com.ufc.app.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode
public class ResearcherWorksInProject implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn
    private Project project;

    @Id
    @ManyToOne
    @JoinColumn
    private Researcher researcher;

}