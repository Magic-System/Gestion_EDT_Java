package modele;

import java.time.LocalDate;
import java.time.LocalTime;

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
 * Etat : 0 = annulé, 1 = validé, 2 = en cours de validation.
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

    /**
     * Constructeur par défaut.
     */
    public Seance() {
        this.id = -1;
        this.semaine = 0;
        this.jour = LocalDate.MIN;
        this.heure_debut = LocalTime.MIN;
        this.heure_fin = LocalTime.MAX;
        this.etat = 0;
        this.cours = new Cours();
        this.type = new Type_Cours();
    }

    /**
     * Constructeur avec tout les parametres.
     * @param semaine Numéro de la semaine.
     * @param jour Jour de la semaine.
     * @param heure_debut Heure de début.
     * @param heure_fin Heure de fin.
     * @param etat Etat de la seance.
     * @param cours Cours enseigné.
     * @param type Type du cours.
     */
    public Seance(int semaine, LocalDate jour, LocalTime heure_debut, LocalTime heure_fin, int etat, Cours cours, Type_Cours type) {
        this.id = -1;
        this.semaine = semaine;
        this.jour = jour;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
        this.etat = etat;
        this.cours = cours;
        this.type = type;
    }

    public Seance(Seance seance) {
        this.id = seance.id;
        this.semaine = seance.semaine;
        this.jour = seance.jour;
        this.heure_debut = seance.heure_debut;
        this.heure_fin = seance.heure_fin;
        this.etat = seance.etat;
        this.cours = new Cours(seance.cours);
        this.type = new Type_Cours(seance.type);
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
                '}';
    }
}
