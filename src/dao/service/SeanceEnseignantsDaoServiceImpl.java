package dao.service;

import dao.DbService;
import modele.Salle;
import modele.Seance_Enseignants;
import modele.Seance_Groupes;
import modele.Type_Cours;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeanceEnseignantsDaoServiceImpl  extends DbService<Seance_Enseignants> {

    @Override
    public void ajouter(Seance_Enseignants objet) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void modifier(Seance_Enseignants objet) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void supprimer(Seance_Enseignants objet) throws SQLException, ClassNotFoundException {

    }

    @Override
    public List<Seance_Enseignants> getAll() throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getAll = co.prepareStatement("SELECT * FROM `seance_enseignants` WHERE 1");
        ResultSet res = getAll.executeQuery();

        ArrayList<Seance_Enseignants> liste = new ArrayList<Seance_Enseignants>();

        while (res.next()) {
            SeanceDaoServiceImpl seance = new SeanceDaoServiceImpl();
            EnseignantDaoServiceImpl prof = new EnseignantDaoServiceImpl();

            liste.add(new Seance_Enseignants(seance.getById(res.getInt("ID_Seance")), prof.getById(res.getInt("ID_Groupe"))));
        }

        return liste;
    }

    @Override
    public Seance_Enseignants getById(int id) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getCoursById = co.prepareStatement("SELECT * FROM `seance_enseignants` WHERE ID = ?");
        getCoursById.setInt(1, id);
        ResultSet res = getCoursById.executeQuery();

        Seance_Enseignants se = new Seance_Enseignants();

        while (res.next()) {
            SeanceDaoServiceImpl seance = new SeanceDaoServiceImpl();
            EnseignantDaoServiceImpl prof = new EnseignantDaoServiceImpl();
            se.setSeance(seance.getById(res.getInt("ID_Seance")));
            se.setProf(prof.getById(res.getInt("ID_Groupe")));
        }

        return se;
    }
}
