package dao.service;

import dao.DbService;
import modele.Seance;
import modele.Seance_Groupes;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author Daniel
 */
public class SeanceGroupeDaoServiceImpl extends DbService<Seance_Groupes> {

    /**
     * Ajoute la seance groupe recu en parametre dans la bdd.
     * @param objet Objet a rajouter dans la base de donnée.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public void ajouter(Seance_Groupes objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement addSg = co.prepareStatement("INSERT INTO `seance_groupes`(`ID_Seance`, `ID_Groupe`) VALUES (?, ?)");
        addSg.setInt(1, objet.getSeance().getId());
        addSg.setInt(2, objet.getGroupe().getId());

        addSg.executeUpdate();
    }

    /**
     * Modifie la seance groupe recu en parametre dans la bdd en fonction de son id seance.
     * @param objet Objet a mettre a jour dans la bdd.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public void modifier(Seance_Groupes objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement majSg = co.prepareStatement("UPDATE `seance_groupes` SET `ID_Groupe`= ? WHERE `ID_Seance`= ?");
        majSg.setInt(1, objet.getGroupe().getId());
        majSg.setInt(2, objet.getSeance().getId());

        majSg.executeUpdate();
    }

    /**
     * Supprime la seance groupe recu en parametre de la bdd.
     * @param objet Objet a supprimer dans la bdd.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public void supprimer(Seance_Groupes objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement delSg = co.prepareStatement("DELETE FROM `seance_groupes` WHERE `ID_Seance`= ? AND `ID_Groupe`= ?");
        delSg.setInt(1, objet.getSeance().getId());
        delSg.setInt(2, objet.getGroupe().getId());

        delSg.executeUpdate();
    }

    /**
     * Recupere la table seance groupe de la bdd.
     * @return ArrayList de seance groupe.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public List<Seance_Groupes> getAll() throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getAll = co.prepareStatement("SELECT * FROM `seance_groupes` WHERE 1");
        ResultSet res = getAll.executeQuery();

        ArrayList<Seance_Groupes> liste = new ArrayList<Seance_Groupes>();

        while (res.next()) {
            SeanceDaoServiceImpl seance = new SeanceDaoServiceImpl();
            GroupeDaoServiceImpl grp = new GroupeDaoServiceImpl();

            liste.add(new Seance_Groupes(seance.getById(res.getInt("ID_Seance")), grp.getById(res.getInt("ID_Groupe"))));
        }

        return liste;
    }

    /**
     * Pas utilisé.
     * @param id Identifiant de l'objet a recuperer.
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public Seance_Groupes getById(int id) throws SQLException, ClassNotFoundException {
        /*
        Connection co = this.connexion();
        PreparedStatement getCoursById = co.prepareStatement("SELECT * FROM `seance_groupes` WHERE ID_Seance = ?");
        getCoursById.setInt(1, id);
        ResultSet res = getCoursById.executeQuery();

        Seance_Groupes sg = new Seance_Groupes();

        while (res.next()) {
            SeanceDaoServiceImpl seance = new SeanceDaoServiceImpl();
            GroupeDaoServiceImpl grp = new GroupeDaoServiceImpl();
            sg.setSeance(seance.getById(res.getInt("ID_Seance")));
            sg.setGroupe(grp.getById(res.getInt("ID_Groupe")));
        }

        return sg;
        */
        return null;
    }

    /**
     * Recupere la liste des id de seance ayant le groupe d'id recu en parametre.
     * @param id_groupe Id du groupe.
     * @return liste des id seance.
     * @throws SQLException Probleme de requete.
     * @throws ClassNotFoundException Probleme de driver.
     */
    public HashSet<Integer> getSeanceIdByGroupe(int id_groupe) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getCoursById = co.prepareStatement("SELECT * FROM `seance_groupes` WHERE ID_Groupe = ?");
        getCoursById.setInt(1, id_groupe);
        ResultSet res = getCoursById.executeQuery();

        HashSet<Integer> idsSeance = new HashSet<Integer>();

        while (res.next()) {
            idsSeance.add(res.getInt("ID_Seance"));
        }

        return idsSeance;
    }

    /**
     * Regarde si le couple groupe/seance recu en parametre est possible en terme de créneau.
     * @param sg Couple groupe/séance a tester.
     * @return True si l'groupe est libre sur le créneau de la séance, False si non.
     * @throws SQLException Probleme de requete.
     * @throws ClassNotFoundException Probleme de driver.
     */
    public boolean isFree(Seance_Groupes sg) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement sql = co.prepareStatement("SELECT COUNT(seance_groupes.ID_Seance) AS Libre " +
                "FROM seance, seance_groupes " +
                "WHERE seance_groupes.ID_Seance = seance.ID " +
                "AND seance.Date = ? AND seance.Heure_Debut = ? AND seance.Heure_Fin = ? AND seance_groupes.ID_Groupe = ?");
        sql.setDate(1, Date.valueOf(sg.getSeance().getJour()));
        sql.setTime(2, Time.valueOf(sg.getSeance().getHeure_debut()));
        sql.setTime(3, Time.valueOf(sg.getSeance().getHeure_fin()));
        sql.setInt(4, sg.getGroupe().getId());
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
