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
                            <a href="/addBook" class="btn btn-primary"> 
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
                                            <th>Edit</th>
                                            <th>Delete</th>

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
                                            <td><a th:href="@{/update/{id}(id=${book.id})}" class="btn btn-primary"><i class="fas fa-user-edit ml-2"></i></a></td>
                                            <td><a th:href="@{/remove-book/{id}(id=${book.id})}" class="btn btn-primary"><i class="fas fa-user-times ml-2"></i></a></td>

                                        </tr>
                                    </tbody>
                                </table>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

<!--        <div th:if="${noOfPages > 0}" class="pagination"
             th:each="pageNumber : ${pageNumbers}">
            <a th:href="@{/showBooks(page=${pageNumber},size=${size})}"
               th:text=${pageNumber}
               th:class="${pageNumber==currentPage + 1} ? active"></a>
        </div>-->


        <nav aria-label="Pagination" th:if="${noOfPages gt 0}">
            <ul class="pagination justify-content-center font-weight-bold">
                <li class="page-item" th:classappend="${currentPage eq 0} ? 'disabled'">
                    <a class="page-link"
                       th:href="@{/showBooks?page={id}(id=${currentPage lt 2 ? 1 : currentPage})}"
                       aria-label="Previous" title="Previous Page" data-toggle="tooltip">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>

                <li class="page-item" th:classappend="${i eq currentPage + 1} ? 'active'"
                    th:each="i : ${#numbers.sequence( 1, noOfPages, 1)}">
                    <a class="page-link" th:href="@{/showBooks?page={id}(id=${i})}" th:text="${i}"
                       th:title="${'Page '+ i}" data-toggle="tooltip"></a>
                </li>
                <li class="page-item" th:classappend="${currentPage + 1 eq noOfPages} ? 'disabled'">
                    <a class="page-link"
                       th:href="@{/showBooks?page={id}(id=${currentPage + 2})}"
                       aria-label="Next" title="Next Page" data-toggle="tooltip">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>
        <!--         customers pagination 
                <nav aria-label="Pagination" th:if="${noOfPages gt 0}">
                    <ul class="pagination justify-content-center font-weight-medium">
                        <li class="page-item" th:classappend="${currentPage eq 0} ? 'disabled'">
                            <a class="page-link svg-icon"
                               th:href="@{/showBooks?page={id}(id=${currentPage lt 2 ? 1 : currentPage})}"
                               aria-label="Previous"
                               title="Previous Page" rel="tooltip">
                                <span aria-hidden="true" data-feather="chevrons-left" width="20" height="20"></span>
                            </a>
                        </li>
                        <li class="page-item" th:classappend="${i eq currentPage + 1} ? 'active'"
                            th:each="i : ${#numbers.sequence( currentPage+ 1, noOfPages > 10 + currentPage ? currentPage + size : noOfPages, 1)}">
                            <a class="page-link" th:href="@{/showBooks?page={id}(id=${i})}" th:text="${i}"
                               th:title="${'Page '+ i}"
                               rel="tooltip"></a>
                        </li>
                        <li class="page-item disabled" th:if="${currentPage + 10 < noOfPages}">
                            <a class="page-link svg-icon" href="#">
                                <span data-feather="more-horizontal" width="20" height="20"></span>
                            </a>
                        </li>
                        <li class="page-item" th:classappend="${currentPage + 1 eq noOfPages} ? 'disabled'">
                            <a class="page-link svg-icon" th:href="@{/showBooks?page={id}(id=${currentPage + 2})}"
                               aria-label="Next"
                               title="Next Page" rel="tooltip">
                                <span aria-hidden="true" data-feather="chevrons-right" width="20" height="20"></span>
                            </a>
                        </li>
                    </ul>
                </nav>-->
    </body>
