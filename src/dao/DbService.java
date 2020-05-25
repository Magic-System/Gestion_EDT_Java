package dao;

import modele.Utilisateur;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
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

    protected Session session;
    private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";
    private static EntityManagerFactory factory;
    private Class<T> type;

    public DbService(Class<T> className) {
        this.session = getSession();
        this.type = className;
    }

    public CriteriaBuilder getBuilder() {
        return this.session.getCriteriaBuilder();
    }

    /**
     *
     * https://www.boraji.com/hibernate-5-jpa-2-configuration-example
     * @return
     */
    private static EntityManagerFactory getEntityManagerFactory() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return factory;
    }

    private static Session getSession() {
        final EntityManagerFactory factory = getEntityManagerFactory();
        SessionFactory sFactory = factory.unwrap(SessionFactory.class);
        return sFactory.openSession();
    }

    public static void shutdown() {
        if (factory != null) {
            factory.close();
        }
    }

    /**
     * Ajout d'un objet dans la base de donnée.
     * @param objet Objet a rajouter dans la base de donnée.
     * @throws SQLException Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    public void ajouter(T objet) {
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(objet);
        transaction.commit();
    }

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
    public ArrayList<T> getAll() {
        CriteriaQuery<T> query = getBuilder().createQuery(type);
        Root<T> root = query.from(type);
        query.select(root);
        Query<T> q=session.createQuery(query);
        return new ArrayList<T>(q.getResultList());
    }

    /**
     * Recupere l'objet de la bdd correspondant a l'id recu en parametre.
     * @param id Identifiant de l'objet a recuperer.
     * @return Retourne l'objet de la bdd.
     * @throws SQLException Erreur lors de l'execution de la requete.
     * @throws ClassNotFoundException Erreur lors du chargement du driver de connexion à la bdd.
     */
    public T getById(int id) {
        CriteriaQuery<T> query = getBuilder().createQuery(type);
        Root<T> root = query.from(type);
        query.select(root).where(getBuilder().equal(root.get("id"),id));
        Query<T> q=session.createQuery(query);
        List<T> user=q.getResultList();

        return user.get(0);
    }


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
