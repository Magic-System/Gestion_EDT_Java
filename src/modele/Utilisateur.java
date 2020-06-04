package modele;

/**
 * Objet Utilisateur contenant le nom, le prénom, l'email, le mot de passe, les droits d'accès et un numéro identifiant.
 * @author Daniel
 */
public class Utilisateur {

    /**
     * Identifiant de l'utilisateur.
     */
    protected int id;
    /**
     * Email de l'utilisateur.
     */
    protected String email;
    /**
     * Mot de passe de l'utilisateur.
     */
    protected String password;
    /**
     * Nom de l'utilisateur.
     */
    protected String nom;
    /**
     * Prénom de l'utilisateur.
     */
    protected String prenom;
    /**
     * Droits d'accès de l'utilisateur.
     */
    protected int droit;

    /**
     * Constructeur par défaut. Initialise l'id à 0, l'email à "email", le mot de passe à "password", le nom à "nom", le prenom à "prenom" et les droits à 0
     */
    public Utilisateur() {
        this.id = 0;
        this.email = "email";
        this.password = "password";
        this.nom = "nom";
        this.prenom = "prenom";
        this.droit = 0;
    }

    /**
     * Constructeur avec tout les parametres.
     * @param id Identifiant de l'utilisateur.
     * @param email Email de l'utilisateur.
     * @param password Mot de passe de l'utilisateur.
     * @param nom Nom de l'utilisateur.
     * @param prenom Prenom de l'utilisateur.
     * @param droit Droits d'accès de l'utilisateur.
     */
    public Utilisateur(int id, String email, String password, String nom, String prenom, int droit) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.droit = droit;
    }

    /**
     * Constructeur par copie.
     * @param utilisateur Utilisateur a copier.
     */
    public Utilisateur(Utilisateur utilisateur) {
        this.id = utilisateur.id;
        this.email = utilisateur.email;
        this.password = utilisateur.password;
        this.nom = utilisateur.nom;
        this.prenom = utilisateur.prenom;
        this.droit = utilisateur.droit;
    }

    /**
     * Getter de l'id de l'utilisateur.
     * @return Retourne l'id de l'utilisateur sous forme d'un int.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter de l'id de l'utilisateur.
     * @param id Nouveau numéro d'id de l'utilisateur.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter de l'email de l'utilisateur.
     * @return Email de l'utilisateur sous forme d'un String.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter de l'email de l'utilisateur.
     * @param email Nouveau email de l'utilisateur.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter du mot de passe de l'utilisateur.
     * @return Retourne le mot de passe de l'utilisateur sous forme d'un String.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter du mot de passe de l'utilisateur.
     * @param password Nouveau mot de passe de l'utilisateur.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter du nom de l'utilisateur.
     * @return Nom de l'utilisateur sous forme d'un String.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter du nom de l'utilisateur.
     * @param nom Nouveau nom de l'utilisateur.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter du prénom de l'utilisateur.
     * @return Prénom de l'utilisateu sous forme d'un String.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Setter du prénom de l'utilisateur.
     * @param prenom Nouveau prénom de l'utilisateur.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Getter des droits d'acces de l'utilisateur.
     * @return Les droits d'acces de l'utilisateur sous forme d'un int.
     */
    public int getDroit() {
        return droit;
    }

    /**
     * Setter des droits d'acces de l'utilisateur.
     * @param droit Nouveau droits d'acces de l'utilisateur.
     */
    public void setDroit(int droit) {
        this.droit = droit;
    }

    /**
     * Convertit les informations de l'utilisateur en String.
     * @return String contenant les attributs de l'utilisateur.
     */
    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", droit=" + droit +
                '}';
    }
}
