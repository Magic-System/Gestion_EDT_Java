package dao.service;

import dao.DbService;
import modele.Type_Cours;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel
 */
public class Type_CoursDaoServiceImpl extends DbService<Type_Cours> {

    /**
     * Pas utilisé.
     * @param objet Objet a rajouter dans la base de donnée
     */
    @Override
    public void ajouter(Type_Cours objet) throws SQLException, ClassNotFoundException {

    }

    /**
     * Pas utilisé.
     * @param objet Objet a mettre a jour dans la bdd.
     */
    @Override
    public void modifier(Type_Cours objet) throws SQLException, ClassNotFoundException {

    }

    /**
     * Pas utilisé.
     * @param objet Objet a supprimer dans la bdd.
     */
    @Override
    public void supprimer(Type_Cours objet) throws SQLException, ClassNotFoundException {

    }

    /**
     * Retourne l'ensemble du contenu d'une table de la bdd.
     *
     * @return List<Type_Cours> Retourne la liste des Type_Cours contenu dans la table.
     * @throws SQLException           Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    @Override
    public List<Type_Cours> getAll() throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getAll = co.prepareStatement("SELECT * FROM `type_cours` WHERE 1");
        ResultSet res = getAll.executeQuery();

        ArrayList<Type_Cours> liste = new ArrayList<Type_Cours>();

        while (res.next()) {
            liste.add(new Type_Cours(res.getInt("ID"), res.getString("Nom")));
        }

        return liste;
    }

    /**
     * Recupere la Type_Cours de la bdd correspondant a l'id recu en parametre.
     *
     * @param id Identifiant de la Type_Cours a recuperer.
     * @return Retourne la Promotion de la bdd.
     * @throws SQLException           Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    @Override
    public Type_Cours getById(int id) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getCoursById = co.prepareStatement("SELECT * FROM `type_cours` WHERE ID = ?");
        getCoursById.setInt(1, id);
        ResultSet res = getCoursById.executeQuery();

        Type_Cours type = new Type_Cours();

        while (res.next()) {
            type.setId(res.getInt("ID"));
            type.setNom(res.getString("Nom"));
        }

        return type;
    }
}
