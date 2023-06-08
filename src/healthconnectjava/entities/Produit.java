package healthconnectjava.entities;

import java.util.Date;

public class Produit {
    private int id ;
    private String nom,description,image;
    private float prix;
    private CategorieProduit category;
    private int quantite_commande ;
    private Date date;



    public Produit() {
    }

    public Produit(String nom, String description, String image, float prix, CategorieProduit category) {
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.prix = prix;
        this.category = category;
    }

    public Produit(int id, String nom, String description, String image, float prix, CategorieProduit category) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.prix = prix;
        this.category = category;
    }

    public Produit(int id, String nom, float prix, int quantite_commande, Date date) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.quantite_commande = quantite_commande;
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

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public CategorieProduit getCategory() {
        return category;
    }

    public int getQuantite_commande() {
        return quantite_commande;
    }

    public void setQuantite_commande(int quantite_commande) {
        this.quantite_commande = quantite_commande;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCategory(CategorieProduit category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", prix=" + prix +
                ", category=" + category +
                '}';
    }
}
