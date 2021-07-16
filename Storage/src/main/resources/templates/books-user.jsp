<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>All Books</title>

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet"href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
        <link rel="stylesheet"href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">

    </head>
    <body>
        <div class="container my-2">
            <div class="card">
                <div class="card-body">
                    <div th:switch="${books}" class="container my-5">
                        <p class="my-5">
                            <a href="/book/add" class="btn btn-primary"> 
                                <i class="fas fa-user-plus ml-2">  Add Book </i></a>
                        </p>
                        <div class="col-md-12">
                            <h2 th:case="null">No record found !!</h2>
                            <div th:case="*">
                                <table class="table table-striped table-responsive-md">
                                    <thead>
                                        <tr>
                                            <th> ISBN </th>
                                            <th> Title </th>
                                            <th> Author </th>
                                            <th> Publisher </th>
                                            <th> Publish Year </th>
                                            <th> image </th> 
                                            <th> Adding Date </th>
                                            <th>Detail</th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr th:each="book : ${books}">
                                            <td><span th:text="${book.ISSN}"> ISBN </span></td>
                                            <td><span th:text="${book.title}"> Title </span></td>
                                            <td><span th:text="${book.author}"> Author </span></td>
                                            <td><span th:text="${book.publisher}"> Publisher </span></td>
                                            <td><span th:text="${book.publishYear}"> Publish Year </span></td>
                                            <td><img th:SRC="@{${book.image}}" with="80" height="80"></img></td>
                                            <td><span th:text="${book.createdAt}"> Adding Date </span></td>
                                            <td><a th:href="@{/book/{id}(id=${book.id})}" class="btn btn-primary"><i class="fas fa-marker ml-2"></i></a></td>
                                          
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>