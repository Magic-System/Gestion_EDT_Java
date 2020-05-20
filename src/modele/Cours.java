package modele;

/**
 * Objet cours contenant le nom du cours et un numéro identifiant.
 * @author Daniel
 */
public class Cours {

    /**
     * Numéro identifiant du cours.
     */
    private int id;
    /**
     * Nom du cours.
     */
    private String nom;

    /**
     * Constructeur par défaut. Initialise l'identifiant a 0 et le nom à "nom".
     */
    public Cours() {
        this.id = 0;
        this.nom = "nom";
    }

    /**
     * Constructeur avec tout les parametres.
     * @param id Identifiant du cours.
     * @param nom Nom du cours.
     */
    public Cours(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    /**
     * Constructeur par copie.
     * @param cours Objet cours a copier.
     */
    public Cours(Cours cours) {
        this.id = cours.id;
        this.nom = cours.nom;
    }

    /**
     * Getter d'id du cours.
     * @return Id du cours sous forme d'un int.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter d'id du cours.
     * @param id Nouveau numéro d'id du cours.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter du nom du cours.
     * @return Nom du cours sous forme d'un String.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter du nom du cours.
     * @param nom Nouveau nom du cours.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Convertit les informations du cours sous forme de String.
     * @return String contenant tout les attributs du Cours.
     */
    @Override
    public String toString() {
        return "Cours{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
