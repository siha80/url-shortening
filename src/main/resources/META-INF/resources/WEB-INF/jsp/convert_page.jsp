<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Url Shorten converter</title>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script type="text/javascript">
        $(function() {
            $("#btnOk").click(function () {
                $.ajax({
                    // type을 설정합니다.
                    type: "POST",
                    url: "/convert",
                    contentType:"application/json",
                    dataType: "text",
                    data: JSON.stringify({"url": $("#originUrl").val()}),
                    // 성공적으로 값을 서버로 보냈을 경우 처리하는 코드입니다.
                    success: function (data) {
                        console.log(data)
                        var shortened = JSON.parse(data).shortened
                        $("#result").html("<a href='" + shortened + "'>" + shortened + "</a>")
                    }
                })
            })

        })
    </script>
</head>
<body>
    Origin Url: <input type="text" id="originUrl" value="https://en.wikipedia.org/wiki/URL_shortening">
    <button id="btnOk">전송</button>
    <BR>
    <BR>
    Result ========> <div id="result"></div>
</body>
</html>