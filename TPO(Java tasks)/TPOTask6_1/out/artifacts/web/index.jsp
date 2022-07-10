<!DOCTYPE html>
<html lang="en">
<head>
    <title>REGEX-PATTERN-PAGE</title>
    <meta charset="UTF-8">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="index.js"></script>
</head>
<body>
<form id="form" action="servlet" method="post">
    <div>Enter a pattern:</div>
    <input id="upper" type="text" name="upper"><br>
    <div>Enter a String:</div>
    <input id="lower" type="text" name="lower" disabled = "true">
    <div id="result">${result}</div>
</form>
</body>
</html>