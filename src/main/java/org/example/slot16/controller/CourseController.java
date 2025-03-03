package org.example.slot16.controller;

import jakarta.persistence.*;
import jakarta.servlet.annotation.WebServlet;
import org.example.slot16.entities.Course;
import org.example.slot16.services.CourseService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/courses")
public class CourseController extends HttpServlet {
    private EntityManagerFactory emf;

    public void init() {
        emf = Persistence.createEntityManagerFactory("WCDApp");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        CourseService service = new CourseService(em);
        List<Course> courses = service.getAllCourses();
        em.close();

        req.setAttribute("courses", courses);
        req.getRequestDispatcher("/views/course.jsp").forward(req, resp);
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
        CourseService service = new CourseService(em);
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        service.addCourse(name, description);
        em.close();
        resp.sendRedirect(req.getContextPath() + "/courses");
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        EntityManager em = emf.createEntityManager();
        CourseService service = new CourseService(em);
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        service.updateCourse(id, name, description);
        em.close();
        resp.sendRedirect(req.getContextPath() + "/courses");
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        EntityManager em = emf.createEntityManager();
        CourseService service = new CourseService(em);
        int id = Integer.parseInt(req.getParameter("id"));
        service.deleteCourse(id);
        em.close();
        resp.sendRedirect(req.getContextPath() + "/courses");
    }
}
