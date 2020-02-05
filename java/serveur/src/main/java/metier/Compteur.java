package metier;

public class Compteur {

    public int getCpt() {
        return cpt;
    }

    private int cpt = 0;

    public void ajouter() {
        cpt++;
    }

    public void raz() {
        cpt = 0;
    }
}
