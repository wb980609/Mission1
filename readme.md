

# Project - 내 위치 기반 공공 와이파이 정보를 제공하는 웹 서비스
  - 서울 공공 데이터 API를 이용해 자신의 위치 및 특정 위치에서 반경 내에 존재하는 공공 와이파이를 찾는 웹 서비스


# 개발 환경
  - IDE : IntelliJ
  - JDK : 1.8

# Tech Stack
  - Language : java
  - Build : Maven
  - DB : SQLITE
  - Server : Tomcat 9.0.97
  - Web : HTML5, CSS, JSP
  - Library : Lombok, Okhttp3, Gson
  - API : https://data.seoul.go.kr/dataList/OA-20883/S/1/datasetView.do

# 프로젝트 개요
  - OPEN API 를 활용하여 서울시 공공 와이파이 크롤링 및 저장
  - 사용자가 입력한 위도 경도 좌표를 기반으로 20개의 공공 와이파이 정보 표출
  - 입력한 위치정보와 조회 시점을 저장, 삭제 및 조회
