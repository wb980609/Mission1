<%@ page import="java.util.*" %>
<%@ page import="dao.WifiDAO" %>
<%@ page import="dto.WifiDTO" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <title>JSP 테스트</title>
    <link href="/res/css/main.css" rel="stylesheet"/>
</head>
<body>
        <%
            String lat = request.getParameter("lat") == null ? "0.0" : request.getParameter("lat");
            String lnt = request.getParameter("lnt") == null ? "0.0" : request.getParameter("lnt");
        %>
        <div>
            <label for = "input">LAT : </label>
            <input type = "text" id = "inputLat" value = 0.0>
            ,
            <label for = "input">LNT : </label>
            <input type = "text" id = "inputLnt" value = 0.0>

            <button id = "getPositionButton">내 위치 가져오기</button>
            <button id = "getNearWifiInfo">근처 WIFI 정보 보기</button>
        </div>

        <div class = "position-info">
            <table class = "table-list">
                <thead>
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
                </thead>
                <tbody>
                    <%
                        if (!("0.0").equals(lat) && !("0.0").equals(lnt)) {
                            WifiDAO wifiDAO = new WifiDAO();
                            List<WifiDTO> list = wifiDAO.getNearestWifiList(lat, lnt);

                            if (list != null) {
                                for (WifiDTO wifiDTO : list) {
                    %>
                        <tr>
                            <td><%=wifiDTO.getDistance()%></td>
                            <td><%=wifiDTO.getXSwifiMgrNo()%></td>
                            <td><%=wifiDTO.getXSwifiWrdofc()%></td>
                            <td><%= wifiDTO.getXSwifiMainNm() %></a></td>
                            <td><%=wifiDTO.getXSwifiAdres1()%></td>
                            <td><%=wifiDTO.getXSwifiAdres2()%></td>
                            <td><%=wifiDTO.getXSwifiInstlFloor()%></td>
                            <td><%=wifiDTO.getXSwifiInstlMby()%></td>
                            <td><%=wifiDTO.getXSwifiInstlTy()%></td>
                            <td><%=wifiDTO.getXSwifiSvcSe()%></td>
                            <td><%=wifiDTO.getXSwifiCmcwr()%></td>
                            <td><%=wifiDTO.getXSwifiCnstcYear()%></td>
                            <td><%=wifiDTO.getXSwifiInoutDoor()%></td>
                            <td><%=wifiDTO.getXSwifiRemars3()%></td>
                            <td><%=wifiDTO.getLat()%></td>
                            <td><%=wifiDTO.getLnt()%></td>
                            <td><%=wifiDTO.getWorkDttm()%></td>
                        </tr>
                    <% } %>
                    <% } %>
                    <% } else { %>
                            <td colspan='17'> 위치 정보를 입력하신 후에 조회해 주세요. </td>
                        <% } %>
                </tbody>
            </table>
        </div>

        <script>
            getNearWifiInfo.addEventListener("click", function (){
                        let latitude = document.getElementById("inputLat").value;
                        let longitude = document.getElementById("inputLnt").value;

                        if (latitude !== "" || longitude !== "") {
                            window.location.assign("http://localhost:8080?lat=" + latitude + "&lnt=" + longitude);
                        } else {
                            alert("위치 정보를 입력하신 후에 조회해주세요.")
                        }
                    })
        </script>
</body>
</html>