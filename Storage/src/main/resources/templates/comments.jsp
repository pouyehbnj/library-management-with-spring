<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Book Comments</title>

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet"href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
        <link rel="stylesheet"href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">

    </head>
    <body>
        <div class="container my-2">
            <div class="card">
                <div class="card-body">
                    <div th:switch="${comments}" class="container my-5">

                        <div class="col-md-12">
                            <h2 th:case="null">No record found !!</h2>
                            <div th:case="*">
                                <table class="table table-striped table-responsive-md">
                                    <thead>
                                        <tr>
                                            <th> User Name </th>
                                            <th> Comment </th>
                                            <th>Edit</th>
                                            <th>Delete</th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr th:each="comment : ${comments}">
                                            <td><span th:text="${comment.user.username}"> User Name </span></td>
                                            <td><span th:text="${comment.content}"> Content </span></td>
                                            <td>
                                                <span th:if="${comment.user.id eq user.id}">
                                                    <a th:href="@{/comment/update/{id}(id=${comment.id})}" class="btn btn-primary"><i class="fas fa-user-edit ml-2"></i></a>
                                                </span>
                                                    

                                            </td> 
                                            <td><span th:if="${comment.user.id eq user.id}">
                                                    <a th:href="@{/comment/remove/{id}(id=${comment.id})}" class="btn btn-primary"><i class="fas fa-user-times ml-2"></i></a>
                                                </span>
                                            </td>

                                        </tr>
                                    </tbody>
                                </table>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <nav aria-label="Pagination" th:if="${noOfPages gt 0}">
            <ul class="pagination justify-content-center font-weight-bold">
                <li class="page-item" th:classappend="${currentPage eq 0} ? 'disabled'">
                    <a class="page-link"
                       th:href="@{/comments/{book_id}/?page={id}(book_id=${book.id}, id=${currentPage lt 2 ? 1 : currentPage})}"
                       aria-label="Previous" title="Previous Page" data-toggle="tooltip">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>

                <li class="page-item" th:classappend="${i eq currentPage + 1} ? 'active'"
                    th:each="i : ${#numbers.sequence( 1, noOfPages, 1)}">
                    <a class="page-link" th:href="@{/comments/{book_id}/?page={id}(book_id=${book.id}, id=${i})}" th:text="${i}"
                       th:title="${'Page '+ i}" data-toggle="tooltip"></a>
                </li>
                <li class="page-item" th:classappend="${currentPage + 1 eq noOfPages} ? 'disabled'">
                    <a class="page-link"
                       th:href="@{/comments/{book_id}/?page={id}(book_id=${book.id}, id=${currentPage + 2})}"
                       aria-label="Next" title="Next Page" data-toggle="tooltip">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>

    </body>
