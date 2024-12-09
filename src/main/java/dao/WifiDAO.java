package dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import dto.WifiDTO;
import Database.DBConnect;
import static dao.HistoryDAO.insertHistory;


public class WifiDAO {
    public static Connection connection;
    public static ResultSet resultSet;
    public static PreparedStatement preparedStatement;

    public static String nowTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter YYYY_MM_DD_HH_mm_ss = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return localDateTime.format(YYYY_MM_DD_HH_mm_ss);
    }

    public static int insertPublicWifi(JsonArray jsonArray) {
        connection = null;
        preparedStatement = null;
        resultSet = null;

        int count = 0;

        /* 쿼리 문 설정 */
        try {
            connection = DBConnect.connectDB();
            connection.setAutoCommit(false);

            /* Insert 진행 */
            String sql = " insert into wifi_info "
                    + " ( x_swifi_mgr_no, x_swifi_wrdofc, x_swifi_main_nm, x_swifi_adres1, x_swifi_adres2, "
                    + " x_swifi_instl_floor, x_swifi_instl_ty, x_swifi_instl_mby, x_swifi_svc_se, x_swifi_cmcwr, "
                    + " x_swifi_cnstc_year, x_swifi_inout_door, x_swifi_remars3, lat, lnt, work_dttm) "
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";

            preparedStatement = connection.prepareStatement(sql);

            for (int i = 0; i < jsonArray.size(); i++) {

                JsonObject data = (JsonObject) jsonArray.get(i).getAsJsonObject();

                preparedStatement.setString(1, data.get("X_SWIFI_MGR_NO").getAsString());
                preparedStatement.setString(2, data.get("X_SWIFI_WRDOFC").getAsString());
                preparedStatement.setString(3, data.get("X_SWIFI_MAIN_NM").getAsString());
                preparedStatement.setString(4, data.get("X_SWIFI_ADRES1").getAsString());
                preparedStatement.setString(5, data.get("X_SWIFI_ADRES2").getAsString());
                preparedStatement.setString(6, data.get("X_SWIFI_INSTL_FLOOR").getAsString());
                preparedStatement.setString(7, data.get("X_SWIFI_INSTL_TY").getAsString());
                preparedStatement.setString(8, data.get("X_SWIFI_INSTL_MBY").getAsString());
                preparedStatement.setString(9, data.get("X_SWIFI_SVC_SE").getAsString());
                preparedStatement.setString(10, data.get("X_SWIFI_CMCWR").getAsString());
                preparedStatement.setString(11, data.get("X_SWIFI_CNSTC_YEAR").getAsString());
                preparedStatement.setString(12, data.get("X_SWIFI_INOUT_DOOR").getAsString());
                preparedStatement.setString(13, data.get("X_SWIFI_REMARS3").getAsString());
                preparedStatement.setString(14, data.get("LAT").getAsString());
                preparedStatement.setString(15, data.get("LNT").getAsString());
                preparedStatement.setString(16, data.get("WORK_DTTM").getAsString());

                preparedStatement.addBatch();               //Batch 메모리에 올려두었다가, 한번에 DB쪽으로 쿼리를 날린다.
                preparedStatement.clearParameters();        //파라미터 클리어

                //1000개 기준으로 임시 batch 실행
                if ((i + 1) % 1000 == 0) {
                    int[] result = preparedStatement.executeBatch();
                    count += result.length;

                    connection.commit();
                }
            }

            int[] result = preparedStatement.executeBatch();
            count += result.length;
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();

            try {
                connection.rollback();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }

        } finally {
            DBConnect.close(connection, preparedStatement, resultSet);
        }

        return count;
    }

    public List<WifiDTO> getNearestWifiList(String lat, String lnt) {

        connection = null;
        preparedStatement = null;
        resultSet = null;

        List<WifiDTO> list = new ArrayList<>();

        try {

            connection = DBConnect.connectDB();

            String sql = " SELECT *, " +
                    " round(6371*acos(cos(radians(?))*cos(radians(LAT))*cos(radians(LNT) " +
                    " -radians(?))+sin(radians(?))*sin(radians(LAT))), 4) " +
                    " AS distance " +   // 거리 구하는 식
                    " FROM wifi_info " +
                    " ORDER BY distance " +
                    " LIMIT 20;";


            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, Double.parseDouble(lat));
            preparedStatement.setDouble(2, Double.parseDouble(lnt));
            preparedStatement.setDouble(3, Double.parseDouble(lat));

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                WifiDTO wifiDTO = WifiDTO.builder()
                        .distance(resultSet.getDouble("distance"))
                        .xSwifiMgrNo(resultSet.getString("x_swifi_mgr_no"))
                        .xSwifiWrdofc(resultSet.getString("x_swifi_wrdofc"))
                        .xSwifiMainNm(resultSet.getString("x_swifi_main_nm"))
                        .xSwifiAdres1(resultSet.getString("x_swifi_adres1"))
                        .xSwifiAdres2(resultSet.getString("x_swifi_adres2"))
                        .xSwifiInstlFloor(resultSet.getString("x_swifi_instl_floor"))
                        .xSwifiInstlTy(resultSet.getString("x_swifi_instl_ty"))
                        .xSwifiInstlMby(resultSet.getString("x_swifi_instl_mby"))
                        .xSwifiSvcSe(resultSet.getString("x_swifi_svc_se"))
                        .xSwifiCmcwr(resultSet.getString("x_swifi_cmcwr"))
                        .xSwifiCnstcYear(resultSet.getString("x_swifi_cnstc_year"))
                        .xSwifiInoutDoor(resultSet.getString("x_swifi_inout_door"))
                        .xSwifiRemars3(resultSet.getString("x_swifi_remars3"))
                        .lat(resultSet.getString("lat"))
                        .lnt(resultSet.getString("lnt"))
                        .workDttm(String.valueOf(resultSet.getTimestamp("work_dttm").toLocalDateTime()))
                        .build();

                list.add(wifiDTO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.close(connection, preparedStatement, resultSet);
        }
        insertHistory(lat, lnt, nowTime());        //해당 값을 조회한 경우 history 데이터에 추가한다!

        return list;
    }
}