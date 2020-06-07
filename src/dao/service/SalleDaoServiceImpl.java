package dao.service;

import dao.DbService;
import modele.Salle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel
 */
public class SalleDaoServiceImpl extends DbService<Salle> {

    /**
     * Pas utilisé.
     * @param objet Objet a rajouter dans la base de donnée.
     */
    @Override
    public void ajouter(Salle objet) throws SQLException, ClassNotFoundException {

    }

    /**
     * Pas utilisé.
     * @param objet Objet a mettre a jour dans la bdd.
     */
    @Override
    public void modifier(Salle objet) throws SQLException, ClassNotFoundException {

    }

    /**
     * Pas utilisé.
     * @param objet Objet a supprimer dans la bdd.
     */
    @Override
    public void supprimer(Salle objet) throws SQLException, ClassNotFoundException {

    }

    /**
     * Recupere la table Salle depuis la bdd.
     * @return ArrayList de Salle.
     * @throws SQLException Probleme de requete.
     * @throws ClassNotFoundException Probleme de driver.
     */
    @Override
    public ArrayList<Salle> getAll() throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getAll = co.prepareStatement("SELECT * FROM `salle` WHERE 1");
        ResultSet res = getAll.executeQuery();

        ArrayList<Salle> liste = new ArrayList<Salle>();

        while (res.next()) {
            SiteDaoServiceImpl site = new SiteDaoServiceImpl();
            liste.add(new Salle(res.getInt("ID"), res.getString("Nom"), res.getInt("Capacite"), site.getById(res.getInt("ID_Site"))));
        }

        co.close();
        return liste;
    }

    /**
     * Recupere une salle de la bdd en fonction de son id.
     * @param id Identifiant de l'objet a recuperer.
     * @return Salle ayant l'id recu en parametre.
     * @throws SQLException Probleme de requete.
     * @throws ClassNotFoundException Probleme de driver.
     */
    @Override
    public Salle getById(int id) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getCoursById = co.prepareStatement("SELECT * FROM `salle` WHERE ID = ?");
        getCoursById.setInt(1, id);
        ResultSet res = getCoursById.executeQuery();

        Salle salle = new Salle();

        while (res.next()) {
            salle.setId(res.getInt("ID"));
            salle.setNom(res.getString("Nom"));
            salle.setCapacite(res.getInt("Capacite"));
            SiteDaoServiceImpl site = new SiteDaoServiceImpl();
            salle.setSite(site.getById(res.getInt("ID_Site")));
        }

        co.close();
        return salle;
    }
}
