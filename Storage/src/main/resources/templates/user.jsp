

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>User Details</title>

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet"href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
        <link rel="stylesheet"href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">

    </head>
    <body>

        <div class="container my-2">
            <div class="card">
                <div class="card-body">
                    <div class="container my-5">
                        <div class="col-md-12">
                            <div >
                                <table class="table table-striped table-responsive-md">
                                    <thead>
                                        <tr>
                                            <th>User Name </th>
                                            <th>Role</th>
                                            <th>Password</th>
                                            
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr >
                                            <td th:text="${user.username}"></td>
                                            <td th:text="${user.role}"></td>
                                            <td th:text="${user.password}"></td>
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

</html>