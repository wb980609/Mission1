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

    public static void insertHistory(String lat, String lnt, String execTime) {

        connection = null;
        preparedStatement = null;
        resultSet = null;

        try {
            connection = DBConnect.connectDB();

            String sql = " insert into pos_history "
                    + " (lat, lnt, reg_dt) "
                    + " values ( ?, ?, ? )";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, lat);
            preparedStatement.setString(2, lnt);
            preparedStatement.setString(3, execTime);

            preparedStatement.executeUpdate();

            System.out.println("history 데이터 삽입 완료");

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
                    + " from pos_history "
                    + " order by id desc ";

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                HistoryDTO historyDTO = new HistoryDTO(
                        resultSet.getInt("id")
                        , resultSet.getString("lat")
                        , resultSet.getString("lnt")
                        , resultSet.getString("reg_dt")
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
            String sql = "delete from pos_history where id = ? ";

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