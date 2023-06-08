/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package healthconnectjava.entities;

/**
 *
 * @author wiki
 */
public class Coach extends User{
    
    private String diplome;
    private boolean etatCompte;

    public Coach() {
    }

    public Coach(String diplome, boolean etatCompte, String email, String password, String nom, String prenom, String dateNaissance, String telephone, String image, String roles, boolean isVerified) {
        super(email, password, nom, prenom, dateNaissance, telephone, image, roles, isVerified);
        this.diplome = diplome;
        this.etatCompte = etatCompte;
    }
    
    

    public Coach(String diplome, boolean etatCompte, int id, String email, String password, String nom, String prenom, String dateNaissance, String telephone, String image, String roles, boolean isVerified) {
        super(id, email, password, nom, prenom, dateNaissance, telephone, image, roles, isVerified);
        this.diplome = diplome;
        this.etatCompte = etatCompte;
    }

    
    
    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public boolean isEtatCompte() {
        return etatCompte;
    }

    public void setEtatCompte(boolean etatCompte) {
        this.etatCompte = etatCompte;
    }

    @Override
    public String toString() {
        return super.toString() +  "Coach{" + "diplome=" + diplome + ", etatCompte=" + etatCompte + '}';
    }

    
}
