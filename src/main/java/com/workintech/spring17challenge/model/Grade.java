package com.workintech.spring17challenge.model;

import org.springframework.stereotype.Component;

@Component
public class Grade {

    private int coefficient;
    private String note;

    public Grade(int coefficient, String note) {
        this.coefficient = coefficient;
        this.note = note;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public String getNote() {
        return note;
    }
}
