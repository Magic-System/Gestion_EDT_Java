/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
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
    private String[] tabChoixEtudiant;
    
//Pour panelRecap :
    private JPanel panelRecapNord, panelRecapCenter;
    private JLabel labelNom2; //Choi nom
    private JTextField textFieldNom2;
    private JButton chercherRecap;
    private JTable tableRecap;
    
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
    private final String[] tabEntetesJTable = {"Matière", "Première séance", "Dernière séance", "Durée", "Nb."};
    
    /**
     * CONSTRUCTEUR
     */
    public PageEtudiants()
    {
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
        // tabChoixEtudiant =  ..............
        /*for(String choixEtudiant : tabChoixEtudiant){
            comboListe.addItem(choixEtudiant);
        }*/
        
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
        tableRecap = new JTable();
        tableRecap.setDragEnabled(false);
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
     * Créé la table contenant la liste de cours d'un étudiant donné et retourne le panel contenant
     * 
     * @return table
     */
    public JPanel dessinerTable(String nomEtudiant)
    {
        JPanel panelCentre = new JPanel();
        panelCentre.setLayout(new BorderLayout());
        
        //On récupère les infos de cours de l'étudiant
        
        
        //En fonction du nombre de matières, on créé des lignes de tableau
        /*for(int i=0; i<tabCours.length(); i++)
        {
            for (String tabEntetesJTable1 : tabEntetesJTable) {
                //On récupère les infos de chaque colonne
                
                
                //On ajoute dans la table
                
                
            }
        }*/
        
        panelCentre.add(tableRecap);
        return panelCentre;
    }
    
    
    /**
     * 
     * Fonction de dessin de l'emploi du temps
     * 
     * @param numSemaine 
     * @return JPanel Retourne un JPanel avec tous les éléments de l'EDT
     */
    public JPanel dessinerEDT(String nomEtudiant, int numSemaine)
    {
        JPanel panelCentre = new JPanel();
        panelCentre.setLayout(new GridLayout(1, 7));
        
        //7 colonnes (lundi au samedi + colonne "horaires")
        for(int i=0; i<7; i++)
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
            for(int j=0; j<8; j++)
            {
                //Panel qui contiendra un créneau de cours 
                JPanel panelCreneau = new JPanel();
                panelCreneau.setLayout(new BorderLayout());
                panelCreneau.setBorder(BorderFactory.createLineBorder(Color.gray));
                //TextPane qui contiendra les infos du créneau de cours
                JTextPane creneau = new JTextPane();
                creneau.setEditable(false);
                creneau.setPreferredSize(new Dimension(5, 150));
                creneau.setFont(new Font("Arial", Font.PLAIN, 11));
                //Gestion du style des textPane
                StyledDocument docTitres = titre.getStyledDocument();
                StyledDocument docCreneaux = creneau.getStyledDocument();
                SimpleAttributeSet horaires = new SimpleAttributeSet();
                SimpleAttributeSet centre = new SimpleAttributeSet();
                StyleConstants.setAlignment(horaires, StyleConstants.ALIGN_RIGHT);
                StyleConstants.setAlignment(centre, StyleConstants.ALIGN_CENTER);
                
                //Premier Jpanel correspondant aux horaires
                if(i == 0){
                    //Première ligne = "Titre"
                    if(j == 0){
                        //On aligne au centre
                        docTitres.setParagraphAttributes(0, docCreneaux.getLength(), centre, false);
                        //On défini le titre
                        titre.setText(tabLabelsEDT[i]);
                        panelCreneau.add(titre);
                    }
                    //Lignes suivantes = "Horaires"
                    else{
                        //On aligne à droite
                        docCreneaux.setParagraphAttributes(0, docCreneaux.getLength(), horaires, false);
                        //On défini l'horaire
                        creneau.setText(tabCreneauxEDT[j]);
                        panelCreneau.add(creneau);
                    }
                }
                //Les suivants sont les jours de la semaine
                else{
                    //Première ligne = "Titre"
                    if(j == 0){
                        //On aligne au centre
                        docTitres.setParagraphAttributes(0, docCreneaux.getLength(), centre, false);
                        //On défini le titre
                        titre.setText(tabLabelsEDT[i]);
                        panelCreneau.add(titre);
                    }
                    //Lignes suivantes = "Créneaux"
                    else{
                        //On aligne au centre
                        docCreneaux.setParagraphAttributes(0, docCreneaux.getLength(), centre, false);

                        //Communiquer avec le controleur pour récup les données nécessaires à l'affichage
                        creneau.setText("Etudiant : " + nomEtudiant + "\nSemaine :" + numSemaine);
                        creneau.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.RED));
                        
                        
                        
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

                //Si le text field n'est pas vide 
                if(nomEtudSelect.length() != 0){
                    panelEDTCenter.removeAll();
                    panelEDTCenter.revalidate();
                    panelEDTCenter.repaint();
                    panelEDTCenter.add(dessinerEDT(nomEtudSelect, semaineSelect), BorderLayout.CENTER);
                }
            }
            //Si c'est une recherche depuis la liste :
            else{
                //On récupère le nom de l'étudiant + la semaine à afficher
                String nomEtudSelect = (String)comboListe.getSelectedItem();
                int semaineSelect = (int)comboSemaine.getSelectedItem();
                
                //Si le text field n'est pas vide 
                if(nomEtudSelect.length() != 0){
                    panelEDTCenter.removeAll();
                    panelEDTCenter.revalidate();
                    panelEDTCenter.repaint();
                    panelEDTCenter.add(dessinerEDT(nomEtudSelect, semaineSelect), BorderLayout.CENTER);
                }
            }
        }
        
    //RECAP COURS
        if(source == chercherRecap)
        {            
            //On récupère le nom de l'étudiant + la semaine à afficher
            String nomEtudSelect = (String)comboListe.getSelectedItem();
            
            //Si le text field n'est pas vide 
            if(nomEtudSelect.length() != 0){
                panelRecapCenter.removeAll();
                panelRecapCenter.revalidate();
                panelRecapCenter.repaint();
                panelRecapCenter.add(dessinerTable(nomEtudSelect));
            }
        }
        
    //COURS ANNULES
        if(source == chercherAnnules)
        {
            //on récupère les infos
            String nomEtudSelect = (String)textFieldNom3.getText();
            int semaineSelect = (int)comboSemaine3.getSelectedItem();

            //Si le text field n'est pas vide 
            if(nomEtudSelect.length() != 0){
                panelEDTCenter.removeAll();
                panelEDTCenter.revalidate();
                panelEDTCenter.repaint();
                panelEDTCenter.add(dessinerEDT(nomEtudSelect, semaineSelect), BorderLayout.CENTER);
            }
            
        }
    }
}
