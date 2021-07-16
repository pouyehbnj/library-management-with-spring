<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Add User</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">
</head>


<body>
    <div class="container my-5">
        <Center>
            <h1>Printemps Library</h1>
        </Center>

        <h2>Add User</h2>

        <div class="card">
            <div class="card-body">
                <div class="col-md-10">
                    <form method="POST" action="/user/add">
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
                               
                                <select id="dropdown" class="form-control" onchange="run()">
                                    <option selected>Choose Role</option>
                                    <option type="text" name="role" value="publisher" class="form-control">Publisher</option>
                                    <option type="text" name="role" value="user" class="form-control">User</option>
                                    <option type="text" name="role" value="admin" class="form-control">Admin</option>
                                </select>
                            </div>

                            <input type="hidden" name="role" class="form-control" id=inputrole />

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
<script>
    function run() {
        document.getElementById("inputrole").value = document.getElementById("dropdown").value;
    }
</script>

</html>