/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthconnectjava.entities;

/**
 *
 * @author LENOVO
 */
public class Salle {
    private int id;
    private String nom;
    private int capacite, equipement;
    private Academie academie;

    public Salle() {
    }

    public Salle(int id, String nom, int capacite, int equipement) {
        this.id = id;
        this.nom = nom;
        this.capacite = capacite;
        this.equipement = equipement;
    }

    public Salle(int id, String nom, int capacite, int equipement, Academie academie) {
        this.id = id;
        this.nom = nom;
        this.capacite = capacite;
        this.equipement = equipement;
        this.academie = academie;
    }

    public Salle(String nom, int capacite, int equipement, Academie academie) {
        this.nom = nom;
        this.capacite = capacite;
        this.equipement = equipement;
        this.academie = academie;
    }

  

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getCapacite() {
        return capacite;
    }

    public int getEquipement() {
        return equipement;
    }

    public Academie getAcademie() {
        return academie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public void setEquipement(int equipement) {
        this.equipement = equipement;
    }

    public void setAcademie(Academie academie) {
        this.academie = academie;
    }

    @Override
    public String toString() {
        return "Salle{" + "id=" + id + ", nom=" + nom + ", capacite=" + capacite + ", equipement=" + equipement + ", academie=" + academie + '}';
    }


    
    
    
}
