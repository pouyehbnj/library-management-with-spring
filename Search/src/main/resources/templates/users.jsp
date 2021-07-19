<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>All Users</title>

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet"href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
        <link rel="stylesheet"href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">

    </head>
    <body>
        <div class="container my-2">
            <div class="card">
                <div class="card-body">
                    <div th:switch="${users}" class="container my-5">
<!--                        <p class="my-5">
                            <a href="/user/add" class="btn btn-primary"> 
                                <i class="fas fa-user-plus ml-2">  Add User </i></a>
                        </p>-->
                        <div class="col-md-12">
                            <h2 th:case="null">No record found !!</h2>
                            <div th:case="*">
                                <table class="table table-striped table-responsive-md">
                                    <thead>
                                        <tr>
                                            <th> Username </th>
                                            <th> Role </th>
                                            <th>Detail</th>
                                            

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr th:each="user : ${users}">
                                            <td><span th:text="${user.username}"> Username </span></td>
                                            <td><span th:text="${user.role}"> Role </span></td>
                                            <td><a th:href="@{http://37.152.183.117/user/{id}(id=${user.id})}" class="btn btn-primary"><i class="fas fa-marker ml-2"></i></a></td>    

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
