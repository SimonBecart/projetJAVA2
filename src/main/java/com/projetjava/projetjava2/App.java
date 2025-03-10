package com.projetjava.projetjava2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.io.IOException;
import com.projetjava.projetjava2.database.DatabaseManager;
import com.projetjava.projetjava2.models.Person;
import com.projetjava.projetjava2.database.PersonDAO;
import java.util.List;



public class App extends Application {
    private static Stage primaryStage;
    
    //Start
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        setRoot("menu"); 
        primaryStage.setTitle("Contact App");
        primaryStage.show();
    }
    
    //set
    public static void setRoot(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/" + fxml + ".fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setScene(new Scene(root));
    }
    
    //main 
    public static void main(String[] args) {
        DatabaseManager.initializeDatabase();  // Création automatique de person.db

        // Ajouter une personne TEST
        Person newPerson = new Person(0, "Doe", "John", "Johnny", "123456789", "123 Rue", "john@example.com", "1990-01-01");
        PersonDAO.addPerson(newPerson);

        // Afficher les personnes TEST
        List<Person> persons = PersonDAO.getAllPersons();
        for (Person p : persons) {
            System.out.println(p.getFirstname() + " " + p.getLastname());
        }

        launch(args);
    }
}
