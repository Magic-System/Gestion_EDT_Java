package modele;

/**
 * Objet Type_cours contenant le nom du type de cours et un numéro identifiant.
 * @author Daniel
 */
public class Type_Cours {

    /**
     * Numéro d'identifiant du type de cours.
     */
    private int id;
    /**
     * Nom du type de cours.
     */
    private String nom;

    /**
     * Constructeur par défaut. Initialise l'identifiant a 0 et le nom a "nom".
     */
    public Type_Cours() {
        this.id = 0;
        this.nom = "nom";
    }

    /**
     * Contructeur avec parametres.
     * @param id Identifiant du type de cours.
     * @param nom Nom du type de cours.
     */
    public Type_Cours(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    /**
     * Getter de l'identifiant du type de cours.
     * @return Identifiant sous forme d'un int.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter de l'identifiant du type de cours.
     * @param id Nouveau identifiant du type de cours.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter du nom du type de cours.
     * @return String contenant le nom du type de cours.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter du nom du type de cours.
     * @param nom Nouveau nom du type de cours.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Convertit les informations du type de cours sous forme de String.
     * @return String contenant les attributs du type de cours.
     */
    @Override
    public String toString() {
        return "Type_Cours{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
