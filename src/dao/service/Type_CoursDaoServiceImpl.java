package dao.service;

import dao.DbService;
import modele.Type_Cours;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Type_CoursDaoServiceImpl extends DbService<Type_Cours> {

    /**
     * Ajout d'une Type_Cours dans la base de donnée.
     *
     * @param objet Type_Cours a rajouter dans la base de donnée.
     * @throws SQLException           Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    @Override
    public void ajouter(Type_Cours objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement ajoutCours = co.prepareStatement("INSERT INTO `type_cours`(`Nom`) VALUES (?)");
        ajoutCours.setString(1, objet.getNom());
        ajoutCours.executeUpdate();

    }

    /**
     * Mise a jour d'un Type_Cours dans la base de donnée.
     *
     * @param objet Type_Cours a mettre a jour dans la bdd.
     * @throws SQLException           Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    @Override
    public void modifier(Type_Cours objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement updateCours = co.prepareStatement("UPDATE `type_cours` SET `Nom`= ? WHERE ID = ?");
        updateCours.setString(1, objet.getNom());
        updateCours.setInt(2, objet.getId());
        updateCours.executeUpdate();

    }

    /**
     * Suppression d'un Type_Cours dans la base de donnée.
     *
     * @param objet Type_Cours a supprimer dans la bdd.
     * @throws SQLException           Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    @Override
    public void supprimer(Type_Cours objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement supprCours = co.prepareStatement("DELETE FROM `type_cours` WHERE ?");
        supprCours.setInt(1, objet.getId());
        supprCours.executeUpdate();
    }

    /**
     * Retourne l'ensemble du contenu d'une table de la bdd.
     *
     * @return List<Type_Cours> Retourne la liste des Type_Cours contenu dans la table.
     * @throws SQLException           Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    @Override
    public List<Type_Cours> getAll() throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getAll = co.prepareStatement("SELECT * FROM `type_cours` WHERE 1");
        getAll.executeQuery();

        return null;
    }

    /**
     * Recupere la Type_Cours de la bdd correspondant a l'id recu en parametre.
     *
     * @param id Identifiant de la Type_Cours a recuperer.
     * @return Retourne la Promotion de la bdd.
     * @throws SQLException           Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    @Override
    public Type_Cours getById(int id) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getCoursById = co.prepareStatement("SELECT * FROM `type_cours` WHERE ?");
        getCoursById.setInt(1, id);
        getCoursById.executeQuery();

        return null;
    }
}
