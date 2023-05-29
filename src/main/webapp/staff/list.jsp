<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Management Application</title>
</head>
<body>
<center>
    <h1>Staff Management</h1>
    <h2>
        <a href="/staffs?action=create">Add New Staff</a>
    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Staffs</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Address</th>
            <th>Phone</th>
            <th>salary</th>
            <th>department</th>

        </tr>
        <c:forEach var="staff" items="${listStaff}">
            <tr>
                <td><c:out value="${staff.id_staff}"/></td>
                <td><c:out value="${staff.name}"/></td>
                <td><c:out value="${staff.email}"/></td>
                <td><c:out value="${staff.address}"/></td>
                <td><c:out value="${staff.phone}"/></td>
                <td><c:out value="${staff.salary}"/></td>
                <td><c:out value="${staff.department}"/></td>
                <td>
                    <a href="/staffs?action=edit&id=${staff.id_staff}">Edit</a>
                    <a href="/staffs?action=delete&id=${staff.id_staff}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        <input type="text"> <button name="Search" value="Search">Search</button>
    </table>
</div>
</body>
</html>