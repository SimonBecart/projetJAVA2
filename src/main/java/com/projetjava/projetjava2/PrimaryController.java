package com.projetjava.projetjava2;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    //Premiere vue
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary"); // Charge "secondary.fxml"
    }
}
