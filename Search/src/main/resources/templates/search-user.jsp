<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Search User</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">
    </head>


    <body>
        <div class="container my-5">
            <Center>
                <h1>Search User</h1>
            </Center>

            <!--        <h2>Search Book</h2>-->

            <div class="card">
                <div class="card-body">
                    <div class="col-md-10">
                        <form method="POST" action="/user/search" modelAttribute="user">
                            <div class="row">

                                <div class="form-group col-md-8">
                                    <label for="username" class="col-form-label">Username</label>
                                    <input type="text" name="username" class="form-control" placeholder="Username" />
                                </div>

                                <div class="form-group col-md-8">
                                    <label for="password" class="col-form-label">Password</label>
                                    <input type="text" name="password" class="form-control" placeholder="Password" />
                                </div>

                                <div class="form-group col-md-8">
                                    <label for="role" class="col-form-label">Role</label>
                                    <input type="text" name="role" class="form-control" placeholder="Role" />
                                </div>

                                <div class="col-md-6">
                                    <input type="submit" class="btn btn-primary" name="submit" />
                                </div>


                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>

</html>