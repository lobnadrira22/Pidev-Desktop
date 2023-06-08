/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package healthconnectjava.GUI;

import healthconnectjava.services.ServiceUser;
import healthconnectjava.utils.MyDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author wiki
 */
public class ProfilCoachFXMLController implements Initializable {
    
    ServiceUser su = new ServiceUser();
    
    public Connection conx;
    @FXML
    private ImageView ImageUserr;
    @FXML
    private Text NomPrenomUSerr;
    @FXML
    private Text emailUserr;

    public ProfilCoachFXMLController() {
        conx = MyDB.getInstance().getConx();
    }

    @FXML
    private Button profil;
    @FXML
    private Button logoutB;
    @FXML
    private TextField NomCo;
    @FXML
    private TextField DNaissanceCo;
    @FXML
    private TextField TelCo;
    @FXML
    private TextField PrenomCo;
    @FXML
    private TextField EmailCo;
    
     @FXML
    private Button Alim;
    
    Image imager = new Image("http://127.0.0.1:8000/Client_img/"+su.getImage());


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        NomPrenomUSerr.setText(su.getNom() +" "+su.getPrenom());
        emailUserr.setText(su.getEmail());
        ImageUserr.setImage(imager);
        
        NomCo.setText(su.getNom());
        PrenomCo.setText(su.getPrenom());
        EmailCo.setText(su.getEmail());
        DNaissanceCo.setText(su.getDateNaissance());
        TelCo.setText(su.getTelephone());
    }    

    @FXML
    private void editProfil(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfilCoachFXML.fxml"));
            Parent root = loader.load();
            profil.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void logout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
            Parent root = loader.load();
            logoutB.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
        if (EmailCo.getText().equals("") || NomCo.getText().equals("") || PrenomCo.getText().equals("") || DNaissanceCo.getText().equals("") || TelCo.getText().equals("")) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Controle de saisie");
            al.setContentText("Veuillez remplir tout les champs !!");
            al.show();
        } else {
            if (!EmailCo.getText().contains("@") || !EmailCo.getText().contains(".")) {
                Alert al = new Alert(Alert.AlertType.WARNING);
                al.setTitle("Controle de saisie");
                al.setContentText("Respecter format email !!");
                al.show();
            } else if(TelCo.getText().matches(".*[a-zA-Z].*")){
                Alert al = new Alert(Alert.AlertType.WARNING);
                al.setTitle("Controle de saisie");
                al.setContentText("Le numéro de téléphone ne doivent pas contenir des lettres !!");
                al.show();
            }else {
                try {
                    String req = "UPDATE user JOIN coach ON user.id = coach.id SET user.email = ?, user.nom = ?, user.prenom = ?, user.date_naissance = ?, user.telephone = ? WHERE user.id = "+su.getId();
                    PreparedStatement pst = conx.prepareStatement(req);
                    pst.setString(1, EmailCo.getText());
                    pst.setString(2, NomCo.getText());
                    pst.setString(3, PrenomCo.getText());
                    pst.setString(4, DNaissanceCo.getText());
                    pst.setString(5, TelCo.getText());

                    pst.executeUpdate();

                    Alert all = new Alert(Alert.AlertType.CONFIRMATION);
                    all.setTitle("Modification du compte");
                    all.setContentText("Informations modifiées avec succès !!");
                    all.show();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
    
     public void aliment(){
             try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsulterAliment.fxml"));
            Parent root = loader.load();
            Alim.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
    
    @FXML
    private void EditPassword(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("EditPasswordFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("Modifier mot de passe");
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    
}
