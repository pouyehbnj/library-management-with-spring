<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Add Book</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">
</head>


    <body>
        <div class="container my-5">
            <Center>
                <h1>Printemps Library</h1>
            </Center>


            <h2>Login</h2>

            <div class="card">
                <div class="card-body">
                    <div class="col-md-10">
                        <form method="POST" action="/login">
                            <div class="row">

                                <div class="form-group col-md-8">
                                    <label for="isbn" class="col-form-label">Username</label>
                                    <input type="text" name="username" class="form-control" placeholder="Username" />
                                </div>

                                <div class="form-group col-md-8">
                                    <label for="name" class="col-form-label">Password</label>
                                    <input type="password" name="password" class="form-control"
                                        placeholder="Password" />

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