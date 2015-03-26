<%--
  Created by IntelliJ IDEA.
  User: andreas
  Date: 2/6/15
  Time: 10:25 PM
--%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/wro/main.css" />
    <script type="text/javascript" src="/wro/main.js"></script>

    <title><sitemesh:write property='title' /></title>
    <sitemesh:write property='head' />


</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Brand Icon</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="/persons">Persons</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <sitemesh:write property='body' />
</div>
</body>
</html>
