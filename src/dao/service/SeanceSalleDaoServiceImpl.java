package dao.service;

import dao.DbService;
import modele.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeanceSalleDaoServiceImpl extends DbService<Seance_Salles> {

    /**
     * Ajoute l'objet Seance_Salle recu dans la bdd.
     * @param objet Objet a ajouter dans la bdd.
     * @throws SQLException Probleme de requete.
     * @throws ClassNotFoundException Probleme de driver.
     */
    @Override
    public void ajouter(Seance_Salles objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement addSg = co.prepareStatement("INSERT INTO `seance_salles`(`ID_Seance`, `ID_Salle`) VALUES (?, ?)");
        addSg.setInt(1, objet.getSeance().getId());
        addSg.setInt(2, objet.getSalle().getId());

        addSg.executeUpdate();
    }

    /**
     * Modifie l'objet Seance_Salle recu dans la bdd.
     * @param objet Objet a modifier dans la bdd.
     * @throws SQLException Probleme de requete.
     * @throws ClassNotFoundException Probleme de driver.
     */
    @Override
    public void modifier(Seance_Salles objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement majSg = co.prepareStatement("UPDATE `seance_salles` SET `ID_Salle`= ? WHERE `ID_Seance`= ?");
        majSg.setInt(1, objet.getSalle().getId());
        majSg.setInt(2, objet.getSeance().getId());

        majSg.executeUpdate();
    }

    /**
     * Supprime l'objet Seance_Salle recu de la bdd.
     * @param objet Objet a supprimer dans la bdd.
     * @throws SQLException Probleme de requete.
     * @throws ClassNotFoundException Probleme de driver.
     */
    @Override
    public void supprimer(Seance_Salles objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement delSg = co.prepareStatement("DELETE FROM `seance_salles` WHERE `ID_Seance`= ? AND `ID_Salle`= ?");
        delSg.setInt(1, objet.getSeance().getId());
        delSg.setInt(2, objet.getSalle().getId());

        delSg.executeUpdate();
    }

    /**
     * Recupere la liste de tout les couples seance/salle.
     * @return ArrayListe de Seance_Salle.
     * @throws SQLException Probleme de requete.
     * @throws ClassNotFoundException Probleme de driver.
     */
    @Override
    public List<Seance_Salles> getAll() throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getAll = co.prepareStatement("SELECT * FROM `seance_salles` WHERE 1");
        ResultSet res = getAll.executeQuery();

        ArrayList<Seance_Salles> liste = new ArrayList<Seance_Salles>();

        while (res.next()) {
            SeanceDaoServiceImpl seance = new SeanceDaoServiceImpl();
            SalleDaoServiceImpl salle = new SalleDaoServiceImpl();

            liste.add(new Seance_Salles(seance.getById(res.getInt("ID_Seance")), salle.getById(res.getInt("ID_Salle"))));
        }

        return liste;
    }

    /**
     * Pas utilisé
     * @param id Identifiant de l'objet a recuperer.
     * @return null.
     * @throws SQLException Probleme de requete.
     * @throws ClassNotFoundException Probleme de driver.
     */
    @Override
    public Seance_Salles getById(int id) throws SQLException, ClassNotFoundException {
        /*
        Connection co = this.connexion();
        PreparedStatement getCoursById = co.prepareStatement("SELECT * FROM `seance_salles` WHERE ID = ?");
        getCoursById.setInt(1, id);
        ResultSet res = getCoursById.executeQuery();

        Seance_Salles sSalle = new Seance_Salles();

        while (res.next()) {
            SeanceDaoServiceImpl seance = new SeanceDaoServiceImpl();
            SalleDaoServiceImpl salle = new SalleDaoServiceImpl();
            sSalle.setSeance(seance.getById(res.getInt("ID_Seance")));
            sSalle.setSalle(salle.getById(res.getInt("ID_Groupe")));
        }

        return sSalle;
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
    public ArrayList<Seance_Salles> getAllBySeance(int idSeance) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getCoursById = co.prepareStatement("SELECT * FROM `seance_salles` WHERE ID_Seance = ?");
        getCoursById.setInt(1, idSeance);
        ResultSet res = getCoursById.executeQuery();

        ArrayList<Seance_Salles> liste = new ArrayList<>();

        while (res.next()) {
            Seance_Salles se = new Seance_Salles();
            SeanceDaoServiceImpl seance = new SeanceDaoServiceImpl();
            SalleDaoServiceImpl salle = new SalleDaoServiceImpl();
            se.setSeance(seance.getById(res.getInt("ID_Seance")));
            se.setSalle(salle.getById(res.getInt("ID_Salle")));
        }

        return liste;
    }

    /**
     * Regarde si le couple salle/seance recu en parametre est possible en terme de créneau.
     * @param ss Couple salle/séance a tester.
     * @return True si l'groupe est libre sur le créneau de la séance, False si non.
     * @throws SQLException Probleme de requete.
     * @throws ClassNotFoundException Probleme de driver.
     */
    public boolean isFree(Seance_Salles ss) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement sql = co.prepareStatement("SELECT COUNT(seance_salles.ID_Seance) AS Libre " +
                "FROM seance, seance_salles " +
                "WHERE seance_salles.ID_Seance = seance.ID " +
                "AND seance.Date = ? AND seance.Heure_Debut = ? AND seance.Heure_Fin = ? AND seance_salles.ID_Salle = ?");
        sql.setDate(1, Date.valueOf(ss.getSeance().getJour()));
        sql.setTime(2, Time.valueOf(ss.getSeance().getHeure_debut()));
        sql.setTime(3, Time.valueOf(ss.getSeance().getHeure_fin()));
        sql.setInt(4, ss.getSalle().getId());
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
