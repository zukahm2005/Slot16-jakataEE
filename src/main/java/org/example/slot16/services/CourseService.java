package org.example.slot16.services;

import jakarta.persistence.EntityManager;
import org.example.slot16.entities.Course;
import org.example.slot16.repositories.CourseRepository;

import java.util.List;

public class CourseService {
    private CourseRepository courseRepository;

    public CourseService(EntityManager entityManager) {
        this.courseRepository = new CourseRepository(entityManager);
    }

    public List<Course> getAllCourses() {
        return courseRepository.getAllCourses();
    }

    public void addCourse(String name, String description) {
        courseRepository.addCourse(name, description);
    }

    public void updateCourse(int id, String name, String description) {
        courseRepository.updateCourse(id, name, description);
    }

    public void deleteCourse(int id) {
        courseRepository.deleteCourse(id);
    }
}
