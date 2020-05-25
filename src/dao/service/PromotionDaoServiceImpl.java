package dao.service;

import dao.DbService;
import modele.Promotion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PromotionDaoServiceImpl extends DbService<Promotion> {

    public PromotionDaoServiceImpl() {
        super(Promotion.class);
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
        co.close();
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
        co.close();
    }
}
