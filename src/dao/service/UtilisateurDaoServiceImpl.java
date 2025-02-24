package dao.service;

import dao.DbService;
import modele.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Daniel
 */
public class UtilisateurDaoServiceImpl extends DbService<Utilisateur> {


    /**
     * Pas utilisé.
     * @param objet Objet a rajouter dans la base de donnée.
     */
    @Override
    public void ajouter(Utilisateur objet) throws SQLException, ClassNotFoundException {

    }

    /**
     * Pas utilisé.
     * @param objet Objet a mettre a jour dans la bdd.
     */
    @Override
    public void modifier(Utilisateur objet) throws SQLException, ClassNotFoundException {

    }

    /**
     * Pas utilisé.
     * @param objet Objet a supprimer dans la bdd.
     */
    @Override
    public void supprimer(Utilisateur objet) throws SQLException, ClassNotFoundException {

    }

    /**
     * Pas utilisé.
     */
    @Override
    public List<Utilisateur> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }


    /**
     * Recupere l'Utilisateur de la bdd correspondant a l'id recu en parametre.
     *
     * @param id Identifiant de la Utilisateur a recuperer.
     * @return Retourne la Promotion de la bdd.
     * @throws SQLException           Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    @Override
    public Utilisateur getById(int id) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getCoursById = co.prepareStatement("SELECT * FROM `utilisateur` WHERE id = ?");
        getCoursById.setInt(1, id);
        ResultSet res = getCoursById.executeQuery();

        Utilisateur user = null;

        while (res.next()) {
            user = new Utilisateur();
            user.setId(res.getInt("id"));
            user.setEmail(res.getString("email"));
            user.setPassword(res.getString("password"));
            user.setNom(res.getString("nom"));
            user.setPrenom(res.getString("prenom"));
            user.setDroit(res.getInt("droit"));
        }

        co.close();
        return user;
    }

    public Utilisateur getByLogin(String email, String password) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getCoursByLogin = co.prepareStatement("SELECT `ID`, `Nom`, `Prenom`, `Droit` FROM `utilisateur` WHERE  `Email` = ? AND `Password` = ?");
        getCoursByLogin.setString(1, email);
        getCoursByLogin.setString(2, password);
        ResultSet res = getCoursByLogin.executeQuery();

        Utilisateur user = null;

        while (res.next()) {
            user = new Utilisateur();
            user.setId(res.getInt("id"));
            user.setEmail(email);
            user.setPassword(password);
            user.setNom(res.getString("nom"));
            user.setPrenom(res.getString("prenom"));
            user.setDroit(res.getInt("droit"));
        }

        co.close();
        return user;
    }
}
