<%@ page import="step.step3.WifiAPI" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<title>와이파이 정보 구하기</title>
	<link href="/res/css/main.css" rel="stylesheet"/>
</head>
<body>

    <%
        WifiAPI WifiApi = new WifiAPI();

        int count = WifiApi.getPublicWifiJson();
    %>

    <div class = "result-div">
        <h1>
            <%=count%>개의 WIFI 정보를 정상적으로 저장하였습니다.
        </h1>
        <a href="/">홈으로 가기</a>
    </div>
</body>
</html>