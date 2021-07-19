<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Update Comment</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">
    </head>

    <body>
        <!--	<div th:insert="fragments/header :: header"></div>-->
        <div class="container my-5">
            <h3>Update Comment</h3>
            <div class="card">
                <div class="card-body">
                    <div class="col-md-10">
                        <form action="#" th:action="@{/update-comment/{id}(id=${comment.id})}" th:object="${comment}" method="post">
                            <div class="row">

                                <div class="form-group col-md-8">
                                    <label for="Content" class="col-form-label">Content</label> 
                                    <input type="text" th:field="*{content}" class="form-control" id="content" placeholder="Content" />

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