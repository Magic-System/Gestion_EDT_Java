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
import java.util.NoSuchElementException;

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
     * Pour un etudiant donné (recherche possible avec son nom ou parmi une liste d’enseignants) et pour une semaine
     * donnée, consulter tous les cours.
     * @param semaine Numero de la semaine.
     * @param etudiant String contenant nom + prenom de l'etudiant.
     * @return ArrayList de Seance.
     */
    public ArrayList<Seance> getSeanceSemaineEtudiant(int semaine, String etudiant) {
        ArrayList<Seance> maSemaine = null;
        SeanceDaoServiceImpl seanceDao = new SeanceDaoServiceImpl();

        try {
            int index = etudiant.lastIndexOf(" ");
            String nom = etudiant.substring(0, index);
            String prenom = etudiant.substring(index+1);
            maSemaine = new ArrayList<Seance>(seanceDao.getSeanceBySemaineAndEtudiant(semaine, nom, prenom));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return maSemaine;
    }

    /**
     * Pour un enseignant donné (recherche possible avec son nom ou parmi une liste d’enseignants) et pour une semaine
     * donnée, consulter tous les cours.
     * @param semaine Numero de la semaine.
     * @param enseignant String contenant nom + prenom de l'enseignant.
     * @return ArrayList de Seance.
     */
    public ArrayList<Seance> getSeanceSemaineEnseignant(int semaine, String enseignant) {
        ArrayList<Seance> maSemaine = null;
        SeanceDaoServiceImpl seanceDao = new SeanceDaoServiceImpl();

        try {
            int index = enseignant.lastIndexOf(" ");
            String nom = enseignant.substring(0, index);
            String prenom = enseignant.substring(index+1);
            maSemaine = new ArrayList<Seance>(seanceDao.getSeanceBySemaineAndEtudiant(semaine, nom, prenom));
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
        ArrayList<Seance> test = rd.getSeanceSemaineEtudiant(23 ,"DIAS DA SILVA Daniel");
        //rd.getSeanceSemaineEtudiant(23, "DIAS DA SILVA Daniel");

        for (Seance s : test)
            System.out.println(s.toString());
    }
}
