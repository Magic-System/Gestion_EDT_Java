package dao.service;

import dao.DbService;
import modele.Seance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author Daniel
 */
public class SeanceDaoServiceImpl extends DbService<Seance> {

    /**
     * Ajoute la seance recu en parametre dans la bdd.
     * @param objet Objet a rajouter dans la base de donnée.
     * @throws SQLException Probleme de requete.
     * @throws ClassNotFoundException Probleme de driver.
     */
    @Override
    public void ajouter(Seance objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement addSeance = co.prepareStatement("INSERT INTO `seance`(`ID`, `Semaine`, `Date`, `Heure_Debut`, `Heure_Fin`, `Etat`, `ID_Cours`, `ID_Type`) VALUES ([?, ?, ?, ?, ?, ? ,?, ?)");
        addSeance.setInt(1, objet.getId());
        addSeance.setInt(2, objet.getSemaine());
        addSeance.setDate(3, java.sql.Date.valueOf(objet.getJour()));
        addSeance.setTime(4, java.sql.Time.valueOf(objet.getHeure_debut()));
        addSeance.setTime(5, java.sql.Time.valueOf(objet.getHeure_fin()));
        addSeance.setInt(6, objet.getEtat());
        addSeance.setInt(7, objet.getCours().getId());
        addSeance.setInt(8, objet.getType().getId());

        addSeance.executeUpdate();
    }

    /**
     * Modifie la seance recu en parametre dans la bdd en fonction de son id.
     * @param objet Objet a mettre a jour dans la bdd.
     * @throws SQLException Probleme de requete.
     * @throws ClassNotFoundException Probleme de driver.
     */
    @Override
    public void modifier(Seance objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement majSeance = co.prepareStatement("UPDATE `seance` SET `Semaine`= ?,`Date`= ?,`Heure_Debut`= ?,`Heure_Fin`= ?,`Etat`= ?,`ID_Cours`= ?,`ID_Type`= ? WHERE ID = ?");
        majSeance.setInt(1, objet.getSemaine());
        majSeance.setDate(2, java.sql.Date.valueOf(objet.getJour()));
        majSeance.setTime(3, java.sql.Time.valueOf(objet.getHeure_debut()));
        majSeance.setTime(4, java.sql.Time.valueOf(objet.getHeure_fin()));
        majSeance.setInt(5, objet.getEtat());
        majSeance.setInt(6, objet.getCours().getId());
        majSeance.setInt(7, objet.getType().getId());
        majSeance.setInt(8, objet.getId());

        majSeance.executeUpdate();
    }

    /**
     * Supprime la seance recu en parametre de la bdd.
     * @param objet Objet a supprimer dans la bdd.
     * @throws SQLException Probleme de requete.
     * @throws ClassNotFoundException Probleme de driver.
     */
    @Override
    public void supprimer(Seance objet) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement delSeance = co.prepareStatement("DELETE FROM `seance` WHERE ID = ?");
        delSeance.setInt(1, objet.getId());

        delSeance.executeUpdate();
    }

    /**
     * Recupere la table seance depuis la bdd.
     * @return ArrayList de seances.
     * @throws SQLException Probleme de requete.
     * @throws ClassNotFoundException Probleme de driver.
     */
    @Override
    public List<Seance> getAll() throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getAll = co.prepareStatement("SELECT * FROM `seance` WHERE 1");
        ResultSet res = getAll.executeQuery();

        ArrayList<Seance> liste = new ArrayList<Seance>();

        while (res.next()) {
            Seance seance = new Seance();
            seance.setId(res.getInt("ID"));
            seance.setSemaine(res.getInt("Semaine"));
            seance.setJour(res.getDate("Date").toLocalDate());
            seance.setHeure_debut(res.getTime("Heure_Debut").toLocalTime());
            seance.setHeure_fin(res.getTime("Heure_Fin").toLocalTime());
            seance.setEtat(res.getInt("Etat"));
            CoursDaoServiceImpl cours = new CoursDaoServiceImpl();
            seance.setCours(cours.getById(res.getInt("ID_Cours")));
            Type_CoursDaoServiceImpl type = new Type_CoursDaoServiceImpl();
            seance.setType(type.getById(res.getInt("ID_Type")));
            liste.add(seance);
        }

        return liste;
    }

    /**
     * Recupere une seance de la bdd en fonction de son id.
     * @param id Identifiant de l'objet a recuperer.
     * @return Seance ayant l'id recu en parametre.
     * @throws SQLException Probleme de requete.
     * @throws ClassNotFoundException Probleme de driver.
     */
    @Override
    public Seance getById(int id) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getCoursById = co.prepareStatement("SELECT * FROM `seance` WHERE ID = ? AND Etat = 1");
        getCoursById.setInt(1, id);
        ResultSet res = getCoursById.executeQuery();

        Seance seance = null;

        while (res.next()) {
            seance = new Seance();
            seance.setId(res.getInt("ID"));
            seance.setSemaine(res.getInt("Semaine"));
            seance.setJour(res.getDate("Date").toLocalDate());
            seance.setHeure_debut(res.getTime("Heure_Debut").toLocalTime());
            seance.setHeure_fin(res.getTime("Heure_Fin").toLocalTime());
            seance.setEtat(res.getInt("Etat"));
            CoursDaoServiceImpl cours = new CoursDaoServiceImpl();
            seance.setCours(cours.getById(res.getInt("ID_Cours")));
            Type_CoursDaoServiceImpl type = new Type_CoursDaoServiceImpl();
            seance.setType(type.getById(res.getInt("ID_Type")));
        }

        return seance;
    }

    /**
     * Recupere des seance de la bdd a partir d'une liste d'id seance.
     * @param ids Liste des ids des seances a récuperer.
     * @return ArrayList de seance.
     * @throws SQLException Probleme de requete.
     * @throws ClassNotFoundException Probleme de driver.
     */
    public ArrayList<Seance> getByIdList(HashSet<Integer> ids) throws SQLException, ClassNotFoundException {
        ArrayList<Seance> liste = new ArrayList<Seance>();

        for (int id : ids) {
            liste.add(this.getById(id));
        }

        return liste;
    }

    /**
     * Recupere un cours en fonction de son id et de sa semaine.
     * @param id Id du cours a chercher.
     * @param semaine Semaine du cours a chercher.
     * @return Seance ayant l'id et la semaine recu en parametre.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Seance getByIdAndSemaine (int id, int semaine) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getCoursById = co.prepareStatement("SELECT * FROM `seance` WHERE ID = ? AND Semaine = ? AND Etat = 1");
        getCoursById.setInt(1, id);
        getCoursById.setInt(2, semaine);
        ResultSet res = getCoursById.executeQuery();

        Seance seance = null;

        while (res.next()) {
            seance = new Seance();
            seance.setId(res.getInt("ID"));
            seance.setSemaine(res.getInt("Semaine"));
            seance.setJour(res.getDate("Date").toLocalDate());
            seance.setHeure_debut(res.getTime("Heure_Debut").toLocalTime());
            seance.setHeure_fin(res.getTime("Heure_Fin").toLocalTime());
            seance.setEtat(res.getInt("Etat"));
            CoursDaoServiceImpl cours = new CoursDaoServiceImpl();
            seance.setCours(cours.getById(res.getInt("ID_Cours")));
            Type_CoursDaoServiceImpl type = new Type_CoursDaoServiceImpl();
            seance.setType(type.getById(res.getInt("ID_Type")));
        }

        return seance;
    }

    /**
     * Recupere liste des cours de la semaine ayant un id de la liste recu.
     * @param ids Liste d'id de cours a chercher.
     * @param semaine Numero de la semaine a regarder.
     * @return Liste des cours de la semaine.
     * @throws SQLException Probleme de requete.
     * @throws ClassNotFoundException Probleme de driver.
     */
    public ArrayList<Seance> getSeanceSemaineParGroupe(HashSet<Integer> ids, int semaine) throws SQLException, ClassNotFoundException {
        ArrayList<Seance> liste = new ArrayList<Seance>();

        for (int id : ids) {
            liste.add(this.getByIdAndSemaine(id, semaine));
        }

        return liste;
    }

    /**
     * Retourne un arrayList de Seance en fonction de la semaine et de la promo recu en parametre.
     * @param semaine Numero de la semaine.
     * @param promo Id de la promotion.
     * @return ArrayList de Seance.
     * @throws SQLException Probleme de requete.
     * @throws ClassNotFoundException Probleme de driver.
     */
    public ArrayList<Seance> getByPromoAndSemaine(int semaine, int promo) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getCoursById = co.prepareStatement("SELECT seance.* FROM seance, seance_groupes, groupe WHERE seance_groupes.ID_Seance = seance.ID AND seance_groupes.ID_Groupe = groupe.ID AND semaine = ? AND groupe.ID_Promotion = ? AND seance.Etat = 1");
        getCoursById.setInt(1, semaine);
        getCoursById.setInt(2, promo);
        ResultSet res = getCoursById.executeQuery();

        ArrayList<Seance> liste = new ArrayList<Seance>();

        while (res.next()) {
            Seance seance = new Seance();
            seance.setId(res.getInt("ID"));
            seance.setSemaine(res.getInt("Semaine"));
            seance.setJour(res.getDate("Date").toLocalDate());
            seance.setHeure_debut(res.getTime("Heure_Debut").toLocalTime());
            seance.setHeure_fin(res.getTime("Heure_Fin").toLocalTime());
            seance.setEtat(res.getInt("Etat"));
            CoursDaoServiceImpl cours = new CoursDaoServiceImpl();
            seance.setCours(cours.getById(res.getInt("ID_Cours")));
            Type_CoursDaoServiceImpl type = new Type_CoursDaoServiceImpl();
            seance.setType(type.getById(res.getInt("ID_Type")));
            liste.add(seance);
        }

        return liste;
    }

    /**
     * Retourne liste de seance en fonction du nom et prenom de l'etudiant concerné et de la semaine.
     * @param semaine Numero de la semaine.
     * @param nom Nom de l'etudiant.
     * @param prenom Prenom de l'etudiant.
     * @return ArrayList de Seance.
     * @throws SQLException Probleme de requete.
     * @throws ClassNotFoundException Probleme de driver.
     */
    public ArrayList<Seance> getSeanceBySemaineAndEtudiant (int semaine, String nom, String prenom) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getCoursById = co.prepareStatement("SELECT seance.* FROM seance, seance_groupes, utilisateur, etudiant " +
                "WHERE seance_groupes.ID_Seance = seance.ID AND utilisateur.ID = etudiant.ID_Utilisateur AND etudiant.ID_Groupe = seance_groupes.ID_Groupe " +
                "AND semaine = ? AND utilisateur.Prenom = ? AND utilisateur.Nom = ? AND seance.Etat = 1");
        getCoursById.setInt(1, semaine);
        getCoursById.setString(2, prenom);
        getCoursById.setString(3, nom);
        ResultSet res = getCoursById.executeQuery();

        ArrayList<Seance> liste = new ArrayList<Seance>();

        while (res.next()) {
            Seance seance = new Seance();
            seance.setId(res.getInt("ID"));
            seance.setSemaine(res.getInt("Semaine"));
            seance.setJour(res.getDate("Date").toLocalDate());
            seance.setHeure_debut(res.getTime("Heure_Debut").toLocalTime());
            seance.setHeure_fin(res.getTime("Heure_Fin").toLocalTime());
            seance.setEtat(res.getInt("Etat"));
            CoursDaoServiceImpl cours = new CoursDaoServiceImpl();
            seance.setCours(cours.getById(res.getInt("ID_Cours")));
            Type_CoursDaoServiceImpl type = new Type_CoursDaoServiceImpl();
            seance.setType(type.getById(res.getInt("ID_Type")));
            liste.add(seance);
        }

        return liste;
    }

}
