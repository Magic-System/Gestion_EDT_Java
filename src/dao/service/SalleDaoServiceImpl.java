package dao.service;

import dao.DbService;
import modele.Salle;
import modele.Site;
import modele.Type_Cours;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalleDaoServiceImpl extends DbService<Salle> {

    @Override
    public void ajouter(Salle objet) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void modifier(Salle objet) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void supprimer(Salle objet) throws SQLException, ClassNotFoundException {

    }

    @Override
    public List<Salle> getAll() throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getAll = co.prepareStatement("SELECT * FROM `salle` WHERE 1");
        ResultSet res = getAll.executeQuery();

        ArrayList<Salle> liste = new ArrayList<Salle>();

        while (res.next()) {
            SiteDaoServiceImpl site = new SiteDaoServiceImpl();
            liste.add(new Salle(res.getInt("ID"), res.getString("Nom"), res.getInt("Capacite"), site.getById(res.getInt("ID_Site"))));
        }

        return liste;
    }

    @Override
    public Salle getById(int id) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getCoursById = co.prepareStatement("SELECT * FROM `salle` WHERE ID = ?");
        getCoursById.setInt(1, id);
        ResultSet res = getCoursById.executeQuery();

        Salle salle = new Salle();

        while (res.next()) {
            salle.setId(res.getInt("ID"));
            salle.setNom(res.getString("Nom"));
            salle.setCapacite(res.getInt("Capacite"));
            SiteDaoServiceImpl site = new SiteDaoServiceImpl();
            salle.setSite(site.getById(res.getInt("ID_Site")));
        }

        return salle;
    }
}
