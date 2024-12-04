<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<title>와이파이 정보 구하기</title>
	<link href="/res/css/main.css" rel="stylesheet"/>
	<script src="/res/js/index.js"></script>
</head>
<body>
	<h1>와이파이 정보 구하기</h1>
	<jsp:include page="inc_menu.jsp"/>

	<label for = "input">LAT : </label>
	<input type = "text" id = "inputLAT" value = 0.0>
	,
	<label for = "input">LNT : </label>
    <input type = "text" id = "inputLNT" value = 0.0>
    <button>내 위치 가져오기</button>
    <button>근처 WIFI 정보 보기</button>

    <div class = "position-info">
        <table class = "table-list">
            <tr>
                <th scope="col">거리(KM)</th>
                <th scope="col">관리번호</th>
                <th scope="col">자치구</th>
                <th scope="col">와이파이명</th>
                <th scope="col">도로명주소</th>
                <th scope="col">상세주소</th>
                <th scope="col">설치위치</th>
                <th scope="col">설치유형</th>
                <th scope="col">설치기관</th>
                <th scope="col">서비스구분</th>
                <th scope="col">망종류</th>
                <th scope="col">설치년도</th>
                <th scope="col">실내외구분</th>
                <th scope="col">WIFI접속환경</th>
                <th scope="col">X좌표</th>
                <th scope="col">Y좌표</th>
                <th scope="col">작업일자</th>
            </tr>
        </table>
    </div>

</body>
</html>