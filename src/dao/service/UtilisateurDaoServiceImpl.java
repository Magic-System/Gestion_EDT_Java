package dao.service;

import dao.DbService;
import modele.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UtilisateurDaoServiceImpl extends DbService<Utilisateur> {


    @Override
    public void ajouter(Utilisateur objet) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void modifier(Utilisateur objet) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void supprimer(Utilisateur objet) throws SQLException, ClassNotFoundException {

    }

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

        Utilisateur user = new Utilisateur();

        while (res.next()) {
            user.setId(res.getInt("id"));
            user.setEmail(res.getString("email"));
            user.setPassword(res.getString("password"));
            user.setNom(res.getString("nom"));
            user.setPrenom(res.getString("prenom"));
            user.setDroit(res.getInt("droit"));
        }

        return user;
    }
}
