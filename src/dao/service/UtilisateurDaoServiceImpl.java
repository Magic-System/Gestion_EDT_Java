package dao.service;

import dao.DbService;
import modele.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UtilisateurDaoServiceImpl extends DbService<Utilisateur> {

    /**
     * Ajout d'une Utilisateur dans la base de donnée.
     *
     * @param objet Utilisateur a rajouter dans la base de donnée.
     * @throws SQLException           Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    @Override
    public void ajouter(Utilisateur objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement ajoutCours = co.prepareStatement("INSERT INTO `utilisateur`(`Nom`) VALUES (?)");
        ajoutCours.setString(1, objet.getNom());
        ajoutCours.executeUpdate();

    }

    /**
     * Mise a jour d'un Utilisateur dans la base de donnée.
     *
     * @param objet Utilisateur a mettre a jour dans la bdd.
     * @throws SQLException           Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    @Override
    public void modifier(Utilisateur objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement updateCours = co.prepareStatement("UPDATE `utilisateur` SET `Nom`= ? WHERE ID = ?");
        updateCours.setString(1, objet.getNom());
        updateCours.setInt(2, objet.getId());
        updateCours.executeUpdate();

    }

    /**
     * Suppression d'un Utilisateur dans la base de donnée.
     *
     * @param objet Utilisateur a supprimer dans la bdd.
     * @throws SQLException           Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    @Override
    public void supprimer(Utilisateur objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement supprCours = co.prepareStatement("DELETE FROM `utilisateur` WHERE ?");
        supprCours.setInt(1, objet.getId());
        supprCours.executeUpdate();
    }

    /**
     * Retourne l'ensemble du contenu d'une table de la bdd.
     *
     * @return List<Utilisateur> Retourne la liste des Utilisateur contenu dans la table.
     * @throws SQLException           Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    @Override
    public List<Utilisateur> getAll() throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getAll = co.prepareStatement("SELECT * FROM `utilisateur` WHERE 1");
        getAll.executeQuery();

        return null;
    }

    /**
     * Recupere la Utilisateur de la bdd correspondant a l'id recu en parametre.
     *
     * @param id Identifiant de la Utilisateur a recuperer.
     * @return Retourne la Promotion de la bdd.
     * @throws SQLException           Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    @Override
    public Utilisateur getById(int id) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getCoursById = co.prepareStatement("SELECT * FROM `utilisateur` WHERE ?");
        getCoursById.setInt(1, id);
        getCoursById.executeQuery();

        return null;
    }
}
