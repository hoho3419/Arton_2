<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <button id="btn">버튼</button>

<script>
    const btn = document.getElementById('btn');

    function sendData() {
        const xhr = new XMLHttpRequest();
        const formData = new FormData();

        formData.append("test1", "test111");
        formData.append("test2", "test222");
        formData.append("test3", "test333");

        xhr.open('POST', '/app/test/test2');
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')
        xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest')

        xhr.send(formData);
    }

    btn.addEventListener('click', () => {
        sendData();
    });
</script>
</body>
</html>