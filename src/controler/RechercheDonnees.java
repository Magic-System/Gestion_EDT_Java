package controler;

import dao.service.*;
import modele.Enseignant;
import modele.Etudiant;
import modele.Seance;
import modele.Utilisateur;
import vue.Connection;

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
    public ArrayList<Seance> getSeanceSemaineGroupe(int semaine, int groupe) {
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

    /**
     * Retourne la liste des cours validés pour une semaine et une promotion données.
     * @param semaine Numero de la semaine des cours a recuperer.
     * @param promo Id de la promo des cours a recuperer.
     * @return ArrayList de Seance.
     */
    public ArrayList<Seance> getSeanceSemainePromotion(int semaine, int promo) {
        SeanceDaoServiceImpl seanceDao = new SeanceDaoServiceImpl();
        ArrayList<Seance> maSemaine = null;

        try {
            maSemaine = new ArrayList<Seance>(seanceDao.getByPromoAndSemaine(semaine, promo));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return maSemaine;
    }

    /**
     * Recupere une liste contenant nom et prenom de tout les etudiants.
     * @return Liste de nom prenom de tout les etudiants.
     */
    public ArrayList<String> getListeEtudiant() {
        EtudiantDaoServiceImpl userDao = new EtudiantDaoServiceImpl();
        ArrayList<String> liste = new ArrayList<String>();
        try {
            ArrayList<Etudiant> users = userDao.getAll();
            for (Etudiant e : users) {
                String temp;
                temp = e.getUtilisateur().getNom().toUpperCase() + " " + e.getUtilisateur().getPrenom();
                liste.add(temp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return liste;
    }

    /**
     * Recupere une liste contenant nom et prenom de tout les enseignants.
     * @return Liste de nom prenom de tout les enseignants.
     */
    public ArrayList<String> getListeEnseignant() {
        EnseignantDaoServiceImpl userDao = new EnseignantDaoServiceImpl();
        ArrayList<String> liste = new ArrayList<String>();
        try {
            ArrayList<Enseignant> users = userDao.getAll();
            for (Enseignant e : users) {
                String temp;
                temp = e.getUtilisateur().getNom().toUpperCase() + " " + e.getUtilisateur().getPrenom();
                liste.add(temp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return liste;
    }


    public static void main(String[] args){
        RechercheDonnees rd = new RechercheDonnees();
        ArrayList<String> test = rd.getListeEtudiant();

        for (String s : test)
            System.out.println(s.toString());
    }
}
