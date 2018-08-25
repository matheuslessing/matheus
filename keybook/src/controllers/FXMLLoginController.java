package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import static controllers.FXMLPrincipalController.carregaUser;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Principal;
import model.dal.ModuloConexao;


public class FXMLLoginController implements Initializable {

    @FXML
    private JFXTextField txtUser;

    @FXML
    private JFXPasswordField txtSenha;

    @FXML
    private JFXButton btnEntrar;

    @FXML
    private JFXButton btnSair;

    @FXML
    private StackPane stackPane;

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    

    public void entrar(ActionEvent event) throws Exception {
        String sql = "select * from usuarios where login=? and senha=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUser.getText());
            pst.setString(2, txtSenha.getText());
            rs = pst.executeQuery();
            
            if (rs.next()) {
                //carregaUser("Teste");
                Principal p = new Principal();
                p.start(new Stage());
                Stage stage = (Stage) btnEntrar.getScene().getWindow(); //Obtendo a janela atual
                stage.close();
                conexao.close();
            } else {
                dialogSenhaIncorreta();
            }
        } catch (Exception e) {
            String t = e.toString();
            dialogException(t);
        }
    }

    @FXML
    private void sair() {
        Stage stage = (Stage) btnSair.getScene().getWindow(); //Obtendo a janela atual
        stage.close();
    }

    @FXML
    private void dialogSenhaIncorreta() {
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Ops... :("));
        content.setBody(new Text("Usuário e/ou senha incorreta! Verifique e tente novamente. ;)"));
        JFXDialog dialogo = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton button = new JFXButton("Entendi!");
        final String IDLE_BUTTON_STYLE = "-fx-background-color: #0070D3; -fx-text-fill:white";
        button.setStyle(IDLE_BUTTON_STYLE);
        button.setOnAction((ActionEvent event) -> {
            dialogo.close(); //To change body of generated methods, choose Tools | Templates.
        });
        content.setActions(button);
        dialogo.show();
    }

    @FXML
    private void dialogException(String t) {
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Ops... :("));
        content.setBody(new Text(t));
        JFXDialog dialogo = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton button = new JFXButton("Entendi!");
        final String IDLE_BUTTON_STYLE = "-fx-background-color: #0070D3; -fx-text-fill:white";
        button.setStyle(IDLE_BUTTON_STYLE);
        button.setOnAction((ActionEvent event) -> {
            dialogo.close(); //To change body of generated methods, choose Tools | Templates.
        });
        content.setActions(button);
        dialogo.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conexao = ModuloConexao.conector();
        txtUser.requestFocus();
        
    }

}
