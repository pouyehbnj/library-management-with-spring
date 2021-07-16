<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
    xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
    xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">
<title>Book Details</title>
<h1 style="text-align:center; color:  rgb(6, 126, 134)">Books Details</h1>
<style>
    table,
    td {
        border: 1px solid rgb(8, 6, 150);
        background-color: #bafaf5;
        text-align: center;

    }

    th {
        color: white;
        background: rgb(7, 155, 241);
    }
</style>
<table align="center">
    <thead>
        <tr>
            <th> Username </th>
            <th> Role </th>
            <th>Detail</th>
            <th>Edit</th>
            <th>Delete</th>

        </tr>
    </thead>
    <tbody>
        <tr th:each="user : ${users}">
            <td><span th:text="${user.username}"> Username </span></td>
            <td><span th:text="${user.role}"> Role </span></td>
            <td><a th:href="@{/user/{id}(id=${book.id})}" class="btn btn-primary"><i class="fas fa-marker ml-2"></i></a>
            </td>
            <td><a th:href="@{/user/update/{id}(id=${book.id})}" class="btn btn-primary"><i class="fas fa-user-edit ml-2"></i></a></td>
            <td><a th:href="@{/remove-user/{id}(id=${book.id})}" class="btn btn-primary"><i class="fas fa-user-times ml-2"></i></a></td>

        </tr>
    </tbody>
</table>

</html>