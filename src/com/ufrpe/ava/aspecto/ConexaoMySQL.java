package com.ufrpe.ava.aspecto;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by paulomenezes on 01/12/15.
 */
public class ConexaoMySQL {
    public static Connection connection = getConnection();

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ava", "root", "");
            } catch (Exception e) {
                System.out.println("Conexão com o banco falhou");
            }
        }

        return connection;
    }
}
