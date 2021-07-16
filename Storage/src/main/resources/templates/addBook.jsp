<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
    xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
    xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">

<center>
    <h1>Welcome to Library</h1>

    <h2>Add New Book</h2>

    <form method="POST" action="/addBook">

        Title: <input type="text" name="title" value="" /><br><br>
        Author: <input type="text" name="author" value="" /><br><br>
        ISSN: <input type="text" name="ISSN" value="" /><br><br>
        Publisher: <input type="text" name="publisher" value="" /><br><br>
        Publish Year: <input type="text" name="publishYear" value="" /><br><br>
        Image: <input type="text" name="image" value="Link to image" /><br><br>

        <input type="submit" name="submit" />
    </form>
</center>

</html>