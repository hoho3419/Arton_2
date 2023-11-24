<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<script>
  let msg = "${msg}";
  let uri = "/app${uri}";
  alert(msg);
  alert(uri);
  location.href = uri;
</script>
</body>
</html>