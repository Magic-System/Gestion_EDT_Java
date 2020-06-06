package controler;

import dao.service.*;
import modele.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class MajDonnees {

    private SeanceDaoServiceImpl seanceDao;
    private SeanceEnseignantsDaoServiceImpl seDao;
    private SeanceGroupeDaoServiceImpl sgDao;
    private SeanceSalleDaoServiceImpl ssDao;


    public MajDonnees() {
        this.seanceDao = new SeanceDaoServiceImpl();
        this.seDao = new SeanceEnseignantsDaoServiceImpl();
        this.sgDao = new SeanceGroupeDaoServiceImpl();
        this.ssDao = new SeanceSalleDaoServiceImpl();
    }

    /**
     * Affecter un enseignant dans une séance de cours à condition que l’enseignant soit disponible sur le créneau de la
     * séance. Par exemple, pour une séance sans enseignant ou en remplacement d’un autre enseignant.
     */
    public void addEnseignantToSeance(final Enseignant prof, Seance seance) {

        /** TEST A FAIRE **/

        Seance_Enseignants se = new Seance_Enseignants();
        se.setProf(prof);
        se.setSeance(seance);
        try {
            seDao.ajouter(se);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Affecter un groupe dans une séance de cours à condition que le groupe soit disponible sur le créneau de la séance et
     * que la (les) salle(s) soient de capacité suffisante. Par exemple, pour une séance sans groupe ou en remplacement
     * d’un autre groupe. Remarque : l’effectif de chaque groupe peut se comptabiliser à partir des étudiants affectés au
     * groupe.
     */
    public void addGroupeToSeance(final Groupe grp, Seance seance) {

        /** TEST A FAIRE **/

        Seance_Groupes sg = new Seance_Groupes();
        sg.setSeance(seance);
        sg.setGroupe(grp);
        try {
            sgDao.ajouter(sg);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Affecter une salle à une séance de cours à condition qu’elle soit disponible sur le créneau de la séance et que sa
     * capacité soit suffisante pour le(s) groupe(s).
     * @param seance Seance a modifier.
     * @param salle Salle a rajouter a la seance.
     */
    public void addSalleToSeance(Seance seance, final Salle salle) {

        /** TEST A FAIRE **/

        Seance_Salles ss = new Seance_Salles();
        ss.setSeance(seance);
        ss.setSalle(salle);
        try {
            ssDao.ajouter(ss);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

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
     * Déplacer une séance de cours vers un autre créneau, sauf le samedi et dimanche et aux horaires d’ouverture de
     * l’école, dans une salle libre durant ce créneau et de capacité suffisante pour le(s) groupe(s), en fonction de la
     * disponibilité de l’enseignant et du (des) groupe(s) sur ce créneau.
     * @param seance Seance a modifier.
     * @param heure_debut Nouvelles heure de début de la seance.
     * @param heure_fin Nouvelle heure de fin de la séance.
     * @param jour Nouvelle date de la seance.
     */
    public void changeCreneau(Seance seance, LocalTime heure_debut, LocalTime heure_fin, LocalDate jour) {

        /** TEST A FAIRE **/

        seance.setHeure_debut(heure_debut);
        seance.setHeure_fin(heure_fin);
        seance.setJour(jour);
        try {
            seanceDao.modifier(seance);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }


    /**
     * Ajouter une séance de cours en lui affectant, si possible, toutes les informations nécessaires : la date (sauf samedi et
     * dimanche), l’heure de début et de fin (en respect des créneaux horaires d’ouverture de l’école), le(s) groupe(s) et
     * le(s) enseignant(s) disponible(s) à ce créneau horaire, la (les) salle(s) disponible(s) dont la capacité est suffisante,
     * l’état (« en cours de validation » ou « validé »), le cours et le type de cours. Remarque : pour la même séance, il ne
     * peut pas y avoir de doublon de groupe, d’enseignant et de salle.
     */
    public void creerNouvelleSeance() {

    }


    /**
     * Annuler une séance de cours (changer l’état de cette séance à « annulée »).
     * @param seance Seance a annuler.
     */
    public void annulerSeance(Seance seance) {
        if (seance.getEtat() != 0) {
            seance.setEtat(0);
            try {
                seanceDao.modifier(seance);
                System.out.println("Séance annulée : " + seance.toString());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        } else {
            System.out.println("Séance déja annulée.");
        }
    }

    /**
     * Valider une séance (changer l’état de cette séance à « validée »).
     * @param seance Objet Seance a valider.
     */
    public void validerSeance(Seance seance){
        if (seance.getEtat() != 1) {
            seance.setEtat(1);
            try {
                seanceDao.modifier(seance);
                System.out.println("Seance validée : " + seance.toString());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        } else {
            System.out.println("Seance déja validée.");
        }
    }

    /**
     * Enlever un groupe d'une séance de cours.
     * @param seance Seance a modifier.
     * @param grp Groupe a supprimer de la séance.
     */
    public void removeGroupeFromSeance(final Groupe grp, final Seance seance) {
        Seance_Groupes sg = new Seance_Groupes();
        sg.setGroupe(grp);
        sg.setSeance(seance);
        try {
            sgDao.supprimer(sg);
            System.out.println(grp.toString() + " supprimé de " + seance.toString());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Enlever un enseignant d'une séance de cours.
     * @param seance Seance a modifier.
     * @param prof Enseignant a supprimer de la séance.
     */
    public void removeEnseignantFromSeance(final Enseignant prof, final Seance seance) {
        Seance_Enseignants se = new Seance_Enseignants();
        se.setProf(prof);
        se.setSeance(seance);
        try {
            seDao.supprimer(se);
            System.out.println(prof.toString() + " supprimé de " + seance.toString());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Enlever une salle d'une séance de cours.
     * @param seance Seance a modifier.
     * @param salle Salle a supprimer de la séance.
     */
    public void removeSalleFromSeance(final Salle salle, final Seance seance) {
        Seance_Salles ss = new Seance_Salles();
        ss.setSalle(salle);
        ss.setSeance(seance);
        try {
            ssDao.supprimer(ss);
            System.out.println(salle.toString() + " supprimée de " + seance.toString());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

}
