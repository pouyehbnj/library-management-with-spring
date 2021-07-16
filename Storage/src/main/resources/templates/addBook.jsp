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

            <h2>َAdd Book</h2>

            <div class="card">
                <div class="card-body">
                    <div class="col-md-10">
                        <form method="POST" action="/book/add">
                            <div class="row">

                                <div class="form-group col-md-8">
                                    <label for="title" class="col-form-label">Title</label>
                                    <input type="text" name="title" class="form-control" placeholder="Title" />
                                </div>

                                <div class="form-group col-md-8">
                                    <label for="author" class="col-form-label">Author</label>
                                    <input type="text" name="author" class="form-control" placeholder="Author" />
                                </div>

                                <div class="form-group col-md-8">
                                    <label for="ISSN" class="col-form-label">ISBN</label>
                                    <input type="text" name="ISSN" class="form-control" placeholder="ISBN" />
                                </div>


                                <div class="form-group col-md-8">
                                    <label for="publisher" class="col-form-label">Publisher</label>
                                    <input type="text" name="publisher" class="form-control" placeholder="publisher" />
                                </div>

                                
                                <div class="form-group col-md-8">
                                    <label for="publishYear" class="col-form-label">Publish Year</label>
                                    <input type="text" name="publishYear" class="form-control" placeholder="Year" />
                                </div>
                                
                                <div class="form-group col-md-8">
                                    <label for="image" class="col-form-label">Image Link</label>
                                    <input type="text" name="image" class="form-control" placeholder="Link" />
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