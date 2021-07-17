
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
    <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet"href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
            <link rel="stylesheet"href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">

                <div class="container my-2">
                    <div class="card">
                        <div class="card-body">
                            <div class="container my-5">
                                <div class="col-md-12">
                                    <div >
                                        <table class="table table-striped table-responsive-md">
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
                                                <tr >
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

                                        <p class="my-5">
                                            <form action="#" th:action="@{/keyword/add/{id}(id=${book.id})}" th:object="${book}"> 

                                                <div class="col-md-6">

                                                    <input type="submit" class="btn btn-primary" value=" Add Keyword ">
                                                </div>
                                            </form>

                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                </html>
