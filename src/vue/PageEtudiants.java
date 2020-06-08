/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controler.RechercheDonnees;
import modele.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EtchedBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Kozlow
 * 
 * Page Etudiant
 */
class PageEtudiants extends JPanel implements ActionListener{
    //Conteneur des onglets
    private JTabbedPane onglets; 
    //Onglets
    private JPanel panelEDT, panelRecap, panelAnnules;
    //Pour pré-selection semaine
    private int semaineAct;
    
//Pour panelEDT :
    private JPanel panelEDTNord, panelEDTCenter;
    private JLabel labelChoix, labelNom, labelListe, labelSemaine;
    private JTextField textFieldNom;
    private JComboBox comboSemaine, comboChoix, comboListe;
    private JButton chercherEDT;
    private ArrayList<Etudiant> tabChoixEtudiant;
    
//Pour panelRecap :
    private JPanel panelRecapNord, panelRecapCenter;
    private JLabel labelNom2; //Choi nom
    private JTextField textFieldNom2;
    private JButton chercherRecap;
    
//Pour panelAnnules :
    private JPanel panelAnnulesNord, panelAnnulesCenter;
    private JLabel labelNom3; //Choi nom
    private JTextField textFieldNom3;
    private JLabel labelSemaine3; //Choix semaine
    private JComboBox comboSemaine3;
    private JButton chercherAnnules;
    
//Tableaux
    private final int[] tabChoixSemaine = {31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29};
    private final String[] tabLabelsEDT = {"\nHoraires","\nLundi","\nMardi","\nMercredi","\nJeudi","\nVendredi","\nSamedi"};
    private final String[] tabCreneauxEDT = {"", "8h30\n\n\n10h", "10h15\n\n\n11h45", "12h\n\n\n13h30", "13h45\n\n\n15h15", "15h30\n\n\n17h", "17h15\n\n\n18h45", "19h\n\n\n20h30"};
    private final String[] tabRecherche = {"Par nom", "Depuis une liste"};
    
    //Utilisateur connecté
    private Utilisateur user;
    
    //Controler pour récupérer les données
    private RechercheDonnees donnees = new RechercheDonnees();
    
    
    /**
     * Constructeur de la page 'Etudiants'
     * @param utilisateurCo Correspond à l'utilisateur connecté
     */
    public PageEtudiants(Utilisateur utilisateurCo)
    {
        //Initialisation user
        user = new Utilisateur(utilisateurCo);
        
        //Initialisation des onglets
        onglets = new JTabbedPane();
        panelEDT = new JPanel();
        panelRecap = new JPanel();
        panelAnnules = new JPanel();
        
        //Initialisation layouts
        panelEDT.setLayout(new BorderLayout());
        panelRecap.setLayout(new BorderLayout());
        panelAnnules.setLayout(new BorderLayout());
        
        //Initialisation Semaine Actuelle
        GregorianCalendar gc = new GregorianCalendar();
        gc.add(Calendar.DATE, 0);
        semaineAct = gc.get(Calendar.WEEK_OF_YEAR); 
        
        //Initialisation panelEDT
        initPanelEDT();
        //Initialisation panelRecap
        initPanelRecap();
        //Initialisation panelAnnules
        initPanelAnnules();
        
        //Ajout des onglets au conteneur
        onglets.add("Emploi du temps", panelEDT);
        onglets.add("Récapitulatif des cours", panelRecap);
        onglets.add("Liste des cours annulés", panelAnnules);
        
        //On ajoute les onglets à la page 
        //(BorderLayout pour prendre tout l'espace, car 'CENTER' par défaut pour les panels)
        this.setLayout(new BorderLayout());
        this.add(onglets);
    }
    
//PANEL EDT
    /**
     * Initialisation Panel EDT
     */
    public final void initPanelEDT()
    {
        initPanelEDTCenter();
        initPanelEDTNord();
        //Mise en place panelEDT
        panelEDT.add(panelEDTNord, BorderLayout.NORTH);
        panelEDT.add(panelEDTCenter, BorderLayout.CENTER);
    }
    /**
     * Initialisation Panel EDT Nord
     */
    public void initPanelEDTNord()
    {
        panelEDTNord = new JPanel();
        panelEDTNord.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelEDTNord.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        
        //Initialisation choix Recherche
        labelChoix = new JLabel("Rechercher");
        comboChoix = new JComboBox();
        comboChoix.setPreferredSize(new Dimension(115, 25));
        for(String choixRecherche : tabRecherche){
            comboChoix.addItem(choixRecherche);
        }
        
        //Initialisation recherche par nom
        labelNom = new JLabel("Entrez le nom d'un élève :");
        textFieldNom = new JTextField();
        textFieldNom.setPreferredSize(new Dimension(200, 25));
        
        //Initialisation recherche par liste
        labelListe = new JLabel("Cherchez depuis la liste des étudiants :");
        comboListe = new JComboBox();
        comboListe.setPreferredSize(new Dimension(200, 25));
        //Récupérer la liste d'étudiants 
        tabChoixEtudiant =  donnees.getListeEtudiant();
        for(int i=0; i<tabChoixEtudiant.size(); i++){
            String temp = tabChoixEtudiant.get(i).getUtilisateur().getNom().toUpperCase();
            comboListe.addItem(temp);
        }
        
        //Initialisation choix semaine
        labelSemaine = new JLabel("Choisissez une semaine :");
        comboSemaine = new JComboBox();
        comboSemaine.setPreferredSize(new Dimension(60, 25));
        for(int choixSemaine : tabChoixSemaine){
            comboSemaine.addItem(choixSemaine);
        }
        
        //Initialisation bouton 
        chercherEDT = new JButton("Rechercher");
        
        //Ajout listeners
        comboChoix.addActionListener(this);
        chercherEDT.addActionListener(this);
        
        //Pré-selection de la semaine actuelle
        comboSemaine.setSelectedItem(semaineAct);
        
        //Ajouts au panelEDTNord
        panelEDTNord.add(labelChoix);
        panelEDTNord.add(comboChoix);
        panelEDTNord.add(labelNom);
        panelEDTNord.add(textFieldNom);
        panelEDTNord.add(labelListe);
        panelEDTNord.add(comboListe);
        panelEDTNord.add(labelSemaine);
        panelEDTNord.add(comboSemaine);
        panelEDTNord.add(chercherEDT);
        
        //Recherche par nom et par liste cachés par défaut
        labelNom.setVisible(false);
        textFieldNom.setVisible(false);
        labelListe.setVisible(false);
        comboListe.setVisible(false);
        labelSemaine.setVisible(false);
        comboSemaine.setVisible(false);
        chercherEDT.setVisible(false);
    }
    /**
     * Initialisation Panel EDT Centre
     */
    public void initPanelEDTCenter()
    {
        panelEDTCenter = new JPanel();
        panelEDTCenter.setLayout(new BorderLayout());
    }
    
    
//PANEL RECAP
    /**
     * Initialisation Panel Recap 
     */
    public final void initPanelRecap()
    {
        initPanelRecapCenter();
        initPanelRecapNord();
        //Mise en place panelRecap
        panelRecap.add(panelRecapNord, BorderLayout.NORTH);
        panelRecap.add(panelRecapCenter, BorderLayout.CENTER);
    }
    /**
     * Initialisation Panel Recap Nord
     */
    public void initPanelRecapNord()
    {
        panelRecapNord = new JPanel();
        panelRecapNord.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelRecapNord.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        
        //Initialisation recherche par nom
        labelNom2 = new JLabel("Entrez le nom d'un élève :");
        textFieldNom2 = new JTextField();
        textFieldNom2.setPreferredSize(new Dimension(150, 25));
        
        //Initialisaion bouton recherche + ajout listener
        chercherRecap = new JButton("Rechercher");
        chercherRecap.addActionListener(this);
        
        //Ajout des Composants au panel
        panelRecapNord.add(labelNom2);
        panelRecapNord.add(textFieldNom2);
        panelRecapNord.add(chercherRecap);
    }
    /**
     * Initialisation Panel Recap Centre
     */
    public void initPanelRecapCenter()
    {
        panelRecapCenter = new JPanel();
        panelRecapCenter.setLayout(new BorderLayout());
    }
    
    
//PANEL ANNULES
    /**
     * Initialisation Panel Annules 
     */
    public final void initPanelAnnules()
    {
        initPanelAnnulesCenter();
        initPanelAnnulesNord();
        //Mise en place panelEDT
        panelAnnules.add(panelAnnulesNord, BorderLayout.NORTH);
        panelAnnules.add(panelAnnulesCenter, BorderLayout.CENTER);
    }
    /**
     * Initialisation Panel Annules Nord
     */
    public void initPanelAnnulesNord()
    {
        panelAnnulesNord = new JPanel();
        panelAnnulesNord.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelAnnulesNord.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        
        //Initialisation recherche par nom
        labelNom3 = new JLabel("Entrez le nom d'un élève :");
        textFieldNom3 = new JTextField();
        textFieldNom3.setPreferredSize(new Dimension(150, 25));
        
        //Initialisation choix semaine
        labelSemaine3 = new JLabel("Choisissez une semaine :");
        comboSemaine3 = new JComboBox();
        comboSemaine3.setPreferredSize(new Dimension(90, 25));
        for(int choix : tabChoixSemaine){
            comboSemaine3.addItem(choix);
        }
        
        //Initialisaion bouton recherche + ajout listener
        chercherAnnules = new JButton("Rechercher");
        chercherAnnules.addActionListener(this);
        
        //Pré-selection de la semaine actuelle
        comboSemaine3.setSelectedItem(semaineAct);
        
        //Ajout des Composants au panel
        panelAnnulesNord.add(labelNom3);
        panelAnnulesNord.add(textFieldNom3);
        panelAnnulesNord.add(labelSemaine3);
        panelAnnulesNord.add(comboSemaine3);
        panelAnnulesNord.add(chercherAnnules);
    }
    /**
     * Initialisation Panel Annules Centre
     */
    public void initPanelAnnulesCenter()
    {
        panelAnnulesCenter = new JPanel();
        panelAnnulesCenter.setLayout(new BorderLayout());
    }
     
    
    /**
     * 
     * Créé le JPanel contenant la liste de cours d'un étudiant donné et le retourne
     * 
     * @return panelCentre
     */
    public JPanel dessinerTable(Etudiant etudiant)
    {
        JPanel panelCentre = new JPanel();
        JPanel panelConteneur = new JPanel();
        JPanel panelConteneurSup = new JPanel();
        panelCentre.setLayout(new FlowLayout(FlowLayout.CENTER));
        Color couleurCours = Color.black;
        ArrayList<Cours> listeCours = donnees.getListeCours();
        
        LocalDate maintenant = LocalDate.now();
        LocalDate debut = maintenant.minusWeeks(6);
        LocalDate fin = maintenant.plusWeeks(6);
        
        //On récupère les infos de cours de l'enseignant
        ArrayList<String> recapCours = donnees.recapitulatifCours(debut, fin, etudiant.getUtilisateur().getId());
        
        //En fonction du nombre de matières, on créé des lignes (gridLayout)
        panelConteneur.setLayout(new GridLayout(recapCours.size(), 1));
        panelConteneur.setBorder(BorderFactory.createEmptyBorder());
        panelConteneurSup.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelConteneurSup.setBorder(BorderFactory.createLineBorder(Color.white));
        
        //On ajoute dans chaque grille du layout un cours
        //VERSION 1 - CLASSIQUE
        for(int i=0; i<recapCours.size(); i++)
        {
            //Pour récupération de la couleur
            //Récupération du nom du cours
            int x1 = recapCours.get(i).lastIndexOf(" | ");
            String nomCours = recapCours.get(i).substring(0, x1);
            for(int j=0; j<listeCours.size(); j++){
                if(nomCours.equals(listeCours.get(j).getNom())){
                    //Récupération de la couleur de la séance
                    try {
                        Field field = Class.forName("java.awt.Color").getField((String)listeCours.get(i).getCouleur().toLowerCase());
                        couleurCours = (Color)field.get(null);
                    } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
                        //Couleur par défaut
                        couleurCours = Color.black;
                    }
                }
            }
            JLabel labelCours = new JLabel(recapCours.get(i));
            //Si c'est la première ligne, on met en gras
            if(i==0){
                labelCours.setFont(new Font("Arial", Font.BOLD, 14));
                labelCours.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, couleurCours));
                //cours.setBorder(BorderFactory.createLineBorder(couleurCours));
            }
            else{
                labelCours.setFont(new Font("Arial", Font.PLAIN, 12));
                labelCours.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, couleurCours));
            }
            panelConteneur.add(labelCours);
        }
        
        //VERSION 2 - BORDER AVEC TITRE
        /*for(int i=1; i<recapCours.size(); i++)
        {
            //Pour récupération de la couleur
            //Récupération du nom du cours
            int x1 = recapCours.get(i).lastIndexOf(" | ");
            String nomCours = recapCours.get(i).substring(0, x1);
            for(int j=0; j<listeCours.size(); j++){
                if(nomCours.equals(listeCours.get(j).getNom())){
                    //Récupération de la couleur de la séance
                    try {
                        Field field = Class.forName("java.awt.Color").getField((String)listeCours.get(i).getCouleur().toLowerCase());
                        couleurCours = (Color)field.get(null);
                    } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
                        //Couleur par défaut
                        couleurCours = Color.black;
                    }
                }
            }
            JLabel labelCours = new JLabel(recapCours.get(i));
            labelCours.setFont(new Font("Arial", Font.PLAIN, 12));
            //Première ligne : avec le titre
            if(i==1){
                labelCours.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, couleurCours), recapCours.get(0), TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14)));
            }
            else{
                labelCours.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, couleurCours));
            }
            
                
            panelConteneur.add(labelCours);
        }*/
        panelConteneurSup.add(panelConteneur);
        panelCentre.add(panelConteneurSup);
        return panelCentre;
    }
    
    
    /**
     * 
     * Fonction de dessin de l'emploi du temps
     * 
     * @param numSemaine 
     * @return JPanel Retourne un JPanel avec tous les éléments de l'EDT
     */
    public JPanel dessinerEDT(Etudiant etudiant, int numSemaine, boolean annule)
    {
        JPanel panelCentre = new JPanel();
        panelCentre.setLayout(new GridLayout(1, 7));
        
        //Initialisation des créneaux
        LocalTime creneau1 = LocalTime.parse("08:30:00.0");
        LocalTime creneau2 = LocalTime.parse("10:15:00.0");
        LocalTime creneau3 = LocalTime.parse("12:00:00.0");
        LocalTime creneau4 = LocalTime.parse("13:45:00.0");
        LocalTime creneau5 = LocalTime.parse("15:30:00.0");
        LocalTime creneau6 = LocalTime.parse("17:15:00.0");
        LocalTime creneau7 = LocalTime.parse("19:00:00.0");
        
        //Récupération des séances du user pour la semaine donné, en fonction du type d'utilisateur (étudiant / enseignant)
        ArrayList<Seance> maSemaine = donnees.getSeanceSemaineEtudiant(numSemaine, etudiant.getUtilisateur().getNom() + " " + etudiant.getUtilisateur().getPrenom());
        
        
        //7 colonnes (lundi au samedi + colonne "horaires")
        for(int numJours=0; numJours<7; numJours++)
        {
            //Panel qui contiendra les horaires d'une journée
            JPanel panelJours = new JPanel();
            GridLayout gl = new GridLayout(8, 1);
            gl.setVgap(15);
            panelJours.setLayout(gl);
            //TextPane qui contiendra les titres des jours ('Horaires', 'Lundi', 'Mardi' etc.)
            JTextPane titre = new JTextPane();
            titre.setEditable(false);
            titre.setPreferredSize(new Dimension(1, 150));
            titre.setFont(new Font("Arial", Font.BOLD, 16));
            titre.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
            
            //8 lignes ('Titres' + 7 créneaux)
            for(int numCreneau=0; numCreneau<8; numCreneau++)
            {
                //Panel qui contiendra un créneau de cours 
                JPanel panelCreneau = new JPanel();
                panelCreneau.setLayout(new BorderLayout());
                panelCreneau.setBorder(BorderFactory.createLineBorder(Color.gray));
                //TextPane qui contiendra les infos du créneau de cours
                JTextPane creneau = new JTextPane();
                creneau.setEditable(false);
                creneau.setPreferredSize(new Dimension(5, 150));
                creneau.setFont(new Font("Arial", Font.PLAIN, 10));
                //Gestion du style des textPane
                StyledDocument docTitres = titre.getStyledDocument();
                StyledDocument docCreneaux = creneau.getStyledDocument();
                SimpleAttributeSet horaires = new SimpleAttributeSet();
                SimpleAttributeSet centre = new SimpleAttributeSet();
                StyleConstants.setAlignment(horaires, StyleConstants.ALIGN_RIGHT);
                StyleConstants.setAlignment(centre, StyleConstants.ALIGN_CENTER);
                
                //Premier Jpanel correspondant aux horaires
                if(numJours == 0){
                    //Première ligne = "Titre"
                    if(numCreneau == 0){
                        //On aligne au centre
                        docTitres.setParagraphAttributes(0, docCreneaux.getLength(), centre, false);
                        //On défini le titre
                        titre.setText(tabLabelsEDT[numJours]);
                        panelCreneau.add(titre);
                    }
                    //Lignes suivantes = "Horaires"
                    else{
                        //On aligne à droite
                        docCreneaux.setParagraphAttributes(0, docCreneaux.getLength(), horaires, false);
                        //On défini l'horaire
                        creneau.setText(tabCreneauxEDT[numCreneau]);
                        panelCreneau.add(creneau);
                    }
                }
                //Les suivants sont les jours de la semaine
                else{
                    //Première ligne = "Titre"
                    if(numCreneau == 0){
                        //On aligne au centre
                        docTitres.setParagraphAttributes(0, docCreneaux.getLength(), centre, false);
                        //On défini le titre
                        titre.setText(tabLabelsEDT[numJours]);
                        panelCreneau.add(titre);
                    }
                    //Lignes suivantes = "Créneaux"
                    else{
                        //On aligne au centre
                        docCreneaux.setParagraphAttributes(0, docCreneaux.getLength(), centre, false);

                        //On parcours la liste de séance Pour check si la séance donné correspond à la séance [i][j]
                        for(int k=0; k<maSemaine.size(); k++)
                        {
                            //Check si le jours correspond
                            int joursSeance = maSemaine.get(k).getJour().getDayOfWeek().getValue();
                            if(joursSeance == numJours)
                            {
                                //Numéro du créneau de la séance donné
                                int numCreneauSeance = 0;
                                //Récupération de l'heure de début de la séance donné
                                LocalTime creneauSeance = maSemaine.get(k).getHeure_debut();
                                
                                //Test de tous les créneaux
                                //Créneau 1
                                if(creneauSeance.equals(creneau1)){
                                    numCreneauSeance = 1;
                                }
                                //Créneau 2
                                if(creneauSeance.equals(creneau2)){
                                    numCreneauSeance = 2;
                                }
                                //Créneau 3
                                if(creneauSeance.equals(creneau3)){
                                    numCreneauSeance = 3;
                                }
                                //Créneau 4
                                if(creneauSeance.equals(creneau4)){
                                    numCreneauSeance = 4;
                                }
                                //Créneau 5
                                if(creneauSeance.equals(creneau5)){
                                    numCreneauSeance = 5;
                                }
                                //Créneau 6
                                if(creneauSeance.equals(creneau6)){
                                    numCreneauSeance = 6;
                                }
                                //Créneau 7
                                if(creneauSeance.equals(creneau7)){
                                    numCreneauSeance = 7;
                                }
                                
                                //Si la séance k correspond à la case [numCreneau] de l'EDT que l'on rempli
                                if(numCreneauSeance == numCreneau)
                                {
                                    //Alors on récupère le reste des infos nécessaires
                                    int idSeance = maSemaine.get(k).getId();
                                    //Récupération de l'état de la séance
                                    int etatSeance = maSemaine.get(k).getEtat();
                                    //Récupération du nom de la séance (nom du cours)
                                    String nomSeance = maSemaine.get(k).getCours().getNom();
                                    //Récupération de la couleur de la séance
                                    Color couleurSeance;
                                    try {
                                        Field field = Class.forName("java.awt.Color").getField((String)maSemaine.get(k).getCours().getCouleur().toLowerCase());
                                        couleurSeance = (Color)field.get(null);
                                    } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
                                        //Couleur par défaut
                                        couleurSeance = Color.lightGray;
                                    }
                                    //Récupération du nom du/des prof qui assurent ce cours
                                    ArrayList<String> listeProfsSeance = donnees.getNomEnseignantSeance(idSeance);
                                    //Récupération de la salle de cours
                                    ArrayList<String > listeSallesSeance = donnees.getNomSalleSeance(idSeance);
                                    
                                    //Initialisation de la 'String' d'affichage
                                    String stringSeance = "";
                                    //Check si le cours est annulé
                                    if(etatSeance == 0){
                                        stringSeance += "ANNULE\n";
                                    }
                                    //Ajout nom de la Séance
                                    stringSeance += nomSeance;
                                    stringSeance += "\n";
                                    //Ajout Prof
                                    stringSeance += "Mme|M. ";
                                    for(int l=0; l<listeProfsSeance.size(); l++){
                                        stringSeance += listeProfsSeance.get(l);
                                        if(listeProfsSeance.size() > 1){
                                            stringSeance += " - ";
                                        }
                                    }
                                    stringSeance += "\n";
                                    //Ajout salles 
                                    for(int l=0; l<listeSallesSeance.size(); l++){
                                        stringSeance += listeSallesSeance.get(l);
                                        stringSeance += "\n";
                                    }
                                    //Puis on rajoute dans la case en fonction de si on veut les cours annule ou non
                                    //Si on veut que les cours annulés
                                    if(annule){
                                        if(etatSeance == 0){
                                            creneau.setText(stringSeance);
                                            creneau.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, couleurSeance));
                                        }
                                    }
                                    //Si on veut afficher tous les cours
                                    else{
                                        creneau.setText(stringSeance);
                                        creneau.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, couleurSeance));
                                    }
                                }
                                //Sinon on met le fond de la case en gris
                                else
                                {
                                    creneau.setBackground(Color.lightGray);
                                }
                            }
                        }
                        panelCreneau.add(creneau);
                    }
                }
                panelJours.add(panelCreneau);
            }
            panelCentre.add(panelJours);
        }
        return panelCentre;
    }
    
    
    
    //GETTERS Page
    /**
     *
     * @return onglets
     */
    public JTabbedPane getOnglets(){
        return onglets;
    }
    
    
    /**
     * 
     * Gérer les actions sur la liste et les boutons 
     * 
     * @param evt
     */
    @Override
    public void actionPerformed(ActionEvent evt)
    {
        Object source = evt.getSource();
        
    //Check choix de l'utilisateur sur l'affichage
        if(source == comboChoix)
        {
            labelSemaine.setVisible(true);
            comboSemaine.setVisible(true);
            chercherEDT.setVisible(true);
            
            int choix = (int)comboChoix.getSelectedIndex();
            
            //Si 'Recherche par nom'
            if(choix == 0){
                labelNom.setVisible(true);
                textFieldNom.setVisible(true);
                labelListe.setVisible(false);
                comboListe.setVisible(false);
            }
            //Sinon 'Recherche depuis liste'
            else{
                labelNom.setVisible(false);
                textFieldNom.setVisible(false);
                labelListe.setVisible(true);
                comboListe.setVisible(true);
            }
        }
        
    //EDT D'UN ETUDIANT
        if(source == chercherEDT)
        {
            //Si c'est une recherche par nom :
            if(comboChoix.getSelectedIndex() == 0)
            {
                //On récupère le nom de l'étudiant + la semaine à afficher
                String nomEtudSelect = (String)textFieldNom.getText();
                int semaineSelect = (int)comboSemaine.getSelectedItem();
                
                //On initialise l'étudiant dont on veut les infos
                Etudiant etudiantSelect = new Etudiant();

                //Si le text field n'est pas vide 
                if(nomEtudSelect.length() != 0){
                    //Test si l'étudiant existe dans la BDD
                    boolean etudiantExiste = false;
                    ArrayList<Etudiant> listeEtudiant = donnees.getListeEtudiant();
                    
                    for(int k = 0; k<listeEtudiant.size(); k++){
                        if(nomEtudSelect.toUpperCase().equals(listeEtudiant.get(k).getUtilisateur().getNom().toUpperCase())){
                            etudiantSelect = listeEtudiant.get(k);
                            etudiantExiste = true;
                        }
                    }
                    //Si l'étudiant existe
                    if(etudiantExiste){
                        panelEDTCenter.removeAll();
                        panelEDTCenter.revalidate();
                        panelEDTCenter.repaint();
                        panelEDTCenter.add(dessinerEDT(etudiantSelect, semaineSelect, false), BorderLayout.CENTER);
                    }
                }
            }
            //Si c'est une recherche depuis la liste :
            else{
                //On récupère le nom de l'étudiant + la semaine à afficher
                String nomEtudSelect = (String)comboListe.getSelectedItem();
                int semaineSelect = (int)comboSemaine.getSelectedItem();
                
                //On initialise l'étudiant dont on veut les infos
                Etudiant etudiantSelect = new Etudiant();
                
                //Si le text field n'est pas vide 
                if(nomEtudSelect.length() != 0){
                    //Test si l'étudiant existe dans la BDD
                    boolean etudiantExiste = false;
                    ArrayList<Etudiant> listeEtudiant = donnees.getListeEtudiant();
                    
                    for(int k = 0; k<listeEtudiant.size(); k++){
                        if(nomEtudSelect.toUpperCase().equals(listeEtudiant.get(k).getUtilisateur().getNom().toUpperCase())){
                            etudiantSelect = listeEtudiant.get(k);
                            etudiantExiste = true;
                        }
                    }
                    if(etudiantExiste){
                        panelEDTCenter.removeAll();
                        panelEDTCenter.revalidate();
                        panelEDTCenter.repaint();
                        panelEDTCenter.add(dessinerEDT(etudiantSelect, semaineSelect, false), BorderLayout.CENTER);
                    }
                }
            }
        }
        
    //RECAP COURS
        if(source == chercherRecap)
        {            
            //On récupère le nom de l'étudiant + la semaine à afficher
            String nomEtudSelect = (String)comboListe.getSelectedItem();
            
            //On initialise l'étudiant dont on veut les infos
            Etudiant etudiantSelect = new Etudiant();
            
            //Si le text field n'est pas vide 
            if(nomEtudSelect.length() != 0){
                //Test si l'étudiant existe dans la BDD
                boolean etudiantExiste = false;
                ArrayList<Etudiant> listeEtudiant = donnees.getListeEtudiant();

                for(int k = 0; k<listeEtudiant.size(); k++){
                    if(nomEtudSelect.toUpperCase().equals(listeEtudiant.get(k).getUtilisateur().getNom().toUpperCase())){
                        etudiantSelect = listeEtudiant.get(k);
                        etudiantExiste = true;
                    }
                }
                //Si l'étudiant existe
                if(etudiantExiste){
                    panelRecapCenter.removeAll();
                    panelRecapCenter.revalidate();
                    panelRecapCenter.repaint();
                    panelRecapCenter.add(dessinerTable(etudiantSelect));
                }
            }
        }
        
    //COURS ANNULES
        if(source == chercherAnnules)
        {
            //on récupère les infos
            String nomEtudSelect = (String)textFieldNom3.getText();
            int semaineSelect = (int)comboSemaine3.getSelectedItem();
            
            //On initialise l'étudiant dont on veut les infos
            Etudiant etudiantSelect = new Etudiant();

            //Si le text field n'est pas vide 
            if(nomEtudSelect.length() != 0){
                //Test si l'étudiant existe dans la BDD
                boolean etudiantExiste = false;
                ArrayList<Etudiant> listeEtudiant = donnees.getListeEtudiant();

                for(int k = 0; k<listeEtudiant.size(); k++){
                    if(nomEtudSelect.toUpperCase().equals(listeEtudiant.get(k).getUtilisateur().getNom().toUpperCase())){
                        etudiantSelect = listeEtudiant.get(k);
                        etudiantExiste = true;
                    }
                }
                if(etudiantExiste){
                    panelAnnulesCenter.removeAll();
                    panelAnnulesCenter.revalidate();
                    panelAnnulesCenter.repaint();
                    panelAnnulesCenter.add(dessinerEDT(etudiantSelect, semaineSelect, true), BorderLayout.CENTER);
                }
            }
        }
    }
}
