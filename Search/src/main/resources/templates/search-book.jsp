<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Search Book</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">
</head>


<body>
    <div class="container my-5">
        <Center>
            <h1>Search Book</h1>
        </Center>

        <!--        <h2>Search Book</h2>-->

        <div class="card">
            <div class="card-body">
                <div class="col-md-10">
                    <form method="POST" action="/book/search" modelAttribute="book">

                        <div class="form-group col-md-8">
                            <label for="title" class="col-form-label">Title</label>
                            <input type="text" name="title" class="form-control" placeholder="Title" />
                        </div>

                        <div class="form-group col-md-8">
                            <label for="author" class="col-form-label">Author</label>
                            <input type="text" name="author" class="form-control" placeholder="Author" />
                        </div>

                        <div class="form-group col-md-8">
                            <label for="publisher" class="col-form-label">Publisher</label>
                            <input type="text" name="publisher" class="form-control" placeholder="publisher" />
                        </div>

                        <div class="form-group col-md-8">
                            <label for="from" class="col-form-label">Year - From: </label>
                            <input type="text" name="from" class="form-control" placeholder="1945" />
                        </div>
                        
                        <div class="form-group col-md-8">
                            <label for="to" class="col-form-label">Year - To: </label>
                            <input type="text" name="to" class="form-control" placeholder="2021" />
                        </div>

                        <div class="col-md-6">
                            <input type="submit" class="btn btn-primary" name="submit" value="Search" />
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>
</body>

</html>