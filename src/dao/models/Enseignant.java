package modele;

import java.util.ArrayList;
import java.util.List;

/**
 * Objet Promotion contenant le nom de la promotion et un numéro identifiant.
 * @author Daniel
 */
public class Enseignant{

    /**
     * Tout les cours enseignés par l'enseignant.
     */
    private List<Cours> cours_enseigne;
    /**
     * Information utilisateur de l'enseignant.
     */
    private Utilisateur utilisateur;

    /**
     * Contructeur par défaut.
     */
    public Enseignant() {
        this.utilisateur = new Utilisateur();
        this.cours_enseigne = new ArrayList<Cours>();
    }

    /**
     * Contructeur avec tout les parametres.
     * @param id Identifiant de l'enseignant.
     * @param email Email de l'enseignant.
     * @param password Mot de passe de l'enseignant.
     * @param nom Nom de l'enseignant.
     * @param prenom Prenom de l'enseignant.
     * @param droit Droits d'acces de l'enseignant.
     * @param cours Liste des cours enseignés par l'enseignant.
     */
    public Enseignant(int id, String email, String password, String nom, String prenom, int droit, List<Cours> cours) {
        this.utilisateur = new Utilisateur(id, email, password, nom, prenom, droit);
        this.cours_enseigne = new ArrayList<Cours>(cours);
    }

    /**
     * Constructeur par copie.
     * @param enseignant Enseignant a copier.
     */
    public Enseignant(Enseignant enseignant) {
        this.utilisateur = new Utilisateur(enseignant.utilisateur);
        this.cours_enseigne = new ArrayList<Cours>(enseignant.cours_enseigne);
    }

    /**
     * Getter de la liste des cours de l'enseignant.
     * @return Liste des cours enseignés par l'enseignant.
     */
    public List<Cours> getCours_enseigne() {
        return cours_enseigne;
    }

    /**
     * Setter de la liste des cours de l'enseignant.
     * @param cours_enseigne Liste des cours enseignés par l'enseignant.
     */
    public void setCours_enseigne(List<Cours> cours_enseigne) {
        this.cours_enseigne = new ArrayList<Cours>(cours_enseigne);
    }

    /**
     * Getter des informations utilisateur de l'enseignant.
     * @return Objet utilisateur contenant les informations utilisateur de l'enseignant.
     */
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    /**
     * Setter des information utilisateur de l'enseignant.
     * @param utilisateur Utilisateur a mettre enseignant.
     */
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = new Utilisateur(utilisateur);
    }

    /**
     * Convertit les informations de l'enseignant en String.
     * @return String contenant les attributs de l'enseignant.
     */
    @Override
    public String toString() {
        return "Enseignant{" +
                "cours_enseigne=" + cours_enseigne +
                ", utilisateur=" + utilisateur +
                '}';
    }
}
