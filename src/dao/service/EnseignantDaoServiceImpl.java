package dao.service;

import dao.DbService;
import modele.Cours;
import modele.Enseignant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author Daniel
 */
public class EnseignantDaoServiceImpl extends DbService<Enseignant> {

    /**
     * Pas utilisé.
     * @param objet Objet a rajouter dans la base de donnée.
     */
    @Override
    public void ajouter(Enseignant objet) throws SQLException, ClassNotFoundException {

    }

    /**
     * Pas utilisé.
     * @param objet Objet a mettre a jour dans la bdd.
     */
    @Override
    public void modifier(Enseignant objet) throws SQLException, ClassNotFoundException {

    }

    /**
     * Pas utilisé.
     * @param objet Objet a supprimer dans la bdd.
     */
    @Override
    public void supprimer(Enseignant objet) throws SQLException, ClassNotFoundException {

    }

    /**
     * Retourne une liste de tout les enseignants de la bdd.
     * @return ArrayList d'enseignants.
     * @throws SQLException Probleme de requete.
     * @throws ClassNotFoundException Probleme de driver.
     */
    @Override
    public List<Enseignant> getAll() throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getAll = co.prepareStatement("SELECT * FROM `enseignant` WHERE 1");
        ResultSet res = getAll.executeQuery();

        HashSet<Integer> ids = new HashSet<Integer>();
        ArrayList<Enseignant> liste = new ArrayList<Enseignant>();

        while (res.next()) {
            ids.add(res.getInt("ID_Utilisateur"));
        }

        for (Integer id : ids) {
            liste.add(getById(id));
        }

        return liste;
    }

    /**
     * Cherche un Enseignant dans la bdd en fonction de son ID utilisateur.
     * @param id Identifiant de l'objet a recuperer.
     * @return Enseignant ayant l'id recu en parametre.
     * @throws SQLException Probleme de requete.
     * @throws ClassNotFoundException Probleme de driver.
     */
    @Override
    public Enseignant getById(int id) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getEnseignantById = co.prepareStatement("SELECT * FROM `enseignant` WHERE ID_Utilisateur = ?");
        getEnseignantById.setInt(1, id);
        ResultSet res = getEnseignantById.executeQuery();

        Enseignant prof = new Enseignant();
        ArrayList<Cours> listeCours = new ArrayList<Cours>();

        while (res.next()) {
            CoursDaoServiceImpl cours = new CoursDaoServiceImpl();
            listeCours.add(cours.getById(res.getInt("ID_Cours")));
        }

        if (listeCours.size() != 0) {
            prof.setCours_enseigne(listeCours);
            UtilisateurDaoServiceImpl user = new UtilisateurDaoServiceImpl();
            prof.setUtilisateur(user.getById(id));
        }

        return prof;
    }
}
