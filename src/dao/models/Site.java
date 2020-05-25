package modele;

/**
 * Objet site contenant le nom du site et un numéro identifiant.
 * @author Daniel
 */
public class Site {

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
    public Site() {
        this.id = 0;
        this.nom = "nom";
    }

    /**
     * Constructeur avec tout les parametres.
     * @param id Identifiant du site.
     * @param nom Nom du site.
     */
    public Site(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    /**
     * Constructeur par copie.
     * @param site Site a copier.
     */
    public Site(Site site) {
        this.id = site.id;
        this.nom = site.nom;
    }

    /**
     * Getter de l'identifiant du site.
     * @return Identifiant du site sous forme d'un int.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter de l'identifiant du site.
     * @param id Nouveau numéro d'identifiant du site.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter du nom du site.
     * @return Nom du site sous forme d'un String.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter du nom du site.
     * @param nom Nouveau nom du site.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Convertit les informations du site sous forme de String.
     * @return String contenant tout les attributs du Site.
     */
    @Override
    public String toString() {
        return "Site{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
