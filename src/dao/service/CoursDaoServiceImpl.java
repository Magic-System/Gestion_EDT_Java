package dao.service;

import dao.DbService;
import modele.Cours;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CoursDaoServiceImpl extends DbService<Cours>{

    public CoursDaoServiceImpl() {
    }

    @Override
    public void ajouter(Cours objet) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void modifier(Cours objet) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void supprimer(Cours objet) throws SQLException, ClassNotFoundException {

    }

    /**
     * Retourne l'ensemble du contenu d'une table de la bdd.
     * @return List<Cours> Retourne la liste des Cours contenu dans la table.
     * @throws SQLException           Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    @Override
    public List<Cours> getAll() throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getAll = co.prepareStatement("SELECT * FROM `cours` WHERE 1");
        ResultSet res = getAll.executeQuery();

        ArrayList<Cours> liste = new ArrayList<Cours>();

        while (res.next()) {
            liste.add(new Cours(res.getInt("ID"), res.getString("Nom")));
        }

        return liste;
    }

    /**
     * Recupere le Cours de la bdd correspondant a l'id recu en parametre.
     * @param id Identifiant du Cours a recuperer.
     * @return Retourne l'objet de la bdd.
     * @throws SQLException           Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    @Override
    public Cours getById(int id) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getCoursById = co.prepareStatement("SELECT * FROM `cours` WHERE ID = ?");
        getCoursById.setInt(1, id);
        ResultSet res = getCoursById.executeQuery();

        Cours c = new Cours();

        while (res.next()) {
            c.setId(res.getInt("ID"));
            c.setNom(res.getString("Nom"));
        }

        return c;
    }
}
