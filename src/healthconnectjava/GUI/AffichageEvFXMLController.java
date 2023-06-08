/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package healthconnectjava.GUI;

import com.itextpdf.text.BaseColor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import healthconnectjava.entities.Evenement;
import healthconnectjava.services.EvenementService;
import java.awt.event.MouseEvent;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;
import javafx.scene.control.Dialog;
import javafx.scene.layout.BorderPane;
import jfxtras.scene.control.CalendarPicker;
import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.Agenda.AppointmentImplLocal;
//import sun.util.calendar.CalendarDate;
import java.time.ZoneId;
import java.util.concurrent.ThreadLocalRandom;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import jfxtras.internal.scene.control.skin.agenda.AgendaDaySkin;
import jfxtras.internal.scene.control.skin.agenda.AgendaMonthSkin;
import jfxtras.internal.scene.control.skin.agenda.AgendaWeekSkin;
import jfxtras.scene.control.agenda.Agenda.AppointmentGroup;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AffichageEvFXMLController implements Initializable {
    @FXML
    private TableView<Evenement> listeE;
    private TableColumn<Evenement, Integer> idCol;
    private TableColumn<Evenement, String> nomCol;
    private TableColumn<Evenement, String> typeCol;
    private TableColumn<Evenement, String> adresseCol;
    private TableColumn<Evenement, String> descriptionCol;
    private TableColumn<Evenement, String> imageCol;
    private TableColumn<Evenement, Date> dateCol;
    private TableColumn<Evenement, Void> supprimerCol;
    private TableColumn<Evenement, Void> modifierCol;
    private Button AjoutEv;
    @FXML
    private Button miseajour;
    @FXML
    private Button boutonTri;
    @FXML
    private Button boutonTri1;
   
    
    private final int ITEMS_PER_PAGE = 2;
    private ObservableList<Evenement> evenements;
    @FXML
    private Button gestionClient;
    @FXML
    private Button gestionCoach;
    /* @FXML
    private Button logoutB;
    @FXML
    private Button refreshListClient;*/
    @FXML
    private Text NomPrenomUSerr;
    @FXML
    private Text emailUserr;
    @FXML
    private Button gestionEvent;
    @FXML
    private Button boutonTri13;
    @FXML
    private Button calendrierButton;
    @FXML
    private Button gestionExercice;
    @FXML
    private Button AjoutB;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idCol=new TableColumn<>("Id");
        nomCol = new TableColumn<>("Nom");
        typeCol = new TableColumn<>("Type");
        adresseCol = new TableColumn<>("Adresse");
        descriptionCol = new TableColumn<>("Description");
        dateCol = new TableColumn<>("Date");
        supprimerCol = new TableColumn<>("");
        modifierCol = new TableColumn<>("");

        
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        adresseCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        //////////Ajout du bouton supprimer//////////////
        supprimerCol.setCellFactory(new Callback<TableColumn<Evenement, Void>, TableCell<Evenement, Void>>() {
            @Override
            public TableCell<Evenement, Void> call(TableColumn<Evenement, Void> param) {
                final TableCell<Evenement, Void> cell = new TableCell<Evenement, Void>() {
                    private final Button btn = new Button("Supprimer");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Evenement evenement = getTableView().getItems().get(getIndex());
                            EvenementService evService = new EvenementService();
                            try {
                                evService.supprimerEvenement(evenement.getId());
                                // Afficher une fenêtre de confirmation
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation de suppression");
                                alert.setHeaderText(null);
                                alert.setContentText("Voulez vous confirmer la suppression?");
                                alert.showAndWait();
                            } catch (SQLException ex) {
                                Logger.getLogger(AffichageEvFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            listeE.getItems().remove(evenement);
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        });
        
        
        
        modifierCol.setCellFactory(new Callback<TableColumn<Evenement, Void>, TableCell<Evenement, Void>>() {
        @Override
        public TableCell<Evenement, Void> call(TableColumn<Evenement, Void> param) {
            final TableCell<Evenement, Void> cell = new TableCell<Evenement, Void>() {
                private final Button btn = new Button("Modifier");

                {
                    btn.setOnAction((ActionEvent event) -> {
                        Evenement evenement = getTableView().getItems().get(getIndex());
                       
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierEventFXML.fxml"));
                        Parent root;
                        try {
                            root = loader.load();
                            ModifierEventFXMLController controller = loader.getController();
                            controller.setEvenement(evenement);
                            Stage stage = new Stage();
                            stage.setScene(new Scene(root));
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btn);
                    }
                }
            };
            return cell;
        }
    });
        
        
        
        
        listeE.getColumns().addAll(idCol,nomCol, typeCol, adresseCol,descriptionCol, dateCol, supprimerCol,modifierCol);
        
        
        EvenementService evService = new EvenementService();
        List<Evenement> events=new ArrayList<>();
        try {
            events = evService.afficherListeEvenement();

        } catch (SQLException ex) {
            Logger.getLogger(AffichageEvFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
     listeE.getItems().addAll(events);
      /*  evenements = FXCollections.observableArrayList(events);
        pagination = new Pagination((int) Math.ceil((double) events.size() / ITEMS_PER_PAGE), 0);
        pagination.setPageFactory(this::createPage);
        intAffichage.getChildren().add(pagination);*/
        


    }    

    /* private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * ITEMS_PER_PAGE;
        int toIndex = Math.min(fromIndex + ITEMS_PER_PAGE, evenements.size());
        listeE.setItems(FXCollections.observableArrayList(evenements.subList(fromIndex, toIndex)));
        return listeE;
    }*/
    @FXML
    private void ajouterEvenement(ActionEvent event) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("EvenementFXML.fxml"));
    Parent root;
    try {
    root = loader.load();
    Stage stage = new Stage();
    stage.setScene(new Scene(root));
    stage.show();
    } catch (IOException e) {
    e.printStackTrace();
    }
    }
    
    
    ////////////////////////////code du bouton refresh
    @FXML
    private void miseajour(ActionEvent event) {
         listeE.getItems().clear(); // Efface les anciens éléments de la liste
        EvenementService evService = new EvenementService();
        List<Evenement> events = new ArrayList<>();
        try {
            events = evService.afficherListeEvenement();
        } catch (SQLException ex) {
            Logger.getLogger(AffichageEvFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listeE.getItems().addAll(events); // Ajoute les nouveaux éléments de la liste
    }

    private void ajouterExercice(ActionEvent event) {
    
       /* try {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageExFXML.fxml"));
            Parent root = loader.load();
            AjoutB2.getScene().setRoot(root);
             } catch (IOException ex) {
                 System.err.println(ex.getMessage());
        }*/
       FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageExFXML.fxml"));
        Parent root;
            try {
            root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void telechargerListeEvent(ActionEvent event) {
    try {
        // Créer un nouveau document PDF
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();

        // Créer un écrivain de PDF pour écrire le document
        PdfWriter.getInstance(document, new FileOutputStream("evenements.pdf"));

        // Ouvrir le document pour écrire
        document.open();

        // Ajouter un en-tête au document
        Paragraph header = new Paragraph();
        header.setAlignment(Element.ALIGN_CENTER);
        header.setSpacingAfter(20);
        header.setFont(new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD, new BaseColor(15, 35, 80)));
        header.add("Liste des événements");
        document.add(header);

        // Ajouter un tableau pour afficher les données des événements
        PdfPTable table = new PdfPTable(4); // 4 colonnes pour id, nom, adresse et date
        table.setWidthPercentage(100);
        table.setSpacingBefore(20);
        table.setSpacingAfter(20);

        // Définir les en-têtes de colonne au tableau
        PdfPCell cellId = new PdfPCell(new Phrase("ID"));
        cellId.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cellId);

        PdfPCell cellNom = new PdfPCell(new Phrase("Nom de l'événement"));
        cellNom.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cellNom);

        PdfPCell cellAdresse = new PdfPCell(new Phrase("Adresse"));
        cellAdresse.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cellAdresse);

        PdfPCell cellDate = new PdfPCell(new Phrase("Date"));
        cellDate.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cellDate);

        // Récupérer la liste des événements depuis la base de données
        EvenementService evenementService = new EvenementService();
        List<Evenement> evenements = evenementService.afficherListeEvenement();

        // Ajouter les données des événements au tableau
        for (Evenement e : evenements) {
            PdfPCell cellIdData = new PdfPCell(new Phrase(String.valueOf(e.getId())));
            cellIdData.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cellIdData);

            PdfPCell cellNomData = new PdfPCell(new Phrase(e.getNom()));
            cellNomData.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cellNomData);

            PdfPCell cellAdresseData = new PdfPCell(new Phrase(e.getAdresse()));
            cellAdresseData.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cellAdresseData);

            PdfPCell cellDateData = new PdfPCell(new Phrase(e.getDate().toString()));
            cellDateData.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cellDateData);
        }

        // Ajouter le tableau au document
        document.add(table);

        // Fermer le document
        document.close();

        // Afficher un message de confirmation
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Fichier PDF créé");
        alert.setContentText("Le fichier evenements.pdf a été créé avec succès.");
        alert.showAndWait();

    } catch (Exception e) {
        // Afficher un message d'erreur si quelque chose ne va pas
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Impossible de créer le fichier PDF");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }
}

    @FXML
    private void TrierEvenements(ActionEvent event) {
     
 // Récupérer la liste des événements à trier
    ObservableList<Evenement> listeEvenements = listeE.getItems();

    // Définir le comparateur pour trier les événements par date décroissante
    Comparator<Evenement> comparateur = new Comparator<Evenement>() {
        @Override
        public int compare(Evenement e1, Evenement e2) {
            return e1.getDate().compareTo(e2.getDate());
        }
    };

    // Trier la liste des événements
    Collections.sort(listeEvenements, comparateur);

    // Rafraîchir la table view pour afficher la liste triée
    listeE.refresh();
    
}

  
    @FXML
    private void affichageCalendrier(ActionEvent event) {
    // Récupération de la liste des événements depuis le contrôleur
    EvenementService evenementService = new EvenementService();
    List<Evenement> evenements = new ArrayList<>();
    try {
        evenements = evenementService.getListeEvenements();
    } catch (SQLException ex) {
        Logger.getLogger(AffichageEvFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        return;
    }

    // Création d'un agenda
    Agenda agenda = new Agenda();

    // Ajout des événements à l'agenda
    // Ajout des événements à l'agenda
for (Evenement evenement : evenements) {
    Date date = (Date) evenement.getDate();
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH) + 1;
    int day = cal.get(Calendar.DAY_OF_MONTH);

    // Heure de début aléatoire sans minutes
    int heureDebut = ThreadLocalRandom.current().nextInt(9, 17);
    LocalDateTime debut = LocalDateTime.of(year, month, day, heureDebut, 0);

    // Heure de fin = heure de début + 2 heures
    LocalDateTime fin = debut.plusHours(2);

    AppointmentImplLocal appointment = new AppointmentImplLocal()
            .withStartLocalDateTime(debut)
            .withEndLocalDateTime(fin)
            .withSummary(evenement.getNom());
    agenda.appointments().add(appointment);
}


    // Configuration de l'agenda
    agenda.setAllowDragging(false);
    agenda.setAllowResize(false);
    agenda.setDisplayedLocalDateTime(LocalDateTime.now());
    agenda.setPrefHeight(600);
    agenda.setPrefWidth(800);
    agenda.setSkin(new AgendaWeekSkin(agenda));

    // Création d'une nouvelle fenêtre pour afficher l'agenda
    Stage stage = new Stage();
    Scene scene = new Scene(new BorderPane(agenda), 800, 600);
    stage.setTitle("Calendrier des événements");
    stage.setScene(scene);
    stage.show();
}

    
    @FXML
private void TrierEvenementsAdresse(ActionEvent event) {
     
    // Récupérer la liste des événements à trier
    ObservableList<Evenement> listeEvenements = listeE.getItems();

    // Définir le comparateur pour trier les événements par adresse
    Comparator<Evenement> comparateur = new Comparator<Evenement>() {
        @Override
        public int compare(Evenement e1, Evenement e2) {
            return e1.getAdresse().compareTo(e2.getAdresse());
        }
    };

    // Trier la liste des événements
    Collections.sort(listeEvenements, comparateur);

    // Rafraîchir la table view pour afficher la liste triée
    listeE.refresh();
    
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

/*@FXML
    private void logout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
            Parent root = loader.load();
            logoutB.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
*/

   
    
  
}