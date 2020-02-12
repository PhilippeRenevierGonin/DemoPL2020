package metier;

public class Identité {

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    private  String nom;

    public Identité() {
        this("nom par défaut");
    }
    public Identité(String nom) {
        this.nom = nom;
    }

    public String toString() {
        return this.getNom();
    }
}
