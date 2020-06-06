package modele;

/**
 * @author Daniel
 */
public class Seance_Groupes {

    private Seance seance;
    private Groupe grp;

    /**
     * Constructeur par défaut vide.
     */
    public Seance_Groupes() {
    }

    /**
     * Constructeur avec parametre.
     * @param seance Seance a mettre dans le couple seance/groupe.
     * @param grp Groupe a mettre dans le couple seance/groupe
     */
    public Seance_Groupes(Seance seance, Groupe grp) {

        this.seance = new Seance(seance);
        this.grp = new Groupe(grp);
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
     * @param seance Nouvelle Seance du couple seance/groupe.
     */
    public void setSeance(Seance seance) {
        this.seance = new Seance(seance);
    }

    /**
     * Getter du Groupe.
     * @return Objet Groupe.
     */
    public Groupe getGroupe() {
        return grp;
    }

    /**
     * Setter Groupe
     * @param grp Nouveau Groupe du couple seance/groupe.
     */
    public void setGroupe(Groupe grp) {
        this.grp = new Groupe(grp);
    }

    /**
     * Convertie en string les attributs.
     * @return String contenant le couple groupe/seance.
     */
    @Override
    public String toString() {
        return "Seance_Groupes{" +
                "seance=" + seance +
                ", grp=" + grp +
                '}';
    }
}
