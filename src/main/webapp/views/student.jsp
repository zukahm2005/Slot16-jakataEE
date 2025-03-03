<%@ page import="java.util.List, org.example.slot16.entities.Student, org.example.slot16.entities.Classroom" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/includes/header.jsp"></jsp:include>

<html>
<head>
    <title>Student Management</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-4">
    <h2 class="text-center text-primary">Student Management</h2>

    <!-- Form Add / Update Student -->
    <div class="card shadow p-4 mb-4">
        <h4 id="form-title" class="text-info">Add Student</h4>
        <form id="studentForm" method="post" action="students">
            <input type="hidden" name="id" id="id">
            <input type="hidden" name="_method" id="_method" value="POST">

            <div class="mb-3">
                <label class="form-label fw-bold">Student Name</label>
                <input type="text" class="form-control" name="name" id="name" required>
            </div>
            <div class="mb-3">
                <label class="form-label fw-bold">Age</label>
                <input type="number" class="form-control" name="age" id="age" required>
            </div>
            <div class="mb-3">
                <label class="form-label fw-bold">Classroom</label>
                <select class="form-control" name="classroom_id" id="classroom_id" required>
                    <option value="">-- Select Classroom --</option>
                    <%
                        List<Classroom> classrooms = (List<Classroom>) request.getAttribute("classrooms");
                        if (classrooms == null || classrooms.isEmpty()) {
                    %>
                    <option value="" disabled>⚠ No classrooms available!</option>
                    <%
                    } else {
                        for (Classroom c : classrooms) {
                    %>
                    <option value="<%= c.getId() %>"><%= c.getName() %></option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>

            <button class="btn btn-success" type="submit" id="submit-btn">Save</button>
            <button class="btn btn-secondary" type="button" id="cancel-btn" style="display: none;" onclick="resetForm()">Cancel</button>
        </form>
    </div>

    <!-- Student Table -->
    <div class="card shadow p-4">
        <h4 class="text-info">Student List</h4>
        <table class="table table-hover table-striped mt-3">
            <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Age</th>
                <th>Classroom</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Student> students = (List<Student>) request.getAttribute("students");
                if (students != null && !students.isEmpty()) {
                    for (Student s : students) {
            %>
            <tr>
                <td><%= s.getId() %></td>
                <td><%= s.getName() %></td>
                <td><%= s.getAge() %></td>
                <td><%= (s.getClassroom() != null) ? s.getClassroom().getName() : "No Classroom Assigned" %></td>
                <td>
                    <button class="btn btn-warning btn-sm" onclick="editStudent('<%= s.getId() %>', '<%= s.getName() %>', '<%= s.getAge() %>', '<%= (s.getClassroom() != null) ? s.getClassroom().getId() : "" %>')">Edit</button>
                    <form action="students" method="post" style="display:inline;">
                        <input type="hidden" name="_method" value="DELETE">
                        <input type="hidden" name="id" value="<%= s.getId() %>">
                        <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this student?')">Delete</button>
                    </form>
                </td>
            </tr>
            <% } } else { %>
            <tr>
                <td colspan="5" class="text-center text-muted">No students found.</td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>

<!-- Bootstrap 5 JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script>
    function editStudent(id, name, age, classroomId) {
        document.getElementById("id").value = id;
        document.getElementById("name").value = name;
        document.getElementById("age").value = age;
        document.getElementById("_method").value = "PUT";

        let classroomSelect = document.getElementById("classroom_id");
        for (let i = 0; i < classroomSelect.options.length; i++) {
            if (classroomSelect.options[i].value == classroomId) {
                classroomSelect.options[i].selected = true;
                break;
            }
        }

        document.getElementById("form-title").textContent = "Update Student";
        document.getElementById("submit-btn").textContent = "Update";
        document.getElementById("cancel-btn").style.display = "inline-block";
    }

    function resetForm() {
        document.getElementById("id").value = "";
        document.getElementById("_method").value = "POST";
        document.getElementById("studentForm").reset();
        document.getElementById("form-title").textContent = "Add Student";
        document.getElementById("submit-btn").textContent = "Save";
        document.getElementById("cancel-btn").style.display = "none";
    }
</script>

<jsp:include page="/includes/footer.jsp"></jsp:include>
</body>
</html>
