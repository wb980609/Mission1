<%@ page import="dao.HistoryDAO" %>
<%@ page import="dto.HistoryDTO" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
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

    <%
        HistoryDAO history = new HistoryDAO();
        List<HistoryDTO> historyList = history.searchHistoryList();

        String strID = request.getParameter("id");
        if (strID != null) {
            history.deleteHistoryList(strID);
        }
    %>

    <div class = "position-info">
        <table class = "table-list">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">X좌표</th>
                    <th scope="col">Y좌표</th>
                    <th scope="col">조회일자</th>
                    <th scope="col">비고</th>
                </tr>
            </thead>
            <tbody>
                <% if (historyList.isEmpty()) {%>
                    <tr>
                        <td class = "td-center" colspan="5">위치 정보를 조회하신 이력이 없습니다.</td>
                    </tr>
                <% } else { %>
                <% for (HistoryDTO historyDTO : historyList) { %>
                    <tr>
                        <td><%=historyDTO.getId()%></td>
                        <td><%=historyDTO.getLat()%></td>
                        <td><%=historyDTO.getLnt()%></td>
                        <td><%=historyDTO.getReg_dt()%></td>
                        <td><button onclick="deleteHistory(<%=historyDTO.getId()%>)">삭제</button></td>
                    </tr>
                <% }} %>
            </tbody>
        </table>
        </div>
        <script>
            function deleteHistory(ID) {
                if (confirm("데이터를 삭제하시겠습니까?")) {
                    $.ajax({
                        url: "http://localhost:8080/history.jsp",
                        data: {id : ID},
                        success: function () {
                            location.reload();
                        },
                        error: function (request, status, error) {
                            alert("code: " + request.status + "\n"+ "message: " + request.responseText + "\n" + "error: " + error);
                        }
                    })
                }
            }
        </script>
</body>
</html>