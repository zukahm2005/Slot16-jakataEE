package org.example.slot16.services;

import jakarta.persistence.EntityManager;
import org.example.slot16.entities.Classroom;
import org.example.slot16.repositories.ClassroomRepository;

import java.util.List;

public class ClassroomService {
    private ClassroomRepository classroomRepository;

    public ClassroomService(EntityManager entityManager) {
        this.classroomRepository = new ClassroomRepository(entityManager);
    }

    public List<Classroom> getAllClassrooms() {
        return classroomRepository.getAllClassrooms();
    }

    public void addClassroom(String name, int capacity) {
        classroomRepository.addClassroom(name, capacity);
    }

    public void updateClassroom(int id, String name, int capacity) {
        classroomRepository.updateClassroom(id, name, capacity);
    }

    public void deleteClassroom(int id) {
        classroomRepository.deleteClassroom(id);
    }
}
