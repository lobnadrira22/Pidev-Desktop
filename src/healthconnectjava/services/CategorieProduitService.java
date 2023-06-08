package healthconnectjava.services;



import healthconnectjava.entities.CategorieProduit;
import healthconnectjava.utils.MyDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieProduitService implements InterfaceServiceCategorie<CategorieProduit> {

    public Connection conx;
    public Statement stm;

    public CategorieProduitService(){
        conx= MyDB.getInstance().getConx();
    }


    @Override
    public void ajouter(CategorieProduit object) throws SQLException {
       String req="INSERT INTO `categorie_produit`(`nom`,`description`) VALUES (?,?)";
//          "'"+categorieProduit.getNom()+"','"+categorieProduit.getDescription()+"') ";


       PreparedStatement ps=conx.prepareStatement(req);
       ps.setString(1,object.getNom());
       ps.setString(2,object.getDescription());
       ps.executeUpdate();
       System.out.println("categorie ajoutée avec succès");
    }

    @Override
    public List<CategorieProduit> afficher() throws SQLException {

        String req="SELECT * FROM `categorie_produit`";
        stm=conx.createStatement();
        ResultSet rs=stm.executeQuery(req);
        List<CategorieProduit> categorieProduits=new ArrayList<>();

        while (rs.next()){
            CategorieProduit c = new CategorieProduit(rs.getInt("id"), rs.getString("nom"), rs.getString("description"));
            categorieProduits.add(c);
        }

        return categorieProduits;
    }

    @Override
    public boolean supprimer(int id) {
        String req="DELETE FROM `categorie_produit` " +
                "WHERE id=?";
    try{
    PreparedStatement stm=conx.prepareStatement(req);
    stm.setInt(1,id);
    stm.executeUpdate();
    }catch (SQLException exception){
        System.out.println(exception.getMessage());
    }

        return true;
    }

    @Override
    public boolean modifier(CategorieProduit object) {
        String req="UPDATE `categorie_produit`" +
                "SET nom=?,description=?" +
                "where id=?";
        try {
            PreparedStatement stm = conx.prepareStatement(req);
            stm.setString(1, object.getNom());
            stm.setString(2, object.getDescription());
            stm.setInt(3,object.getId());
            stm.executeUpdate();
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        return true;
    }

    @Override
    public CategorieProduit get(int id) {
        CategorieProduit categorieProduit=null;
        try {
            String req="SELECT * FROM `categorie_produit` " +
                    "where id=?";
            PreparedStatement ps=conx.prepareStatement(req);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){

                CategorieProduit c = new CategorieProduit(rs.getInt("id"),
                        rs.getString("nom"), rs.getString("description"));
                categorieProduit=c;

            }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }

        return categorieProduit;
    }


}
