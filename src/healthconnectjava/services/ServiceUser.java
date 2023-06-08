/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package healthconnectjava.services;

import healthconnectjava.entities.User;
import java.sql.Connection;
import java.sql.SQLException;
import healthconnectjava.utils.MyDB;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author wiki
 */
public class ServiceUser implements IServiceUser<User>{
    
    public Connection conx;
    public ServiceUser() {
        conx = MyDB.getInstance().getConx();
    }
    
    private static int id ;
    private static String email; 
    private static String password ;
    private static String nom ;
    private static String prenom ;
    private static String dateNaissance ;
    private static String telephone ;
    private static String image ;
    private static String roles ;
    
    private String urlI = "http://127.0.0.1:8000/Client_img/";
    
    private static String diplome;
    
    private static String adresse ;
    private static String ville ;
    private static String genre ;
    private static String taille ;
    private static String poids ;

    @Override
    public String login(User t) throws SQLException {
        String role = "";
        try {

            if (t.getEmail()!= null && t.getPassword()!= null) {

                String req = "SELECT * from user";
                PreparedStatement pst = conx.prepareStatement(req);

                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    
                    // Créer l'URL avec les paramètres nécessaires
                    String url = "http://127.0.0.1:8000/verifyPasswordjson?email="+t.getEmail()+"&password="+t.getPassword();

                    // Établir une connexion HTTP à l'URL
                    URL obj = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con.setRequestMethod("GET");

                    // Vérifier que la réponse HTTP est OK avant de récupérer la réponse JSON
                    if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        String inputLine;
                        StringBuilder response = new StringBuilder();
                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        in.close();
                        
                        if (rs.getString("email").equals(t.getEmail()) && response.toString().equals("true")) {
                            switch (rs.getString("roles")) {
                                case "[\"ROLE_COACH\"]":

                                    id = rs.getInt("id");
                                    nom = rs.getString("nom");
                                    prenom = rs.getString("prenom");
                                    email = rs.getString("email");
                                    password = rs.getString("password");
                                    telephone = rs.getString("telephone");
                                    roles = rs.getString("roles");
                                    image = rs.getString("image");
                                    dateNaissance = rs.getString("date_naissance");
                                    role = rs.getString("roles");
                                    break;
                                case "[\"ROLE_CLIENT\"]":
                                    
                                    String reqM = "SELECT adresse, ville, genre, taille, poids FROM client WHERE id="+rs.getInt("id");
                                    PreparedStatement pstM = conx.prepareStatement(reqM);
                                    ResultSet rsM = pstM.executeQuery();
                                    
                                    id = rs.getInt("id");
                                    nom = rs.getString("nom");
                                    prenom = rs.getString("prenom");
                                    email = rs.getString("email");
                                    password = rs.getString("password");
                                    telephone = rs.getString("telephone");
                                    roles = rs.getString("roles");
                                    image = rs.getString("image");
                                    dateNaissance = rs.getString("date_naissance");
                                    
                                    while (rsM.next()) {
                                        poids = rsM.getString("poids");
                                        genre = rsM.getString("genre");
                                        taille = rsM.getString("taille");
                                        adresse = rsM.getString("adresse");
                                        ville = rsM.getString("ville");
                                    }
                                    
                                    role = rs.getString("roles");
                                    break;
                                case "[\"ROLE_ADMIN\"]":
                                    id = rs.getInt("id");
                                    nom = rs.getString("nom");
                                    prenom = rs.getString("prenom");
                                    email = rs.getString("email");
                                    roles = rs.getString("roles");
                                    image = rs.getString("image");
                                    role = rs.getString("roles");
                                    break;
                                default:
                                    break;
                            }
                        } else {
                            System.err.println("Verifier vos donneés !");
                        }
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return role;
    }

    @Override
    public void forgotPassword(User t) throws SQLException {
        
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        ServiceUser.id = id;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        ServiceUser.email = email;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        ServiceUser.password = password;
    }

    public static String getNom() {
        return nom;
    }

    public static void setNom(String nom) {
        ServiceUser.nom = nom;
    }

    public static String getPrenom() {
        return prenom;
    }

    public static void setPrenom(String prenom) {
        ServiceUser.prenom = prenom;
    }

    public static String getDateNaissance() {
        return dateNaissance;
    }

    public static void setDateNaissance(String dateNaissance) {
        ServiceUser.dateNaissance = dateNaissance;
    }

    public static String getTelephone() {
        return telephone;
    }

    public static void setTelephone(String telephone) {
        ServiceUser.telephone = telephone;
    }

    public static String getImage() {
        return image;
    }

    public static void setImage(String image) {
        ServiceUser.image = image;
    }

    public static String getRoles() {
        return roles;
    }

    public static void setRoles(String roles) {
        ServiceUser.roles = roles;
    }

    public static String getDiplome() {
        return diplome;
    }

    public static void setDiplome(String diplome) {
        ServiceUser.diplome = diplome;
    }

    public static String getAdresse() {
        return adresse;
    }

    public static void setAdresse(String adresse) {
        ServiceUser.adresse = adresse;
    }

    public static String getVille() {
        return ville;
    }

    public static void setVille(String ville) {
        ServiceUser.ville = ville;
    }

    public static String getGenre() {
        return genre;
    }

    public static void setGenre(String genre) {
        ServiceUser.genre = genre;
    }

    public static String getTaille() {
        return taille;
    }

    public static void setTaille(String taille) {
        ServiceUser.taille = taille;
    }

    public static String getPoids() {
        return poids;
    }

    public static void setPoids(String poids) {
        ServiceUser.poids = poids;
    }
    
    
    
    
}
