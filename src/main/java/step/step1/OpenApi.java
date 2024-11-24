package step.step1;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OpenApi {

    /**
     * 오픈 API 샘플을 통한 JSON 문자열 리턴
     */
    public static String get(int startPage, int endPage) {

        String request = null;

        try {
            String url = String.format("http://openapi.seoul.go.kr:8088/7073676c4a7361743131375672575259/json/TbPublicWifiInfo/%s/%s/", startPage, endPage);

            URL url1 = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
            BufferedReader rd;

            // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();
            request = sb.toString();

        } catch (Exception e) {
            System.out.println("openApi error" + e.getMessage());
        }

        return request;
    }
}
