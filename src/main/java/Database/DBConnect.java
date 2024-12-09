package Database;

import java.sql.*;

public class DBConnect {
    public static Connection connectDB() {

        //SQLite 데이터베이스 파일 위치
        //String dbFilePath = "C:/Source/Projects/Mission1/db/wifi_db.sqlite";
        String dbFilePath = "C:/java/Mission1/db/test_db.db";

        String url = "jdbc:sqlite:" + dbFilePath;

        Connection connection = null;

        try {
            Class.forName("org.sqlite.JDBC");  //JDBC 드라이버 로드
            connection = DriverManager.getConnection(url);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {

        try {
            if (resultSet != null && ! resultSet.isClosed()) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (preparedStatement != null && ! preparedStatement.isClosed()) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (connection != null && ! connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}