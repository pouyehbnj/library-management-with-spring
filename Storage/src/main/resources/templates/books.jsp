<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>All Books</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">

</head>

<body>
    <div class="container my-2">
        <div class="card">
            <div class="card-body">

                <div class="col-md-10">
                    <div class="row">


                        <div class="form-group col-md-8">
                            <label for="op" class="col-form-label">Sort</label>

                            <select id="dropdown" class="form-control" onchange="run()">
                                <option selected aria-placeholder="Sort by ..."></option>
                                <option type="text" name="op" value="author" class="form-control">Author</option>

                                <option type="text" name="op" value="publishYear" class="form-control">Year</option>
                                <option type="text" name="op" value="title" class="form-control">Title</option>

                                <option type="text" name="op" value="publisher" class="form-control">Publisher</option>
                                <option type="text" name="op" value="createdAt" class="form-control">Date</option>

                            </select>
                        </div>




                    </div>
                </div>


                <div th:switch="${books}" class="container my-5">
                    <p class="my-5">
                        <a href="/addBook" class="btn btn-primary">
                            <i class="fas fa-user-plus ml-2"> Add Book </i></a>
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
                                        <td><a th:href="@{/book/{id}(id=${book.id})}" class="btn btn-primary"><i
                                                    class="fas fa-marker ml-2"></i></a></td>
                                        <td><a th:href="@{/update/{id}(id=${book.id})}" class="btn btn-primary"><i
                                                    class="fas fa-user-edit ml-2"></i></a></td>
                                        <td><a th:href="@{/remove-book/{id}(id=${book.id})}" class="btn btn-primary"><i
                                                    class="fas fa-user-times ml-2"></i></a></td>

                                    </tr>
                                </tbody>
                            </table>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <nav aria-label="Pagination" th:if="${noOfPages gt 0}" onmouseover="run();">
        <ul class="pagination justify-content-center font-weight-bold" onmouseover="run();">
            <li class="page-item" th:classappend="${currentPage eq 0} ? 'disabled'" onmouseover="run();">
                <a class="page-link"
                    th:href="@{/books?page={id}&filter={filter}(id=${currentPage lt 2 ? 1 : currentPage},filter=${filterValue})}"
                    aria-label="Previous" title="Previous Page" data-toggle="tooltip" onmouseover="run();" id="link-1">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>

            <li class="page-item" th:classappend="${i eq currentPage + 1} ? 'active'"
                th:each="i : ${#numbers.sequence( 1, noOfPages, 1)}" onmouseover="run();">
                <a class="page-link" th:href="@{/books?page={id}&filter={filter}(id=${i},filter=${filterValue})}"
                    th:text="${i}" th:title="${'Page '+ i}" data-toggle="tooltip" id="link-2" value="i"></a>
            </li>
            <li class="page-item" th:classappend="${currentPage + 1 eq noOfPages} ? 'disabled'" onmouseover="run()">
                <a class="page-link"
                    th:href="@{/books?page={id}&filter={filter}(id=${currentPage + 2},filter=${filterValue})}"
                    aria-label="Next" title="Next Page" data-toggle="tooltip" id="link-3">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
</body>
<script>
    function run() {
        filterValue = document.getElementById("dropdown").value;

        console.log("filter", filterValue)

        key = "filter";
        url = window.location.href;
        var match = url.match('[?&]' + key + '=([^&]+)');
        param = match ? match[1] : null;
        url = url.replace(param, filterValue);
        window.location.href = url
        //    console.log("i->>", document.getElementById("link-2").value)
        //    document.getElementById("link-1").href = `http://localhost:8002/books?page=${currentPage < 2 ? 1 : currentPage}&filter=${filterValue}`
        //    document.getElementById("link-2").href = `http://localhost:8002/books?page=${document.getElementById("link-2").value}&filter=${filterValue}`
        //    document.getElementById("link-3").href = `http://localhost:8002/book?page=${currentPage + 2}&filter=${filterValue}`

        //    console.log(document.getElementById("link-1").href)
        //    console.log(document.getElementById("link-2").href)
        //    console.log(document.getElementById("link-3").href)
    }
</script>


</html>