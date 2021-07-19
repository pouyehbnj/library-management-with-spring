<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Book Details</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">

</head>

<style>
    td {
        vertical-align: center;
    }
</style>

<body>

    <div class="container my-2">
        <div class="card">
            <div class="card-body">
                <div class="container my-5">
                    <div class="col-md-12">
                        <div>
                            <table class="table table-striped table-responsive-md">
                                <thead>
                                    <thead>
                                        <tr>
                                            <th> ISBN </th>
                                            <th> Title </th>
                                            <th> Author </th>
                                            <th> Publisher </th>
                                            <th> Year </th>
                                            <th> Date </th>
                                            <th>Book Cover</th>

                                        </tr>
                                    </thead>
                                </thead>
                                <tbody>

                                    <tr>
                                        <td><span th:text="${book.ISSN}"> ISSN </span></td>
                                        <td><span th:text="${book.title}"> Title </span></td>
                                        <td><span th:text="${book.author}"> Author </span></td>
                                        <td><span th:text="${book.publisher}"> Publisher </span></td>
                                        <td><span th:text="${book.publishYear}"> Year </span></td>
                                        <td><span th:text="${book.createdAt}"> Date </span></td>
                                        <td><img th:SRC="@{${book.image}}" with="200" height="200"></img></td>
                                    </tr>


                                </tbody>
                            </table>

                            <div class="row btn-toolbar">

                                <form action="#" th:action="@{/books}" th:object="${book}">
                        
                                    <div class="col-sm-4 col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                        <input type="submit" class="btn btn-primary" value=" Back ">
                                    </div>
                                </form>

                                <form action="#" th:action="@{/comment/add/{id}(id=${book.id})}" th:object="${book}">
                        
                                    <div class="col-sm-4 col-md-offset-2  col-sm-offset-2 col-lg-offset-2 col-lg-4 col-md-4 col-sm-4 col-xs-12">        
                                        <input type="submit" class="btn btn-primary" value=" Add Comment ">
                                    </div>
                                </form>
                        
                                <form action="#" th:action="@{/comments/{id}(id=${book.id})}" th:object="${book}">
                        
                                    <div class="col-sm-4 col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                        <input type="submit" class="btn btn-primary" value=" View Comments ">
                                    </div>
                                </form>

                            </div>
                        
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


</body>

</html>