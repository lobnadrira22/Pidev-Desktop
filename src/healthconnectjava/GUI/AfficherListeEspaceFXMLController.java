/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthconnectjava.GUI;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import healthconnectjava.entities.Espace;
import healthconnectjava.services.EspaceService;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author lobna
 */
public class AfficherListeEspaceFXMLController implements Initializable {
      @FXML
    private AnchorPane anchores;

    @FXML
    private TableView<Espace> tablespace;

    @FXML
    private TableColumn<Espace, Integer> idspace;

    @FXML
    private TableColumn<Espace, String> nomesp;

    @FXML
    private TableColumn<Espace, String> caract;

    @FXML
    private TableColumn<Espace, String> img;

    @FXML
    private TableColumn<Espace, String> adresse;

    @FXML
    private TableColumn<Espace, String> dispo;

    @FXML
    private TableColumn<Espace, Date> tarif;

    @FXML
    private TableColumn<Espace, Double> prixloc;

    @FXML
    private TableColumn<Espace, String> cat;

    @FXML
    private Label labeles;

    @FXML
    private Button ajouteres;

    @FXML
    private Button modifieres;

    @FXML
    private Button suppespace;
    
       @FXML
    private TextField txtfieldrecherche;

    @FXML
    private Label labelrecherche;
     @FXML
    private Button tri;
        @FXML
    private Button triadress;

    @FXML
    private Button triprix;
    @FXML
            private Pagination pagination;
    
    @FXML
    private ImageView code_qr;
    
    
    
    
    ObservableList<Espace> EspaceList;
  

    /**
     * Initializes the controller class.
     */
     
         
         
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       afficherListeES();
       tri.setOnAction(e -> trierParNom());
       triadress.setOnAction(e-> trierParAdresse() );
       triprix.setOnAction(e -> trierParPrix());
       
       

       
       SearchFilter();
     

       //pagination.setPageFactory(() -> this.createPagination());
      


       
    }    
    
   @FXML 
private void SearchFilter() {
    ObservableList<Espace> espaces = tablespace.getItems();
    FilteredList<Espace> filterData = new FilteredList(espaces,e->true);
    txtfieldrecherche.setOnKeyReleased(e->{
        txtfieldrecherche.textProperty().addListener((observable,oldValue,newValue)->{
            filterData.setPredicate((Predicate<? super Espace >) esp->{
                if(newValue==null){
                    return true;
                }
                String toLowerCaseFilter = newValue.toLowerCase();
                if(esp.getNom().contains(newValue)){
                    return true;
                } else if(esp.getCaracteristique().toLowerCase().contains(toLowerCaseFilter)) {
                    return true;
                } else if (esp.getAdresse().toLowerCase().contains(toLowerCaseFilter)) {
                    return true;
                } else if(esp.getDispo().toLowerCase().contains(toLowerCaseFilter)){
                    return true;
                } else if (esp.getPrixlocation().toString().contains(newValue)) {
                    return true;
                }
                return false;
            });
        });
        final SortedList<Espace> esps = new SortedList<>(filterData);
        esps.comparatorProperty().bind(tablespace.comparatorProperty());
        tablespace.setItems(esps);
    });
}


    
    
      @FXML
private void trierParNom() {
    // Récupérer la liste des espaces depuis le TableView
    ObservableList<Espace> espaces = tablespace.getItems();
    
    // Trier la liste par nom
    tablespace.getSortOrder().clear(); // Effacer tout tri précédent
    nomesp.setSortType(TableColumn.SortType.ASCENDING); // Spécifier le type de tri (ascendant)
    tablespace.getSortOrder().add(nomesp); // Ajouter la colonne de tri
    tablespace.sort(); // Appliquer le tri
}

@FXML
private void trierParAdresse() {
    // Récupérer la liste des espaces depuis le TableView
    ObservableList<Espace> espaces = tablespace.getItems();
    
    // Trier la liste par adresse
    tablespace.getSortOrder().clear(); // Effacer tout tri précédent
    adresse.setSortType(TableColumn.SortType.ASCENDING); // Spécifier le type de tri (ascendant)
    tablespace.getSortOrder().add(adresse); // Ajouter la colonne de tri
    tablespace.sort(); // Appliquer le tri
}

@FXML
private void trierParPrix() {
    // Récupérer la liste des espaces depuis le TableView
    ObservableList<Espace> espaces = tablespace.getItems();
    
    // Trier la liste par prix
    SortedList<Espace> espacesSorted = espaces.sorted((e1, e2) -> Double.compare(e1.getPrixlocation(), e2.getPrixlocation()));
    
    // Mettre à jour la table avec la nouvelle liste triée
    tablespace.setItems(espacesSorted);
}

private void createPagination() {
    int size = EspaceList.size();
    pagination.setPageCount(size/10 + 1);
    pagination.setPageFactory(pageIndex -> {
        if (pageIndex >= pagination.getPageCount()) {
            return null;
        }
        int start = pageIndex * 10;
        int end = Math.min(start + 10, size);
        tablespace.setItems(FXCollections.observableArrayList(EspaceList.subList(start, end)));
        return tablespace;
    });
}




    private void afficherListeES() {
        EspaceService es = new EspaceService();
        
         try {
        // Récupérer la liste des espaces depuis le service
        ObservableList<Espace> espaces = FXCollections.observableArrayList(es.afficherListeES());
        
idspace.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Integer>(Integer.valueOf(cellData.getValue().getId())));
      
nomesp.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNom()));
       caract.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCaracteristique()));
       img.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getImage()));
       adresse.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getAdresse()));
        dispo.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDispo()));
        
        tarif.setCellValueFactory(cellData -> {
    Date dateValue = cellData.getValue().getTarifhoraire();
    return new SimpleObjectProperty<>(dateValue);
        });
        /* prixloc.setCellValueFactory(cellData -> {
        Double prixLocation = cellData.getValue().getPrixlocation();
        return new SimpleDoubleProperty(prixLocation);
        });*/
         prixloc.setCellValueFactory(cellData ->
                    new SimpleObjectProperty<Double>(Double.valueOf(cellData.getValue().getPrixlocation())));
        cat.setCellValueFactory(cellData ->
                new SimpleObjectProperty(cellData.getValue().getCategorieloc().getNom()));


       
      
        // Afficher les données dans le TableView
        tablespace.setItems(espaces);
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Afficher une alerte en cas d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Erreur lors de l'affichage de la liste des espaces.");
        alert.show();
    }
        
    }
       public void redirect(ActionEvent e){
        Pane newLoadedPane = null;
      
        
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterEspaceFXML.fxml"));
            newLoadedPane = loader.load();
            
            
            AjouterEspaceFXMLController a = loader.getController();
           
            
          

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        anchores.getChildren().clear();
        anchores.getChildren().add(newLoadedPane);
        
        
        

    }
        public void redirigermod(ActionEvent e){
        Pane newLoadedPane = null;
      
        
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierEspaceFXML.fxml"));
            newLoadedPane = loader.load();
            
            
            ModifierEspaceFXMLController m = loader.getController();
            m.setEspace(tablespace.getSelectionModel().getSelectedItem());
            
          

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        anchores.getChildren().clear();
        anchores.getChildren().add(newLoadedPane);
        
        
        

    }
        public void supprimerespace(ActionEvent e){
        EspaceService esser = new EspaceService();
        esser.supprimerEspace(tablespace.getSelectionModel().getSelectedItem().getId());
        afficherListeES();
    }
        
        @FXML
    private void QrCodeEspace(ActionEvent event) 
    {
        Espace esp = tablespace.getSelectionModel().getSelectedItem();
      

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String Information = "Nom : "+esp.getNom()+"\n"+"caracteristique : "+esp.getCaracteristique()+"adresse : "+esp.getAdresse()+"date  : "+esp.getTarifhoraire()+"prix : "+esp.getDispo()+"image :"+esp.getImage()+"categorie : "+esp.getCategorieloc();
        int width = 300;
        int height = 300;
        BufferedImage bufferedImage = null;
         try{
            BitMatrix byteMatrix = qrCodeWriter.encode(Information, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
            
            code_qr.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
            
        } catch (WriterException ex) {
        }
        
    }
    
}
