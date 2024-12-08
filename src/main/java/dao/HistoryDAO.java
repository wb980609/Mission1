package dao;

import Database.DBConnect;
import dto.HistoryDTO;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;
import java.util.Date;


public class HistoryDAO {
    public static Connection connection;
    public static PreparedStatement preparedStatement;
    public static ResultSet resultSet;

    public static void searchHistory(String lat, String lnt) {

        connection = null;
        preparedStatement = null;
        resultSet = null;

        try {
            connection = DBConnect.connectDB();

            DateFormatSymbols dfs = new DateFormatSymbols(Locale.KOREAN);
            dfs.setWeekdays(new String[]{
                    "unused",
                    "일요일",
                    "월요일",
                    "화요일",
                    "수요일",
                    "목요일",
                    "금요일",
                    "토요일"
            });
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd '('E')' HH:mm:ss", dfs);
            String strDate = sdf.format(new Date());

            String sql = " insert into search_wifi "
                    + " (lat, lnt, search_dttm) "
                    + " values ( ?, ?, ? )";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, lat);
            preparedStatement.setString(2, lnt);
            preparedStatement.setString(3, strDate.toString());

            preparedStatement.executeUpdate();

            System.out.println("데이터가 삽입 완료되었습니다.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.close(connection, preparedStatement, resultSet);
        }
    }

    public List<HistoryDTO> searchHistoryList() {
        List<HistoryDTO> list = new ArrayList<>();

        connection = null;
        preparedStatement = null;
        resultSet = null;

        try {
            connection = DBConnect.connectDB();
            String sql = " select * "
                    + " from search_wifi "
                    + " order by id desc ";

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                HistoryDTO historyDTO = new HistoryDTO(
                        resultSet.getInt("id")
                        , resultSet.getString("lat")
                        , resultSet.getString("lnt")
                        , resultSet.getString("search_dttm")
                );
                list.add(historyDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.close(connection, preparedStatement, resultSet);
        }

        return list;
    }

    public void deleteHistoryList(String id) {

        connection = null;
        preparedStatement = null;
        resultSet = null;

        try {
            connection = DBConnect.connectDB();
            String sql = "delete from search_wifi where id = ? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(id));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnect.close(connection, preparedStatement, resultSet);
        }
    }
}