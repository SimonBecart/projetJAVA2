package com.projetjava.projetjava2.controllers;

import com.projetjava.projetjava2.database.PersonDAO;
import com.projetjava.projetjava2.models.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


import java.util.List;

public class MenuVueControl {

    @FXML
    private TableView<Person> tableView;
    @FXML
    private TableColumn<Person, Integer> idColumn;
    @FXML
    private TableColumn<Person, String> lastnameColumn;
    @FXML
    private TableColumn<Person, String> firstnameColumn;
    @FXML
    private TableColumn<Person, String> nicknameColumn;
    @FXML
    private TableColumn<Person, String> phoneColumn;
    @FXML
    private TableColumn<Person, String> emailColumn;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;

    private ObservableList<Person> personList;
    
    @FXML
    private void handleAdd() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ajouterContact.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Ajouter un Contact");
            stage.setScene(new Scene(root));

            // Attendre que la fenêtre soit fermée pour rafraîchir la liste
            stage.setOnHidden(event -> loadPersonData());

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void handleEdit() {
        Person selectedPerson = tableView.getSelectionModel().getSelectedItem();
        if (selectedPerson == null) {
            System.out.println("Aucun contact sélectionné.");
            return;
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/modifierContact.fxml"));
            Parent root = fxmlLoader.load();

            ModifierContactController controller = fxmlLoader.getController();
            controller.setPerson(selectedPerson);

            Stage stage = new Stage();
            stage.setTitle("Modifier un Contact");
            stage.setScene(new Scene(root));

            // Rafraîchir la liste après modification
            stage.setOnHidden(event -> loadPersonData());

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleDelete() {
        Person selectedPerson = tableView.getSelectionModel().getSelectedItem();
        if (selectedPerson == null) {
            System.out.println("Aucun contact sélectionné.");
            return;
        }

        // Confirmer la suppression avec une boîte de dialogue
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous vraiment supprimer " + selectedPerson.getFirstname() + " " + selectedPerson.getLastname() + " ?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                PersonDAO.deletePerson(selectedPerson.getId());
                loadPersonData();  // Mettre à jour la liste après suppression
            }
        });
    }

    
    
    @FXML
    public void initialize() {
        // Associer les colonnes aux attributs de Person
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        lastnameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastname()));
        firstnameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstname()));
        nicknameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNickname()));
        phoneColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        
        addButton.setOnAction(event -> handleAdd());
        editButton.setOnAction(event -> handleEdit());
        deleteButton.setOnAction(event -> handleDelete());

        loadPersonData();
    }


    private void loadPersonData() {
        List<Person> persons = PersonDAO.getAllPersons();
        personList = FXCollections.observableArrayList(persons);
        tableView.setItems(personList);
    }

}
