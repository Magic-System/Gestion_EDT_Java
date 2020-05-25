package dao.service;

import dao.DbService;
import modele.Site;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SiteDaoServiceImpl extends DbService<Site> {


    public SiteDaoServiceImpl() {
        super(Site.class);
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
        co.close();

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
        co.close();
    }

}
