package org.example.slot16.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;
import org.example.slot16.entities.Student;

import java.util.List;

public class StudentRepository {
    private EntityManager entityManager;

    public StudentRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Student> getAllStudents() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GetAllStudents", Student.class);
        return query.getResultList();
    }

    public void addStudent(String name, int age, int classroomId) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("InsertStudent");
        query.registerStoredProcedureParameter("student_name", String.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("student_age", Integer.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("classroom_id", Integer.class, jakarta.persistence.ParameterMode.IN);
        query.setParameter("student_name", name);
        query.setParameter("student_age", age);
        query.setParameter("classroom_id", classroomId);
        query.execute();
    }

    public void updateStudent(int id, String name, int age, int classroomId) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("UpdateStudent");
        query.registerStoredProcedureParameter("student_id", Integer.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("student_name", String.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("student_age", Integer.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("classroom_id", Integer.class, jakarta.persistence.ParameterMode.IN);
        query.setParameter("student_id", id);
        query.setParameter("student_name", name);
        query.setParameter("student_age", age);
        query.setParameter("classroom_id", classroomId);
        query.execute();
    }

    public void deleteStudent(int id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("DeleteStudent");
        query.registerStoredProcedureParameter("student_id", Integer.class, jakarta.persistence.ParameterMode.IN);
        query.setParameter("student_id", id);
        query.execute();
    }
}
