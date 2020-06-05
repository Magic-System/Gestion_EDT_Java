package controler;

import dao.service.UtilisateurDaoServiceImpl;
import modele.Utilisateur;

import java.sql.SQLException;

/**
 * @author Daniel
 */
public class RechercheDonnees {

    public RechercheDonnees() {
    }

    public Utilisateur login(String email, String password) {
        UtilisateurDaoServiceImpl userDao = new UtilisateurDaoServiceImpl();

        Utilisateur user = null;

        try {
            System.out.println(email);
            System.out.println(password);
            user = userDao.getByLogin(email, password);
            return user;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
