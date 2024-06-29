package com.workintech.spring17challenge.model;

import org.springframework.stereotype.Component;

@Component
public class Course {

    private String name;
    private int credit;
    private Grade grade;

    public Course(String name, int credit, Grade grade) {
        this.name = name;
        this.credit = credit;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getCredit() {
        return credit;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
