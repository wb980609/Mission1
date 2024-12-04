<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>와이파이 정보 구하기</title>
	<link href="/res/css/main.css" rel="stylesheet"/>
	<script src="/res/js/index.js"></script>
</head>
<body>
    <h1>위치 히스토리 목록</h1>
    <jsp:include page="inc_menu.jsp"/>

    <div class = "position-info">
            <table class = "table-list">
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">X좌표</th>
                    <th scope="col">Y좌표</th>
                    <th scope="col">조회일자</th>
                    <th scope="col">비고</th>
                </tr>
            </table>
        </div>
</body>
</html>