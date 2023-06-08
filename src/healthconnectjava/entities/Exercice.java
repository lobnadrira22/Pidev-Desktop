/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package healthconnectjava.entities;

/**
 *
 * @author User
 */
public class Exercice {
          private int id;
    private int evenementId;
    private String evenementNom;
    private String nom;
    private String description;
    private String duree;
    private String image;

    public Exercice(int evenementId, String nom, String description, String duree, String image) {
        this.evenementId = evenementId;
        this.nom = nom;
        this.description = description;
        this.duree = duree;
        this.image = image;
    }

    public Exercice(int id, int evenementId, String nom, String description, String duree, String image) {
        this.id = id;
        this.evenementId = evenementId;
        this.nom = nom;
        this.description = description;
        this.duree = duree;
        this.image = image;
    }
      public Exercice(int id, int evenementId, String evenementNom, String nom, String description, String duree, String image) {
        this.id = id;
        this.evenementId = evenementId;
        this.evenementNom = evenementNom;
        this.nom = nom;
        this.description = description;
        this.duree = duree;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public int getEvenementId() {
        return evenementId;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getDuree() {
        return duree;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEvenementId(int evenementId) {
        this.evenementId = evenementId;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEvenementNom() {
        return evenementNom;
    }

    public void setEvenementNom(String evenementNom) {
        this.evenementNom = evenementNom;
    }

    @Override
    public String toString() {
        return "Exercice{" + "id=" + id + ", evenementId=" + evenementId + ", nom=" + nom + ", description=" + description + ", duree=" + duree + ", image=" + image + '}';
    }
    
    
    
    
    
    
    
    
    
}
