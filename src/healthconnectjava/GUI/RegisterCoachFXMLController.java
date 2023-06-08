/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package healthconnectjava.GUI;

import healthconnectjava.utils.MyDB;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author wiki
 */
public class RegisterCoachFXMLController implements Initializable {

    public Connection conx;
    public RegisterCoachFXMLController() {
        conx = MyDB.getInstance().getConx();
    }
    
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Hyperlink PLog;
    @FXML
    private Button Binscrit;
    @FXML
    private TextField dateN;
    @FXML
    private TextField prenom;
    @FXML
    private TextField nom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void PageLogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
            Parent root = loader.load();
            PLog.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void registerCoach(ActionEvent event) throws MalformedURLException, IOException {
        if (email.getText().equals("") || password.getText().equals("") || nom.getText().equals("") || prenom.getText().equals("") || dateN.getText().equals("")) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Controle de saisie");
            al.setContentText("Veuillez remplir tout les champs !!");
            al.show();
        } else {
            if (!email.getText().contains("@") || !email.getText().contains(".")) {
                Alert al = new Alert(Alert.AlertType.WARNING);
                al.setTitle("Controle de saisie");
                al.setContentText("Respecter format email !!");
                al.show();
            } else if(password.getText().length() < 8){
                Alert al = new Alert(Alert.AlertType.WARNING);
                al.setTitle("Controle de saisie");
                al.setContentText("Le mot de passe doit contenir au moins 9 caractères !!");
                al.show();
            }else {
                try {

                    String checkQuery = "SELECT COUNT(*) FROM user WHERE email = ?";
                    PreparedStatement pstt = conx.prepareStatement(checkQuery);
                    pstt.setString(1, email.getText());
                    ResultSet checkResult = pstt.executeQuery();
                    checkResult.next();
                    int count = checkResult.getInt(1);
                    if (count > 0) {
                        Alert al = new Alert(Alert.AlertType.WARNING);
                        al.setTitle("Inscription Coach");
                        al.setContentText("Le Coach existe déjà dans la base de données !!");
                        al.show();
                    }

                    // Créer l'URL avec les paramètres nécessaires
                    String url = "http://127.0.0.1:8000/signupCoachJson?email="+email.getText()+"&password="+password.getText()+"&nom="+nom.getText()+"&prenom="+prenom.getText()+"&dateNaissance="+dateN.getText();

                    // Établir une connexion HTTP à l'URL
                    URL obj = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con.setRequestMethod("GET");

                    // Récupérer la réponse JSON
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    Alert all = new Alert(Alert.AlertType.CONFIRMATION);
                    all.setTitle("Inscription Coach");
                    all.setContentText("Coach ajouté !!");
                    all.show();

                } catch (SQLException e) {
                    System.err.print(e.getMessage());
                }
            }
        }
    }
}
