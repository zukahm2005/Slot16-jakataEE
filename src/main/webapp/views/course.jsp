
<%@ page import="java.util.List, org.example.slot16.entities.Course" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/includes/header.jsp"></jsp:include>
<html>
<head>
    <title>Course Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-4">
    <h2 class="text-center">Course Management</h2>

    <!-- Form Add / Update Course -->
    <div class="card p-4 mb-4">
        <h4 id="form-title">Add Course</h4>
        <form id="courseForm" method="post" action="courses">
            <input type="hidden" name="id" id="id">
            <input type="hidden" name="_method" id="_method" value="POST">

            <div class="mb-3">
                <label class="form-label">Course Name</label>
                <input type="text" class="form-control" name="name" id="name" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Description</label>
                <textarea class="form-control" name="description" id="description" required></textarea>
            </div>
            <button class="btn btn-primary" type="submit" id="submit-btn">Save</button>
            <button class="btn btn-secondary" type="button" id="cancel-btn" style="display: none;" onclick="resetForm()">Cancel</button>
        </form>
    </div>

    <!-- Course Table -->
    <div class="card p-4">
        <h4>Course List</h4>
        <table class="table table-bordered table-striped mt-3">
            <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Course> courses = (List<Course>) request.getAttribute("courses");
                if (courses != null && !courses.isEmpty()) {
                    for (Course c : courses) {
            %>
            <tr>
                <td><%= c.getId() %></td>
                <td><%= c.getName() %></td>
                <td><%= c.getDescription() %></td>
                <td>
                    <button class="btn btn-warning btn-sm" onclick="editCourse('<%= c.getId() %>', '<%= c.getName() %>', '<%= c.getDescription() %>')">Edit</button>
                    <form action="courses" method="post" style="display:inline;">
                        <input type="hidden" name="_method" value="DELETE">
                        <input type="hidden" name="id" value="<%= c.getId() %>">
                        <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this course?')">Delete</button>
                    </form>
                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="4" class="text-center">No courses found.</td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
</div>

<script>
    function editCourse(id, name, description) {
        document.getElementById("id").value = id;
        document.getElementById("name").value = name;
        document.getElementById("description").value = description;
        document.getElementById("_method").value = "PUT";
        document.getElementById("form-title").textContent = "Update Course";
        document.getElementById("submit-btn").textContent = "Update";
        document.getElementById("cancel-btn").style.display = "inline-block";
    }

    function resetForm() {
        document.getElementById("id").value = "";
        document.getElementById("_method").value = "POST";
        document.getElementById("courseForm").reset();
        document.getElementById("form-title").textContent = "Add Course";
        document.getElementById("submit-btn").textContent = "Save";
        document.getElementById("cancel-btn").style.display = "none";
    }
</script>
<jsp:include page="/includes/footer.jsp"></jsp:include>

</body>
</html>
