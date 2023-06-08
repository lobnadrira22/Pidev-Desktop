/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package healthconnectjava.services;

import healthconnectjava.utils.MyDB;
import java.sql.Connection;
import java.sql.SQLException;
import healthconnectjava.entities.Coach;
import java.sql.Statement;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.mindrot.jbcrypt.BCrypt;
import java.util.ArrayList;

/**
 *
 * @author wiki
 */
public class ServiceCoach implements IServiceCoach<Coach> {
    
    public Connection conx;
    public ServiceCoach() {
        conx = MyDB.getInstance().getConx();
    }

    @Override
    public void registerCoach(Coach t) throws SQLException {
        try {

            String checkQuery = "SELECT COUNT(*) FROM user WHERE email = ?";
            PreparedStatement pstt = conx.prepareStatement(checkQuery);
            pstt.setString(2, t.getEmail());
            ResultSet checkResult = pstt.executeQuery();
            checkResult.next();
            int count = checkResult.getInt(1);
            if (count > 0) {
                System.out.println("Le Coach existe déjà dans la base de données");
            }

            String req = "INSERT INTO user(email, roles, password, nom, prenom, date_naissance, type, is_verified) VALUES (?,'[\"ROLE_COACH\"]',?,?,?,?,'coach','false')"
                    +"INSERT INTO coach(id, etat_compte) VALUES (LAST_INSERT_ID(),'true')";
            PreparedStatement pst = conx.prepareStatement(req);
            
            pst.setString(2, t.getEmail());
            String hashedPassword = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt());
            pst.setString(4, hashedPassword);
            pst.setString(5, t.getNom());
            pst.setString(6, t.getPrenom());
            pst.setString(7, t.getDateNaissance());

            pst.executeUpdate();
            System.out.println("Coach ajouté");

        } catch (SQLException e) {
            System.err.print(e.getMessage());
        }
    }

    @Override
    public void update(Coach t) throws SQLException {
        try {
            String req = "UPDATE user JOIN coach ON user.id = coach.id SET user.email = ?, user.password = ?, user.nom = ?, user.prenom = ?, user.date_naissance = ?, user.telephone = ? WHERE user.id = ?";
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setString(2, t.getEmail());
            String hashedPassword = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt());
            pst.setString(4, hashedPassword);
            pst.setString(5, t.getNom());
            pst.setString(6, t.getPrenom());
            pst.setString(7, t.getDateNaissance());
            pst.setString(8, t.getTelephone());

            pst.setInt(1, t.getId());

            pst.executeUpdate();
            System.out.println("Coach modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(Coach t) throws SQLException {
        try {
            String req = "DELETE FROM user where id=?";
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Coach supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Coach> selectAllForAdmin() throws SQLException {
        
        List<Coach> temp = new  ArrayList<>();
        
        String req = "SELECT * FROM user JOIN coach ON user.id = coach.id WHERE type = 'coach'";
        
        Statement st = conx.createStatement();
        
        ResultSet rs = st.executeQuery(req);
        
        while(rs.next()){
            Coach co = new Coach();
            
            co.setNom(rs.getString("nom"));
            co.setPrenom(rs.getString("prenom"));
            co.setEmail(rs.getString("email"));
            co.setTelephone(rs.getString("telephone"));
            co.setIsVerified(rs.getBoolean("is_verified"));
            co.setEtatCompte(rs.getBoolean("etat_compte"));
            co.setDiplome(rs.getString("diplome"));
            co.setImage(rs.getString("image"));
            
            temp.add(co);
        }

        return temp;
    }

    @Override
    public List<Coach> selectAllForClient() throws SQLException {
        List<Coach> temp = new  ArrayList<>();
        
        String req = "SELECT * FROM user JOIN coach ON user.id = coach.id WHERE etat_compte = 1 AND type = 'coach'";
        
        Statement st = conx.createStatement();
        
        ResultSet rs = st.executeQuery(req);
        
        while(rs.next()){
            Coach co = new Coach();
            
            co.setNom(rs.getString("nom"));
            co.setPrenom(rs.getString("prenom"));
            co.setEmail(rs.getString("email"));
            co.setTelephone(rs.getString("telephone"));
            co.setImage(rs.getString("image"));
            
            temp.add(co);
        }

        return temp;
    }

    @Override
    public void activer(Coach t) throws SQLException {
        try {
            String req = "UPDATE user JOIN coach ON user.id = coach.id SET user.is_verified = true WHERE user.id = ?";
            PreparedStatement pst = conx.prepareStatement(req);

            pst.setInt(1, t.getId());

            pst.executeUpdate();
            System.out.println("Coach activé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void desactiver(Coach t) throws SQLException {
        try {
            String req = "UPDATE user JOIN coach ON user.id = coach.id SET user.is_verified = false WHERE user.id = ?";
            PreparedStatement pst = conx.prepareStatement(req);

            pst.setInt(1, t.getId());

            pst.executeUpdate();
            System.out.println("Coach desactivé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void confirmer(Coach t) throws SQLException {
        try {
            String req = "UPDATE user JOIN coach ON user.id = coach.id SET coach.etat_compte = true WHERE user.id = ?";
            PreparedStatement pst = conx.prepareStatement(req);

            pst.setInt(1, t.getId());

            pst.executeUpdate();
            System.out.println("Coach confirmé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
