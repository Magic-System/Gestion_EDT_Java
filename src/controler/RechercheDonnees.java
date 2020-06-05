package controler;

import dao.service.SeanceDaoServiceImpl;
import dao.service.SeanceGroupeDaoServiceImpl;
import dao.service.UtilisateurDaoServiceImpl;
import modele.Seance;
import modele.Utilisateur;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author Daniel
 */
public class RechercheDonnees {

    public RechercheDonnees() {
    }

    /**
     * Retourne l'utilisateur login.
     * @param email email de l'utilisateur a login.
     * @param password Mot de passe de l'utilisateur a login.
     * @return Utilisateur login ou null si introuvable.
     */
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

    /**
     * Retourne la liste des cours pour un groupe et une semaine donnés.
     * @param semaine Numero de la semaine des cours a recuperer.
     * @param groupe Id du groupe des cours a recuperer.
     * @return ArrayList de Seance.
     */
    public ArrayList<Seance> getSeanceSemaine(int semaine, int groupe) {
        SeanceGroupeDaoServiceImpl sgDao = new SeanceGroupeDaoServiceImpl();
        SeanceDaoServiceImpl seanceDao = new SeanceDaoServiceImpl();
        ArrayList<Seance> maSemaine = null;
        try {
            HashSet<Integer> seancesGroupe = sgDao.getSeanceIdByGroupe(groupe);
            maSemaine = new ArrayList<>(seanceDao.getSeanceSemaineParGroupe(seancesGroupe, semaine));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return maSemaine;
    }
}
