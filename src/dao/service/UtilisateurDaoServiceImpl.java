package dao.service;

import dao.DbService;
import modele.Utilisateur;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDaoServiceImpl extends DbService<Utilisateur> {

    public UtilisateurDaoServiceImpl() {
        super(Utilisateur.class);
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
        co.close();

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
        co.close();
    }
}
