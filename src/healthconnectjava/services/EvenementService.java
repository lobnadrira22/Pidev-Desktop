/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package healthconnectjava.services;

import healthconnectjava.entities.Evenement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import healthconnectjava.utils.MyDB;

/**
 *
 * @author User
 */
public class EvenementService {
       public Connection conx;
    public Statement stm;
    public EvenementService(){
        conx =MyDB.getInstance().getConx();
    }
    
 public List<Evenement> getListeEvenements() throws SQLException {
    List<Evenement> evenements = afficherListeEvenement();
    return evenements;
}

    
    public List<Evenement> afficherListeEvenement() throws SQLException{
        String req = "SELECT * FROM `evenement`";
        stm = conx.createStatement();
        ResultSet rs = stm.executeQuery(req);
       ///// System.out.println("rs: "+rs.toString());///affichage du contenu de rs qui contient le resultat de la requete
        List<Evenement> evenements = new ArrayList<>(); 
        while(rs.next()){
            //////au niveau de l'affichage l'index commence par 1
            //Evenement e = new Evenement(rs.getInt("id"),rs.getString(2),rs.getString("type"),rs.getString("adresse"),rs.getString("description"),rs.getString("type"),rs.getString("image"),rs.getDate("date"));
            
            Evenement e = new Evenement(rs.getInt("id"),rs.getString("nom"),rs.getString("type"),rs.getString("adresse"),rs.getString("description"),rs.getString("image"),rs.getDate("date"));
            evenements.add(e);
        }
        return evenements;
    }
    
    
        public void ajoutEvenement(Evenement e) throws SQLException{
     
    String req="INSERT INTO `evenement`(`nom`, `type`, `date`, `adresse`, `image`, `description`) VALUES ('"+e.getNom()+"','"+e.getType()+"','"+e.getDate()+"','"+e.getAdresse()+"','"+e.getImage().replaceAll("\\\\", "/")+"','"+e.getDescription()+"')";

    stm=conx.createStatement();
    stm.executeUpdate(req);
    System.out.println("evenement ajoutee");
}

        public void supprimerEvenement(int id) throws SQLException {
        String req = "DELETE FROM `evenement` WHERE `id`=" + id;
        stm = conx.createStatement();
        int result = stm.executeUpdate(req);
        if (result == 0) {
            System.out.println("Aucun événement n'a été supprimé.");
        } else {
            System.out.println("L'événement a été supprimé avec succès.");
        }
        }
        
            public void modifierEvenement(Evenement e) throws SQLException {
                      

            String req = "UPDATE `evenement` SET `nom`='" + e.getNom() + "',`type`='" + e.getType() + "',`date`='" + e.getDate() + "',`adresse`='" + e.getAdresse() + "',`image`='"+e.getImage().replaceAll("\\\\", "/")+"',`description`='" + e.getDescription() + "' WHERE `id`=" + e.getId();

            stm = conx.createStatement();
            int result = stm.executeUpdate(req);
            if (result == 0) {
                System.out.println("Aucun événement n'a été modifié.");
            } else {
                System.out.println("L'événement a été modifié avec succès.");
            }
        }
            
            public List<String> getNomsEvenements() throws SQLException {
        String req = "SELECT nom FROM evenement";
        Statement stm = conx.createStatement();
        ResultSet rs = stm.executeQuery(req);

        List<String> noms = new ArrayList<>();
        while (rs.next()) {
            noms.add(rs.getString("nom"));
        }
        return noms;
    }
       
       public int getIdEvenementByNom(String nomEvenement) throws SQLException {
    int idEvenement = -1;
    String req = "SELECT id FROM evenement WHERE nom=?";
    PreparedStatement ps = conx.prepareStatement(req);
    ps.setString(1, nomEvenement);
    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
        idEvenement = rs.getInt("id");
    }
    return idEvenement;
}     
      
          
    }

