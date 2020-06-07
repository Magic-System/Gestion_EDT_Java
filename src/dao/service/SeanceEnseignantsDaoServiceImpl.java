package dao.service;

import dao.DbService;
import modele.Seance_Enseignants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel
 */
public class SeanceEnseignantsDaoServiceImpl  extends DbService<Seance_Enseignants> {

    /**
     * Ajoute la seance enseignant recu en parametre dans la bdd.
     * @param objet Objet a rajouter dans la base de donnée.
     * @throws SQLException Probleme de requete.
     * @throws ClassNotFoundException Probleme de driver.
     */
    @Override
    public void ajouter(Seance_Enseignants objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement addSe = co.prepareStatement("INSERT INTO `seance_enseignants`(`ID_Seance`, `ID_Enseignant`) VALUES (?, ?)");
        addSe.setInt(1, objet.getSeance().getId());
        addSe.setInt(2, objet.getProf().getUtilisateur().getId());

        addSe.executeUpdate();
    }

    /**
     * Modifie la seance enseignant recu en parametre dans la bdd en fonction de son id seance.
     * @param objet Objet a mettre a jour dans la bdd.
     * @throws SQLException Probleme requete.
     * @throws ClassNotFoundException Probleme driver.
     */
    @Override
    public void modifier(Seance_Enseignants objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement majSe = co.prepareStatement("UPDATE `seance_enseignants` SET `ID_Enseignant`= ? WHERE `ID_Seance`= ?");
        majSe.setInt(1, objet.getProf().getUtilisateur().getId());
        majSe.setInt(2, objet.getSeance().getId());

        majSe.executeUpdate();
    }

    /**
     * Supprime la seance enseignant recu en parametre de la bdd.
     * @param objet Objet a supprimer dans la bdd.
     * @throws SQLException Erreur de requete.
     * @throws ClassNotFoundException Erreur de driver.
     */
    @Override
    public void supprimer(Seance_Enseignants objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement delSe = co.prepareStatement("DELETE FROM `seance_enseignants` WHERE `ID_Seance`= ? AND `ID_Enseignant`= ?");
        delSe.setInt(1, objet.getSeance().getId());
        delSe.setInt(2, objet.getProf().getUtilisateur().getId());

        delSe.executeUpdate();
    }

    /**
     * Recupere la table seance enseignant de la bdd.
     * @return ArrayList de seance enseignant.
     * @throws SQLException Erreur de requete.
     * @throws ClassNotFoundException Erreur de driver.
     */
    @Override
    public List<Seance_Enseignants> getAll() throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getAll = co.prepareStatement("SELECT * FROM `seance_enseignants` WHERE 1");
        ResultSet res = getAll.executeQuery();

        ArrayList<Seance_Enseignants> liste = new ArrayList<Seance_Enseignants>();

        while (res.next()) {
            SeanceDaoServiceImpl seance = new SeanceDaoServiceImpl();
            EnseignantDaoServiceImpl prof = new EnseignantDaoServiceImpl();

            liste.add(new Seance_Enseignants(seance.getById(res.getInt("ID_Seance")), prof.getById(res.getInt("ID_Groupe"))));
        }

        return liste;
    }

    /**
     * PAS UTILE.
     * @param idSeance Identifiant de l'objet a recuperer.
     * @return Seance enseignant ayant l'id seance recu en parametre.
     * @throws SQLException Erreur de requete.
     * @throws ClassNotFoundException Erreur de driver.
     */
    @Override
    public Seance_Enseignants getById(int idSeance) throws SQLException, ClassNotFoundException {
        /*
        Connection co = this.connexion();
        PreparedStatement getCoursById = co.prepareStatement("SELECT * FROM `seance_enseignants` WHERE ID_Seance = ?");
        getCoursById.setInt(1, idSeance);
        ResultSet res = getCoursById.executeQuery();

        Seance_Enseignants se = new Seance_Enseignants();

        while (res.next()) {
            SeanceDaoServiceImpl seance = new SeanceDaoServiceImpl();
            EnseignantDaoServiceImpl prof = new EnseignantDaoServiceImpl();
            se.setSeance(seance.getById(res.getInt("ID_Seance")));
            se.setProf(prof.getById(res.getInt("ID_Groupe")));
        }

        return se;

         */
        return null;
    }

    /**
     * Recupere l'ensemble des des enseignant pour une seance.
     * @param idSeance Identifiant seance.
     * @return ArrayList de Seance_Enseignant.
     * @throws SQLException Probleme de requete.
     * @throws ClassNotFoundException Probleme de drive.
     */
    public ArrayList<Seance_Enseignants> getAllBySeance(int idSeance) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getCoursById = co.prepareStatement("SELECT * FROM `seance_enseignants` WHERE ID_Seance = ?");
        getCoursById.setInt(1, idSeance);
        ResultSet res = getCoursById.executeQuery();

        ArrayList<Seance_Enseignants> liste = new ArrayList<>();

        while (res.next()) {
            Seance_Enseignants se = new Seance_Enseignants();
            SeanceDaoServiceImpl seance = new SeanceDaoServiceImpl();
            EnseignantDaoServiceImpl prof = new EnseignantDaoServiceImpl();
            se.setSeance(seance.getById(res.getInt("ID_Seance")));
            se.setProf(prof.getById(res.getInt("ID_Enseignant")));
        }

        return liste;
    }

    /**
     * Regarde si le couple enseignants/seance recu en parametre est possible en terme de créneau.
     * @param se Couple enseignant/seance a tester.
     * @return True si l'enseignant est libre sur le créneau de la séance, False si non.
     * @throws SQLException Probleme de requete.
     * @throws ClassNotFoundException Probleme de driver.
     */
    public boolean isFree(Seance_Enseignants se) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement sql = co.prepareStatement("SELECT COUNT(seance_enseignants.ID_Seance) AS Libre " +
                "FROM seance, seance_enseignants " +
                "WHERE seance_enseignants.ID_Seance = seance.ID " +
                "AND seance.Date = ? AND seance.Heure_Debut = ? AND seance.Heure_Fin = ? AND seance_enseignants.ID_Enseignant = ?");
        sql.setDate(1, Date.valueOf(se.getSeance().getJour()));
        sql.setTime(2, Time.valueOf(se.getSeance().getHeure_debut()));
        sql.setTime(3, Time.valueOf(se.getSeance().getHeure_fin()));
        sql.setInt(4, se.getProf().getUtilisateur().getId());
        ResultSet res = sql.executeQuery();

        while (res.next()) {
            if (res.getInt("Libre") == 0) {
                return true;
            }
            else {
                return false;
            }
        }

        return false;
    }
}
