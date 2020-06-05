package modele;

/**
 * Objet Promotion contenant le nom de la promotion et un numéro identifiant.
 * @author Daniel
 */
public class Promotion {

    /**
     * Identifiant de la promotion.
     */
    private int id;
    /**
     * Nom de la promotion.
     */
    private String nom;

    /**
     * Constructeur par défaut. Initialise Id à 0 et Nom à "nom"
     */
    public Promotion() {
        this.id = 0;
        this.nom = "nom";
    }

    /**
     * Contructeur avec tout les parametres.
     * @param nom Nom de la promotion.
     * @param id Identifiant de la promotion.
     */
    public Promotion(int id, String nom) {
        this.nom = nom;
        this.id = id;
    }

    /**
     * Constructeur par copie.
     * @param promotion Objet promotion a copier.
     */
    public Promotion(Promotion promotion) {
        this.nom = promotion.nom;
        this.id = promotion.id;
    }

    /**
     * Getter du nom de la promotion.
     * @return nom de la promotion sous forme d'un String.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter du nom de la promotion.
     * @param nom Nouveau nom de la promotion.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter d'id de la promotion.
     * @return Identifiant de la promotion sous forme d'un int.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter de l'id de la promotion.
     * @param id Nouveau numéro d'id de la promotion.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Convertit les informations de la promo en String.
     * @return String contenant les attributs de Promotion.
     */
    @Override
    public String toString() {
        return "Promotion{" +
                "nom='" + nom + '\'' +
                ", id=" + id +
                '}';
    }
}
