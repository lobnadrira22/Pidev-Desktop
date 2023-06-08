/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package healthconnectjava.entities;

/**
 *
 * @author wiki
 */
public class User {
    
    private int id ;
    private String email ;
    private String password ;
    private String nom ;
    private String prenom ;
    private String dateNaissance ;
    private String telephone ;
    private String image ;
    private String roles ;
    private boolean isVerified ; 

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    

    public User(String email, String password, String nom, String prenom, String dateNaissance, String telephone, String image, String roles, boolean isVerified) {
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.telephone = telephone;
        this.image = image;
        this.roles = roles;
        this.isVerified = isVerified;
    }

    public User(int id, String email, String password, String nom, String prenom, String dateNaissance, String telephone, String image, String roles, boolean isVerified) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.telephone = telephone;
        this.image = image;
        this.roles = roles;
        this.isVerified = isVerified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public boolean isIsVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", password=" + password + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + ", telephone=" + telephone + ", image=" + image + ", roles=" + roles + ", isVerified=" + isVerified + '}';
    }

}
