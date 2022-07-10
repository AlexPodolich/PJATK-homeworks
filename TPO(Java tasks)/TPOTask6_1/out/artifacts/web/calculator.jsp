<!DOCTYPE html>
<html>
<head>
    <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="apps.js"></script>
        </head>
<body>
    <form id="calculator" action="inputservlet" method="post">
        <p>
            Calculate with POST
            <input type="number" name="left">
            <input type="number" name="right">
        </p>
        <p>Result: <span id="result">${sum}</span></p>
    </form>
</body>
</html>