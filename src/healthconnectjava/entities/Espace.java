/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthconnectjava.entities;
import java.util.Date;

/**
 *
 * @author lobna
 */
public class Espace {
    private int id;
    private String nom;
    private String caracteristique;
    private String image;
    private String adresse;
    private String dispo;
    private Date tarifhoraire;
    private Double prixlocation;
    private CategorieLocation categorieloc;

    public Espace() {
    }

    public Espace(int id, String nom, String caracteristique, String image, String adresse, String dispo, Date tarifhoraire, Double prixlocation,CategorieLocation categorieloc ) {
        this.id = id;
        this.nom = nom;
        this.caracteristique = caracteristique;
        this.image = image;
        this.adresse = adresse;
        this.dispo = dispo;
        this.tarifhoraire = tarifhoraire;
        this.prixlocation = prixlocation;
        this.categorieloc = categorieloc;
    }

    public Espace(String nom, String caracteristique, String image, String adresse, String dispo, Date tarifhoraire, Double prixlocation,CategorieLocation categorieloc ) {
        this.nom = nom;
        this.caracteristique = caracteristique;
        this.image = image;
        this.adresse = adresse;
        this.dispo = dispo;
        this.tarifhoraire = tarifhoraire;
        this.prixlocation = prixlocation;
        this.categorieloc = categorieloc;
         
        
    }

    public Espace(int id, String nom, String caracteristique, String image, Double prixlocation) {
        this.id = id;
        this.nom = nom;
        this.caracteristique = caracteristique;
        this.image = image;
        this.prixlocation = prixlocation;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCaracteristique() {
        return caracteristique;
    }

    public void setCaracteristique(String caracteristique) {
        this.caracteristique = caracteristique;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDispo() {
        return dispo;
    }

    public void setDispo(String dispo) {
        this.dispo = dispo;
    }

    public Date getTarifhoraire() {
        return tarifhoraire;
    }

    public void setTarifhoraire(Date tarifhoraire) {
        this.tarifhoraire = tarifhoraire;
    }

    public Double getPrixlocation() {
        return prixlocation;
    }

    public void setPrixlocation(Double prixlocation) {
        this.prixlocation = prixlocation;
    }

    public CategorieLocation getCategorieloc() {
        return categorieloc;
    }

    public void setCategorieloc(CategorieLocation categorieloc) {
        this.categorieloc = categorieloc;
    }
    

    @Override
    public String toString() {
        return "Espace{" + "id=" + id + ", nom=" + nom + ", adresse=" + adresse + ", image=" + image +  ", prixlocation=" + prixlocation  +'}';
    }
    
    
    
    
    
}
