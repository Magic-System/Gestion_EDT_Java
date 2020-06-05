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
        Connection co = this.connexion();
        PreparedStatement addSe = co.prepareStatement("INSERT INTO `seance_enseignants`(`ID_Seance`, `ID_Enseignant`) VALUES (?, ?)");
        addSe.setInt(1, objet.getSeance().getId());
        addSe.setInt(2, objet.getProf().getUtilisateur().getId());

        addSe.executeUpdate();
    }

    @Override
    public void modifier(Seance_Enseignants objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement majSe = co.prepareStatement("UPDATE `seance_enseignants` SET `ID_Enseignant`= ? WHERE `ID_Seance`= ?");
        majSe.setInt(1, objet.getProf().getUtilisateur().getId());
        majSe.setInt(2, objet.getSeance().getId());

        majSe.executeUpdate();
    }

    @Override
    public void supprimer(Seance_Enseignants objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement delSe = co.prepareStatement("DELETE FROM `seance_enseignants` WHERE `ID_Seance`= ? AND `ID_Enseignant`= ?");
        delSe.setInt(1, objet.getSeance().getId());
        delSe.setInt(2, objet.getProf().getUtilisateur().getId());

        delSe.executeUpdate();
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
