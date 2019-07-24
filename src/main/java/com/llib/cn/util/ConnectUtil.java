package com.llib.cn.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class ConnectUtil {

   private ConnectUtil() {
    }
    public  static Connection getConnect() {
        try {
            FileInputStream f = new FileInputStream("src/main/resources/config.properties");
            Properties p = new Properties();
            p.load(f);

            Class.forName(p.getProperty("drivername"));

            Connection connection = DriverManager.getConnection(p.getProperty("url"),
                    p.getProperty("username"),
                    p.getProperty("password"));

            return connection;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(Connection con, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (con != null)
                con.close();
            if (preparedStatement != null)
                preparedStatement.close();

            if (resultSet != null)
                preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

