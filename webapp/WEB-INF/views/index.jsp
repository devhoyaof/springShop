<%--
  Created by IntelliJ IDEA.
  User: hoyao
  Date: 2021-10-05
  Time: 오후 4:54
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
  <title>Home</title>
  <script
          src="https://code.jquery.com/jquery-3.4.1.js"
          integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
          crossorigin="anonymous"></script>
</head>
<body>
<h1>
  Hello world!
</h1>
<P>  The time on the server is ${serverTime}. </P>
</body>


<script>
  $(document).ready(function(){
    setTimeout(function () {
      location = '/main';
    }, 5000);
  });

</script>
</html>
