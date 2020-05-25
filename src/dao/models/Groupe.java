package modele;

/**
 * Objet Groupe contenant le nom du groupe, un numéro identifiant et la promotion de ce groupe.
 * @author Daniel
 */
public class Groupe {

    /**
     * Identifiant du groupe
     */
    private int id;
    /**
     * Nom du groupe
     */
    private String nom;
    /**
     * Promotion du groupe
     */
    private Promotion promotion;

    /**
     * Constructeur par défaut. Initialise id à 0, nom à "nom" et appelle le contructeur par defaut de Promotion.
     */
    public Groupe() {
        this.id = 0;
        this.nom  = "nom";
        this.promotion = new Promotion();
    }

    /**
     * Constructeur par copie.
     * @param groupe Groupe a copier.
     */
    public Groupe(Groupe groupe) {
        this.id = groupe.id;
        this.nom = groupe.nom;
        this.promotion = new Promotion(groupe.promotion);
    }

    /**
     * Contructeur avec tout les parametres.
     * @param id identifiant du groupe
     * @param nom nom du groupe
     * @param promotion promotion du groupe
     */
    public Groupe(int id, String nom, Promotion promotion) {
        this.id = id;
        this.nom = nom;
        this.promotion = new Promotion(promotion);
    }

    /**
     * Getter de l'id du groupe.
     * @return Id du groupe sous forme d'un int.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter de l'id du groupe.
     * @param id Nouveau numéro d'id du groupe.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter du nom du groupe.
     * @return Nom du groupe sous forme d'un String.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter du nom du groupe.
     * @param nom Nouveau nom du groupe.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter de la promotion du groupe.
     * @return Objet Promotion du groupe.
     */
    public Promotion getPromotion() {
        return promotion;
    }

    /**
     * Setter du groupe de la promotion
     * @param promotion Nouveau objet Promotion du groupe
     */
    public void setPromotion(Promotion promotion) {
        this.promotion = new Promotion(promotion);
    }

    /**
     * Convertit les informations du groupe en String.
     * @return String contenant les attributs du Groupe.
     */
    @Override
    public String toString() {
        return "Groupe{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", promotion=" + promotion +
                '}';
    }
}
