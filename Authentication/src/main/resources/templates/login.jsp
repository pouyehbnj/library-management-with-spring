<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
    xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
    xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">

<center>
    <h1>Welcome to Library</h1>

    <h2>Login Page</h2>

    <form method="POST" action="/login">
        Username: <input type="text" name="username" value="test" /><br><br>
        Password: <input type="password" name="password" value="test" /><br><br>
        <input type="submit" name="submit" />
    </form>
</center>

</html>