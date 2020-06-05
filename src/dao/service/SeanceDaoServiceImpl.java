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
        Connection co = this.connexion();
        PreparedStatement addSeance = co.prepareStatement("INSERT INTO `seance`(`ID`, `Semaine`, `Date`, `Heure_Debut`, `Heure_Fin`, `Etat`, `ID_Cours`, `ID_Type`) VALUES ([?, ?, ?, ?, ?, ? ,?, ?)");
        addSeance.setInt(1, objet.getId());
        addSeance.setInt(2, objet.getSemaine());
        addSeance.setDate(3, java.sql.Date.valueOf(objet.getJour()));
        addSeance.setTime(4, java.sql.Time.valueOf(objet.getHeure_debut()));
        addSeance.setTime(5, java.sql.Time.valueOf(objet.getHeure_fin()));
        addSeance.setInt(6, objet.getEtat());
        addSeance.setInt(7, objet.getCours().getId());
        addSeance.setInt(8, objet.getType().getId());

        addSeance.executeUpdate();
    }

    @Override
    public void modifier(Seance objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement majSeance = co.prepareStatement("UPDATE `seance` SET `Semaine`= ?,`Date`= ?,`Heure_Debut`= ?,`Heure_Fin`= ?,`Etat`= ?,`ID_Cours`= ?,`ID_Type`= ? WHERE ID = ?");
        majSeance.setInt(1, objet.getSemaine());
        majSeance.setDate(2, java.sql.Date.valueOf(objet.getJour()));
        majSeance.setTime(3, java.sql.Time.valueOf(objet.getHeure_debut()));
        majSeance.setTime(4, java.sql.Time.valueOf(objet.getHeure_fin()));
        majSeance.setInt(5, objet.getEtat());
        majSeance.setInt(6, objet.getCours().getId());
        majSeance.setInt(7, objet.getType().getId());
        majSeance.setInt(8, objet.getId());

        majSeance.executeUpdate();
    }

    @Override
    public void supprimer(Seance objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement delSeance = co.prepareStatement("DELETE FROM `seance` WHERE ID = ?");
        delSeance.setInt(1, objet.getId());

        delSeance.executeUpdate();
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
