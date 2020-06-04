package dao.service;

import dao.DbService;
import modele.Promotion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PromotionDaoServiceImpl extends DbService<Promotion> {

    /**
     * Ajout d'une Promotion dans la base de donnée.
     *
     * @param objet Promotion a rajouter dans la base de donnée.
     * @throws SQLException           Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    @Override
    public void ajouter(Promotion objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement ajoutCours = co.prepareStatement("INSERT INTO `promotion`(`Nom`) VALUES (?)");
        ajoutCours.setString(1, objet.getNom());
        ajoutCours.executeUpdate();

    }

    /**
     * Mise a jour d'un Promotion dans la base de donnée.
     *
     * @param objet Promotion a mettre a jour dans la bdd.
     * @throws SQLException           Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    @Override
    public void modifier(Promotion objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement updateCours = co.prepareStatement("UPDATE `promotion` SET `Nom`= ? WHERE ID = ?");
        updateCours.setString(1, objet.getNom());
        updateCours.setInt(2, objet.getId());
        updateCours.executeUpdate();

    }

    /**
     * Suppression d'un Promotion dans la base de donnée.
     *
     * @param objet Promotion a supprimer dans la bdd.
     * @throws SQLException           Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    @Override
    public void supprimer(Promotion objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement supprCours = co.prepareStatement("DELETE FROM `promotion` WHERE ?");
        supprCours.setInt(1, objet.getId());
        supprCours.executeUpdate();
    }

    /**
     * Retourne l'ensemble du contenu d'une table de la bdd.
     *
     * @return List<Promotion> Retourne la liste des Promotion contenu dans la table.
     * @throws SQLException           Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    @Override
    public List<Promotion> getAll() throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getAll = co.prepareStatement("SELECT * FROM `promotion` WHERE 1");
        getAll.executeQuery();

        return null;
    }

    /**
     * Recupere la Promotion de la bdd correspondant a l'id recu en parametre.
     *
     * @param id Identifiant de la Promotion a recuperer.
     * @return Retourne la Promotion de la bdd.
     * @throws SQLException           Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    @Override
    public Promotion getById(int id) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getCoursById = co.prepareStatement("SELECT * FROM `promotion` WHERE ?");
        getCoursById.setInt(1, id);
        getCoursById.executeQuery();
        
        return null;
    }
}
