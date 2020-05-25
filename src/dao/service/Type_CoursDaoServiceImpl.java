package dao.service;

import dao.DbService;
import modele.Type_Cours;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Type_CoursDaoServiceImpl extends DbService<Type_Cours> {

    public Type_CoursDaoServiceImpl() {
        super(Type_Cours.class);
    }



    /**
     * Mise a jour d'un Type_Cours dans la base de donnée.
     *
     * @param objet Type_Cours a mettre a jour dans la bdd.
     * @throws SQLException           Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    @Override
    public void modifier(Type_Cours objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement updateCours = co.prepareStatement("UPDATE `type_cours` SET `Nom`= ? WHERE ID = ?");
        updateCours.setString(1, objet.getNom());
        updateCours.setInt(2, objet.getId());
        updateCours.executeUpdate();
        co.close();

    }

    /**
     * Suppression d'un Type_Cours dans la base de donnée.
     *
     * @param objet Type_Cours a supprimer dans la bdd.
     * @throws SQLException           Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    @Override
    public void supprimer(Type_Cours objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement supprCours = co.prepareStatement("DELETE FROM `type_cours` WHERE ?");
        supprCours.setInt(1, objet.getId());
        supprCours.executeUpdate();
        co.close();
    }


}
