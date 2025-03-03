package org.example.slot16.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;
import org.example.slot16.entities.Classroom;

import java.util.List;

public class ClassroomRepository {
    private EntityManager entityManager;

    public ClassroomRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Classroom> getAllClassrooms() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GetAllClassrooms", Classroom.class);
        return query.getResultList();
    }

    public void addClassroom(String name, int capacity) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("InsertClassroom");
        query.registerStoredProcedureParameter("class_name", String.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("class_capacity", Integer.class, jakarta.persistence.ParameterMode.IN);
        query.setParameter("class_name", name);
        query.setParameter("class_capacity", capacity);
        query.execute();
    }

    public void updateClassroom(int id, String name, int capacity) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("UpdateClassroom");
        query.registerStoredProcedureParameter("class_id", Integer.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("class_name", String.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("class_capacity", Integer.class, jakarta.persistence.ParameterMode.IN);
        query.setParameter("class_id", id);
        query.setParameter("class_name", name);
        query.setParameter("class_capacity", capacity);
        query.execute();
    }

    public void deleteClassroom(int id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("DeleteClassroom");
        query.registerStoredProcedureParameter("class_id", Integer.class, jakarta.persistence.ParameterMode.IN);
        query.setParameter("class_id", id);
        query.execute();
    }
}
