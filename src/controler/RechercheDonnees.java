package controler;

import dao.service.*;
import modele.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

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

        Utilisateur user;

        try {
            System.out.println(email);
            System.out.println(password);
            user = userDao.getByLogin(email, password);
            return user;
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    /**
     * Retourne la liste des cours pour un groupe et une semaine donnés.
     * @param semaine Numero de la semaine des cours a recuperer.
     * @param groupe Id du groupe des cours a recuperer.
     * @return ArrayList de Seance.
     */
    public ArrayList<Seance> getSeanceSemaineGroupe(final int semaine,final  int groupe) {
        SeanceGroupeDaoServiceImpl sgDao = new SeanceGroupeDaoServiceImpl();
        SeanceDaoServiceImpl seanceDao = new SeanceDaoServiceImpl();
        ArrayList<Seance> maSemaine = null;
        try {
            HashSet<Integer> seancesGroupe = sgDao.getSeanceIdByGroupe(groupe);
            maSemaine = new ArrayList<>(seanceDao.getSeanceSemaineParGroupe(seancesGroupe, semaine));
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return maSemaine;
    }

    /**
     * Retourne la liste des cours validés pour une semaine et une promotion données.
     * @param semaine Numero de la semaine des cours a recuperer.
     * @param promo Id de la promo des cours a recuperer.
     * @return ArrayList de Seance.
     */
    public ArrayList<Seance> getSeanceSemainePromotion(final int semaine,final  int promo) {
        SeanceDaoServiceImpl seanceDao = new SeanceDaoServiceImpl();
        ArrayList<Seance> maSemaine = null;

        try {
            maSemaine = new ArrayList<>(seanceDao.getByPromoAndSemaine(semaine, promo));
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return maSemaine;
    }

    /**
     * Pour un etudiant donné (recherche possible avec son nom ou parmi une liste d’étudiants) et pour une semaine
     * donnée, consulter tous les cours.
     * @param semaine Numero de la semaine.
     * @param etudiant String contenant nom + prenom de l'etudiant.
     * @return ArrayList de Seance.
     */
    public ArrayList<Seance> getSeanceSemaineEtudiant(final int semaine,final  String etudiant) {
        ArrayList<Seance> maSemaine = null;
        SeanceDaoServiceImpl seanceDao = new SeanceDaoServiceImpl();

        try {
            int index = etudiant.lastIndexOf(" ");
            String nom = etudiant.substring(0, index);
            String prenom = etudiant.substring(index+1);
            maSemaine = new ArrayList<>(seanceDao.getSeanceBySemaineAndEtudiant(semaine, nom, prenom));
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
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
    public ArrayList<Seance> getSeanceSemaineEnseignant(final int semaine,final  String enseignant) {
        ArrayList<Seance> maSemaine = new ArrayList<>();
        SeanceDaoServiceImpl seanceDao = new SeanceDaoServiceImpl();

        try {
            System.out.println(enseignant);
            int index = enseignant.lastIndexOf(" ");
            String nom = enseignant.substring(0, index);
            String prenom = enseignant.substring(index+1);
            maSemaine = new ArrayList<>(seanceDao.getSeanceBySemaineAndEnseignant(semaine, nom, prenom));
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return maSemaine;
    }

    /**
     * Pour une salle donnée (recherche possible avec son nom ou parmi une liste d’enseignants) et pour une semaine
     * donnée, consulter tous les cours.
     * @param semaine Numero de la semaine.
     * @param salle Nom de la salle.
     * @return ArrayList de Seance.
     */
    public ArrayList<Seance> getSeanceSemaineSalle(final int semaine,final  String salle) {
        ArrayList<Seance> maSemaine = new ArrayList<>();
        SeanceDaoServiceImpl seanceDao = new SeanceDaoServiceImpl();

        try {
            maSemaine = new ArrayList<>(seanceDao.getSeanceBySemaineAndSalle(semaine, salle));
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return maSemaine;
    }

    /**
     * Recupere une liste contenant nom et prenom de tout les etudiants.
     * @return Liste de nom prenom de tout les etudiants.
     */
    public ArrayList<String> getListeEtudiant() {
        EtudiantDaoServiceImpl userDao = new EtudiantDaoServiceImpl();
        ArrayList<String> liste = new ArrayList<>();
        try {
            ArrayList<Etudiant> users = userDao.getAll();
            for (Etudiant e : users) {
                String temp;
                temp = e.getUtilisateur().getNom().toUpperCase() + " " + e.getUtilisateur().getPrenom();
                liste.add(temp);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return liste;
    }

    /**
     * Recupere une liste contenant nom et prenom de tout les enseignants.
     * @return Liste de nom prenom de tout les enseignants.
     */
    public ArrayList<String> getListeEnseignant() {
        EnseignantDaoServiceImpl userDao = new EnseignantDaoServiceImpl();
        ArrayList<String> liste = new ArrayList<>();
        try {
            ArrayList<Enseignant> users = userDao.getAll();
            for (Enseignant e : users) {
                String temp;
                temp = e.getUtilisateur().getNom().toUpperCase() + " " + e.getUtilisateur().getPrenom();
                liste.add(temp);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return liste;
    }

    /**
     * Recupere une liste contenant le nom de toutes les salles.
     * @return Liste de String de nom de salle.
     */
    public ArrayList<String> getListeSalles() {
        SalleDaoServiceImpl salleDao = new SalleDaoServiceImpl();
        ArrayList<String> liste = new ArrayList<>();

        try {
            ArrayList<Salle> salles = salleDao.getAll();
            for (Salle s : salles) {
                liste.add(s.getNom());
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return liste;
    }

    /**
     * Récapitulatif des cours d’un enseignant sur une période donnée (date de début et de fin) : consulter ses cours
     * par groupe(s) (rappel : une séance de cours peut concerner plusieurs groupes), en précisant la date et le créneau
     * horaire de la première et dernière séance sur cette période. Puis pour chaque cours et groupe(s), calculer le
     * nombre total d’heures et de séances par cours et groupe(s) sur la période.
     * @return Liste de string au format suivant : "Matiere-Public   Premiere séance   Derniere séance   Durée   Nb.".
     * @param debut Date de début de la période a consulter.
     * @param fin Date de fin de la période a consulter.
     */
    public ArrayList<String> recapitulatifCours(LocalDate debut, LocalDate fin, int idEnseignant) {
        ArrayList<String> liste = new ArrayList<>();
        ArrayList<String> recap = new ArrayList<>();
        SeanceDaoServiceImpl sDao = new SeanceDaoServiceImpl();
        recap.add("Cours | Promo Groupe | Premier Cours | Dernier Cours | Nb d'heures");

        try {
            liste = sDao.getComboCoursAndGroupeByEnseignant(idEnseignant);
            for (String s : liste) {
                //System.out.println(s);
                int x1 = s.lastIndexOf(" | ");
                String cours = s.substring(0, x1);
                int x2 = s.lastIndexOf("/");
                String promo = s.substring(x1+3, x2);
                String groupe = s.substring(x2+1);

                String stats = sDao.getStatsByCoursGroupeAndEnseignants(idEnseignant, cours, groupe, promo);
                int x3 = stats.lastIndexOf(" ");
                int nb = Integer.parseInt(stats.substring(x3+1));
                stats = stats.substring(0, x3);
                int min = (nb*90)%60;
                int heure = (nb*90)/60;

                String recapCours = cours + " | " + promo + " " + groupe + " | " ;
                recap.add(recapCours + stats + " " + heure + "h" + min);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return recap;
    }

    /**
     * Recupere la liste des seance en cours de validation dans une liste de string de la forme suivante :
     * TYPE de COURS le DATE de HEURE_DEBUT a HEURE_FIN.
     * @return Liste de String.
     */
    public ArrayList<String> getListeSeanceValidation() {
        ArrayList<String> liste = new ArrayList<>();

        SeanceDaoServiceImpl sDao = new SeanceDaoServiceImpl();

        try {
            ArrayList<Seance> ls = sDao.getSeanceValidation();
            for (Seance s : ls) {
                String seanceNom = s.getType().getNom() + " de " + s.getCours().getNom() + " le " + s.getJour() + " de " + s.getHeure_debut() + " a " + s.getHeure_fin();
                liste.add(seanceNom);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return liste;
    }

    /**
     * Recupere la liste des Nom des enseignants d'une seance.
     * @param idSeance id de la Seance.
     * @return Liste de Nom d'enseignants.
     */
    public ArrayList<String> getNomEnseignantSeance(int idSeance) {
        ArrayList<String > liste = new ArrayList<>();

        SeanceEnseignantsDaoServiceImpl seDao = new SeanceEnseignantsDaoServiceImpl();

        try {
            ArrayList<Seance_Enseignants> se = seDao.getAllBySeance(idSeance);
            for (Seance_Enseignants var : se) {
                liste.add(var.getProf().getUtilisateur().getNom().toUpperCase());
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return liste;
    }

    /**
     * Recupere une liste de nom promo + nom groupe d'une séance.
     * @param idSeance Id de la séance.
     * @return Liste de nom des groupe et promo.
     */
    public ArrayList<String> getNomGroupeSeance (int idSeance) {
        ArrayList<String > liste = new ArrayList<>();

        SeanceGroupeDaoServiceImpl sgDao = new SeanceGroupeDaoServiceImpl();

        try {
            ArrayList<Seance_Groupes> sg = sgDao.getAllBySeance(idSeance);
            for (Seance_Groupes var : sg) {
                liste.add(var.getGroupe().getPromotion().getNom() + " " + var.getGroupe().getNom());
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return liste;
    }

    /**
     * Recupere la liste des nom et capacité de salles d'une séance.
     * @param idSeance Id de la séance.
     * @return Liste de nom et capacité de salle.
     */
    public ArrayList<String> getNomSalleSeance(int idSeance) {
        ArrayList<String > liste = new ArrayList<>();

        SeanceSalleDaoServiceImpl ssDao = new SeanceSalleDaoServiceImpl();

        try {
            ArrayList<Seance_Salles> ss = ssDao.getAllBySeance(idSeance);
            for (Seance_Salles var : ss) {
                liste.add(var.getSalle().getNom() + " (" + var.getSalle().getCapacite() + ") " + " - " + var.getSalle().getSite().getNom());
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return liste;
    }

    /**
     * Main de test !!
     */
    public static void main(String[] args){
        int choix = 1;

        while (choix != 0) {
            Scanner in = new Scanner(System.in);
            do {
                System.out.println("1) test Semaine groupe");
                System.out.println("2) test Semaine promo");
                System.out.println("3) test Semaine etudiant");
                System.out.println("4) test Semaine enseignant");
                System.out.println("5) test Semaine salle");
                System.out.println("6) test liste etudiant");
                System.out.println("7) test liste enseignant");
                System.out.println("8) test liste salle");
                System.out.println("9) test recap cours enseignant (pas fini manque la periode de temps)");
                System.out.println("10) test liste seance a valider");
                System.out.println("0) quitter");

                choix = in.nextInt();
            }while (choix < 0 || choix > 10);

            RechercheDonnees rech = new RechercheDonnees();

            switch (choix) {
                case 1: {
                    System.out.println("1) test Semaine groupe");
                    int semaine, groupe;
                    System.out.println("Semaine :");
                    semaine = in.nextInt();
                    System.out.println("Groupe :");
                    groupe = in.nextInt();
                    ArrayList<Seance> coursSemaine = rech.getSeanceSemaineGroupe(semaine, groupe);
                    for (Seance s : coursSemaine) {
                        System.out.println(s.toString());
                    }
                    break;
                }
                case 2: {
                    System.out.println("2) test Semaine promo");
                    int semaine, promo;
                    System.out.println("Semaine :");
                    semaine = in.nextInt();
                    System.out.println("Promo :");
                    promo = in.nextInt();
                    ArrayList<Seance> coursSemaine = rech.getSeanceSemainePromotion(semaine, promo);
                    for (Seance s : coursSemaine) {
                        System.out.println(s.toString());
                    }
                    break;
                }
                case 3: {
                    System.out.println("3) test Semaine etudiant");
                    int semaine;
                    String etudiant;
                    System.out.println("Semaine :");
                    semaine = in.nextInt();
                    System.out.println("Etudiant :");
                    etudiant = in.nextLine();
                    ArrayList<Seance> coursSemaine = rech.getSeanceSemaineEtudiant(semaine, etudiant);
                    for (Seance s : coursSemaine) {
                        System.out.println(s.toString());
                    }
                    break;
                }
                case 4: {
                    System.out.println("4) test Semaine enseignant");
                    int semaine;
                    String enseignant;
                    System.out.println("Semaine :");
                    semaine = in.nextInt();
                    System.out.println("Enseignant :");
                    in.nextLine();
                    enseignant = in.nextLine();
                    ArrayList<Seance> coursSemaine = new ArrayList<>(rech.getSeanceSemaineEnseignant(semaine, enseignant));
                    System.out.println(coursSemaine.size());
                    for (Seance s : coursSemaine) {
                        System.out.println(s.toString());
                    }
                    break;
                }
                case 5: {
                    System.out.println("5) test Semaine salle");
                    int semaine;
                    String salle;
                    System.out.println("Semaine :");
                    semaine = in.nextInt();
                    System.out.println("Salle :");
                    salle = in.nextLine();
                    ArrayList<Seance> coursSemaine = rech.getSeanceSemaineSalle(semaine, salle);
                    for (Seance s : coursSemaine) {
                        System.out.println(s.toString());
                    }
                    break;
                }
                case 6: {
                    System.out.println("6) test liste etudiant");
                    for (String s : rech.getListeEtudiant()) {
                        System.out.println(s);
                    }
                    break;
                }
                case 7: {
                    System.out.println("7) test liste enseignant");
                    for (String s : rech.getListeEnseignant()) {
                        System.out.println(s);
                    }
                    break;
                }
                case 8: {
                    System.out.println("8) test liste salle");
                    for (String s : rech.getListeSalles()) {
                        System.out.println(s);
                    }
                    break;
                }
                case 9: {
                    System.out.println("9) test recap cours enseignant (pas fini manque la periode de temps)");
                    int enseignant;
                    enseignant = in.nextInt();

                    for (String s : rech.recapitulatifCours(LocalDate.MIN, LocalDate.MAX, enseignant)){
                        System.out.println(s);
                    }
                    break;
                }
                case 10: {
                    System.out.println("10) test liste seance a valider");
                    for (String s : rech.getListeSeanceValidation()) {
                        System.out.println(s);
                    }
                    break;
                }
            }
        }
    }
}
