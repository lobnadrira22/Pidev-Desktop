/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package healthconnectjava.services;

import healthconnectjava.utils.MyDB;
import java.sql.Connection;
import java.sql.SQLException;
import healthconnectjava.entities.Client;
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
public class ServiceClient implements IServiceClient<Client> {
    
    public Connection conx;
    public ServiceClient() {
        conx = MyDB.getInstance().getConx();
    }

    @Override
    public void registerClient(Client t) throws SQLException {
        try {

            String checkQuery = "SELECT COUNT(*) FROM user WHERE email = ?";
            PreparedStatement pstt = conx.prepareStatement(checkQuery);
            pstt.setString(2, t.getEmail());
            ResultSet checkResult = pstt.executeQuery();
            checkResult.next();
            int count = checkResult.getInt(1);
            if (count > 0) {
                System.out.println("Le Client existe déjà dans la base de données");
            }

            String req = "INSERT INTO user(email, roles, password, nom, prenom, date_naissance, type, is_verified) VALUES (?,'[\"ROLE_CLIENT\"]',?,?,?,?,'client','false')"
                    +"INSERT INTO client(id, genre) VALUES (LAST_INSERT_ID(),?)";
            PreparedStatement pst = conx.prepareStatement(req);
            
            pst.setString(2, t.getEmail());
            String hashedPassword = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt());
            pst.setString(4, hashedPassword);
            pst.setString(5, t.getNom());
            pst.setString(6, t.getPrenom());
            pst.setString(7, t.getDateNaissance());
            
            pst.setString(4, t.getGenre());
            
            pst.executeUpdate();
            System.out.println("Client ajouté");

        } catch (SQLException e) {
            System.err.print(e.getMessage());
        }
    }

    @Override
    public void update(Client t) throws SQLException {
        try {
            String req = "UPDATE user JOIN client ON user.id = client.id SET user.email = ?, user.password = ?, user.nom = ?, user.prenom = ?, user.date_naissance = ?, user.telephone = ?, client.adresse = ?, client.ville = ?, client.genre = ?, client.taille = ?, client.poids = ? WHERE user.id = ?";
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setString(2, t.getEmail());
            String hashedPassword = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt());
            pst.setString(4, hashedPassword);
            pst.setString(5, t.getNom());
            pst.setString(6, t.getPrenom());
            pst.setString(7, t.getDateNaissance());
            pst.setString(8, t.getTelephone());
            
            pst.setString(2, t.getAdresse());
            pst.setString(3, t.getVille());
            pst.setString(4, t.getGenre());
            pst.setString(5, t.getTaille());
            pst.setString(6, t.getPoids());

            pst.setInt(1, t.getId());

            pst.executeUpdate();
            System.out.println("Client modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(Client t) throws SQLException {
        try {
            String req = "DELETE FROM user where id=?";
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Client supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Client> selectAllForAdmin() throws SQLException {
        
        List<Client> temp = new  ArrayList<>();
        
        String req = "SELECT * FROM user JOIN client ON user.id = client.id WHERE type = 'client'";
        
        Statement st = conx.createStatement();
        
        ResultSet rs = st.executeQuery(req);
        
        while(rs.next()){
            Client cl = new Client();
            
            cl.setNom(rs.getString("nom"));
            cl.setPrenom(rs.getString("prenom"));
            cl.setEmail(rs.getString("email"));
            cl.setTelephone(rs.getString("telephone"));
            cl.setIsVerified(rs.getBoolean("is_verified"));
            cl.setImage(rs.getString("image"));
            
            temp.add(cl);
        }

        return temp;
    }

    @Override
    public void activer(Client t) throws SQLException {
        try {
            String req = "UPDATE user JOIN client ON user.id = client.id SET user.is_verified = true WHERE user.id = ?";
            PreparedStatement pst = conx.prepareStatement(req);

            pst.setInt(1, t.getId());

            pst.executeUpdate();
            System.out.println("Client activé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void desactiver(Client t) throws SQLException {
        try {
            String req = "UPDATE user JOIN client ON user.id = client.id SET user.is_verified = false WHERE user.id = ?";
            PreparedStatement pst = conx.prepareStatement(req);

            pst.setInt(1, t.getId());

            pst.executeUpdate();
            System.out.println("Client desactivé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
