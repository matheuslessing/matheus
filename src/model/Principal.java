package model;


import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

 public class Principal extends Application {

@Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLPrincipal.fxml"));
        
        Scene tela = new Scene(root);
        stage.setScene(tela);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Login!");
        stage.show();
    }
 
    
    public static void main(String[] args) {
        launch(args);
    }
    
}

