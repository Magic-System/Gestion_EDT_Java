package modele;

/**
 * Objet Salle contenant le nom de la salle, la capacité de la salle, un numéro identifiant et le site contenant cette salle.
 * @author Daniel
 */
public class Salle {

    /**
     * Attribut identifiant de la salle.
     */
    private int id;
    /**
     * Nom de la salle.
     */
    private String nom;
    /**
     * Capacité en nombre d'éleves de la salle.
     */
    private int capacite;
    /**
     * Site sur lequel se trouve cette salle.
     */
    private Site site;

    /**
     * Constructeur par defaut.
     */
    public Salle() {
        this.id = 0;
        this.nom = "nom";
        this.capacite = 0;
        this.site = new Site();
    }

    /**
     * Constructeur avec tout les parametres.
     * @param id Identifiant de la salle.
     * @param nom Nom de la salle.
     * @param capacite Capacite de la salle.
     * @param site Site sur lequel se trouve la salle.
     */
    public Salle(int id, String nom, int capacite, Site site) {
        this.id = id;
        this.nom = nom;
        this.capacite = capacite;
        this.site = site;
    }

    /**
     * Getter de l'identifiant de la salle.
     * @return Identifiant sous forme d'un int.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter de l'identifiant de la salle.
     * @param id Nouveau numéro d'identifiant de la salle.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter du nom de la salle.
     * @return Nom de la salle sous forme d'un String.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter du nom de la salle.
     * @param nom Nouveau nom de la salle.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter de la capacité de la salle.
     * @return Capacité de la salle sous forme d'un int.
     */
    public int getCapacite() {
        return capacite;
    }

    /**
     * Setter de la capacité de la salle.
     * @param capacite Nouveau nombre de capacité de la salle.
     */
    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    /**
     * Getter du site sur lequel se trouve la salle.
     * @return Objet Site.
     */
    public Site getSite() {
        return site;
    }

    /**
     * Setter du site de la salle.
     * @param site Nouveau Site de la salle.
     */
    public void setSite(Site site) {
        this.site = new Site(site);
    }

    /**
     * Convertit les informations de la salle sous forme d'un String.
     * @return String contenant les attributs de la salle.
     */
    @Override
    public String toString() {
        return "Salle{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", capacite=" + capacite +
                ", site=" + site +
                '}';
    }
}
