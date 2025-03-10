package com.projetjava.projetjava2.controllers;

import com.projetjava.projetjava2.database.PersonDAO;
import com.projetjava.projetjava2.models.Person;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


public class AjouterContactController {
    
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

    @FXML
    private void handleSave() {
        if (!isValidEmail(emailField.getText())) {
            showAlert("Erreur", "L'adresse e-mail doit contenir un '@' !");
            return;
        }

        if (!isValidPhoneNumber(phoneField.getText())) {
            showAlert("Erreur", "Le numéro de téléphone doit contenir exactement 10 chiffres !");
            return;
        }

        Person newPerson = new Person(0, lastnameField.getText(), firstnameField.getText(),
                nicknameField.getText(), phoneField.getText(), emailField.getText(), "", "");

        PersonDAO.addPerson(newPerson);

        // Fermer la fenêtre après ajout
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
