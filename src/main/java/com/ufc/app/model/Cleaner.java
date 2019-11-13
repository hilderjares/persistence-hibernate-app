package com.ufc.app.model;

import javax.persistence.Entity;

@Entity
public class Cleaner extends Employee {

    private String companyPosition;
    private String EducationLevel;

    public String getCompanyPosition() {
        return companyPosition;
    }

    public void setCompanyPosition(String companyPosition) {
        this.companyPosition = companyPosition;
    }

    public String getEducationLevel() {
        return EducationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        EducationLevel = educationLevel;
    }
}