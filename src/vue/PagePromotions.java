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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EtchedBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Kozlow
 * 
 * Page Promotion
 */
class PagePromotions extends JPanel implements ActionListener{
    private JPanel panelPromoNord, panelPromoCenter;
    private JLabel labelPromo, labelTD, labelSemaine;
    private JComboBox comboPromo, comboTD, comboSemaine;
    private JButton chercher;
    private int semaineAct;
    private final String[] tabChoixPromo = {"ING1", "ING2", "ING3", "ING4", "ING5"};
    private final int[] tabChoixTD = {1,2,3,4,5,6,7,8,9,10,11,12};
    private final int[] tabChoixSemaine = {31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29};
    private final String[] tabLabelsEDT = {"\nHoraires","\nLundi","\nMardi","\nMercredi","\nJeudi","\nVendredi","\nSamedi"};
    private final String[] tabCreneauxEDT = {"", "8h30\n\n\n10h", "10h15\n\n\n11h45", "12h\n\n\n13h30", "13h45\n\n\n15h15", "15h30\n\n\n17h", "17h15\n\n\n18h45", "19h\n\n\n20h30"};
    
    //Controler pour récupérer les données
    private RechercheDonnees donnees = new RechercheDonnees();
    
    /**
     * Constructeur de la page 'Promotions'
     * @param utilisateurCo Correspond à l'utilisateur connecté
     */
    public PagePromotions(Utilisateur utilisateurCo)
    {     
        //Initialisation layout
        this.setLayout(new BorderLayout());
        
        initPanelPromoCenter();
        initPanelPromoNord();
        
        this.add(panelPromoNord, BorderLayout.NORTH);
        this.add(panelPromoCenter, BorderLayout.CENTER);
    }
    
    /**
     * Initialisation Panel Nord
     */
    public final void initPanelPromoNord()
    {
        panelPromoNord = new JPanel();
        panelPromoNord.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelPromoNord.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        
        //Initialisation recherche Promo
        labelPromo = new JLabel("Choisissez une promo :");
        comboPromo = new JComboBox();
        comboPromo.setPreferredSize(new Dimension(100, 25));
        for(String choixPromo : tabChoixPromo){
            comboPromo.addItem(choixPromo);
        }
        
        //Initialisation recherche TD
        labelTD = new JLabel("Choisissez un TD :");
        comboTD = new JComboBox();
        comboTD.setPreferredSize(new Dimension(100, 25));
        for(int choixTD : tabChoixTD){
            comboTD.addItem(choixTD);
        }
        
        //Initialisation choix Semaine
        labelSemaine = new JLabel("Choisissez une semaine :");
        comboSemaine = new JComboBox();
        comboSemaine.setPreferredSize(new Dimension(100, 25));
        for(int choixSemaine : tabChoixSemaine){
            comboSemaine.addItem(choixSemaine);
        }

        //Pré-selection de la semaine actuelle
        GregorianCalendar gc = new GregorianCalendar();
        gc.add(Calendar.DATE, 0);
        semaineAct = gc.get(Calendar.WEEK_OF_YEAR); 
        comboSemaine.setSelectedItem(semaineAct);
        
        //Ajout bouton + listener
        chercher = new JButton("Rechercher");
        chercher.addActionListener(this);
        
        //Ajouts au panelPromoNord
        //panelPromoNord.add(labelPromo);
        //panelPromoNord.add(comboPromo);
        panelPromoNord.add(labelTD);
        panelPromoNord.add(comboTD);
        panelPromoNord.add(labelSemaine);
        panelPromoNord.add(comboSemaine);
        panelPromoNord.add(chercher);
    }
    
    /**
     * Initialisation Panel Centre
     */
    public final void initPanelPromoCenter()
    {
        panelPromoCenter = new JPanel();
        panelPromoCenter.setLayout(new BorderLayout());
    }
    
    
    /**
     * 
     * Fonction de dessin de l'EDT
     * 
     * @param promo
     * @param td
     * @return JPanel
     */
    public JPanel dessinerEDT(String promo, int td, int numSemaine)
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
        
        //Récupération des séances du TD pour la semaine donné, en fonction du type d'utilisateur (étudiant / enseignant)
        ArrayList<Seance> maSemaine = donnees.getSeanceSemaineGroupe(numSemaine, td);
        
        
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
                            System.out.println(maSemaine);
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
                                    //Récupération du/des TD participant à la séance
                                    ArrayList<String> listeTDSeance = donnees.getNomGroupeSeance(idSeance);
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
                                    //Ajout Prof(s)
                                    stringSeance += "Mme|M. ";
                                    for(int l=0; l<listeProfsSeance.size(); l++){
                                        stringSeance += listeProfsSeance.get(l);
                                        if(listeProfsSeance.size() > 1){
                                            stringSeance += " - ";
                                        }
                                    }
                                    stringSeance += "\n";
                                    //Ajout Groupes
                                    for(int l=0; l<listeTDSeance.size(); l++){
                                        stringSeance += listeTDSeance.get(l);
                                        if(listeTDSeance.size() > 1){
                                            stringSeance += " - ";
                                        }
                                    }
                                    stringSeance += "\n";
                                    //Ajout salles 
                                    for(int l=0; l<listeSallesSeance.size(); l++){
                                        stringSeance += listeSallesSeance.get(l);
                                        stringSeance += "\n";
                                    }
                                    //Puis on rajoute dans la case
                                    creneau.setText(stringSeance);
                                    creneau.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, couleurSeance));
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
        if(source == chercher)
        {
            //On récupère les choix
            String promoSelect = (String)comboPromo.getSelectedItem();
            int TDSelect = (int)comboTD.getSelectedItem();
            int semaineSelect = (int)comboSemaine.getSelectedItem();
            
            panelPromoCenter.removeAll();
            panelPromoCenter.revalidate();
            panelPromoCenter.repaint();
            panelPromoCenter.add(dessinerEDT(promoSelect, TDSelect, semaineSelect), BorderLayout.CENTER);
        }
    }
}
