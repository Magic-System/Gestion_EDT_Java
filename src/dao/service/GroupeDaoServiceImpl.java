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

public class GroupeDaoServiceImpl extends DbService<Groupe> {

    @Override
    public void ajouter(Groupe objet) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void modifier(Groupe objet) throws SQLException, ClassNotFoundException {

    }

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
            liste.add(new Groupe());
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
