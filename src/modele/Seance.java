package modele;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Objet Seance contenant l'id de la seance,
 *                        le numero de la semaine,
 *                        le jour,
 *                        les heures de debut et de fin,
 *                        l'etat,
 *                        le cours,
 *                        le type de cours,
 *                        la liste des enseignant,
 *                        la liste des salles
 *                        et la liste des groupes.
 * @author Daniel
 */
public class Seance {

    private int id;
    private int semaine;
    private LocalDate jour;
    private LocalTime heure_debut;
    private LocalTime heure_fin;
    private int etat;
    private Cours cours;
    private Type_Cours type;
    private List<Enseignant> enseignants;
    private List<Salle> salles;
    private List<Groupe> groupes;

    /**
     * Constructeur par défaut.
     */
    public Seance() {
        this.id = 0;
        this.semaine = 0;
        this.jour = LocalDate.MIN;
        this.heure_debut = LocalTime.MIN;
        this.heure_fin = LocalTime.MAX;
        this.etat = 0;
        this.cours = new Cours();
        this.type = new Type_Cours();
        this.enseignants = new ArrayList<Enseignant>();
        this.salles = new ArrayList<Salle>();
        this.groupes = new ArrayList<Groupe>();
    }

    /**
     * Constructeur avec tout les parametres.
     * @param id Identifiant de la seance.
     * @param semaine Numéro de la semaine.
     * @param jour Jour de la semaine.
     * @param heure_debut Heure de début.
     * @param heure_fin Heure de fin.
     * @param etat Etat de la seance.
     * @param cours Cours enseigné.
     * @param type Type du cours.
     * @param enseignants Liste des enseignants.
     * @param salles Liste des salles.
     * @param groupes Liste des groupes.
     */
    public Seance(int id, int semaine, LocalDate jour, LocalTime heure_debut, LocalTime heure_fin, int etat, Cours cours, Type_Cours type, List<Enseignant> enseignants, List<Salle> salles, List<Groupe> groupes) {
        this.id = id;
        this.semaine = semaine;
        this.jour = jour;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.etat = etat;
        this.cours = cours;
        this.type = type;
        this.enseignants = new ArrayList<Enseignant>(enseignants);
        this.salles = new ArrayList<Salle>(salles);
        this.groupes = new ArrayList<Groupe>(groupes);
    }

    /**
     * Getter de l'id de la seance.
     * @return Id de la seance sous forme d'un int.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter de l'id de la seance.
     * @param id Nouveau numero d'id de la seance.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter de la semaine de la seance.
     * @return Numéro de la semaine
     */
    public int getSemaine() {
        return semaine;
    }

    /**
     * Setter de la semaine de la seance.
     * @param semaine Nouveau numero de semaine de la seance.
     */
    public void setSemaine(int semaine) {
        this.semaine = semaine;
    }

    /**
     * Getter du jour de la seance.
     * @return Jour de la seance.
     */
    public LocalDate getJour() {
        return jour;
    }

    /**
     * Setter du jour de la seance.
     * @param jour Nouveau jour de la seance.
     */
    public void setJour(LocalDate jour) {
        this.jour = jour;
    }

    /**
     * Getter de l'heure de debut de la seance.
     * @return Heure de début.
     */
    public LocalTime getHeure_debut() {
        return heure_debut;
    }

    /**
     * Setter de l'heure de debut de la seance.
     * @param heure_debut Nouvelle heure de debut de la seance.
     */
    public void setHeure_debut(LocalTime heure_debut) {
        this.heure_debut = heure_debut;
    }

    /**
     * Getter de l'heure de fin de la seance.
     * @return Heure de fin de la seance.
     */
    public LocalTime getHeure_fin() {
        return heure_fin;
    }

    /**
     * Setter de l'heure de fin de la seance.
     * @param heure_fin Nouvelle heure de fin de la seance.
     */
    public void setHeure_fin(LocalTime heure_fin) {
        this.heure_fin = heure_fin;
    }

    /**
     * Getter de l'etat de la seance.
     * @return Etat de la seance.
     */
    public int getEtat() {
        return etat;
    }

    /**
     * Setter d'etat de la seance.
     * @param etat Etat de la seance.
     */
    public void setEtat(int etat) {
        this.etat = etat;
    }

    /**
     * Getter du cours de cette seance.
     * @return Cours de la seance.
     */
    public Cours getCours() {
        return cours;
    }

    /**
     * Setter du cours de la seance.
     * @param cours Nouveau cours de la seance.
     */
    public void setCours(Cours cours) {
        this.cours = cours;
    }

    /**
     * Getter du type de cours de la seance.
     * @return Type du cours de la seance.
     */
    public Type_Cours getType() {
        return type;
    }

    /**
     * Setter du type de cours de la seance.
     * @param type Nouveau type de cours de la seance.
     */
    public void setType(Type_Cours type) {
        this.type = type;
    }

    /**
     * Getter de la liste des enseignants de la seance.
     * @return List des enseigants.
     */
    public List<Enseignant> getEnseignants() {
        return enseignants;
    }

    /**
     * Setter de la liste des enseignants de la seance.
     * @param enseignants List des nouveaux Enseignants
     */
    public void setEnseignants(List<Enseignant> enseignants) {
        this.enseignants = new ArrayList<Enseignant>(enseignants);
    }

    /**
     * Getter de la liste des salles de la seance.
     * @return List des salles.
     */
    public List<Salle> getSalles() {
        return salles;
    }

    /**
     * Setter de la liste des salles de la seance.
     * @param salles List des nouvelles Salles.
     */
    public void setSalles(List<Salle> salles) {
        this.salles = new ArrayList<Salle>(salles);
    }

    /**
     * Getter de la liste des groupes de la seance.
     * @return List des groupes.
     */
    public List<Groupe> getGroupes() {
        return groupes;
    }

    /**
     * Setter des la liste des groupes de la seance.
     * @param groupes List des nouveaux groupes.
     */
    public void setGroupes(List<Groupe> groupes) {
        this.groupes = new ArrayList<Groupe>(groupes);
    }

    /**
     * Convertit les informations de la seance sous forme d'un String.
     * @return String contenant les attributs de la seance.
     */
    @Override
    public String toString() {
        return "Seance{" +
                "id=" + id +
                ", semaine=" + semaine +
                ", jour=" + jour +
                ", heure_debut=" + heure_debut +
                ", heure_fin=" + heure_fin +
                ", etat=" + etat +
                ", cours=" + cours +
                ", type=" + type +
                ", enseignants=" + enseignants +
                ", salles=" + salles +
                ", groupes=" + groupes +
                '}';
    }
}
