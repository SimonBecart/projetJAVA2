package com.projetjava.projetjava2.controllers;

import com.projetjava.projetjava2.database.PersonDAO;
import com.projetjava.projetjava2.models.Person;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ModifierContactController {
    
    @FXML
    private TextField lastnameField;
    @FXML
    private TextField firstnameField;
    @FXML
    private TextField nicknameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField emailField;

    private Person person;

    public void setPerson(Person person) {
        this.person = person;
        lastnameField.setText(person.getLastname());
        firstnameField.setText(person.getFirstname());
        nicknameField.setText(person.getNickname());
        phoneField.setText(person.getPhoneNumber());
        emailField.setText(person.getEmail());
    }

    @FXML
    private void handleUpdate() {
        if (!isValidEmail(emailField.getText())) {
            showAlert("Erreur", "L'adresse e-mail doit contenir un '@' !");
            return;
        }

        if (!isValidPhoneNumber(phoneField.getText())) {
            showAlert("Erreur", "Le numéro de téléphone doit contenir exactement 10 chiffres !");
            return;
        }

        person.setLastname(lastnameField.getText());
        person.setFirstname(firstnameField.getText());
        person.setNickname(nicknameField.getText());
        person.setPhoneNumber(phoneField.getText());
        person.setEmail(emailField.getText());

        PersonDAO.updatePerson(person);

        // Fermer la fenêtre après modification
        Stage stage = (Stage) lastnameField.getScene().getWindow();
        stage.close();
    }

    // Méthode pour valider un e-mail
    private boolean isValidEmail(String email) {
        return email.contains("@") && email.length() > 3;
    }

    // Méthode pour valider un numéro de téléphone
    private boolean isValidPhoneNumber(String phone) {
        return phone.matches("\\d{10}");  // Vérifie si le numéro contient exactement 10 chiffres
    }

    // Affichage d'une alerte
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
