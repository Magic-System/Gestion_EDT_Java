package modele;

/**
 * @author Daniel
 */
public class Seance_Enseignants {

    private Seance seance;
    private Enseignant prof;

    /**
     * Constructeur par défaut vide.
     */
    public Seance_Enseignants() {
    }

    /**
     * Constructeur avec parametre.
     * @param seance Seance a mettre dans le couple seance/enseignant.
     * @param prof Enseignant a mettre dans le couple seance/enseignant
     */
    public Seance_Enseignants(Seance seance, Enseignant prof) {
        this.prof = new Enseignant(prof);
        this.seance = new Seance(seance);
    }

    /**
     * Getter de la Enseignant.
     * @return Objet Enseignant.
     */
    public Enseignant getProf() {
        return prof;
    }

    /**
     * Setter Seance.
     * @param prof Nouveau Enseignant du couple seance/enseignant.
     */
    public void setProf(Enseignant prof) {
        this.prof = new Enseignant(prof);
    }

    /**
     * Getter de la Seance.
     * @return Objet Seance.
     */
    public Seance getSeance() {
        return seance;
    }

    /**
     * Setter Seance.
     * @param seance Nouvelle Seance du couple seance/enseignant.
     */
    public void setSeance(Seance seance) {
        this.seance = new Seance(seance);
    }

    /**
     * Convertie en string les attributs.
     * @return String contenant le couple enseignant/seance.
     */
    @Override
    public String toString() {
        return "Seance_Enseignants{" +
                "prof=" + prof +
                ", seance=" + seance +
                '}';
    }
}
