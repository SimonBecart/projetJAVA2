package com.projetjava.projetjava2.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String URL = "jdbc:sqlite:src/main/resources/database/person.db";
    //url déstitanoation du fichier person.db
    
    //connect url
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }
    
    //initialise
    public static void initializeDatabase() {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS person ("
                    + "idperson INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "lastname VARCHAR(45) NOT NULL, "
                    + "firstname VARCHAR(45) NOT NULL, "
                    + "nickname VARCHAR(45) NOT NULL, "
                    + "phone_number VARCHAR(15), "
                    + "address VARCHAR(200), "
                    + "email_address VARCHAR(150), "
                    + "birth_date DATE)";
            stmt.execute(sql);
            System.out.println("Base de données initialisée");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
