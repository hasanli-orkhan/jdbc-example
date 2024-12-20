package com.example.learn.jdbc;

import java.sql.*;

public class PostgreExample {

    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/learn-jdbc";
        String user = "postgres";
        String password = "postgres";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            int id = 2;
            String title = "Harry Potter 1";
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO books (id, title) VALUES (?, ?)");
            pstmt.setInt(1, id);
            pstmt.setString(2, title);
            int rowsInserted = pstmt.executeUpdate();
            System.out.println(rowsInserted);


            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM books");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " " + resultSet.getString("title"));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
