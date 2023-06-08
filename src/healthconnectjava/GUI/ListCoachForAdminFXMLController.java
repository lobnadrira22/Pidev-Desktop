/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package healthconnectjava.GUI;

import healthconnectjava.entities.Coach;
import healthconnectjava.services.SendEmailMailtrap;
import healthconnectjava.services.ServiceUser;
import healthconnectjava.utils.MyDB;
import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author wiki
 */
public class ListCoachForAdminFXMLController implements Initializable {
    
    ServiceUser su = new ServiceUser();
    
    public Connection conx;
    @FXML
    private TextField recherche;
    @FXML
    private Text NomPrenomUSerr;
    @FXML
    private Text emailUserr;
    @FXML
    private Button gestionExercice;
    
    
    public ListCoachForAdminFXMLController() {
        conx = MyDB.getInstance().getConx();
    }
    @FXML
    private Button gestionEvent;
    @FXML
    private Button gestionClient;
    @FXML
    private Button gestionCoach;
    @FXML
    private Button logoutB;
    @FXML
    private TableView<Coach> listCoach;
    @FXML
    private TableColumn<Coach, String> Nom;
    @FXML
    private TableColumn<Coach, String> Prenom;
    @FXML
    private TableColumn<Coach, String> Tel;
    @FXML
    private TableColumn<Coach, String> Email;
    @FXML
    private TableColumn<Coach, String> IsVerified;
    @FXML
    private TableColumn<Coach, String> Ecompte;
    @FXML
    private TableColumn<Coach, String> Action;
    @FXML
    private Button refreshListClient;
        @FXML
    private Button gestionCoach1;
        
          @FXML
    private Button objectif;
          
          @FXML
    private Button routine;

    
    ObservableList<Coach> CoachList;
    
    Coach coach = null ;
    
    private static String CoEmail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        NomPrenomUSerr.setText(su.getNom() +" "+su.getPrenom());
        emailUserr.setText(su.getEmail());
        
        loadDate();
        CoachSearch();
    }    
    
    private void loadDate() {
        CoachList = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM user JOIN coach ON user.id = coach.id WHERE type = 'coach'";
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {
                Coach co = new Coach();
            
                co.setId(rs.getInt("id"));
                co.setNom(rs.getString("nom"));
                co.setPrenom(rs.getString("prenom"));
                co.setEmail(rs.getString("email"));
                co.setTelephone(rs.getString("telephone"));
                co.setIsVerified(rs.getBoolean("is_verified"));
                co.setEtatCompte(rs.getBoolean("etat_compte"));
                
                CoachList.add(co);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        Tel.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        Email.setCellValueFactory(new PropertyValueFactory<>("email"));
        IsVerified.setCellValueFactory(new PropertyValueFactory<>("isVerified"));
        Ecompte.setCellValueFactory(new PropertyValueFactory<>("etatCompte"));
        
        listCoach.setItems(CoachList);
        
        Callback<TableColumn<Coach, String>, TableCell<Coach, String>> cellFoctory = (TableColumn<Coach, String> param) -> {
        final TableCell<Coach, String> cell = new TableCell<Coach, String>() {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    Button activerCoach = new Button("Activer");
                    Button desactiverCoach = new Button("Désactiver");
                    Button confirmerCoach = new Button("Confirmer");

                    // Définir les styles des boutons

                    activerCoach.setStyle(
                            "-fx-background-color: #2196f3;"+
                            "-fx-font-family: Tahoma;"+
                            "-fx-font-size: 12px;"+
                            "-fx-text-fill: #FFF;"+
                            "-fx-background-radius: 20px;"
                        );
                        desactiverCoach.setStyle(
                            "-fx-background-color: #2196f3;"+
                            "-fx-font-family: Tahoma;"+
                            "-fx-font-size: 12px;"+
                            "-fx-text-fill: #FFF;"+
                            "-fx-background-radius: 20px;"
                        );
                        confirmerCoach.setStyle(
                            "-fx-background-color: #2196f3;"+
                            "-fx-font-family: Tahoma;"+
                            "-fx-font-size: 12px;"+
                            "-fx-text-fill: #FFF;"+
                            "-fx-background-radius: 20px;"
                        );


                        Coach coach = getTableView().getItems().get(getIndex());
                        boolean isVerified = coach.isIsVerified();
                        boolean etatCompte = coach.isEtatCompte();
                        
                        // Afficher les boutons appropriés en fonction de la valeur de la colonne "is_verified"
                        if (isVerified == true && etatCompte == false) {
                            setGraphic(new HBox(desactiverCoach, confirmerCoach));
                        } else if (isVerified == true && etatCompte == true){
                            setGraphic(new HBox(desactiverCoach));
                        }else {
                            setGraphic(activerCoach);
                        }
                        setText(null);

                    // Définir les actions des boutons
                    activerCoach.setOnAction((ActionEvent event) -> {
                        try {
                            String req = "UPDATE user JOIN coach ON user.id = coach.id SET user.is_verified = true WHERE user.id = ?";
                            PreparedStatement pst = conx.prepareStatement(req);
                            pst.setInt(1, coach.getId());
                            System.out.println("Coach activé");
                            pst.executeUpdate();
                            loadDate();
                        } catch (SQLException ex) {
                            System.err.println(ex.getMessage());
                        }
                    });

                    desactiverCoach.setOnAction((ActionEvent event) -> {
                        try {
                            String req = "UPDATE user JOIN coach ON user.id = coach.id SET user.is_verified = false WHERE user.id = ?";
                            PreparedStatement pst = conx.prepareStatement(req);
                            pst.setInt(1, coach.getId());
                            System.out.println("Coach désactivé");
                            pst.executeUpdate();
                            loadDate();
                        } catch (SQLException ex) {
                            System.err.println(ex.getMessage());
                        }
                    });

                    confirmerCoach.setOnAction((ActionEvent event) -> {
                        try {
                            String req = "UPDATE user JOIN coach ON user.id = coach.id SET coach.etat_compte = true WHERE user.id = ?";
                            PreparedStatement pst = conx.prepareStatement(req);
                            pst.setInt(1, coach.getId());
                            System.out.println("Coach confirmé");
                            pst.executeUpdate();
                            loadDate();
                            CoEmail = coach.getEmail();
                            SendEmailMailtrap.getSendEmail();
                        } catch (SQLException ex) {
                            System.err.println(ex.getMessage());
                        } catch (UnknownHostException ex) {
                            System.err.println(ex.getMessage());
                        }
                    });
                }
            }
        };
        return cell;
    };
        Action.setCellFactory(cellFoctory);
        listCoach.setItems(CoachList);
    }
    
    

    @FXML
    private void gestClient(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListClientForAdminFXML.fxml"));
            Parent root = loader.load();
            gestionClient.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    private void gestCoach(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListCoachForAdminFXML.fxml"));
            Parent root = loader.load();
            gestionCoach.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
     @FXML
    private void gestEvent(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageEvFXML.fxml"));
            Parent root = loader.load();
            gestionEvent.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
     @FXML
    private void gestExercice(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageExFXML.fxml"));
            Parent root = loader.load();
            gestionEvent.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
 public void academy(){
             try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AcademieFXML.fxml"));
            Parent root = loader.load();
            gestionCoach1.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
 
 public void objectif(){
             try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsulterObj.fxml"));
            Parent root = loader.load();
            objectif.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
 public void routine(){
             try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsulterRoutine.fxml"));
            Parent root = loader.load();
            routine.getScene().setRoot(root);
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
    private void refreshList(ActionEvent event) {
        loadDate();
    }
    
    public void CoachSearch() {

        FilteredList<Coach> filter = new FilteredList<>(CoachList, e -> true);

        recherche.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateCoachData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateCoachData.getNom().toLowerCase().startsWith(searchKey)) {
                    return true;
                } 
                else if (predicateCoachData.getPrenom().toLowerCase().startsWith(searchKey)) {
                    return true;
                }
                else if (predicateCoachData.getTelephone().toLowerCase().startsWith(searchKey)) {
                    return true;
                }
                else if (predicateCoachData.getEmail().toLowerCase().startsWith(searchKey)) {
                    return true;
                } 
                else {
                    return false;
                }
            });
        });

        SortedList<Coach> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(listCoach.comparatorProperty());
        listCoach.setItems(sortList);
    }

    public static String getCoEmail() {
        return CoEmail;
    }

    public static void setCoEmail(String CoEmail) {
        ListCoachForAdminFXMLController.CoEmail = CoEmail;
    }

   

   
    
}
