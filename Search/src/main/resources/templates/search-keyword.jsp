<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Search Keyword</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">
</head>


    <body>
        <div class="container my-5">
            <Center>
                <h1>Search Keyword</h1>
            </Center>

<!--            <h2>Add Boo</h2>-->

            <div class="card">
                <div class="card-body">
                    <div class="col-md-10">
                        <form method="POST" action="#" th:action="@{http://37.152.183.117/search/book/keyword}">
                            <div class="row">

                                <div class="form-group col-md-8">
                                    <label for="keywords" class="col-form-label">Keywords</label>
                                    <input type="text" name="keywords" class="form-control" placeholder="tree,girl,park" />
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