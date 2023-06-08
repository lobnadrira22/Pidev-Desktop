/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package healthconnectjava.GUI;

import healthconnectjava.entities.User;
import healthconnectjava.services.ServiceUser;
import healthconnectjava.utils.MyDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author wiki
 */
public class LoginFXMLController implements Initializable {
    
    public Connection conx;
    public LoginFXMLController() {
        conx = MyDB.getInstance().getConx();
    }

    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Button login;
    @FXML
    private Hyperlink PfPass;
    @FXML
    private Hyperlink PchReg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnLogin(ActionEvent event) throws SQLException, IOException {
        ServiceUser uti = new ServiceUser();
        
        if (email.getText().equals("") || password.getText().equals("")) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Controle de saisie");
            al.setContentText("Veuillez remplir tout les champs !!");
            al.show();
        } else {
            if (!email.getText().contains("@")  || !email.getText().contains(".")) {
                Alert al = new Alert(Alert.AlertType.WARNING);
                al.setTitle("Controle de saisie");
                al.setContentText("Respecter format email !!");
                al.show();
            }else if (uti.login(new User(email.getText(), password.getText())).equals("[\"ROLE_COACH\"]")) {
                Stage stage = (Stage) login.getScene().getWindow();
                stage.close();
                Parent page = FXMLLoader.load(getClass().getResource("ProfilCoachFXML.fxml"));

                Scene scene = new Scene(page);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(scene);
                app_stage.show();
            } else if (uti.login(new User(email.getText(), password.getText())).equals("[\"ROLE_CLIENT\"]")) {
                Stage stage = (Stage) login.getScene().getWindow();
                stage.close();
                Parent page = FXMLLoader.load(getClass().getResource("ProfilClientFXML.fxml"));

                Scene scene = new Scene(page);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(scene);
                app_stage.show();
            } else if (uti.login(new User(email.getText(), password.getText())).equals("[\"ROLE_ADMIN\"]")) {
                Stage stage = (Stage) login.getScene().getWindow();
                stage.close();
                Parent page = FXMLLoader.load(getClass().getResource("ListClientForAdminFXML.fxml"));

                Scene scene = new Scene(page);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(scene);
                app_stage.show();
            } 
        }
    }

    @FXML
    private void PageforgotPass(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ForgotPasswordFXML.fxml"));
            Parent root = loader.load();
            PfPass.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void PagechoixReg(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ChoixRegisterFXML.fxml"));
            Parent root = loader.load();
            PchReg.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}
