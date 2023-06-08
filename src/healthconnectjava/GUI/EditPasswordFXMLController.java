/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package healthconnectjava.GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import healthconnectjava.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author Mondher
 */
public class EditPasswordFXMLController implements Initializable {
    
    ServiceUser su = new ServiceUser();

    @FXML
    private TextField password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void EditPass(ActionEvent event) throws MalformedURLException, IOException {
        if (password.getText().equals("")) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Controle de saisie");
            al.setContentText("Veuillez remplir tout les champs !!");
            al.show();
        } else if(password.getText().length() < 8){
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setTitle("Controle de saisie");
            al.setContentText("Le mot de passe doit contenir au moins 9 caractères !!");
            al.show();
        }else {
            // Créer l'URL avec les paramètres nécessaires
            String url = "http://127.0.0.1:8000/editPasswordjson?password="+password.getText()+"&id="+su.getId();
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
            all.setTitle("Modifier mot de passe");
            all.setContentText("Mot de passe modifié avec succès !!");
            all.show();
        }
    }
    
}
