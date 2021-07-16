<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
 
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Update Book</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">
</head>
 
<body>
<!--	<div th:insert="fragments/header :: header"></div>-->
    <div class="container my-5">
        <h3>Update Book</h3>
        <div class="card">
            <div class="card-body">
                <div class="col-md-10">
                    <form action="#" th:action="@{/update-book/{id}(id=${book.id})}" th:object="${book}" method="post">
                        <div class="row">
                        
                            <div class="form-group col-md-8">
                                <label for="isbn" class="col-form-label">ISSN</label> 
                                <input type="text" th:field="*{ISSN}" class="form-control" id="isbn" placeholder="ISSN" />
                                
                            </div>
                            
                            <div class="form-group col-md-8">
                                <label for="Title" class="col-form-label">Book Title</label> 
                                <input type="text" th:field="*{title}" class="form-control" id="title" placeholder="Book Title" />
                            </div>
                            
                            <div class="form-group col-md-8">
                                <label for="Author" class="col-form-label">Author</label> 
                                <input type="text" th:field="*{author}" class="form-control" id="author" placeholder="Author" />
                            </div>
                            
                             <div class="form-group col-md-8">
                                <label for="Publisher" class="col-form-label">Publisher</label> 
                                <input type="text" th:field="*{publisher}" class="form-control" id="publisher" placeholder="Publisher" />
                            </div>
                            <div class="form-group col-md-8">
                                <label for="publishYear" class="col-form-label">Publish Year</label> 
                                <input type="text" th:field="*{publishYear}" class="form-control" id="publishYear" placeholder="Publish Year" />
                            </div>
                            
                            <div class="form-group col-md-8">
                                <label for="image" class="col-form-label">Image URL</label> 
                                <br></br>
                                <img th:SRC="@{${book.image}}" with="50" height="50">
                                <br></br>
                                <input type="text" th:field="*{image}" class="form-control" id="createdAt" placeholder="Image" />
                            </div>
                            <div class="form-group col-md-8">
                                <label for="Publisher" class="col-form-label">Adding Date</label> 
                                <input type="text" th:field="*{createdAt}" class="form-control" id="createdAt" placeholder="Adding Date" />
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