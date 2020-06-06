package dao.service;

import dao.DbService;
import modele.Seance;

import java.sql.*;
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
     * Recupere l'id d'une seance a partir de ses autres parametres.
     * @param seance Seance dont on veux recuperer l'id.
     * @return Id de la séance.
     * @throws SQLException Probleme de requete.
     * @throws ClassNotFoundException Probleme de driver.
     */
    public int getId(Seance seance) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement sql = co.prepareStatement("SELECT `ID` FROM `seance` WHERE `Semaine` = ? " +
                "AND `Date` = ? AND `Heure_Debut` = ? AND `Heure_Fin` = ? AND `Etat` = ? AND `ID_Cours` = ? " +
                "AND `ID_Type` = ?");
        sql.setInt(1, seance.getSemaine());
        sql.setDate(2, Date.valueOf(seance.getJour()));
        sql.setTime(3, Time.valueOf(seance.getHeure_debut()));
        sql.setTime(4, Time.valueOf(seance.getHeure_fin()));
        sql.setInt(5, seance.getEtat());
        sql.setInt(6, seance.getCours().getId());
        sql.setInt(7, seance.getType().getId());
        ResultSet res = sql.executeQuery();

        int id = -1;

        while (res.next()) {
            id = res.getInt("ID");
        }

        return id;
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

    /**
     * Retourne la liste de seance en fonction du nom et prenom de l'enseignant concerné et de la semaine.
     * @param semaine Numero de la semaine.
     * @param nom Nom de l'enseignant.
     * @param prenom Prenom de l'enseignant.
     * @return ArrayList de Seance.
     * @throws SQLException Probleme de requete.
     * @throws ClassNotFoundException Probleme de driver.
     */
    public ArrayList<Seance> getSeanceBySemaineAndEnseignant(int semaine, String nom, String prenom) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getCoursById = co.prepareStatement("SELECT seance.* FROM seance, seance_enseignants, utilisateur WHERE seance_enseignants.ID_Seance = seance.ID AND seance_enseignants.ID_Enseignant = utilisateur.ID " +
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

    /**
     * Retourne la liste de seance en fonction du nom de la salle et de la semaine.
     * @param semaine Numero de la semaine.
     * @param salle Nom de la salle.
     * @return ArrayList de Seance.
     * @throws SQLException Probleme de requete.
     * @throws ClassNotFoundException Probleme de driver.
     */
    public ArrayList<Seance> getSeanceBySemaineAndSalle(int semaine, String salle) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getCoursById = co.prepareStatement("SELECT seance.* FROM seance, seance_salles, salle WHERE seance.ID = seance_salles.ID_Seance AND seance_salles.ID_Salle = salle.ID AND seance.Semaine = ? AND salle.Nom = ? AND seance.Etat = 1");
        getCoursById.setInt(1, semaine);
        getCoursById.setString(2, salle);
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
     * Recupere une liste de String contenant cours, groupe et promo sur chaque ligne correspondant a tout les cours enseignés par un enseignant.
     * @param idEnseignant Id de l'enseignant.
     * @return String des info cours, groupe et promo.
     * @throws SQLException Probleme de requete.
     * @throws ClassNotFoundException Probleme de driver.
     */
    public ArrayList<String> getComboCoursAndGroupeByEnseignant(int idEnseignant) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getCoursById = co.prepareStatement("SELECT cours.Nom AS cNom, groupe.Nom AS gNom, seance_enseignants.ID_Enseignant, promotion.Nom AS pNom " +
                "FROM seance, cours, seance_groupes, groupe, seance_enseignants, promotion " +
                "WHERE seance.ID = seance_groupes.ID_Seance AND cours.ID = seance.ID_Cours AND seance_groupes.ID_Groupe = groupe.ID AND seance_enseignants.ID_Seance = seance.ID AND promotion.ID = groupe.ID_Promotion " +
                "AND seance_enseignants.ID_Enseignant = ? GROUP BY cours.Nom, groupe.Nom ");
        getCoursById.setInt(1, idEnseignant);
        ResultSet res = getCoursById.executeQuery();

        ArrayList<String> liste = new ArrayList<>();

        while (res.next()) {
            liste.add(res.getString("cNom") + " | " + res.getString("pNom") + "/" + res.getString("gNom"));
        }

        return liste;
    }

    /**
     * Recupere les stats d'un cours. Premiere séance, derniere séance et nombre de séances.
     * @param idEnseignant Id de l'enseignant.
     * @param cours Nom du cours.
     * @param groupe Nom du groupe.
     * @param promo Nom de la promo.
     * @return String des stats de cours.
     * @throws SQLException Probleme de requete.
     * @throws ClassNotFoundException Probleme de driver.
     */
    public String getStatsByCoursGroupeAndEnseignants(int idEnseignant, String cours, String groupe, String promo) throws SQLException, ClassNotFoundException {
        Connection co = this.connexion();
        PreparedStatement getCoursById = co.prepareStatement("SELECT COUNT(seance.ID) AS Nb, MIN(TIMESTAMP(seance.Date, seance.Heure_Debut)) AS Premier, MAX(TIMESTAMP(seance.Date, seance.Heure_Debut)) AS dernier " +
                "FROM seance, seance_groupes, seance_enseignants, groupe, promotion, cours " +
                "WHERE seance_groupes.ID_Seance = seance.ID AND seance_enseignants.ID_Seance = seance.ID AND seance_groupes.ID_Groupe = groupe.ID AND groupe.ID_Promotion = promotion.ID AND seance.ID_Cours = cours.ID " +
                "AND groupe.Nom = ? AND promotion.Nom = ? AND cours.Nom = ? AND seance_enseignants.ID_Enseignant = ?");
        getCoursById.setString(1, groupe);
        getCoursById.setString(2, promo);
        getCoursById.setString(3, cours);
        getCoursById.setInt(4, idEnseignant);
        ResultSet res = getCoursById.executeQuery();

        String stats = null;

        while (res.next()) {
            stats = res.getString("Premier") + " | " + res.getString("dernier") + " | " + res.getInt("Nb");
        }

        return stats;
    }
}
