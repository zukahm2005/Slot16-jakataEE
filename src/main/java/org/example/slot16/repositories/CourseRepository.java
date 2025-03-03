package org.example.slot16.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;
import org.example.slot16.entities.Course;

import java.util.List;

public class CourseRepository {
    private EntityManager entityManager;

    public CourseRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Course> getAllCourses() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GetAllCourses", Course.class);
        return query.getResultList();
    }

    public void addCourse(String name, String description) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("InsertCourse");
        query.registerStoredProcedureParameter("course_name", String.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("course_description", String.class, jakarta.persistence.ParameterMode.IN);
        query.setParameter("course_name", name);
        query.setParameter("course_description", description);
        query.execute();
    }

    public void updateCourse(int id, String name, String description) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("UpdateCourse");
        query.registerStoredProcedureParameter("course_id", Integer.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("course_name", String.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("course_description", String.class, jakarta.persistence.ParameterMode.IN);
        query.setParameter("course_id", id);
        query.setParameter("course_name", name);
        query.setParameter("course_description", description);
        query.execute();
    }

    public void deleteCourse(int id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("DeleteCourse");
        query.registerStoredProcedureParameter("course_id", Integer.class, jakarta.persistence.ParameterMode.IN);
        query.setParameter("course_id", id);
        query.execute();
    }
}
