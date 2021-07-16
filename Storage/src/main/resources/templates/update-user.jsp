<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
 
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Update User</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">
</head>
 
<body>
<!--	<div th:insert="fragments/header :: header"></div>-->
    <div class="container my-5">
        <h3>Update User</h3>
        <div class="card">
            <div class="card-body">
                <div class="col-md-10">
                    <form action="#" th:action="@{/update-user/{id}(id=${user.id})}" th:object="${user}" method="post">
                        <div class="row">
                        
                            <div class="form-group col-md-8">
                                <label for="Username" class="col-form-label">Username</label> 
                                <input type="text" th:field="*{username}" class="form-control" id="isbn" placeholder="Username" />
                                
                            </div>
                            
                            <div class="form-group col-md-8">
                                <label for="Role" class="col-form-label">Role</label> 
                                <input type="text" th:field="*{role}" class="form-control" id="title" placeholder="Role" />
                            </div>
                            
                            <div class="form-group col-md-8">
                                <label for="Password" class="col-form-label">Password</label> 
                                <input type="text" th:field="*{password}" class="form-control" id="author" placeholder="Password" />
                            </div>
                            
                            <div class="col-md-6">
                                <input type="submit" class="btn btn-primary" value=" Submit ">
                            </div>
     
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
<!--<div th:insert="fragments/footer :: footer"></div>-->
</body>
 
</html>