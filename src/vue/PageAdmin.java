/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controler.MajDonnees;
import controler.RechercheDonnees;
import modele.*;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import static java.time.temporal.TemporalQueries.localDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Kozlow
 */
/**
 * Création de la class admin
 * @author gesli
 */
class PageAdmin extends JPanel implements ActionListener {

    private final CardLayout Layout;
    private final JPanel Conteneur;
    // Creation des panels pour l'affichage des options admin;
    private final JPanel Pmenu, PAffecterUnEnseignant, PAffecterUnGroupe, PAffecterUneSalle, PDeplacerUneSceance, PModifierCours, PAjouterUneSceance, PAjouterUnEnseignant, PAjouterUnGroupe, PValider, PAnnuler, PEnlever, PRecapitulatif;
    // Creation des bouttons permettant d'aller sur tel ou tel panel.
    private JButton GAffecterUnEnseignant, GAffecterUnGroupe, GAffecterUneSalle, GDeplacerUneSceance, GModifierCours, GAjouterUneSceance, GAjouterUnEnseignant, GAjouterUnGroupe, GValider, GAnnuler, GEnlever, GRecapitulatif;
    // Creation des bouttons permattant de retourner au menu.
    private JButton RAffecterUnEnseignant, RAffecterUnGroupe, RAffecterUneSalle, RDeplacerUneSceance, RModifierCours, RAjouterUneSceance, RAjouterUnEnseignant, RAjouterUnGroupe, RValider, RAnnuler, REnlever, RRecapitulatif;
    //Variable Menu
    java.awt.Color fond = new java.awt.Color(221, 240, 255);

    //Utilisateur connecté
    private Utilisateur user;

    //Controler pour récupérer les données
    private RechercheDonnees donnees = new RechercheDonnees();
    private MajDonnees Maj = new MajDonnees();

    /**
     * Constructeur de la page 'Admin'
     *
     * @param utilisateurCo Correspond à l'utilisateur connecté
     */
    public PageAdmin(Utilisateur utilisateurCo) {
        super();

        //Initialisation user
        user = new Utilisateur(utilisateurCo);

        setLayout(new FlowLayout());
        //Creation du layout pour naviguer
        Layout = new CardLayout();
        Conteneur = new JPanel();
        Conteneur.setLayout(Layout);

        Pmenu = new JPanel();
        Pmenu.setBackground(fond);
        Conteneur.add(Pmenu, "Pmenu");

        PAjouterUnEnseignant = new JPanel();
        Conteneur.add(PAjouterUnEnseignant, "PAjouterUnEnseignant");
        PAjouterUnEnseignant.setBackground(fond);
        this.AjouterUnEnseignant();

        PAffecterUnEnseignant = new JPanel();
        Conteneur.add(PAffecterUnEnseignant, "PAffecterUnEnseignant");
        PAffecterUnEnseignant.setBackground(fond);
        this.AffecterUnEnseignant();

        PAffecterUnGroupe = new JPanel();
        Conteneur.add(PAffecterUnGroupe, "PAffecterUnGroupe");
        PAffecterUnGroupe.setBackground(fond);
        this.AffecterUnGroupe();

        PAffecterUneSalle = new JPanel();
        Conteneur.add(PAffecterUneSalle, "PAffecterUneSalle");
        PAffecterUneSalle.setBackground(fond);
        this.AffecterUneSalle();

        PDeplacerUneSceance = new JPanel();
        Conteneur.add(PDeplacerUneSceance, "PDeplacerUneSceance");
        PDeplacerUneSceance.setBackground(fond);
        this.DeplacerUneSceance();

        PModifierCours = new JPanel();
        Conteneur.add(PModifierCours, "PModifierCours");
        PModifierCours.setBackground(fond);
        this.ModifierCours();

        PAjouterUneSceance = new JPanel();
        Conteneur.add(PAjouterUneSceance, "PAjouterUneSceance");
        PAjouterUneSceance.setBackground(fond);
        this.AjouterUneSceance();

        PAjouterUnGroupe = new JPanel();
        Conteneur.add(PAjouterUnGroupe, "PAjouterUnGroupe");
        PAjouterUnGroupe.setBackground(fond);
        this.AjouterUnGroupe();

        PValider = new JPanel();
        Conteneur.add(PValider, "PValider");
        PValider.setBackground(fond);
        this.Valider();

        PAnnuler = new JPanel();
        Conteneur.add(PAnnuler, "PAnnuler");
        PAnnuler.setBackground(fond);
        this.Annuler();

        PEnlever = new JPanel();
        Conteneur.add(PEnlever, "PEnlever");
        PEnlever.setBackground(fond);
        this.Enlever();

        PRecapitulatif = new JPanel();
        Conteneur.add(PRecapitulatif, "PRecapitulatif");
        PRecapitulatif.setBackground(fond);
        this.Recapitulatif();

        this.prop_conteneur();
        this.add(Conteneur);
    }
    /**
     * propriété du conteneur
     */
    private void prop_conteneur() {
        this.menu();
        this.GAjouterUnEnseignant.addActionListener(this);
        this.GAjouterUneSceance.addActionListener(this);
        this.GAjouterUnGroupe.addActionListener(this);
        this.GAffecterUnEnseignant.addActionListener(this);
        this.GAffecterUnGroupe.addActionListener(this);
        this.GAffecterUneSalle.addActionListener(this);
        this.GDeplacerUneSceance.addActionListener(this);
        this.GModifierCours.addActionListener(this);
        this.GAnnuler.addActionListener(this);
        this.GValider.addActionListener(this);
        this.GEnlever.addActionListener(this);
        this.GRecapitulatif.addActionListener(this);
    }

    // Menu
    /**
     * Menu
     */
    private void menu() {
        //Bouton pour naviguer
        GAffecterUnEnseignant = new JButton("Acceder : Affecter un enseignant.");
        GAffecterUnGroupe = new JButton("Acceder : Affecter un groupe.");
        GAffecterUneSalle = new JButton("Acceder : Affecter une salle.");
        GDeplacerUneSceance = new JButton("Acceder : Deplacer une sceance.");
        GModifierCours = new JButton("Acceder : Modifier un cours.");
        GAjouterUneSceance = new JButton("Acceder : Ajouter une sceance.");
        GAjouterUnEnseignant = new JButton("Acceder : Ajouter un enseignant.");
        GAjouterUnGroupe = new JButton("Acceder : Ajouter un groupe.");
        GValider = new JButton("Acceder : Valider un cours.");
        GAnnuler = new JButton("Acceder : Annuler un cours.");
        GEnlever = new JButton("Acceder : Enlever un groupe ou une enseignant.");
        GRecapitulatif = new JButton("Acceder : Voir le récapitulatif.");
        GAffecterUnEnseignant.setBounds(200, 200, 200, 200);
        Pmenu.add(GAffecterUnEnseignant);
        Pmenu.add(GAffecterUnGroupe);
        Pmenu.add(GAffecterUneSalle);
        Pmenu.add(GDeplacerUneSceance);
        Pmenu.add(GModifierCours);
        Pmenu.add(GAjouterUneSceance);
        Pmenu.add(GAjouterUnEnseignant);
        Pmenu.add(GAjouterUnGroupe);
        Pmenu.add(GValider);
        Pmenu.add(GAnnuler);
        Pmenu.add(GEnlever);
        //Pmenu.add(GRecapitulatif);

    }

    // Affecter un Enseignant
    /**
     * Affecter un enseigner
     */
    private void AffecterUnEnseignant() {
        //Init
        RAffecterUnEnseignant = new JButton("Retour au menu");
        PAffecterUnEnseignant.add(RAffecterUnEnseignant);
        ArrayList<String> aje_cours = new ArrayList();
        for (String g : donnees.getListeSeanceValidation())
        {aje_cours.add("a");}
        JList list_aje_cours;
        ArrayList<String> aje_enseignant = new ArrayList();
        for (Enseignant g : donnees.getListeEnseignant()) {
            aje_enseignant.add(g.getUtilisateur().getNom());
        }
        JList list_aje_enseignant;
        this.setLayout(new BorderLayout());
        list_aje_cours = new JList(aje_cours.toArray());
        list_aje_cours.setVisibleRowCount(5);
        list_aje_enseignant = new JList(aje_enseignant.toArray());
        list_aje_enseignant.setVisibleRowCount(5);
        list_aje_cours.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_aje_enseignant.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane liste_cours = new JScrollPane(list_aje_cours);
        JScrollPane liste_enseignant = new JScrollPane(list_aje_enseignant);
        JButton affecter = new JButton("Affecter cet enseignant");
        //Si click + selection des cases alors on ajoute.
        affecter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((list_aje_cours.getSelectedValue() != null) && (list_aje_enseignant.getSelectedValue() != null)) {
                    System.out.println(list_aje_cours.getSelectedValue());
                    System.out.println(list_aje_enseignant.getSelectedValue());
                }
            }
        });

        PAffecterUnEnseignant.add(liste_cours, BorderLayout.CENTER);
        PAffecterUnEnseignant.add(liste_enseignant, BorderLayout.CENTER);
        PAffecterUnEnseignant.add(affecter, BorderLayout.SOUTH);

        this.RAffecterUnEnseignant.addActionListener(this);
    }    //NEED REQUETES

    // Affecter un Groupe
    /**
     * Affectation d'un groupe
     */
    private void AffecterUnGroupe() {
        //Initialisation
        RAffecterUnGroupe = new JButton("Retour au menu");
        PAffecterUnGroupe.add(RAffecterUnGroupe);
        String aje_cours[] = {"Jeudi 12 : thermo", "Mardi 10 : Anthropologie", " Mardi 03 : English ", " Mardi 03 : Traitement du signal ", " Mercredi 04 : Droit du travail "};
        JList list_aje_cours;
        String aje_enseignant[] = {" ING3 TD04 ", " ING3 TD04 ", " ING3 TD04 ", " ING3 TD04 ", " ING3 TD04 ", " ING3 TD04 ", " ING3 TD04 ", " ING3 TD04 "};
        JList list_aje_enseignant;
        this.setLayout(new BorderLayout());
        list_aje_cours = new JList(aje_cours);
        list_aje_cours.setVisibleRowCount(5);
        list_aje_enseignant = new JList(aje_enseignant);
        list_aje_enseignant.setVisibleRowCount(5);
        list_aje_cours.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_aje_enseignant.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane liste_cours = new JScrollPane(list_aje_cours);
        JScrollPane liste_enseignant = new JScrollPane(list_aje_enseignant);
        JButton affecter = new JButton("Affecter un groupe");
        //Si click + selection des cases alors on ajoute.
        affecter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((list_aje_cours.getSelectedValue() != null) && (list_aje_enseignant.getSelectedValue() != null)) {
                    System.out.println(list_aje_cours.getSelectedValue());
                    System.out.println(list_aje_enseignant.getSelectedValue());
                }
            }
        });

        PAffecterUnGroupe.add(liste_cours, BorderLayout.CENTER);
        PAffecterUnGroupe.add(liste_enseignant, BorderLayout.CENTER);
        PAffecterUnGroupe.add(affecter, BorderLayout.SOUTH);
        this.RAffecterUnGroupe.addActionListener(this);
    }        //NEED REQUETES

    // Affecter Une salle
    /**
     * Affectation d'une salle
     */
    private void AffecterUneSalle() {
        //Initialisation
        String aje_cours[] = {"Jeudi 12 : thermo", "Mardi 10 : Anthropologie", " Mardi 03 : English ", " Mardi 03 : Traitement du signal ", " Mercredi 04 : Droit du travail "};
        JList list_aje_cours;
        String aje_salle[] = {" E2 304 ", " E2 415 ", " E2 407 ", " E2 312 ", " E2 347 ", " E4 G02 ", " E4 G04 ", " E4 G05 "};
        JList list_aje_salle;
        this.setLayout(new BorderLayout());
        list_aje_cours = new JList(aje_cours);
        list_aje_cours.setVisibleRowCount(5);
        list_aje_salle = new JList(aje_salle);
        list_aje_salle.setVisibleRowCount(5);
        list_aje_cours.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_aje_salle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane liste_cours = new JScrollPane(list_aje_cours);
        JScrollPane liste_enseignant = new JScrollPane(list_aje_salle);
        JButton affecter = new JButton("Affecter une salle");
        //Si click + selection des cases alors on ajoute.
        affecter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((list_aje_cours.getSelectedValue() != null) && (list_aje_salle.getSelectedValue() != null)) {
                    System.out.println(list_aje_cours.getSelectedValue());
                    System.out.println(list_aje_salle.getSelectedValue());
                }
            }
        });
        RAffecterUneSalle = new JButton("Retour au menu");
        PAffecterUneSalle.add(RAffecterUneSalle);
        PAffecterUneSalle.add(liste_cours, BorderLayout.EAST);
        PAffecterUneSalle.add(liste_enseignant, BorderLayout.CENTER);
        PAffecterUneSalle.add(affecter, BorderLayout.SOUTH);
        this.RAffecterUneSalle.addActionListener(this);
    }        //NEED REQUETES

    // Deplacer Une sceance
    /**
     * Deplacement d'une seance
     */
    private void DeplacerUneSceance() {
        //Initialisation
        RDeplacerUneSceance = new JButton("Retour au menu");
        PDeplacerUneSceance.add(RDeplacerUneSceance);
        String aje_cours[] = {"Jeudi 12 : thermo", "Mardi 10 : Anthropologie", " Mardi 03 : English ", " Mardi 03 : Traitement du signal ", " Mercredi 04 : Droit du travail "};
        JList list_sceance;
        String creneau[] = {" Mardi 14h30", " Mardi 14h30 ", " Mardi 14h30 ", " Mardi 14h30 ", " Mardi 14h30 ", " Mardi 14h30 ", " Mardi 14h30 ", " Mardi 14h30 "};
        JList list_creneau;
        this.setLayout(new BorderLayout());
        list_sceance = new JList(aje_cours);
        list_sceance.setVisibleRowCount(5);
        list_creneau = new JList(creneau);
        list_creneau.setVisibleRowCount(5);
        list_sceance.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_creneau.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane liste_cours = new JScrollPane(list_sceance);
        JScrollPane liste_enseignant = new JScrollPane(list_creneau);
        JButton affecter = new JButton("Choisir ce cour");
        //Si click = le cours est choisit pour changer l'horaire
        affecter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (list_sceance.getSelectedValue() != null) {
                    affecter.setText((String) list_sceance.getSelectedValue());
                    PDeplacerUneSceance.add(liste_enseignant, BorderLayout.CENTER);
                    JButton horaire_choisit = new JButton("Choisir cet horaire");
                    PDeplacerUneSceance.add(horaire_choisit, BorderLayout.SOUTH);
                    // HOraire choisit --> Validation
                    horaire_choisit.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (list_creneau.getSelectedValue() != null) {
                                System.out.println(list_sceance.getSelectedValue());
                                System.out.println(list_creneau.getSelectedValue());
                            }
                        }
                    });
                }
            }
        });

        PDeplacerUneSceance.add(liste_cours, BorderLayout.EAST);
        PDeplacerUneSceance.add(affecter, BorderLayout.SOUTH);
        this.RDeplacerUneSceance.addActionListener(this);
    }      //NEED REQUETES

    // Modfier Cours
    /**
     * Modification d'un cours
     */
    private void ModifierCours() {
        //Initalisation des variables
        String aje_cours[] = {"Jeudi 12 : thermo", "Mardi 10 : Anthropologie", " Mardi 03 : English ", " Mardi 03 : Traitement du signal ", " Mercredi 04 : Droit du travail "};
        JList list_cours;
        list_cours = new JList(aje_cours);
        list_cours.setVisibleRowCount(5);
        list_cours.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane liste_cours = new JScrollPane(list_cours);
        JButton nom = new JButton("Modifier le nom");
        JTextField nom_saisit = new JTextField("Saisir le nouveau nom");
        nom_saisit.setVisible(false);
        JButton nom_valider = new JButton("Valider le nom");
        nom_valider.setVisible(false);
        JButton type = new JButton("Modifier le type");
        JTextField type_saisit = new JTextField("Saisir le nouveau type");
        type_saisit.setVisible(false);
        JButton type_valider = new JButton("Valider le type");
        type_valider.setVisible(false);
        //SI click : on affiche le nom a changer
        nom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nom_saisit.setVisible(true);
                nom_valider.setVisible(true);
            }
        });
        //SI click : on affiche le nom a changer
        type.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type_saisit.setVisible(true);
                type_valider.setVisible(true);
            }
        });
        //utiliser le getSelectedValue() pour obtenir le cours choisit.

        PModifierCours.add(liste_cours, BorderLayout.EAST);
        PModifierCours.add(nom);
        PModifierCours.add(nom_saisit);
        PModifierCours.add(nom_valider);
        PModifierCours.add(type);
        PModifierCours.add(type_saisit);
        PModifierCours.add(type_valider);
        RModifierCours = new JButton("Retour au menu");
        PModifierCours.add(RModifierCours);
        this.RModifierCours.addActionListener(this);
    }           //NEED REQUETES

    // Ajouter une Sceance
    /**
     * Ajout d'un seance
     */
    private void AjouterUneSceance() {
        RAjouterUneSceance = new JButton("Retour au menu");
        PAjouterUneSceance.add(RAjouterUneSceance);

        //INIT DE TES VARIABLES ET DES LISTES : Need requetes.
        JLabel date, heureDebut, heureFin;
        JTextField Tdate, Theuredebut, TheureFin;
        date = new JLabel("Choisir la date : ");
        heureDebut = new JLabel("Choisir l'heure de début :(hh:mm:ss) ");
        heureFin = new JLabel("Choisir l'heure de fin : (hh:mm:ss) ");
        Tdate = new JTextField("ajouter une date (dd mm yyyy)");
        Theuredebut = new JTextField("ajouter une heure (hh:mm)");
        TheureFin = new JTextField("ajouter une heure (hh:mm)");

        ArrayList<String> groupe_string = new ArrayList();
        for (Groupe g : donnees.getListeGroupe()) {
            System.out.println(g.getId());
            groupe_string.add(g.getNom());
            
        }
        JList list_groupe;
        list_groupe = new JList(groupe_string.toArray());
        list_groupe.setVisibleRowCount(4);
        list_groupe.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane liste_groupe = new JScrollPane(list_groupe);

        ArrayList<String> Enseignantstring = new ArrayList();
        for (Enseignant g : donnees.getListeEnseignant()) {
            Enseignantstring.add(g.getUtilisateur().getNom());
        }
        JList list_enseignant;
        list_enseignant = new JList(Enseignantstring.toArray());
        list_enseignant.setVisibleRowCount(4);
        list_enseignant.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane liste_enseignant = new JScrollPane(list_enseignant);

        ArrayList<String> salle_str = new ArrayList();
        for (Salle g : donnees.getListeSalles()) {
            salle_str.add(g.getNom());
        }
        JList list_salle;
        list_salle = new JList(salle_str.toArray());
        list_salle.setVisibleRowCount(4);
        list_salle.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane liste_salle = new JScrollPane(list_salle);

        ArrayList<String> cours = new ArrayList();
        for (Cours c : donnees.getListeCours()) {
            cours.add(c.getNom());
        }
        JList list_cours;
        list_cours = new JList(cours.toArray());
        list_cours.setVisibleRowCount(4);
        list_cours.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane liste_cours = new JScrollPane(list_cours);

        ArrayList<String> type_cours = new ArrayList();
        for (Type_Cours t : donnees.getListeTypeCours()) {
            type_cours.add(t.getNom());
        }
        JList list_type_cours;
        list_type_cours = new JList(type_cours.toArray());
        list_type_cours.setVisibleRowCount(4);
        list_type_cours.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane liste_type_cours = new JScrollPane(list_type_cours);

        PAjouterUneSceance.add(date);
        PAjouterUneSceance.add(Tdate);
        PAjouterUneSceance.add(heureDebut);
        PAjouterUneSceance.add(Theuredebut);
        PAjouterUneSceance.add(heureFin);
        PAjouterUneSceance.add(TheureFin);
        PAjouterUneSceance.add(liste_enseignant);
        PAjouterUneSceance.add(liste_groupe);
        PAjouterUneSceance.add(liste_salle);
        PAjouterUneSceance.add(liste_cours);
        PAjouterUneSceance.add(liste_type_cours);
        JButton valider = new JButton("Valider");
        //Click (blindage) = Ajout de la nouvelle Seance
        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String datelocale = (String) Tdate.getText();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
                LocalDate dt = LocalDate.parse(datelocale, formatter);
                LocalTime hDebut = LocalTime.parse(Theuredebut.getText());
                LocalTime hFin = LocalTime.parse(TheureFin.getText());
                TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
                int weekNumber = dt.get(woy);
                String Stype = list_type_cours.getSelectedValue().toString();
                Type_Cours selectType = null;
                for (Type_Cours co : donnees.getListeTypeCours()) {
                    {
                        if (co.getNom() .equals(Stype)) {
                            selectType = new Type_Cours(co);
                        }
                    }
                }
                String Scours =  list_cours.getSelectedValue().toString();
                Cours selectCours = null;
                for (Cours co : donnees.getListeCours()) {
                    {
                        if (co.getNom() .equals(Scours) ) {
                            selectCours = new Cours(co);
                        }
                    }
                }
                String SGroupe = list_groupe.getSelectedValue().toString();
                System.out.println(Scours);
                HashSet <Groupe> selectGroupe = new HashSet ();
                for (Groupe co : donnees.getListeGroupe()) {
                    {
                        if (co.getNom() .equals (SGroupe)) {
                            selectGroupe.add(new Groupe(co));
                        }
                    }
                }
                String SProf = list_enseignant.getSelectedValue().toString();
                HashSet <Enseignant> selectEnseignant = new HashSet ();
                for (Enseignant co : donnees.getListeEnseignant()) {
                    {
                        if (co.getUtilisateur().getNom() .equals(SProf) ) {
                            selectEnseignant.add(new Enseignant(co));
                        }
                    }
                }
                String SSalle = list_salle.getSelectedValue().toString();
                HashSet <Salle> selectSalle = new HashSet ();
                for (Salle co : donnees.getListeSalles()) {
                    {
                        if (co.getNom() .equals(SSalle)) {
                            selectSalle.add(new Salle(co));
                        }
                    }
                }
                
                Maj.creerNouvelleSeance(weekNumber, dt, hDebut, hFin, 2, selectCours, selectType, selectEnseignant, selectGroupe, selectSalle);

            }
        });
        PAjouterUneSceance.add(valider);
        this.RAjouterUneSceance.addActionListener(this);
    }       //NEED REQUETES

    // Ajouter Un enseignant
    /**
     * Ajout d'un enseignant 
     */
    private void AjouterUnEnseignant() {                   //NEED REQUETES
        //initialisation des variables
        RAjouterUnEnseignant = new JButton("Retour au menu");
        PAjouterUnEnseignant.add(RAjouterUnEnseignant);
        JLabel nom, prenom, email, password, cours_enseigné;
        JTextField Tnom, Tprenom, Temail, Tpassword, Tcours;
        nom = new JLabel("Choisir un nom");
        Tnom = new JTextField("Taper votre nom");
        PAjouterUnEnseignant.add(nom);
        PAjouterUnEnseignant.add(Tnom);
        prenom = new JLabel("Choisir un prenom");
        Tprenom = new JTextField("Taper votre prenom");
        PAjouterUnEnseignant.add(prenom);
        PAjouterUnEnseignant.add(Tprenom);
        email = new JLabel("Choisir un email");
        Temail = new JTextField("Taper votre email");
        PAjouterUnEnseignant.add(email);
        PAjouterUnEnseignant.add(Temail);
        password = new JLabel("Choisir un password");
        Tpassword = new JTextField("Taper votre password");
        PAjouterUnEnseignant.add(password);
        PAjouterUnEnseignant.add(Tpassword);
        String aje_cours[] = {" thermo", " Anthropologie", " English ", "  Traitement du signal ", " Droit du travail ", "Math"};
        JList list_cours;
        list_cours = new JList(aje_cours);
        list_cours.setVisibleRowCount(5);
        list_cours.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane liste_cours = new JScrollPane(list_cours);
        PAjouterUnEnseignant.add(liste_cours);
        JButton valider = new JButton("Valider");
        //SI click (need blindage) alors ca ajoute le nouveau enseignant.
        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Enseignant ajouté");

            }
        });
        PAjouterUnEnseignant.add(valider);
        this.RAjouterUnEnseignant.addActionListener(this);

    }

    // Ajouter Un Groupe
    /**
     * Ajout d'un groupe
     */
    private void AjouterUnGroupe() {                    //Need Requete
        //Initialisation  des variables
        JLabel nom, promo;
        JTextField Tnom, Tpromo;
        nom = new JLabel("Choisir un nom");
        Tnom = new JTextField("Taper votre nom");
        PAjouterUnGroupe.add(nom);
        PAjouterUnGroupe.add(Tnom);
        promo = new JLabel("Choisir un promo");
        Tpromo = new JTextField("Taper votre promo");
        PAjouterUnGroupe.add(promo);
        PAjouterUnGroupe.add(Tpromo);
        JButton valider = new JButton("Valider");
        // Si click (need blindage) = ajouter un groupe;
        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Groupe ajouté");

            }
        });
        PAjouterUnGroupe.add(valider);
        RAjouterUnGroupe = new JButton("Retour au menu");
        PAjouterUnGroupe.add(RAjouterUnGroupe);
        this.RAjouterUnGroupe.addActionListener(this);
    }           //NEED REQUETES

    // Valider
    /**
     * Validation 
     */
    private void Valider() {                           //     OK
        // Initialisation des variables

        ArrayList<String> aje_cours = donnees.getListeSeanceValidation();
        JList list_cours;
        list_cours = new JList(aje_cours.toArray());

        list_cours.setVisibleRowCount(5);
        list_cours.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane liste_cours = new JScrollPane(list_cours);
        PValider.add(liste_cours);
        JButton valider = new JButton("Valider");
        //Si click + Cours selectionné : on valide le cours
        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (list_cours.getSelectedValue() != null) {
                    String idseance = (String) list_cours.getSelectedValue();
                    idseance = idseance.substring(idseance.lastIndexOf("*") + 1);
                    Maj.validerSeance(Integer.parseInt(idseance));
                }
            }
        });
        PValider.add(valider);
        RValider = new JButton("Retour au menu");
        PValider.add(RValider);
        this.RValider.addActionListener(this);
    }

    // Annuler
    /**
     * annulation d'un cours
     */
    private void Annuler() {                                //NEED REQUETES
        //Initialisation des variables.
        String aje_cours[] = {"Jeudi 12 : thermo", "Mardi 10 : Anthropologie", " Mardi 03 : English ", " Mardi 03 : Traitement du signal ", " Mercredi 04 : Droit du travail "};
        JList list_cours;
        list_cours = new JList(aje_cours);
        list_cours.setVisibleRowCount(5);
        list_cours.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane liste_cours = new JScrollPane(list_cours);
        PAnnuler.add(liste_cours);
        JButton valider = new JButton("Annuler le cours");
        //Si clique et un cours selectionné : on annule.
        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (list_cours.getSelectedValue() != null) {
                    System.out.println("Cours annulé");
                }

            }
        });
        PAnnuler.add(valider);
        RAnnuler = new JButton("Retour au menu");
        PAnnuler.add(RAnnuler);
        this.RAnnuler.addActionListener(this);
    }

    // Enlever
    /**
     * Enlever un prof
     */
    private void Enlever() {            //NEED REQUETES
        REnlever = new JButton("Retour au menu");
        PEnlever.add(REnlever);
        //Initialisation des variables
        String cours[] = {"Jeudi 12 : thermo", "Mardi 10 : Anthropologie", " Mardi 03 : English ", " Mardi 03 : Traitement du signal ", " Mercredi 04 : Droit du travail "};
        JList list_sceance;
        String groupe[] = {" Mardi 14h30", " Mardi 14h30 ", " Mardi 14h30 ", " Mardi 14h30 ", " Mardi 14h30 ", " Mardi 14h30 ", " Mardi 14h30 ", " Mardi 14h30 "};
        JList list_Grp;
        this.setLayout(new BorderLayout());
        list_sceance = new JList(cours);
        list_sceance.setVisibleRowCount(5);
        list_Grp = new JList(groupe);
        list_Grp.setVisibleRowCount(5);
        list_sceance.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_Grp.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane liste_cours = new JScrollPane(list_sceance);
        JScrollPane liste_enseignant = new JScrollPane(list_Grp);
        JButton affecter = new JButton("Choisir ce cour");
        affecter.addActionListener(new ActionListener() {
            @Override
            //Lorsqu'un cours est selectionné on affiche :
            public void actionPerformed(ActionEvent e) {
                if (list_sceance.getSelectedValue() != null) {
                    affecter.setText((String) list_sceance.getSelectedValue());
                    PEnlever.add(liste_enseignant);
                    JButton EnleverGroupe = new JButton("Enlever le groupe");
                    PEnlever.add(EnleverGroupe);
                    //Si on clique sur enlevé groupe et qu'il est choisit:
                    EnleverGroupe.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (list_Grp.getSelectedValue() != null) {
                                System.out.println(list_sceance.getSelectedValue());
                                System.out.println(list_Grp.getSelectedValue());
                            }
                        }
                    });
                    JLabel nom_prof = new JLabel("nom du prof");
                    JButton enlever_prof = new JButton("Enlever l'enseignant");
                    PEnlever.add(nom_prof, BorderLayout.SOUTH);
                    PEnlever.add(enlever_prof, BorderLayout.SOUTH);
                    //Si on clique sur enlevé groupe et qu'il est choisit:
                    enlever_prof.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (list_Grp.getSelectedValue() != null) {
                                System.out.println(list_sceance.getSelectedValue());
                                System.out.println(list_Grp.getSelectedValue());
                            }
                        }
                    });
                }
            }
        });

        PEnlever.add(liste_cours, BorderLayout.EAST);
        PEnlever.add(affecter, BorderLayout.SOUTH);
        this.REnlever.addActionListener(this);
    }                   //NEED REQUETES

    // Recapitulatif 
    /**
     * Récapitulatif
     */
    private void Recapitulatif() {
        RRecapitulatif = new JButton("Retour au menu");
        PRecapitulatif.add(RRecapitulatif);
        this.RRecapitulatif.addActionListener(this);
    }

    // Click
    /**
     * Navigation grace a ActionEvent
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.GAjouterUnEnseignant) {
            Layout.show(Conteneur, "PAjouterUnEnseignant");
        }
        if (e.getSource() == this.RAjouterUnEnseignant) {
            Layout.show(Conteneur, "Pmenu");
        }

        if (e.getSource() == this.GAffecterUnEnseignant) {
            Layout.show(Conteneur, "PAffecterUnEnseignant");
        }
        if (e.getSource() == this.RAffecterUnEnseignant) {
            Layout.show(Conteneur, "Pmenu");
        }

        if (e.getSource() == this.GAffecterUnGroupe) {
            Layout.show(Conteneur, "PAffecterUnGroupe");
        }
        if (e.getSource() == this.RAffecterUnGroupe) {
            Layout.show(Conteneur, "Pmenu");
        }

        if (e.getSource() == this.GAffecterUneSalle) {
            Layout.show(Conteneur, "PAffecterUneSalle");
        }
        if (e.getSource() == this.RAffecterUneSalle) {
            Layout.show(Conteneur, "Pmenu");
        }

        if (e.getSource() == this.GDeplacerUneSceance) {
            Layout.show(Conteneur, "PDeplacerUneSceance");
        }
        if (e.getSource() == this.RDeplacerUneSceance) {
            Layout.show(Conteneur, "Pmenu");
        }

        if (e.getSource() == this.GModifierCours) {
            Layout.show(Conteneur, "PModifierCours");
        }
        if (e.getSource() == this.RModifierCours) {
            Layout.show(Conteneur, "Pmenu");
        }

        if (e.getSource() == this.GAjouterUneSceance) {
            Layout.show(Conteneur, "PAjouterUneSceance");
        }
        if (e.getSource() == this.RAjouterUneSceance) {
            Layout.show(Conteneur, "Pmenu");
        }

        if (e.getSource() == this.GAjouterUnGroupe) {
            Layout.show(Conteneur, "PAjouterUnGroupe");
        }
        if (e.getSource() == this.RAjouterUnGroupe) {
            Layout.show(Conteneur, "Pmenu");
        }

        if (e.getSource() == this.GValider) {
            Layout.show(Conteneur, "PValider");
        }
        if (e.getSource() == this.RValider) {
            Layout.show(Conteneur, "Pmenu");
        }

        if (e.getSource() == this.GAnnuler) {
            Layout.show(Conteneur, "PAnnuler");
        }
        if (e.getSource() == this.RAnnuler) {
            Layout.show(Conteneur, "Pmenu");
        }

        if (e.getSource() == this.GEnlever) {
            Layout.show(Conteneur, "PEnlever");
        }
        if (e.getSource() == this.REnlever) {
            Layout.show(Conteneur, "Pmenu");
        }

        if (e.getSource() == this.GRecapitulatif) {
            Layout.show(Conteneur, "PRecapitulatif");
        }
        if (e.getSource() == this.RRecapitulatif) {
            Layout.show(Conteneur, "Pmenu");
        }
    }
}
