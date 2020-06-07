package dao.service;

import dao.DbService;
import modele.Site;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel
 */
public class SiteDaoServiceImpl extends DbService<Site> {

    /**
     * Pas utilisé.
     * @param objet Objet a rajouter dans la base de donnée.
     */
    @Override
    public void ajouter(Site objet) throws SQLException, ClassNotFoundException {

    }

    /**
     * Pas utilisé.
     * @param objet Objet a mettre a jour dans la bdd.
     */
    @Override
    public void modifier(Site objet) throws SQLException, ClassNotFoundException {

    }

    /**
     * Pas utilisé.
     * @param objet Objet a supprimer dans la bdd.
     */
    @Override
    public void supprimer(Site objet) throws SQLException, ClassNotFoundException {

    }

    /**
     * Retourne l'ensemble du contenu d'une table de la bdd.
     *
     * @return List<Site> Retourne la liste des Site contenu dans la table.
     * @throws SQLException           Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    @Override
    public List<Site> getAll() throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getAll = co.prepareStatement("SELECT * FROM `site` WHERE 1");
        ResultSet res = getAll.executeQuery();

        ArrayList<Site> liste = new ArrayList<Site>();

        while (res.next()) {
            liste.add(new Site(res.getInt("ID"), res.getString("Nom")));
        }

        co.close();
        return liste;
    }

    /**
     * Recupere la Site de la bdd correspondant a l'id recu en parametre.
     *
     * @param id Identifiant de la Site a recuperer.
     * @return Retourne la Promotion de la bdd.
     * @throws SQLException           Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    @Override
    public Site getById(int id) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getCoursById = co.prepareStatement("SELECT * FROM `site` WHERE ID = ?");
        getCoursById.setInt(1, id);
        ResultSet res = getCoursById.executeQuery();

        Site site = new Site();

        while (res.next()) {
            site.setId(res.getInt("ID"));
            site.setNom(res.getString("Nom"));
        }

        co.close();
        return site;
    }
}
