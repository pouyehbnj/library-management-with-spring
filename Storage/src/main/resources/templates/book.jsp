
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
    <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet"href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
            <link rel="stylesheet"href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">
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

                            <td><span th:text="${book.ISSN}"> ISSN </span></td>
                            <td><span th:text="${book.title}"> Title </span></td>
                            <td><span th:text="${book.author}"> Author </span></td>
                            <td><span th:text="${book.publisher}"> Publisher </span></td>
                            <td><span th:text="${book.publishYear}"> Publish Year </span></td>
                            <td><img th:SRC="@{${book.image}}" with="200" height="200"></img></td>
                            <td><span th:text="${book.createdAt}"> Adding Date </span></td>
                        </tr>
                    </tbody>
                </table>     
                </html>
