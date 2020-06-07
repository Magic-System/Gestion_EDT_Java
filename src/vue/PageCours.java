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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
 * Page Cours
 */
class PageCours extends JPanel implements ActionListener{
    //Panel EDT
    private JPanel panelEDTNord, panelEDTCenter, panImage;
    //Layout EDT Centre
    private GridLayout glEDTCenter;
    //Choix semaine
    private JLabel labelSemaine;
    private JComboBox comboSemaine;
    private int semaineAct;
    private final int[] tabChoixSemaine = {31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29};
    private final String[] tabLabelsEDT = {"\nHoraires","\nLundi","\nMardi","\nMercredi","\nJeudi","\nVendredi","\nSamedi"};
    private final String[] tabCreneauxEDT = {"", "8h30\n\n\n10h", "10h15\n\n\n11h45", "12h\n\n\n13h30", "13h45\n\n\n15h15", "15h30\n\n\n17h", "17h15\n\n\n18h45", "19h\n\n\n20h30"};
    
    
    /**
     * CONSTRUCTEUR
     */
    public PageCours() 
    {
        //Initialisation layout
        this.setLayout(new BorderLayout());
        
        //panelEDTCenter :
        initPanelEDTCenter();
        //panelEDTNord :
        initPanelEDTNord();
        
        //Ajouts à la page
        this.add(panelEDTNord, BorderLayout.NORTH);
        this.add(panelEDTCenter, BorderLayout.CENTER);
    }
    /**
     * Initialisation Panel Nord
     */
    public final void initPanelEDTNord()
    {
        panelEDTNord = new JPanel();
        panelEDTNord.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelEDTNord.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        
        //Initialisation choix semaine
        labelSemaine = new JLabel("Choisissez une semaine :");
        comboSemaine = new JComboBox();
        comboSemaine.setPreferredSize(new Dimension(60,25));
        for(int choix : tabChoixSemaine){
            comboSemaine.addItem(choix);
        }
        
        //Ajout des Listeners
        comboSemaine.addActionListener(this);
        
        //Pré-selection de la semaine actuelle
        GregorianCalendar gc = new GregorianCalendar();
        gc.add(Calendar.DATE, 0);
        semaineAct = gc.get(Calendar.WEEK_OF_YEAR); 
        comboSemaine.setSelectedItem(semaineAct);
        
        //Ajouts au panel
        panelEDTNord.add(labelSemaine);
        panelEDTNord.add(comboSemaine);
    }
    /**
     * Initialisation Panel Centre
     */
    public final void initPanelEDTCenter()
    {
        panelEDTCenter = new JPanel();
        panelEDTCenter.setLayout(new BorderLayout());
        //Pour ajouter image de récap des semaines
        panImage = new JPanel();
        try {
            BufferedImage imageSemaines = ImageIO.read(new File("semaines.png"));
            panImage.add(new JLabel(new ImageIcon(imageSemaines)));
            panelEDTCenter.add(panImage, BorderLayout.NORTH);
        } catch (IOException ex) {
            System.out.println("Problème au chargement de l'image");
        }
    }
    
    /**
     * 
     * Fonction de dessin de l'emploi du temps
     * 
     * @param numSemaine 
     */
    public JPanel dessinerEDT(int numSemaine)
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
                        creneau.setText("Test : [" + i + "][" + j + "]\nSemaine :" + numSemaine);
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
        if(source == comboSemaine)
        {
            //On récupère le choix
            int semaineSelectionnee = (int)comboSemaine.getSelectedItem();
            panelEDTCenter.removeAll();
            panelEDTCenter.revalidate();
            panelEDTCenter.repaint();
            panelEDTCenter.add(panImage, BorderLayout.NORTH);
            panelEDTCenter.add(dessinerEDT(semaineSelectionnee), BorderLayout.CENTER);
        }
    }
}
