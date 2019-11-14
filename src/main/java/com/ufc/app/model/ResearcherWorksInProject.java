package com.ufc.app.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@Table(name = "researcher_works_in_project")
public class ResearcherWorksInProject implements Serializable {

    private static final long serialVersionUID = 1L;

    public ResearcherWorksInProject() {
        /*
         * do nothing, is necessary because hibernate need a default constructor in this
         * class
         */
    }

    @Id
    @ManyToOne
    @JoinColumn
    private Project project;

    @Id
    @ManyToOne
    @JoinColumn
    private Researcher researcher;

    private int totalHours;
}