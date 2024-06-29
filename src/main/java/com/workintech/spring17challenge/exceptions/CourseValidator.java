package com.workintech.spring17challenge.validation;

import com.workintech.spring17challenge.exceptions.ApiException;
import com.workintech.spring17challenge.model.Course;

import java.util.List;

public class CourseValidator {

    public static void validateCourse(Course course, List<Course> courses) {
        // Koşul 1: Aynı isimde birden fazla course eklenemez
        for (Course c : courses) {
            if (c.getName().equalsIgnoreCase(course.getName())) {
                throw new ApiException("A course with the same name already exists.");
            }
        }

        // Koşul 2: credit değeri 0'dan küçük olamaz, 4'ten büyük olamaz
        if (course.getCredit() < 0 || course.getCredit() > 4) {
            throw new ApiException("Credit value must be between 0 and 4.");
        }
    }

    public static void validateCourseId(int id, List<Course> courses) {
        if (id < 0 || id >= courses.size()) {
            throw new ApiException("Course not found.");
        }
    }
}
