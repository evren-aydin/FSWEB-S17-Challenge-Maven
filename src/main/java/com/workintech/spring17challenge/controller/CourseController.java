package com.workintech.spring17challenge.controller;

import com.workintech.spring17challenge.exceptions.ApiException;
import com.workintech.spring17challenge.model.*;

import com.workintech.spring17challenge.validation.CourseValidator;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/workintech")
public class CourseController {

    private List<Course> courses;
    private final LowCourseGpa lowCourseGpa;
    private final MediumCourseGpa mediumCourseGpa;
    private final HighCourseGpa highCourseGpa;

    @Autowired
    public CourseController(LowCourseGpa lowCourseGpa, MediumCourseGpa mediumCourseGpa, HighCourseGpa highCourseGpa) {
        this.lowCourseGpa = lowCourseGpa;
        this.mediumCourseGpa = mediumCourseGpa;
        this.highCourseGpa = highCourseGpa;
    }

    @PostConstruct
    public void init() {
        this.courses = new ArrayList<>();
        courses.add(new Course("programlama", 3, new Grade(2, "katsayı belirlendi")));
    }

    @GetMapping("/courses")
    public List<Course> returnList() {
        return courses;
    }

    @GetMapping("/courses/{name}")
    public ResponseEntity<?> returnCourse(@PathVariable String name) {
        for (Course course : courses) {
            if (course.getName().equalsIgnoreCase(name)) {
                return ResponseEntity.ok(course);
            }
        }
        throw new ApiException("Course not found.");
    }

    @PostMapping("/courses")
    public ResponseEntity<?> addCourse(@RequestBody Course course) {
        CourseValidator.validateCourse(course, courses);
        courses.add(course);

        double totalGpa;
        if (course.getCredit() <= 2) {
            totalGpa = course.getGrade().getCoefficient() * course.getCredit() * lowCourseGpa.getGpa();
        } else if (course.getCredit() == 3) {
            totalGpa = course.getGrade().getCoefficient() * course.getCredit() * mediumCourseGpa.getGpa();
        } else {
            totalGpa = course.getGrade().getCoefficient() * course.getCredit() * highCourseGpa.getGpa();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Course added. Total GPA: " + totalGpa);
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable int id, @RequestBody Course course) {
        CourseValidator.validateCourseId(id, courses);
        CourseValidator.validateCourse(course, courses);

        Course existingCourse = courses.get(id);
        existingCourse.setName(course.getName());
        existingCourse.setCredit(course.getCredit());
        existingCourse.setGrade(course.getGrade());

        double totalGpa;
        if (course.getCredit() <= 2) {
            totalGpa = course.getGrade().getCoefficient() * course.getCredit() * lowCourseGpa.getGpa();
        } else if (course.getCredit() == 3) {
            totalGpa = course.getGrade().getCoefficient() * course.getCredit() * mediumCourseGpa.getGpa();
        } else {
            totalGpa = course.getGrade().getCoefficient() * course.getCredit() * highCourseGpa.getGpa();
        }

        return ResponseEntity.ok("Course updated. Total GPA: " + totalGpa);
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<?> removeCourse(@PathVariable int id) {
        CourseValidator.validateCourseId(id, courses);
        courses.remove(id);
        return ResponseEntity.status(HttpStatus.OK).body("Course deleted successfully.");
    }
}
