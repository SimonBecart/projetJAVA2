

module com.projetjava.projetjava2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.projetjava.projetjava2.controllers to javafx.fxml;
    exports com.projetjava.projetjava2;
}

