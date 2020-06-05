package dao.service;

import dao.DbService;
import modele.Salle;
import modele.Seance_Groupes;
import modele.Type_Cours;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeanceGroupeDaoServiceImpl extends DbService<Seance_Groupes> {

    @Override
    public void ajouter(Seance_Groupes objet) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void modifier(Seance_Groupes objet) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void supprimer(Seance_Groupes objet) throws SQLException, ClassNotFoundException {

    }

    @Override
    public List<Seance_Groupes> getAll() throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getAll = co.prepareStatement("SELECT * FROM `seance_groupes` WHERE 1");
        ResultSet res = getAll.executeQuery();

        ArrayList<Seance_Groupes> liste = new ArrayList<Seance_Groupes>();

        while (res.next()) {
            SeanceDaoServiceImpl seance = new SeanceDaoServiceImpl();
            GroupeDaoServiceImpl grp = new GroupeDaoServiceImpl();

            liste.add(new Seance_Groupes(seance.getById(res.getInt("ID_Seance")), grp.getById(res.getInt("ID_Groupe"))));
        }

        return liste;
    }

    @Override
    public Seance_Groupes getById(int id) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getCoursById = co.prepareStatement("SELECT * FROM `seance_groupes` WHERE ID = ?");
        getCoursById.setInt(1, id);
        ResultSet res = getCoursById.executeQuery();

        Seance_Groupes sg = new Seance_Groupes();

        while (res.next()) {
            SeanceDaoServiceImpl seance = new SeanceDaoServiceImpl();
            GroupeDaoServiceImpl grp = new GroupeDaoServiceImpl();
            sg.setSeance(seance.getById(res.getInt("ID_Seance")));
            sg.setGroupe(grp.getById(res.getInt("ID_Groupe")));
        }

        return sg;
    }
}
