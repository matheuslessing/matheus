package controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FXMLSobreController implements Initializable {

    @FXML
    private JFXButton btnSair;
    
    @FXML
    private ImageView imageView;
    
    @FXML
    private void sair(){
        Stage stage = (Stage) btnSair.getScene().getWindow(); //Obtendo a janela atual
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
