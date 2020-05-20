package dao.service;

import dao.DbService;
import modele.Site;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SiteDaoServiceImpl extends DbService<Site> {

    /**
     * Ajout d'une Site dans la base de donnée.
     *
     * @param objet Site a rajouter dans la base de donnée.
     * @throws SQLException           Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    @Override
    public void ajouter(Site objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement ajoutCours = co.prepareStatement("INSERT INTO `site`(`Nom`) VALUES (?)");
        ajoutCours.setString(1, objet.getNom());
        ajoutCours.executeUpdate();

    }

    /**
     * Mise a jour d'un Site dans la base de donnée.
     *
     * @param objet Site a mettre a jour dans la bdd.
     * @throws SQLException           Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    @Override
    public void modifier(Site objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement updateCours = co.prepareStatement("UPDATE `site` SET `Nom`= ? WHERE ID = ?");
        updateCours.setString(1, objet.getNom());
        updateCours.setInt(2, objet.getId());
        updateCours.executeUpdate();

    }

    /**
     * Suppression d'un Site dans la base de donnée.
     *
     * @param objet Site a supprimer dans la bdd.
     * @throws SQLException           Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    @Override
    public void supprimer(Site objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement supprCours = co.prepareStatement("DELETE FROM `site` WHERE ?");
        supprCours.setInt(1, objet.getId());
        supprCours.executeUpdate();
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
        getAll.executeQuery();

        return null;
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
        PreparedStatement getCoursById = co.prepareStatement("SELECT * FROM `site` WHERE ?");
        getCoursById.setInt(1, id);
        getCoursById.executeQuery();

        return null;
    }
}
