<%@ page import="step.step3.JspTest" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%
    JspTest jspTest = new JspTest();

    String message = jspTest.hello();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>JSP 테스트</title>
    <link href="/res/css/main.css" rel="stylesheet"/>
</head>
<body>
    <div class = "result-div">
        <h1>
            <%=message%>개의 WIFI 정보를 정상적으로 저장하였습니다.
        </h1>
        <a href="/">홈으로 가기</a>
    </div>
</body>
</html>