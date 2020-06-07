package dao.service;

import dao.DbService;
import modele.Groupe;
import modele.Promotion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel
 */
public class GroupeDaoServiceImpl extends DbService<Groupe> {

    /**
     * Pas utilisé.
     * @param objet Objet a rajouter dans la base de donnée.
     */
    @Override
    public void ajouter(Groupe objet) throws SQLException, ClassNotFoundException {

    }

    /**
     * Pas utilisé.
     * @param objet Objet a mettre a jour dans la bdd.
     */
    @Override
    public void modifier(Groupe objet) throws SQLException, ClassNotFoundException {

    }

    /**
     * Pas utilisé.
     * @param objet Objet a supprimer dans la bdd.
     */
    @Override
    public void supprimer(Groupe objet) throws SQLException, ClassNotFoundException {

    }

    @Override
    public List<Groupe> getAll() throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getAll = co.prepareStatement("SELECT * FROM `groupe` WHERE 1");
        ResultSet res = getAll.executeQuery();

        ArrayList<Groupe> liste = new ArrayList<Groupe>();

        while (res.next()) {
            Groupe grp = new Groupe();
            grp.setId(res.getInt("ID"));
            grp.setNom(res.getString("Nom"));
            PromotionDaoServiceImpl promo = new PromotionDaoServiceImpl();
            grp.setPromotion(promo.getById(res.getInt("ID_Promotion")));
            liste.add(grp);
        }

        return liste;
    }

    @Override
    public Groupe getById(int id) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getCoursById = co.prepareStatement("SELECT * FROM `groupe` WHERE id = ?");
        getCoursById.setInt(1, id);
        ResultSet res = getCoursById.executeQuery();

        Groupe grp = new Groupe();

        while (res.next()) {
            grp.setId(res.getInt("ID"));
            grp.setNom(res.getString("Nom"));
            PromotionDaoServiceImpl promo = new PromotionDaoServiceImpl();
            grp.setPromotion(promo.getById(res.getInt("ID_Promotion")));
        }

        return grp;
    }
}
