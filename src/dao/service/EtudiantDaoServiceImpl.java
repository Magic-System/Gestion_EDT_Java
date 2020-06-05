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

/**
 * @author Daniel
 */
public class EtudiantDaoServiceImpl extends DbService<Etudiant> {
    /**
     * Pas utilisé.
     * @param objet Objet a rajouter dans la base de donnée.
     */
    @Override
    public void ajouter(Etudiant objet) throws SQLException, ClassNotFoundException {

    }

    /**
     * Pas utilisé.
     * @param objet Objet a mettre a jour dans la bdd.
     */
    @Override
    public void modifier(Etudiant objet) throws SQLException, ClassNotFoundException {

    }

    /**
     * Pas utilisé.
     * @param objet Objet a supprimer dans la bdd.
     */
    @Override
    public void supprimer(Etudiant objet) throws SQLException, ClassNotFoundException {

    }

    /**
     * Recupere la table Etudiant de la bdd.
     * @return ArrayList d'etudiants.
     * @throws SQLException Probleme de requete.
     * @throws ClassNotFoundException Probleme de driver.
     */
    @Override
    public ArrayList<Etudiant> getAll() throws SQLException, ClassNotFoundException {
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

    /**
     * Recupere un etudiant de la bdd en fonction de l'id recu en parametre.
     * @param id Identifiant de l'objet a recuperer.
     * @return Etudiant ayant l'id recu en parametre.
     * @throws SQLException Probleme de requete.
     * @throws ClassNotFoundException Probleme de driver.
     */
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
