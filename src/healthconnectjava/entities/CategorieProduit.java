package healthconnectjava.entities;

public class CategorieProduit {

    private int id;
    private String nom,description;


    public CategorieProduit() {
    }

    public CategorieProduit(int id, String nom, String description)
    {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    public CategorieProduit(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public void setID(int id) {
        this.id = id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CategorieProduit{" +
                ", nom='" + nom + '\'' +
                '}';
    }
}
