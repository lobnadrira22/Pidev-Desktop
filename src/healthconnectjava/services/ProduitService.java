package healthconnectjava.services;



import healthconnectjava.entities.Produit;
import healthconnectjava.utils.MyDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitService implements InterfaceServiceProduit<Produit> {
    public Connection conx;
    public Statement stm;
    CategorieProduitService categorieProduitService=new CategorieProduitService();

    public ProduitService(){
        conx= MyDB.getInstance().getConx();
    }

    @Override
    public void ajouter(Produit object) {
        try {
            String req="INSERT INTO `produit`(`category_id`,`nom`,`description`,`prix`,`image`)" +
                    "values (?,?,?,?,?)";
            PreparedStatement statement=conx.prepareStatement(req);
            statement.setInt(1,object.getCategory().getId());
            statement.setString(2,object.getNom());
            statement.setString(3,object.getDescription());
            statement.setFloat(4,object.getPrix());
            statement.setString(5,object.getImage());
            statement.executeUpdate();
            System.out.println("Produit ajouté avec succée");
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public List<Produit> afficher() {
        List<Produit> produits=new ArrayList<>();
        try {
            String req="SELECT * FROM `produit`";
            stm=conx.createStatement();
            ResultSet rs=stm.executeQuery(req);

//            while (rs.next()){
//                Produit p = new Produit(rs.getInt(1),rs.getString(2),
//                        rs.getString(3),rs.getString(4)
//                        ,rs.getFloat(5),categorieProduitService.get(rs.getInt(6)));
//                produits.add(p);
//            }

            while (rs.next()){
                float prix = Float.parseFloat(rs.getString("prix"));
                Produit p = new Produit(rs.getInt("id"),rs.getString("nom"),
                        rs.getString("description"),rs.getString("image")
                        ,prix,categorieProduitService.get(rs.getInt("category_id")));
                produits.add(p);
            }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return produits;
    }

    @Override
    public boolean supprimer(int id) {

        String req="DELETE FROM `produit` " +
                "WHERE id=?";

        try {
            PreparedStatement statement=conx.prepareStatement(req);
            statement.setInt(1,id);
            statement.executeUpdate();
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return true;
    }

    @Override
    public boolean modifier(Produit object) {
        String req="UPDATE produit " +
                "SET category_id =?,nom=?,description=?," +
                "prix=?,image=?" +
                "where id=?";
        try {
            PreparedStatement stm = conx.prepareStatement(req);
            stm.setInt(1,object.getCategory().getId());
            stm.setString(2, object.getNom());
            stm.setString(3, object.getDescription());
            stm.setFloat(4,object.getPrix());
            stm.setString(5,object.getImage());
            stm.setInt(6,object.getId());
            stm.executeUpdate();
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return true;
    }
}
