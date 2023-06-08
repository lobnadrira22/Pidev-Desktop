/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthconnectjava.entities;

import java.util.List;

/**
 *
 * @author LENOVO
 */
public class Academie {
    private int id;
    private String nom;
    private String adresse;
    private String numtel;
    private String sportpropose;
    List <Salle> salles;
   

    public Academie() {
    }

    public Academie(int id, String nom, String adresse, String numtel, String sportpropose) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.numtel = numtel;
        this.sportpropose = sportpropose;
    }

    public Academie(String nom, String adresse, String numtel, String sportpropose) {
        this.nom = nom;
        this.adresse = adresse;
        this.numtel = numtel;
        this.sportpropose = sportpropose;
    }

    public Academie(String nom, String adresse, String numtel, String sportpropose, List<Salle> salles) {
        this.nom = nom;
        this.adresse = adresse;
        this.numtel = numtel;
        this.sportpropose = sportpropose;
        this.salles = salles;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getNumtel() {
        return numtel;
    }

    public String getSportpropose() {
        return sportpropose;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

  public void setNumtel(String numtel) {
    // Vérifier si le numéro de téléphone est composé de 8 chiffres
    if (numtel.matches("\\d{8}")) {
        this.numtel = numtel;
    } else {
        // Afficher un message d'erreur si le numéro de téléphone ne contient pas 8 chiffres
        System.out.println("Erreur : Le numéro de téléphone doit être composé de 8 chiffres.");
    }
}


    public void setSportpropose(String sportpropose) {
        this.sportpropose = sportpropose;
    }

    public List<Salle> getSalles() {
        return salles;
    }

    public void setSalles(List<Salle> salles) {
        this.salles = salles;
    }

    
    
    @Override
    public String toString() {
        return "Academie{" + ", nom=" + nom  + '}';
    }

 
    
    
    
    
    
}
