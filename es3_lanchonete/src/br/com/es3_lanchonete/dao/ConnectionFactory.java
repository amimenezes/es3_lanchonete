package br.com.es3_lanchonete.dao;

import java.sql.*;

public class ConnectionFactory {

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/bd", "root", "");
        } catch (ClassNotFoundException e) {
            System.out.println("driver não localizado");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("não conseguiu conectar");
        }
        return null;
    }
}
