package org.example.slot16.controller;

import jakarta.persistence.*;
import jakarta.servlet.annotation.WebServlet;
import org.example.slot16.entities.Classroom;
import org.example.slot16.services.ClassroomService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/classrooms")
public class ClassRoomController extends HttpServlet {
    private EntityManagerFactory emf;

    public void init() {
        emf = Persistence.createEntityManagerFactory("WCDApp");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        ClassroomService service = new ClassroomService(em);
        List<Classroom> classrooms = service.getAllClassrooms();
        em.close();

        req.setAttribute("classrooms", classrooms);
        req.getRequestDispatcher("/views/classroom.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String method = req.getParameter("_method");

        if (method != null) {
            if (method.equalsIgnoreCase("DELETE")) {
                doDelete(req, resp);
                return;
            }
            if (method.equalsIgnoreCase("PUT")) {
                doPut(req, resp);
                return;
            }
        }

        EntityManager em = emf.createEntityManager();
        ClassroomService service = new ClassroomService(em);
        String name = req.getParameter("name");
        int capacity = Integer.parseInt(req.getParameter("capacity"));
        service.addClassroom(name, capacity);
        em.close();
        resp.sendRedirect(req.getContextPath() + "/classrooms");
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        EntityManager em = emf.createEntityManager();
        ClassroomService service = new ClassroomService(em);
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        int capacity = Integer.parseInt(req.getParameter("capacity"));
        service.updateClassroom(id, name, capacity);
        em.close();
        resp.sendRedirect(req.getContextPath() + "/classrooms");
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        EntityManager em = emf.createEntityManager();
        ClassroomService service = new ClassroomService(em);
        int id = Integer.parseInt(req.getParameter("id"));
        service.deleteClassroom(id);
        em.close();
        resp.sendRedirect(req.getContextPath() + "/classrooms");
    }
}
