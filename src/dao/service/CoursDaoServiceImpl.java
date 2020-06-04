package dao.service;

import dao.DbService;
import modele.Cours;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 */
public class CoursDaoServiceImpl extends DbService<Cours>{

    public CoursDaoServiceImpl() {
    }

    /**
     * Ajout d'un Cours dans la base de donnée.
     * @param objet Cours a rajouter dans la base de donnée.
     * @throws SQLException           Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    @Override
    public void ajouter(Cours objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement ajoutCours = co.prepareStatement("INSERT INTO `cours`(`Nom`) VALUES (?)");
        ajoutCours.setString(1, objet.getNom());
        ajoutCours.executeUpdate();
    }

    /**
     * Mise a jour d'un Cours dans la base de donnée.
     * @param objet Cours a mettre a jour dans la bdd.
     * @throws SQLException           Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    @Override
    public void modifier(Cours objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement updateCours = co.prepareStatement("UPDATE `cours` SET `Nom`= ? WHERE ID = ?");
        updateCours.setString(1, objet.getNom());
        updateCours.setInt(2, objet.getId());
        updateCours.executeUpdate();
    }

    /**
     * Suppression d'un Cours dans la base de donnée.
     * @param objet Cours a supprimer dans la bdd.
     * @throws SQLException           Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    @Override
    public void supprimer(Cours objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement supprCours = co.prepareStatement("DELETE FROM `cours` WHERE ?");
        supprCours.setInt(1, objet.getId());
        supprCours.executeUpdate();
    }

    /**
     * Retourne l'ensemble du contenu d'une table de la bdd.
     * @return List<Cours> Retourne la liste des Cours contenu dans la table.
     * @throws SQLException           Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    @Override
    public List<Cours> getAll() throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getAll = co.prepareStatement("SELECT * FROM `cours` WHERE 1");
        getAll.executeQuery();

        return null;
    }

    /**
     * Recupere le Cours de la bdd correspondant a l'id recu en parametre.
     * @param id Identifiant du Cours a recuperer.
     * @return Retourne l'objet de la bdd.
     * @throws SQLException           Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    @Override
    public Cours getById(int id) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getCoursById = co.prepareStatement("SELECT * FROM `cours` WHERE ?");
        getCoursById.setInt(1, id);
        getCoursById.executeQuery();

        return null;
    }
}
