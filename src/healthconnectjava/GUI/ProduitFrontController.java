package healthconnectjava.GUI;


import healthconnectjava.GUI.AfficheListeProduitController;
import healthconnectjava.GUI.cardProductController;
import healthconnectjava.entities.Produit;
import healthconnectjava.services.CategorieProduitService;
import healthconnectjava.utils.MyDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProduitFrontController implements Initializable {
    @FXML
    private TableColumn<Produit,Float> menuColProduitPrix;
    @FXML
    private TableColumn<Produit,String> menuColProduitName;


    @FXML
    private Label totalPrix;

    @FXML
    private TableColumn<Produit,Integer> menuColProduitQuantite;


    @FXML
    private Button menu_removeBtn;

    @FXML
    private Pane menuForm;
    @FXML
    private Pane pane;

    @FXML
    private GridPane menuGridPane;

    @FXML
    private ScrollPane menuScrollPane;

    @FXML
    private TableView<Produit> menuTableView;

    @FXML
    private Label menuTot;

    private ObservableList<Produit> cardListData= FXCollections.observableArrayList();
    public Connection conx;
    public Statement stm;
    private double totalP;
    private int getid;
    private Alert alert;

    CategorieProduitService categorieProduitService =new CategorieProduitService();
    public ProduitFrontController(){
        conx= MyDB.getInstance().getConx();
    }

    public ObservableList<Produit>menuGetData(){
        ObservableList<Produit>listData=FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM produit";
            stm=conx.createStatement();
            ResultSet rs=stm.executeQuery(sql);
            while (rs.next()){
                float prix = Float.parseFloat(rs.getString("prix"));
                Produit p = new Produit(rs.getInt("id"),rs.getString("nom"),
                        rs.getString("description"),rs.getString("image")
                        ,prix,categorieProduitService.get(rs.getInt("category_id")));

                listData.add(p);

            }
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return listData;
    }
    public void menuDisplayCard(){
        cardListData.clear();
        cardListData.addAll(menuGetData());
        int row=0;
        int column=0;

        menuGridPane.getRowConstraints().clear();
        menuGridPane.getColumnConstraints().clear();

        for(int q=0;q<cardListData.size();q++){
            try {
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(getClass().getResource("CardProduit.fxml"));
                AnchorPane pane=loader.load();
                cardProductController cardC=loader.getController();
                cardC.setData(cardListData.get(q));
                if(column==3){
                    column=0;
                    row+=1;
                }
                menuGridPane.add(pane,column++,row);
               // GridPane.setMargin(pane,new Insets(10));
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }

    public void menuDisplayTotal(){
        try{
            String total="SELECT sum(prix) from commande ";
            stm=conx.createStatement();
            ResultSet rs =stm.executeQuery(total);
            if (rs.next()){
                 totalP=rs.getDouble("SUM(prix)");

            }
            totalPrix.setText("$"+totalP);

        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }

    }
    public void menuselectedOrder(){
        //renvoie l'élément actuellement sélectionné dans le modèle de sélection. Ici,
        Produit produit=menuTableView.getSelectionModel().getSelectedItem();
        int num=menuTableView.getSelectionModel().getSelectedIndex();
        if((num -1)<-1)return;
        getid=produit.getId();
    }
    public void menuRemoveBtn(){
        if(getid==0){
            alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please select the item you want to remove");
            alert.showAndWait();
        }else {
            String delete="DELETE FROM Commande WHERE ID ="+getid;
            try {
                alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Confirmation message");
                alert.setHeaderText(null);
                alert.setContentText("Are you want sure to delete this product");
                Optional<ButtonType> option=alert.showAndWait();
                if (option.get().equals(ButtonType.OK)){
                    PreparedStatement statement=conx.prepareStatement(delete);
                    statement.executeUpdate();
                }


            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }
    public ObservableList<Produit> menuGetOrder(){
        ObservableList<Produit> listData=FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM commande";
            stm=conx.createStatement();
            ResultSet rs=stm.executeQuery(sql);

            while (rs.next()){
                float prix = Float.parseFloat(rs.getString("prix"));
                Produit p = new Produit(rs.getInt("id"),rs.getString("nom"),
                        prix,rs.getInt("quantite_commande"),rs.getDate("date_commande"));

                listData.add(p);
            }

        }catch (Exception exception){
            exception.printStackTrace();
        }

        return  listData;
    }


    private ObservableList<Produit> menuOrderListData ;
    public void menuShowOrderData(){
        menuOrderListData =menuGetOrder();
        menuColProduitName.setCellValueFactory(new PropertyValueFactory<>("nom"));
        menuColProduitQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite_commande"));
        menuColProduitPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));

        menuTableView.setItems(menuOrderListData );



    }

    public void back(){
        Pane newLoadedPane = null;
        try {


            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheListeProduit.fxml"));
            newLoadedPane = loader.load();
            AfficheListeProduitController c = loader.getController();

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        pane.getChildren().clear();
        pane.getChildren().add(newLoadedPane);
    }


    public void paiement(){

        Pane newLoadedPane = null;
        try {


            FXMLLoader loader = new FXMLLoader(getClass().getResource("Payment.fxml"));
            newLoadedPane = loader.load();
            PaymentController c = loader.getController();

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        pane.getChildren().clear();
        pane.getChildren().add(newLoadedPane);
    } 

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuDisplayCard();
        menuGetOrder();
        menuShowOrderData();
        menuDisplayTotal();
    }
}
