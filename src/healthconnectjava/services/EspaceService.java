/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthconnectjava.services;
import healthconnectjava.entities.Espace;
import healthconnectjava.utils.MyDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.ZoneId;


/**
 *
 * @author lobna
 */
public class EspaceService implements IEspace<Espace> {
    public Connection conx;
    public Statement stm;
    CategorieLocationService categorielocService=new CategorieLocationService();
    
    public EspaceService(){
         conx= MyDB.getInstance().getConx();
    }
    
    
        @Override
public void ajoutEspace(Espace es) throws SQLException {
    try {
        // Vérification du prix de location
        if (es.getPrixlocation() <= 0) {
            System.out.println("Erreur : Le prix de location doit être supérieur à 0.");
            return;
        }
         // Vérification de l'adresse
        String adresse = es.getAdresse();
        if (adresse.contains("-") || adresse.contains("*")) {
            System.out.println("Erreur : L'adresse ne doit pas contenir de tirets ni de caractères spéciaux.");
            return;
        }
        // Vérification des champs vides
    if (es.getCaracteristique().isEmpty() || es.getNom().isEmpty() || es.getAdresse().isEmpty() || es.getDispo().isEmpty()) {
        System.out.println("Erreur : Les champs ne doivent pas être vides.");
        return;
    }

        String req = "INSERT INTO `espace`(`categorielocation_id`,`nom`,`caracteristique`,`image`,`adresse`,`dispo`, `tarifhoraire`, `prixlocation`)" +
                "values (?,?,?,?,?,?,?,?)";
        PreparedStatement statement = conx.prepareStatement(req);
        statement.setInt(1, es.getCategorieloc().getId());
        statement.setString(2, es.getNom());
        statement.setString(3, es.getCaracteristique());
        statement.setString(4, es.getImage());
        statement.setString(5, es.getAdresse());
        statement.setString(6, es.getDispo());
        statement.setDate(7, new java.sql.Date(es.getTarifhoraire().getTime()));
        statement.setDouble(8, es.getPrixlocation());

        // Vérification de la date
        LocalDate currentDate = LocalDate.now(); // Date actuelle sans heure
        LocalDate tarifHoraireDate = es.getTarifhoraire().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (tarifHoraireDate.isBefore(currentDate)) {
            System.out.println("Erreur : La date ne doit pas être dans le passé.");
            return;
        }

        statement.executeUpdate();
        System.out.println("espace ajouté avec succès");
    } catch (SQLException exception) {
        System.out.println("Erreur lors de l'ajout de l'espace : " + exception.getMessage());
    }
}


    @Override
    public List<Espace> afficherListeES() throws SQLException {
        String req="SELECT * FROM `espace`";
        stm=conx.createStatement();
        ResultSet rs=stm.executeQuery(req);
        List<Espace> espaces=new ArrayList<>();

        while (rs.next()){
            Espace es = new Espace(rs.getInt("id"), rs.getString("nom"), rs.getString("caracteristique"),rs.getString("image"),rs.getString("adresse"),rs.getString("dispo"),rs.getDate("tarifhoraire"),rs.getDouble("prixlocation"),categorielocService.get(rs.getInt("categorielocation_id")));
            espaces.add(es);
        }

        return espaces;
    }

   @Override
public boolean modifierEspace(Espace es) throws SQLException {
    String req = "UPDATE `espace` "
            + "SET categorielocation_id=?, nom=?, caracteristique=?, image=?, adresse=?, dispo=?, tarifhoraire=?, prixlocation=?" 
            + " WHERE id=?";
    try {
        // Vérifier si les champs requis sont vides
        if (es.getCaracteristique().isEmpty() || es.getNom().isEmpty() || es.getAdresse().isEmpty() || es.getDispo().isEmpty()) {
            System.out.println("Erreur : Les champs ne doivent pas être vides.");
            return false;
        }
        // Vérification du prix de location
        if (es.getPrixlocation() <= 0) {
            System.out.println("Erreur : Le prix de location doit être supérieur à 0.");
            return false;
        }
         // Vérification de l'adresse
        String adresse = es.getAdresse();
        if (adresse.contains("-") || adresse.contains("*")) {
            System.out.println("Erreur : L'adresse ne doit pas contenir de tirets ni de caractères spéciaux.");
            return false;
        }
       


        PreparedStatement statement = conx.prepareStatement(req);
        statement.setInt(1, es.getCategorieloc().getId());
        statement.setString(2, es.getNom());
        statement.setString(3, es.getCaracteristique());
        statement.setString(4, es.getImage());
        statement.setString(5, es.getAdresse());
        statement.setString(6, es.getDispo());
        statement.setDate(7, new java.sql.Date(es.getTarifhoraire().getTime()));
        statement.setDouble(8, es.getPrixlocation());
        statement.setInt(9, es.getId());
        statement.executeUpdate();

    } catch (SQLException exception) {
        System.out.println("Erreur lors de la modification de l'espace : " + exception.getMessage());
        return false;
    }
    return true;
}


    @Override
    public boolean supprimerEspace(int id) {
                    String req="DELETE FROM `espace` " +
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
    public Espace get(int id) throws SQLException {
         Espace esp=null;
        try {
            String req="SELECT * FROM `espace` " +
                    "where id=?";
            PreparedStatement ps=conx.prepareStatement(req);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){

                Espace space = new Espace(rs.getInt("id"), rs.getString("nom"), rs.getString("caracteristique"),rs.getString("image"),rs.getString("adresse"),rs.getString("dispo"),rs.getDate("tarifhoraire"),rs.getDouble("prixlocation"),categorielocService.get(rs.getInt("categorielocation_id")));
                esp=space;

            }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }

        return esp;
    }
    
    
    
}
