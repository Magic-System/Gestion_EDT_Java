package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Gestion du CRUD et de la connexion de la bdd pour un objet T.
 * @author Daniel
 * @param <T> Objet utilisé
 */
public abstract class DbService<T> {

    /**
     * Url de connexion à la base de donnée.
     */
    private final String URL_DB = "jdbc:mysql://localhost:3306/planning";
    /**
     * Login de connexion à la base de donnée.
     */
    private final String LOGIN_DB = "root";
    /**
     * Mot de passe de connexion à la base de donnée.
     */
    private final String PASSWORD_DB = "";
    /**
     * Driver de connexion a la base de donnée.
     */
    private final String DRIVER_DB = "com.mysql.jdbc.Driver";

    /**
     * Ajout d'un objet dans la base de donnée.
     * @param objet Objet a rajouter dans la base de donnée.
     * @throws SQLException Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    public abstract void ajouter(T objet) throws SQLException, ClassNotFoundException;

    /**
     * Mise a jour d'un objet dans la base de donnée.
     * @param objet Objet a mettre a jour dans la bdd.
     * @throws SQLException Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    public abstract void modifier(T objet) throws SQLException, ClassNotFoundException;

    /**
     * Suppression d'un objet dans la base de donnée.
     * @param objet Objet a supprimer dans la bdd.
     * @throws SQLException Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    public abstract void supprimer(T objet) throws SQLException, ClassNotFoundException;

    /**
     * Retourne l'ensemble du contenu d'une table de la bdd.
     * @return List<Objet> Retourne la liste des objets contenu dans la table.
     * @throws SQLException Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    public abstract List<T> getAll() throws SQLException, ClassNotFoundException;

    /**
     * Recupere l'objet de la bdd correspondant a l'id recu en parametre.
     * @param id Identifiant de l'objet a recuperer.
     * @return Retourne l'objet de la bdd.
     * @throws SQLException Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    public abstract T getById(int id) throws SQLException, ClassNotFoundException;

    /**
     * Recupere la connexion a la bdd.
     * @return Retourne la connexion a la bdd.
     * @throws SQLException Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    protected Connection connexion() throws ClassNotFoundException, SQLException {
        //Chargement driver
        Class.forName(DRIVER_DB);
        //retourne d'une connexion JDBC à la db
        return DriverManager.getConnection(URL_DB, LOGIN_DB, PASSWORD_DB);
    }
}
