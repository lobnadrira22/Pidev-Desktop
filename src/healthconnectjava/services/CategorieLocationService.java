/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthconnectjava.services;
import healthconnectjava.entities.CategorieLocation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import healthconnectjava.utils.MyDB;


/**
 *
 * @author lobna
 */
public class CategorieLocationService implements ICategorieLocation<CategorieLocation>{
    
    public Connection conx;
    public Statement stm;

    public CategorieLocationService() {
        conx= MyDB.getInstance().getConx();
        
    }

    

    @Override
    public void ajoutCategorielocation(CategorieLocation c) throws SQLException {
      String req= "INSERT INTO `categorie_location`(`nom`, `description`) VALUES (?,?)";
      
      // Vérification des champs vides
    if (c.getNom().isEmpty() || c.getDescription().isEmpty()) {
        System.out.println("Erreur : Les champs ne doivent pas être vides.");
        return;
    }
      // Vérification de la longueur de la description
    if (c.getDescription().length() <= 7) {
        System.out.println("Erreur : La description doit avoir une longueur supérieure à 7 caractères.");
        return;
    }
       PreparedStatement ps=conx.prepareStatement(req);
       //1 c'est l'index de colonne nom
       ps.setString(1,c.getNom());
       //2 c'est l'index de colonne description
       ps.setString(2,c.getDescription());
       ps.executeUpdate();
       System.out.println("categorie de location ajoutée avec succès");
       
    }

    @Override
    public List<CategorieLocation> afficherListeC() throws SQLException {
        String req="SELECT * FROM `categorie_location`";
        stm=conx.createStatement();
        ResultSet rs=stm.executeQuery(req);
        List<CategorieLocation> categorielocations=new ArrayList<>();

        while (rs.next()){
            CategorieLocation c = new CategorieLocation(rs.getInt("id"), rs.getString("nom"), rs.getString("description"));
            categorielocations.add(c);
        }

        return categorielocations;
    }

    @Override
    public boolean modifiercategorieL(CategorieLocation c) {
        String req= "UPDATE `categorie_location`" +
                "SET nom=?,description=?" +
                "where id=?";
   
    // Vérification des champs vides
    if (c.getNom().isEmpty() || c.getDescription().isEmpty()) {
        System.out.println("Erreur : Les champs ne doivent pas être vides.");
        return false;
    }

    // Vérification de la longueur de la description
    if (c.getDescription().length() <= 7) {
        System.out.println("Erreur : La description doit avoir une longueur supérieure à 7 caractères.");
        return false;
    }

    try {
        PreparedStatement stm = conx.prepareStatement(req);
        stm.setString(1, c.getNom());
        stm.setString(2, c.getDescription());
        stm.setInt(3, c.getId());
        stm.executeUpdate();
    } catch (SQLException exception) {
        System.out.println(exception.getMessage());
        return false;
    }
    return true;
}

    @Override
    public CategorieLocation get(int id) {
  CategorieLocation categorieLocation=null;
        try {
            String req="SELECT * FROM `categorie_location` " +
                    "where id=?";
            PreparedStatement ps=conx.prepareStatement(req);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){

                CategorieLocation c = new CategorieLocation(rs.getInt("id"),
                        rs.getString("nom"), rs.getString("description"));
                categorieLocation=c;

            }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }

        return categorieLocation;
        
    }

    @Override
    public boolean supprimercategorieL(int id) {
             String req="DELETE FROM `categorie_location` " +
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
    
}
