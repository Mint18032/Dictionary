package com.example.dictionary_graphic;
import java.sql.*;
import java.sql.SQLException;

public class DictionaryManager {
    public static java.sql.Connection connection;
    public static java.sql.PreparedStatement preparedness;

    /**
     * Dictionary Connection.
     */
    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/amitdb", "root", "khanhminh123@");
            System.out.println("Connected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Search detail of word in database.
     */
    public static String searchWord(String word) throws SQLException {
        final String sqlSearchDetail = "select detail from dictionary where word=?";
        preparedness = connection.prepareStatement(sqlSearchDetail);

        preparedness.setString( 1, word);

        ResultSet rs = preparedness.executeQuery();

        System.out.println("Search already!");

        if (!rs.next()) {
            return "";
        } else {
            System.out.println(rs.getString("detail"));
            return rs.getString("detail");
        }

    }

    /**
     * Insert word to the database.
     */
    public static void insertWord(final String word, final String detail) throws SQLException {
        final String sqlInsertData = "insert into dictionary (word, detail) value (?, ?)";
        preparedness = connection.prepareStatement(sqlInsertData);

        preparedness.setString(1,word);
        preparedness.setString(2,detail);

        preparedness.executeUpdate();
        System.out.println("Inserted successfully");
    }

    /**
     * Delete word from the database.
     */
    public static void deleteWord(String word) throws SQLException {
        String sqlDeleteData = "delete from dictionary where word = ?";
        preparedness = connection.prepareStatement(sqlDeleteData);

        preparedness.setString(1,word);
        preparedness.executeUpdate();
        System.out.println("Deleted successfully");
    }

    /**
     * Fix Word.
     */

}
