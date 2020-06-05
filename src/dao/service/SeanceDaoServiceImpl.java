package dao.service;

import dao.DbService;
import modele.Salle;
import modele.Seance;
import modele.Type_Cours;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SeanceDaoServiceImpl extends DbService<Seance> {

    @Override
    public void ajouter(Seance objet) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void modifier(Seance objet) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void supprimer(Seance objet) throws SQLException, ClassNotFoundException {

    }

    @Override
    public List<Seance> getAll() throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getAll = co.prepareStatement("SELECT * FROM `seance` WHERE 1");
        ResultSet res = getAll.executeQuery();

        ArrayList<Seance> liste = new ArrayList<Seance>();

        while (res.next()) {
            Seance seance = new Seance();
            seance.setId(res.getInt("ID"));
            seance.setSemaine(res.getInt("Semaine"));
            seance.setJour(res.getDate("Date").toLocalDate());
            seance.setHeure_debut(res.getTime("Heure_Debut").toLocalTime());
            seance.setHeure_fin(res.getTime("Heure_Fin").toLocalTime());
            seance.setEtat(res.getInt("Etat"));
            CoursDaoServiceImpl cours = new CoursDaoServiceImpl();
            seance.setCours(cours.getById(res.getInt("ID_Cours")));
            Type_CoursDaoServiceImpl type = new Type_CoursDaoServiceImpl();
            seance.setType(type.getById(res.getInt("ID_Type")));
            liste.add(seance);
        }

        return liste;
    }

    @Override
    public Seance getById(int id) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getCoursById = co.prepareStatement("SELECT * FROM `seance` WHERE ID = ?");
        getCoursById.setInt(1, id);
        ResultSet res = getCoursById.executeQuery();

        Seance seance = new Seance();

        while (res.next()) {
            seance.setId(res.getInt("ID"));
            seance.setSemaine(res.getInt("Semaine"));
            seance.setJour(res.getDate("Date").toLocalDate());
            seance.setHeure_debut(res.getTime("Heure_Debut").toLocalTime());
            seance.setHeure_fin(res.getTime("Heure_Fin").toLocalTime());
            seance.setEtat(res.getInt("Etat"));
            CoursDaoServiceImpl cours = new CoursDaoServiceImpl();
            seance.setCours(cours.getById(res.getInt("ID_Cours")));
            Type_CoursDaoServiceImpl type = new Type_CoursDaoServiceImpl();
            seance.setType(type.getById(res.getInt("ID_Type")));
        }

        return seance;
    }
}
