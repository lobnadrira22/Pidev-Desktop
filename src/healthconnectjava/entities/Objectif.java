package healthconnectjava.entities;

public class Objectif {
    private int id;
    private int PoidsDes;
    private int TypeReg;
    private int CalCons;
    private int Age;
    private String Sexe;
    private int Poids;
    private int taille;

    public Objectif() {
    }

    @Override
    public String toString() {
        return
                "" + id
                ;
    }

    public Objectif(int poidsDes, int typeReg, int calCons, int age, String sexe, int poids, int taille) {
        PoidsDes = poidsDes;
        TypeReg = typeReg;
        CalCons = calCons;
        Age = age;
        Sexe = sexe;
        Poids = poids;
        this.taille = taille;
    }

    public Objectif(int id, int poidsDes, int typeReg, int calCons, int age, String sexe, int poids, int taille) {
        this.id = id;
        PoidsDes = poidsDes;
        TypeReg = typeReg;
        CalCons = calCons;
        Age = age;
        Sexe = sexe;
        Poids = poids;
        this.taille = taille;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoidsDes() {
        return PoidsDes;
    }

    public void setPoidsDes(int poidsDes) {
        PoidsDes = poidsDes;
    }

    public int getTypeReg() {
        return TypeReg;
    }

    public void setTypeReg(int typeReg) {
        TypeReg = typeReg;
    }

    public int getCalCons() {
        return CalCons;
    }

    public void setCalCons(int calCons) {
        CalCons = calCons;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getSexe() {
        return Sexe;
    }

    public void setSexe(String sexe) {
        Sexe = sexe;
    }

    public int getPoids() {
        return Poids;
    }

    public void setPoids(int poids) {
        Poids = poids;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }
}
