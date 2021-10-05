package com.example.dictionary_graphic;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DictionaryManager {
    /**
     * Dictionary Connection.
     */
    public static void checkConnection() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "khanhminh123@");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from people ");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
