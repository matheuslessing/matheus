package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Sobre;
import model.dal.ModuloConexao;

public class FXMLPrincipalController implements Initializable {

    @FXML
    private JFXButton btnSair, btnGravar, btnLGravar, btnLimpaCad, btnRemove, btnCancela;

    @FXML
    private JFXTextField txtSite, txtLogin, txtAnot, txtPesq, txtSenha;

    @FXML
    private static Label lblUser;

    @FXML
    private StackPane stackPane;

    @FXML
    private ListView<String> listView;

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    @FXML
    private void carregaListView() {
        try {
            listView.getItems().clear();
            rs = conexao.createStatement().executeQuery("select servicos from servicos");
            while (rs.next()) {
                listView.getItems().addAll(rs.getString("servicos"));
            }
        } catch (SQLException e) {
            String t = e.toString();
            dialogException(t);
        }
    }

    @FXML
    private void addServicos() {
        String sql = "insert into servicos(servicos,login,senha,anotacoes) value(?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtSite.getText());
            pst.setString(2, txtLogin.getText());
            pst.setString(3, txtSenha.getText());
            pst.setString(4, txtAnot.getText());
            int add = pst.executeUpdate();
            if (add > 0) {
                diagGravOk();
                desabilitaCampos();
                carregaListView();
            }
        } catch (SQLException e) {
            String t = e.toString();
            dialogException(t);
        }
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

    @FXML
    private void sair() {
        Stage stage = (Stage) btnSair.getScene().getWindow(); //Obtendo a janela atual
        stage.close();
    }

    @FXML
    private void btnADD() {
        btnGravar.setDisable(false);
        btnLimpaCad.setDisable(false);
        btnCancela.setDisable(false);
        txtSite.setDisable(false);
        txtLogin.setDisable(false);
        txtSenha.setDisable(false);
        txtAnot.setDisable(false);
        txtSite.requestFocus();
    }

    @FXML
    private void remove() {
        
        desabilitaCampos();
    }

    @FXML
    private void desabilitaCampos() {
        limparcad();
        btnGravar.setDisable(true);
        btnLimpaCad.setDisable(true);
        btnCancela.setDisable(true);
        txtSite.setDisable(true);
        txtLogin.setDisable(true);
        txtSenha.setDisable(true);
        txtAnot.setDisable(true);
    }

    @FXML
    private void diagGravOk() {

        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Perfeito! :)"));
        content.setBody(new Text("Os dados foram gravados com sucesso!"));
        JFXDialog dialogo = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton button = new JFXButton("OK");
        final String IDLE_BUTTON_STYLE = "-fx-background-color:  #009149; -fx-text-fill:white";
        button.setStyle(IDLE_BUTTON_STYLE);
        button.setOnAction((ActionEvent event) -> {
            dialogo.close(); //To change body of generated methods, choose Tools | Templates.
        });
        content.setActions(button);
        dialogo.show();
    }

    @FXML
    private void diagGravErro() {

        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Ops... :("));
        content.setBody(new Text("Ouve um erro ao gravar os dados!"));
        JFXDialog dialogo = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton button = new JFXButton("OK");
        final String IDLE_BUTTON_STYLE = "-fx-background-color:  #009149; -fx-text-fill:white";
        button.setStyle(IDLE_BUTTON_STYLE);
        button.setOnAction((ActionEvent event) -> {
            dialogo.close(); //To change body of generated methods, choose Tools | Templates.
        });
        content.setActions(button);
        dialogo.show();
    }

    @FXML
    private void limparcad() {
        txtLogin.setText("");
        txtAnot.setText("");
        txtSenha.setText("");
        txtSite.setText("");
    }

    @FXML
    private void limparpesq() {
        txtPesq.setText("");
    }
    
    @FXML
    static void carregaUser(String user){
        lblUser.setText(user);
    }
    
    @FXML
    private void sobre() throws Exception{
        Sobre sobre = new Sobre();
        sobre.start(new Stage());
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conexao = ModuloConexao.conector();
        carregaListView();
    }
}
