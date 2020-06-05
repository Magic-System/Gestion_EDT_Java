package dao.service;

import dao.DbService;
import modele.Salle;
import modele.Seance_Groupes;
import modele.Seance_Salles;
import modele.Type_Cours;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeanceSalleDaoServiceImpl extends DbService<Seance_Salles> {

    @Override
    public void ajouter(Seance_Salles objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement addSg = co.prepareStatement("INSERT INTO `seance_salles`(`ID_Seance`, `ID_Salle`) VALUES (?, ?)");
        addSg.setInt(1, objet.getSeance().getId());
        addSg.setInt(2, objet.getSalle().getId());

        addSg.executeUpdate();
    }

    @Override
    public void modifier(Seance_Salles objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement majSg = co.prepareStatement("UPDATE `seance_salles` SET `ID_Salle`= ? WHERE `ID_Seance`= ?");
        majSg.setInt(1, objet.getSalle().getId());
        majSg.setInt(2, objet.getSeance().getId());

        majSg.executeUpdate();
    }

    @Override
    public void supprimer(Seance_Salles objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement delSg = co.prepareStatement("DELETE FROM `seance_salles` WHERE `ID_Seance`= ? AND `ID_Salle`= ?");
        delSg.setInt(1, objet.getSeance().getId());
        delSg.setInt(2, objet.getSalle().getId());

        delSg.executeUpdate();
    }

    @Override
    public List<Seance_Salles> getAll() throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getAll = co.prepareStatement("SELECT * FROM `seance_salles` WHERE 1");
        ResultSet res = getAll.executeQuery();

        ArrayList<Seance_Salles> liste = new ArrayList<Seance_Salles>();

        while (res.next()) {
            SeanceDaoServiceImpl seance = new SeanceDaoServiceImpl();
            SalleDaoServiceImpl salle = new SalleDaoServiceImpl();

            liste.add(new Seance_Salles(seance.getById(res.getInt("ID_Seance")), salle.getById(res.getInt("ID_Salle"))));
        }

        return liste;
    }

    @Override
    public Seance_Salles getById(int id) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getCoursById = co.prepareStatement("SELECT * FROM `seance_salles` WHERE ID = ?");
        getCoursById.setInt(1, id);
        ResultSet res = getCoursById.executeQuery();

        Seance_Salles sSalle = new Seance_Salles();

        while (res.next()) {
            SeanceDaoServiceImpl seance = new SeanceDaoServiceImpl();
            SalleDaoServiceImpl salle = new SalleDaoServiceImpl();
            sSalle.setSeance(seance.getById(res.getInt("ID_Seance")));
            sSalle.setSalle(salle.getById(res.getInt("ID_Groupe")));
        }

        return sSalle;
    }
}
