    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
     */
    package healthconnectjava.GUI;

    import healthconnectjava.entities.Coach;
    import healthconnectjava.services.ServiceUser;
    import healthconnectjava.utils.MyDB;
    import java.io.IOException;
    import java.net.URL;
    import java.sql.Connection;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.sql.Statement;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.ResourceBundle;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.fxml.Initializable;
    import javafx.scene.Parent;
    import javafx.scene.control.Button;
    import javafx.scene.image.Image;
    import javafx.scene.image.ImageView;
    import javafx.scene.layout.GridPane;
    import javafx.scene.layout.HBox;
    import javafx.scene.text.Text;

    /**
     * FXML Controller class
     *
     * @author wiki
     */
    public class ListCoachForClientFXMLController implements Initializable {

        ServiceUser su = new ServiceUser();

        public Connection conx;
        @FXML
        private ImageView ImageUserr;
        @FXML
        private Text NomPrenomUSerr;
        @FXML
        private Text emailUserr;
        @FXML
        private GridPane menuGridPane;
        @FXML
        private Button eventB;

        @FXML
        private Button aliment;
        
           @FXML
    private Button academie;


       




        public ListCoachForClientFXMLController() {
            conx = MyDB.getInstance().getConx();
        }

        @FXML
        private Button profil;
        @FXML
        private Button logoutB;
        @FXML
        private Button listCo;
        @FXML
        private HBox cardLayout;

        private List<Coach> listCardCoach;
            @FXML
        private Button eventB1;

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

            try {
                listCardCoach = new ArrayList<>(listCardCoach());
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }

            try {
                for(int i=0; i<listCardCoach.size(); i++){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("CardCoachFXML.fxml"));
                    HBox card = loader.load();
                    CardCoachFXMLController cardPatFXML = loader.getController();
                    cardPatFXML.setData(listCardCoach.get(i));
                    //cardLayout.getChildren().add(card);
                }
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }    

        @FXML
        private void editProfil(ActionEvent event) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfilClientFXML.fxml"));
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
        private void listCoach(ActionEvent event) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ListCoachForClientFXML.fxml"));
                Parent root = loader.load();
                listCo.getScene().setRoot(root);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }


        public void location(){
                 try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CategorieLocationFXML.fxml"));
                Parent root = loader.load();
                listCo.getScene().setRoot(root);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }

        }

          public void alimentation(){
                 try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("RoutineFront.fxml"));
                Parent root = loader.load();
                aliment.getScene().setRoot(root);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }

        }
        private List<Coach> listCardCoach() throws SQLException{
                List<Coach> temp = new  ArrayList<>();

                String req = "SELECT * FROM user JOIN coach ON user.id = coach.id WHERE etat_compte = 1 AND type = 'coach'";

                Statement st = conx.createStatement();

                ResultSet rs = st.executeQuery(req);

                while(rs.next()){
                    Coach pat = new Coach();

                    pat.setId(rs.getInt("id"));
                    pat.setNom(rs.getString("nom"));
                    pat.setPrenom(rs.getString("prenom"));
                    pat.setEmail(rs.getString("email"));
                    pat.setTelephone(rs.getString("telephone"));
                    pat.setImage(rs.getString("image"));

                    temp.add(pat);
                }

                return temp;
            }

        public void academie(){
             try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FrontAcFXML.fxml"));
                Parent root = loader.load();
                academie.getScene().setRoot(root);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }


        }  

        @FXML
        private void listEvent(ActionEvent event) {
              try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageEvFront.fxml"));
                Parent root = loader.load();
                listCo.getScene().setRoot(root);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
        }


