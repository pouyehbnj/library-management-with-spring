
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">

   <title>Product Offers</title>
    <h1 style="text-align:center; color:  rgb(6, 126, 134)">Books List</h1>
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
            <th> ISSN </th>
            <th> Title </th>
            <th> Author </th>
            <th> Publisher </th>
            <th> Publish Year </th>
            <th> image </th> 
            <th> Adding Date </th>
            
        </tr>
    </thead>
    <tbody>
	<tr th:if="${books.empty}">
            <td colspan="8"> No Books Available </td>
        </tr>
        <tr th:each="book : ${books}">
            <td><span th:text="${book.ISSN}"> ISSN </span></td>
            <td><span th:text="${book.title}"> Title </span></td>
            <td><span th:text="${book.author}"> Author </span></td>
            <td><span th:text="${book.publisher}"> Publisher </span></td>
            <td><span th:text="${book.publishYear}"> Publish Year </span></td>
            <td><span th:text="${book.image}"> Image </span></td>
            <td><span th:text="${book.createdAt}"> Adding Date </span></td>
        </tr>
    </tbody>
</table>     
</html>
