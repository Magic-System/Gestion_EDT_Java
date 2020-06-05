package dao.service;

import dao.DbService;
import modele.Promotion;
import modele.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PromotionDaoServiceImpl extends DbService<Promotion> {

    @Override
    public void ajouter(Promotion objet) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void modifier(Promotion objet) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void supprimer(Promotion objet) throws SQLException, ClassNotFoundException {

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
        ResultSet res = getAll.executeQuery();

        ArrayList<Promotion> liste = new ArrayList<Promotion>();

        while (res.next()) {
            liste.add(new Promotion(res.getInt("id"), res.getString("nom")));
        }

        return liste;
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
        PreparedStatement getCoursById = co.prepareStatement("SELECT * FROM `promotion` WHERE id = ?");
        getCoursById.setInt(1, id);
        ResultSet res = getCoursById.executeQuery();

        Promotion promo = new Promotion();

        while (res.next()) {
            promo.setId(res.getInt("id"));
            promo.setNom(res.getString("nom"));
        }

        return promo;
    }
}
