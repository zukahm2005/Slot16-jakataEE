package org.example.slot16.controller;

import jakarta.persistence.*;
import jakarta.servlet.annotation.WebServlet;
import org.example.slot16.entities.Classroom;
import org.example.slot16.entities.Student;
import org.example.slot16.services.ClassroomService;
import org.example.slot16.services.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/students")
public class StudentController extends HttpServlet {
    private EntityManagerFactory emf;

    public void init() {
        emf = Persistence.createEntityManagerFactory("WCDApp");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        StudentService studentService = new StudentService(em);
        ClassroomService classroomService = new ClassroomService(em);

        // Lấy danh sách sinh viên
        List<Student> students = studentService.getAllStudents();
        System.out.println("DEBUG: Số lượng sinh viên lấy được: " + students.size());

        // Lấy danh sách lớp học
        List<Classroom> classrooms = classroomService.getAllClassrooms();
        System.out.println("DEBUG: Số lượng lớp học lấy được: " + classrooms.size());

        em.close();

        // Đặt danh sách vào request
        req.setAttribute("students", students);
        req.setAttribute("classrooms", classrooms);

        req.getRequestDispatcher("/views/student.jsp").forward(req, resp);
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
        StudentService service = new StudentService(em);
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        int classroomId = Integer.parseInt(req.getParameter("classroom_id"));
        service.addStudent(name, age, classroomId);
        em.close();
        resp.sendRedirect(req.getContextPath() + "/students");
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        EntityManager em = emf.createEntityManager();
        StudentService service = new StudentService(em);
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        int classroomId = Integer.parseInt(req.getParameter("classroom_id"));
        service.updateStudent(id, name, age, classroomId);
        em.close();
        resp.sendRedirect(req.getContextPath() + "/students");
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        EntityManager em = emf.createEntityManager();
        StudentService service = new StudentService(em);
        int id = Integer.parseInt(req.getParameter("id"));
        service.deleteStudent(id);
        em.close();
        resp.sendRedirect(req.getContextPath() + "/students");
    }
}
