package healthconnectjava.entities;

public class Aliment {
    private int id;
    private String Nom;
    private int Nbcal;
    private String Desc;


    public Aliment(String nom, int nbcal, String desc) {
        Nom = nom;
        Nbcal = nbcal;
        Desc = desc;
    }

    public Aliment(int id, String nom, int nbcal, String desc) {
        this.id = id;
        Nom = nom;
        Nbcal = nbcal;
        Desc = desc;
    }
    public Aliment(){}

    @Override
    public String toString() {
        return Nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public int getNbcal() {
        return Nbcal;
    }

    public void setNbcal(int nbcal) {
        Nbcal = nbcal;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }
}
