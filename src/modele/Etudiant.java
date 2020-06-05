package modele;

/**
 * Objet Etudiant contenant le numéro étudiant, les info utilisateurs et le groupe.
 * @author Daniel
 */
public class Etudiant {

    /**
     * Attribut utilisateur de l'étudiant.
     */
    private Utilisateur utilisateur;
    /**
     * Numéro d'étudiant.
     */
    private int numero;
    /**
     * Groupe auquel l'etudiant appartient.
     */
    private Groupe groupe;

    /**
     * Constructeur vide par defaut.
     */
    public Etudiant() {
        this.utilisateur = new Utilisateur();
        this.numero = 0;
        this.groupe = new Groupe();
    }

    /**
     * Constructeur avec tout les paramètres.
     * @param id Identifiant de l'utilisateur étudiant.
     * @param email Email de l'étudiant.
     * @param password Mot de passe de l'étudiant.
     * @param nom Nom de l'étudiant.
     * @param prenom Prénom de l'étudiant.
     * @param droit Droit d'accès de l'étudiant.
     * @param numero Numéro d'étudiant.
     * @param groupe Groupe auquel appartient l'étudiant.
     */
    public Etudiant(int id, String email, String password, String nom, String prenom, int droit, int numero, Groupe groupe) {
        this.utilisateur = new Utilisateur(id, email, password, nom, prenom, droit);
        this.numero = numero;
        this.groupe = new Groupe(groupe);
    }

    public Etudiant(Utilisateur utilisateur, int numero, Groupe groupe) {
        this.utilisateur = utilisateur;
        this.numero = numero;
        this.groupe = groupe;
    }

    /**
     * Constructeur par copie.
     * @param etudiant Etudiant a copier.
     */
    public Etudiant(Etudiant etudiant) {
        this.utilisateur = new Utilisateur(etudiant.utilisateur);
        this.numero = etudiant.numero;
        this.groupe = new Groupe(etudiant.groupe);
    }

    /**
     * Renvoi le numéro d'étudiant.
     * @return retourne le numéro de l'étudiant sous forme d'un int.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Met a jour le numéro d'étudiant.
     * @param numero Nouveau numéro d'étudiant.
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Renvoi les info relative au groupe de l'étudiant.
     * @return Retourne un objet groupe correspondant au groupe de l'étudiant.
     */
    public Groupe getGroupe() {
        return groupe;
    }

    /**
     * Met a jour le groupe de l'étudiant
     * @param groupe Nouveau groupe de l'étudiant
     */
    public void setGroupe(Groupe groupe) {
        this.groupe = new Groupe(groupe);
    }

    /**
     * Renvoi les info relative au groupe de l'étudiant.
     * @return Retourne un objet groupe correspondant au groupe de l'étudiant.
     */
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    /**
     * Met a jour le groupe de l'étudiant
     * @param utilisateur Nouveau groupe de l'étudiant
     */
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = new Utilisateur(utilisateur);
    }

    /**
     * Convertit les informations de l'etudiant sous forme d'un String.
     * @return String contenant les attributs de l'etudiant.
     */
    @Override
    public String toString() {
        return "Etudiant{" +
                "utilisateur=" + utilisateur +
                ", numero=" + numero +
                ", groupe=" + groupe +
                '}';
    }
}
