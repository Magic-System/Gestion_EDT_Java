package dao.service;

import dao.DbService;
import modele.Etudiant;
import modele.Groupe;
import modele.Utilisateur;

import java.security.acl.Group;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EtudiantDaoServiceImpl extends DbService<Etudiant> {
    @Override
    public void ajouter(Etudiant objet) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void modifier(Etudiant objet) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void supprimer(Etudiant objet) throws SQLException, ClassNotFoundException {

    }

    @Override
    public List<Etudiant> getAll() throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getAll = co.prepareStatement("SELECT * FROM `etudiant` WHERE 1");
        ResultSet res = getAll.executeQuery();

        ArrayList<Etudiant> liste = new ArrayList<Etudiant>();

        while (res.next()) {
            GroupeDaoServiceImpl grp = new GroupeDaoServiceImpl();
            UtilisateurDaoServiceImpl user = new UtilisateurDaoServiceImpl();
            liste.add(new Etudiant(user.getById(res.getInt("ID_Utilisateur")), res.getInt("Numero"), grp.getById(res.getInt("ID_Groupe"))));
        }

        return liste;
    }

    @Override
    public Etudiant getById(int id) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getCoursById = co.prepareStatement("SELECT * FROM `etudiant` WHERE ID_Utilisateur = ?");
        getCoursById.setInt(1, id);
        ResultSet res = getCoursById.executeQuery();

        Etudiant etudiant = new Etudiant();

        while (res.next()) {
            GroupeDaoServiceImpl grp = new GroupeDaoServiceImpl();
            UtilisateurDaoServiceImpl user = new UtilisateurDaoServiceImpl();
            etudiant.setGroupe(grp.getById(res.getInt("ID_Groupe")));
            etudiant.setUtilisateur(user.getById(res.getInt("ID_Utilisateur")));
            etudiant.setNumero(res.getInt("Numero"));
        }

        return etudiant;
    }
}
