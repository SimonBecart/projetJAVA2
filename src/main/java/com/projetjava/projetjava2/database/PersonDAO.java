package com.projetjava.projetjava2.database;

import com.projetjava.projetjava2.models.Person;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {

    //add quelqu'un
    public static void addPerson(Person person) {
        String sql = "INSERT INTO person (lastname, firstname, nickname, phone_number, address, email_address, birth_date) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, person.getLastname());
            pstmt.setString(2, person.getFirstname());
            pstmt.setString(3, person.getNickname());
            pstmt.setString(4, person.getPhoneNumber());
            pstmt.setString(5, person.getAddress());
            pstmt.setString(6, person.getEmail());
            pstmt.setString(7, person.getBirthDate());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //récup toutes les personnes
    public static List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<>();
        String sql = "SELECT * FROM person";

        try (Connection conn = DatabaseManager.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Person person = new Person(
                        rs.getInt("idperson"),
                        rs.getString("lastname"),
                        rs.getString("firstname"),
                        rs.getString("nickname"),
                        rs.getString("phone_number"),
                        rs.getString("address"),
                        rs.getString("email_address"),
                        rs.getString("birth_date")
                );
                persons.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persons;
    }

    //update une personne
    public static void updatePerson(Person person) {
        String sql = "UPDATE person SET lastname = ?, firstname = ?, nickname = ?, phone_number = ?, address = ?, email_address = ?, birth_date = ? WHERE idperson = ?";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, person.getLastname());
            pstmt.setString(2, person.getFirstname());
            pstmt.setString(3, person.getNickname());
            pstmt.setString(4, person.getPhoneNumber());
            pstmt.setString(5, person.getAddress());
            pstmt.setString(6, person.getEmail());
            pstmt.setString(7, person.getBirthDate());
            pstmt.setInt(8, person.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Supp une personne
    public static void deletePerson(int id) {
        String sql = "DELETE FROM person WHERE idperson = ?";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}