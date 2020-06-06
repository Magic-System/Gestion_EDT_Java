package modele;

/**
 * @author Daniel
 */
public class Seance_Salles {

    private Seance seance;
    private Salle salle;

    /**
     * Constructeur par défaut vide.
     */
    public Seance_Salles() {
    }

    /**
     * Constructeur avec parametre.
     * @param seance Seance a mettre dans le couple seance/salle.
     * @param salle Salle a mettre dans le couple seance/salle
     */
    public Seance_Salles(Seance seance, Salle salle) {
        this.salle = new Salle(salle);
        this.seance = new Seance(seance);
    }

    /**
     * Getter de la salle.
     * @return Objet Salle.
     */
    public Salle getSalle() {
        return salle;
    }

    /**
     * Setter Salle.
     * @param salle Nouvelle salle du couple seance/salle
     */
    public void setSalle(Salle salle) {
        this.salle = new Salle(salle);
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
     * @param seance Nouvelle Seance du couple seance/salle.
     */
    public void setSeance(Seance seance) {
        this.seance = new Seance(seance);
    }

    /**
     * Convertie en string les attributs.
     * @return String contenant le couple salle/seance.
     */
    @Override
    public String toString() {
        return "Seance_Salles{" +
                "salle=" + salle +
                ", seance=" + seance +
                '}';
    }
}
