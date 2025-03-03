package org.example.slot16.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.slot16.entities.Student;
import org.example.slot16.repositories.StudentRepository;

import java.util.List;

public class StudentService {
    private StudentRepository studentRepository;
    private EntityManager entityManager; // Thêm biến này để quản lý EntityManager

    public StudentService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.studentRepository = new StudentRepository(entityManager);
    }

    /**
     * Lấy danh sách sinh viên, đảm bảo lấy đầy đủ Classroom bằng JOIN FETCH.
     */
    public List<Student> getAllStudents() {
        return entityManager.createQuery(
                        "SELECT s FROM Student s JOIN FETCH s.classroom", Student.class)
                .getResultList();
    }

    public void addStudent(String name, int age, int classroomId) {
        studentRepository.addStudent(name, age, classroomId);
    }

    public void updateStudent(int id, String name, int age, int classroomId) {
        studentRepository.updateStudent(id, name, age, classroomId);
    }

    public void deleteStudent(int id) {
        studentRepository.deleteStudent(id);
    }
}
