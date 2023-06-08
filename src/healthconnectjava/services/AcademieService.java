/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthconnectjava.services;

import healthconnectjava.entities.Academie;
import healthconnectjava.utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author LENOVO
 */
public class AcademieService implements IAcademie<Academie>{
public Connection conx;
public Statement stm;
   

public AcademieService(){
conx=MyDB.getInstance().getConx();}

    @Override
    public void ajoutAcademie(Academie a) throws SQLException {
      String req="INSERT INTO `Academie`(`nom`, `adresse`, `numtel`, `sportpropose`) VALUES ('"+a.getNom()+"','"+a.getAdresse()+"','"+a.getNumtel()+"','"+a.getSportpropose()+"')";
      stm=conx.createStatement();
      stm.executeUpdate(req);
      System.out.println("Academie ajoutée");
    }

   @Override
public void ajoutAcademiee(Academie aa) throws SQLException {
    String req = "INSERT INTO `Academie`(`nom`, `adresse`, `numtel`, `sportpropose`) VALUES (?,?,?,?)";
    PreparedStatement ps = conx.prepareStatement(req);

    // Vérifier si les champs ne sont pas nuls
    String nom = aa.getNom();
    String adresse = aa.getAdresse();
    String numtel = aa.getNumtel();
    String sportpropose = aa.getSportpropose();
    if (!nom.isEmpty() && !adresse.isEmpty() && !numtel.isEmpty() && !sportpropose.isEmpty()) {
        // Vérifier si le numéro de téléphone contient 8 chiffres
        if (numtel.matches("\\d{8}")) {
            ps.setString(1, nom);
            ps.setString(2, adresse);
            ps.setString(3, numtel);
            ps.setString(4, sportpropose);
            ps.executeUpdate();
            System.out.println("Academie ajoutée");
        } else {
            // Afficher un message d'erreur si le numéro de téléphone ne contient pas 8 chiffres
            System.out.println("Erreur : Le numéro de téléphone doit être composé de 8 chiffres.");
        }
    } else {
        // Afficher un message d'erreur si un champ est vide
        System.out.println("Erreur : Tous les champs doivent être remplis.");
    }
}


    @Override
    public List<Academie> afficherListeA() throws SQLException {
        String req="SELECT * FROM `Academie`";
      stm=conx.createStatement();
      ResultSet rs=stm.executeQuery(req);
      List<Academie> academies=new ArrayList<>();
      while(rs.next()){
          Academie a=new Academie(rs.getInt("id"), rs.getString("nom"), rs.getString("adresse"), rs.getString("numtel"), rs.getString("sportpropose"));
          academies.add(a);
              
      }
      return academies;
    }
    
    @Override
    public Academie getAcademiee(int id)throws SQLException {
           Academie Academie=null;
        try {
            String req="SELECT * FROM `Academie` " +
                    "where id=?";
            PreparedStatement ps=conx.prepareStatement(req);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){

                Academie a = new Academie(rs.getInt("id"),
                        rs.getString("nom"), rs.getString("adresse"), rs.getString("numtel"), rs.getString("sportpropose"));
                Academie=a;

            }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }

        return Academie;
    }
 
    
 @Override
public boolean modifieracademie(Academie object) throws SQLException {
    String req = "UPDATE `Academie` SET nom=?, adresse=?, numtel=?, sportpropose=? WHERE id=?";
    try {
        PreparedStatement stm = conx.prepareStatement(req);
        
        // Vérifier si les champs ne sont pas nuls
        String nom = object.getNom();
        String adresse = object.getAdresse();
        String numtel = object.getNumtel();
        String sportpropose = object.getSportpropose();
        int id = object.getId();
        if (!nom.isEmpty() && !adresse.isEmpty() && !numtel.isEmpty() && !sportpropose.isEmpty()) {
            // Vérifier si le numéro de téléphone contient 8 chiffres
            if (numtel.matches("\\d{8}")) {
                stm.setString(1, nom);
                stm.setString(2, adresse);
                stm.setString(3, numtel);
                stm.setString(4, sportpropose);
                stm.setInt(5, id);
                stm.executeUpdate();
                return true;
            } else {
                // Afficher un message d'erreur si le numéro de téléphone ne contient pas 8 chiffres
                System.out.println("Erreur : Le numéro de téléphone doit être composé de 8 chiffres.");
            }
        } else {
            // Afficher un message d'erreur si un champ est vide
            System.out.println("Erreur : Tous les champs doivent être remplis.");
        }
    } catch (SQLException exception) {
        System.out.println(exception.getMessage());
    }
    return false;
}

    
    @Override
    public boolean supprimeracademie(int id) {
        String req="DELETE FROM `Academie` " +
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
