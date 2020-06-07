/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controler.RechercheDonnees;

import java.awt.event.ActionEvent;import java.awt.CardLayout;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 *
 * @author Kozlow
 */
public class FenetreEDT extends JFrame implements ActionListener{
    //Menu
    private Menu menuBar;
    //Layout manager
    private CardLayout layoutPages;
    //Conteneur des pages
    private JPanel conteneurPages;
    //Differentes Pages
    private PageCours pageCours;
    private PageEtudiants pageEtudiants;
    private PagePromotions pagePromotions;
    private PageEnseignants pageEnseignants;
    private PageSalles pageSalles;
    private PageAdmin pageAdmin;

    /**
     * Constructeur de la fenêtre
     */
    public FenetreEDT()
    {
        //Initialisation fenetre
        this.setTitle("Projet EDT Java");
        this.setSize(1100,700);
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Initialisation menu
        menuBar = new Menu();   
        initMenuListener();
        this.setJMenuBar((Menu) menuBar);
        
        //Initialisation du layout et du conteneur
        layoutPages = new CardLayout();
        conteneurPages = new JPanel();
        conteneurPages.setLayout(layoutPages);
        //Initialisation des pages
        pageCours = new PageCours();
        pageEtudiants = new PageEtudiants();
        pagePromotions = new PagePromotions();
        pageEnseignants = new PageEnseignants();
        pageSalles = new PageSalles();
        pageAdmin = new PageAdmin();
        //Ajout des pages au conteneur
        conteneurPages.add(pageCours, "pageCours");
        conteneurPages.add(pageEtudiants, "pageEtudiants");
        conteneurPages.add(pagePromotions, "pagePromotions");
        conteneurPages.add(pageEnseignants, "pageEnseignants");
        conteneurPages.add(pageSalles, "pageSalles");
        conteneurPages.add(pageAdmin, "pageAdmin");
        
        //Ajout du conteneur des pages et affichage fenetre
        this.setContentPane(conteneurPages);
        this.setVisible(true);
    }
    
    public final void initMenuListener()
    {
        menuBar.getMesCours().addActionListener(this);
        menuBar.getEmploiDuTempsE().addActionListener(this);
        menuBar.getRecapCoursE().addActionListener(this);
        menuBar.getCoursAnnulesE().addActionListener(this);
        menuBar.getEmploiDuTempsP().addActionListener(this);
        menuBar.getRecapCoursP().addActionListener(this);
        menuBar.getCoursAnnulesP().addActionListener(this);
        menuBar.getChercherPromotion().addActionListener(this);
        menuBar.getChercherSalle().addActionListener(this);
        menuBar.getPageAdmin().addActionListener(this);
    }
    
    /**
     *
     * Pour gerer les actions sur les boutons
     *
     * @param evt
     */
    @Override
    public void actionPerformed(ActionEvent evt)
    {
        Object source = evt.getSource();
        //MENU
        if(source == menuBar.getMesCours()){
            layoutPages.show(conteneurPages, "pageCours");
        }
        if(source == menuBar.getEmploiDuTempsE()){
            layoutPages.show(conteneurPages, "pageEtudiants");
            pageEtudiants.getOnglets().setSelectedIndex(0);
        }
        if(source == menuBar.getRecapCoursE()){
            layoutPages.show(conteneurPages, "pageEtudiants");
            pageEtudiants.getOnglets().setSelectedIndex(1);
        }
        if(source == menuBar.getCoursAnnulesE()){
            layoutPages.show(conteneurPages, "pageEtudiants");
            pageEtudiants.getOnglets().setSelectedIndex(2);
        }
        if(source == menuBar.getEmploiDuTempsP()){
            layoutPages.show(conteneurPages, "pageEnseignants");
        }
        if(source == menuBar.getRecapCoursP()){
            layoutPages.show(conteneurPages, "pageEnseignants");
        }
        if(source == menuBar.getCoursAnnulesP()){
            layoutPages.show(conteneurPages, "pageEnseignants");
        }
        if(source == menuBar.getChercherPromotion()){
            layoutPages.show(conteneurPages, "pagePromotions");
        }
        if(source == menuBar.getChercherSalle()){
            layoutPages.show(conteneurPages, "pageSalles");
        }
        if(source == menuBar.getPageAdmin()){
            layoutPages.show(conteneurPages, "pageAdmin");
        }
    }
    
}
