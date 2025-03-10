package com.projetjava.projetjava2;

import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController {
    
    
    //deuxieme vue
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary"); // Charge "primary.fxml"
    }
}
