<!DOCTYPE html>
<html>
<head>
    <title>CALCULATOR</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="index.js"></script>
</head>
<body>
<form id="calculate" method="post">
    <div>Enter num1: </div>
    <input type="number" name="num1" onblur="calculate()">
    <div>Enter num2: </div>
    <input type="number" name="num2" onblur="calculate()">
    <div id="result">Result: ${sum}</div>
</form>
</body>
</html>