/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package healthconnectjava.entities;

import java.util.Date;

/**
 *
 * @author User
 */
public class Evenement {
      private int id;
    private String nom,type,adresse,description,image;
    private Date date;

    public Evenement(String nom, String type, String adresse, String description, Date date) {
        this.nom = nom;
        this.type = type;
        this.adresse = adresse;
        this.description = description;
        this.date = date;
    }

    public Evenement(String nom, String type, String adresse, String description, String image, Date date) {
        this.nom = nom;
        this.type = type;
        this.adresse = adresse;
        this.description = description;
        this.image = image;
        this.date = date;
    }

    public Evenement(int id, String nom, String type, String adresse, String description, String image, Date date) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.adresse = adresse;
        this.description = description;
        this.image = image;
        this.date = date;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

   
     @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", nom=" + nom + ", type=" + type + ", adresse=" + adresse + ", description=" + description + ", image=" + image + ", date=" + date + '}';
    }

}
