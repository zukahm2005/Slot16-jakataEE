<%@ page import="java.util.List, org.example.slot16.entities.Classroom" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/includes/header.jsp"></jsp:include>

<html>
<head>
    <title>Classroom Management</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-4">
    <h2 class="text-center text-primary">Classroom Management</h2>

    <!-- Form Add / Update Classroom -->
    <div class="card shadow p-4 mb-4">
        <h4 id="form-title" class="text-info">Add Classroom</h4>
        <form id="classroomForm" method="post" action="classrooms">
            <input type="hidden" name="id" id="id">
            <input type="hidden" name="_method" id="_method" value="POST">

            <div class="mb-3">
                <label class="form-label fw-bold">Classroom Name</label>
                <input type="text" class="form-control" name="name" id="name" required>
            </div>
            <div class="mb-3">
                <label class="form-label fw-bold">Capacity</label>
                <input type="number" class="form-control" name="capacity" id="capacity" required>
            </div>
            <button class="btn btn-success" type="submit" id="submit-btn">Save</button>
            <button class="btn btn-secondary" type="button" id="cancel-btn" style="display: none;" onclick="resetForm()">Cancel</button>
        </form>
    </div>

    <!-- Classroom Table -->
    <div class="card shadow p-4">
        <h4 class="text-info">Classroom List</h4>
        <table class="table table-hover table-striped mt-3">
            <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Capacity</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <% List<Classroom> classrooms = (List<Classroom>) request.getAttribute("classrooms");
                if (classrooms != null && !classrooms.isEmpty()) {
                    for (Classroom c : classrooms) { %>
            <tr>
                <td><%= c.getId() %></td>
                <td><%= c.getName() %></td>
                <td><%= c.getCapacity() %></td>
                <td>
                    <button class="btn btn-warning btn-sm" onclick="editClass('<%= c.getId() %>', '<%= c.getName() %>', '<%= c.getCapacity() %>')">Edit</button>
                    <form action="classrooms" method="post" style="display:inline;">
                        <input type="hidden" name="_method" value="DELETE">
                        <input type="hidden" name="id" value="<%= c.getId() %>">
                        <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this classroom?')">Delete</button>
                    </form>
                </td>
            </tr>
            <% } } else { %>
            <tr>
                <td colspan="4" class="text-center text-muted">No classrooms found.</td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>

<!-- Bootstrap 5 JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script>
    function editClass(id, name, capacity) {
        document.getElementById("id").value = id;
        document.getElementById("name").value = name;
        document.getElementById("capacity").value = capacity;
        document.getElementById("_method").value = "PUT";
        document.getElementById("form-title").textContent = "Update Classroom";
        document.getElementById("submit-btn").textContent = "Update";
        document.getElementById("cancel-btn").style.display = "inline-block";
    }

    function resetForm() {
        document.getElementById("id").value = "";
        document.getElementById("_method").value = "POST";
        document.getElementById("classroomForm").reset();
        document.getElementById("form-title").textContent = "Add Classroom";
        document.getElementById("submit-btn").textContent = "Save";
        document.getElementById("cancel-btn").style.display = "none";
    }
</script>

<jsp:include page="/includes/footer.jsp"></jsp:include>
</body>
</html>
