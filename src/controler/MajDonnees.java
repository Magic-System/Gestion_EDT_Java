package controler;

import dao.service.*;
import modele.*;

import java.sql.SQLException;

public class MajDonnees {

    private SeanceDaoServiceImpl seanceDao;
    private EnseignantDaoServiceImpl profDao;
    private SeanceEnseignantsDaoServiceImpl seDao;
    private SeanceGroupeDaoServiceImpl sgDao;
    private SeanceSalleDaoServiceImpl ssDao;


    public MajDonnees() {
        this.seanceDao = new SeanceDaoServiceImpl();
        this.profDao = new EnseignantDaoServiceImpl();
        this.seDao = new SeanceEnseignantsDaoServiceImpl();
        this.sgDao = new SeanceGroupeDaoServiceImpl();
        this.ssDao = new SeanceSalleDaoServiceImpl();
    }

    /**
     * Affecter un enseignant dans une séance de cours à condition que l’enseignant soit disponible sur le créneau de la
     * séance. Par exemple, pour une séance sans enseignant ou en remplacement d’un autre enseignant.
     */
    public void addEnseignantToSeance(final Enseignant prof, Seance seance) {

    }

    /**
     * Affecter un groupe dans une séance de cours à condition que le groupe soit disponible sur le créneau de la séance et
     * que la (les) salle(s) soient de capacité suffisante. Par exemple, pour une séance sans groupe ou en remplacement
     * d’un autre groupe. Remarque : l’effectif de chaque groupe peut se comptabiliser à partir des étudiants affectés au
     * groupe.
     */

    /**
     * Modifier le cours (son nom ou son type) dans une séance de cours.
     * @param seance Objet Seance a modifier.
     * @param cours Nouveau cours de la Seance.
     * @param type Nouveau Type de la Seance.
     * @throws SQLException Probleme requete.
     * @throws ClassNotFoundException Probleme driver.
     */
    public void editSeance(Seance seance,final Cours cours,final Type_Cours type) throws SQLException, ClassNotFoundException {
        seance.setCours(cours);
        seance.setType(type);
        seanceDao.modifier(seance);
    }

    /**
     * Affecter une salle à une séance de cours à condition qu’elle soit disponible sur le créneau de la séance et que sa
     * capacité soit suffisante pour le(s) groupe(s).
     * @param seance Seance a modifier.
     * @param salle Salle a rajouter a la seance.
     */
    public void addSalleToSeance(Seance seance, final Salle salle) {

    }

    /**
     * Déplacer une séance de cours vers un autre créneau, sauf le samedi et dimanche et aux horaires d’ouverture de
     * l’école, dans une salle libre durant ce créneau et de capacité suffisante pour le(s) groupe(s), en fonction de la
     * disponibilité de l’enseignant et du (des) groupe(s) sur ce créneau
     */

    /**
     * Ajouter une séance de cours en lui affectant, si possible, toutes les informations nécessaires : la date (sauf samedi et
     * dimanche), l’heure de début et de fin (en respect des créneaux horaires d’ouverture de l’école), le(s) groupe(s) et
     * le(s) enseignant(s) disponible(s) à ce créneau horaire, la (les) salle(s) disponible(s) dont la capacité est suffisante,
     * l’état (« en cours de validation » ou « validé »), le cours et le type de cours. Remarque : pour la même séance, il ne
     * peut pas y avoir de doublon de groupe, d’enseignant et de salle.
     */

    /**
     *  Ajouter un enseignant à une séance de cours à condition que l’enseignant ne soit pas déjà affecté à cette séance et
     *  qu’il soit disponible sur le créneau de la séance
     */


    /**
     * Ajouter un groupe à une séance de cours à condition que le groupe ne soit pas déjà affecté à cette séance, qu’il soit
     * disponible sur le créneau de la séance et que la salle soit de capacité suffisante pour le(s) groupe(s)
     */

    /**
     * Annuler une séance de cours (changer l’état de cette séance à « annulée »).
     * @param seance Seance a annuler.
     * @throws SQLException Probleme requete.
     * @throws ClassNotFoundException Probleme driver.
     */
    public void annulerSeance(Seance seance) throws SQLException, ClassNotFoundException {
        if (seance.getEtat() != 0) {
            seance.setEtat(0);
            seanceDao.modifier(seance);
        } else {
            System.out.println("Séance déja annulée.");
        }
    }

    /**
     * Valider une séance (changer l’état de cette séance à « validée »).
     * @param seance Objet Seance a valider.
     * @throws SQLException Probleme requete.
     * @throws ClassNotFoundException Probleme driver.
     */
    public void validerSeance(Seance seance) throws SQLException, ClassNotFoundException {
        if (seance.getEtat() != 1) {
            seance.setEtat(1);
            seanceDao.modifier(seance);
        } else {
            System.out.println("Seance déja validée.");
        }
    }

    /**
     * Enlever un groupe ou/et un enseignant à une séance de cours
     */

}
