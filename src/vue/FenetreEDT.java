/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controler.RechercheDonnees;
import modele.*;

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
    
    //Utilisateur connecté
    private Utilisateur user;    

    /**
     * Constructeur de la fenêtre une fois qu'un user s'est connecté
     * @param utilisateurCo Correspond à l'utilisateur connecté
     */
    public FenetreEDT(Utilisateur utilisateurCo)
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
        
        //Initialisation user
        user = new Utilisateur(utilisateurCo);
        
        //Initialisation du layout et du conteneur
        layoutPages = new CardLayout();
        conteneurPages = new JPanel();
        conteneurPages.setLayout(layoutPages);
        
        //Initialisation des pages
        pageCours = new PageCours(user);
        pageEtudiants = new PageEtudiants(user);
        pagePromotions = new PagePromotions(user);
        pageEnseignants = new PageEnseignants(user);
        pageSalles = new PageSalles(user);
        pageAdmin = new PageAdmin(user);
        
        //Ajout des pages au conteneur
        conteneurPages.add(pageCours, "pageCours");
        conteneurPages.add(pageEtudiants, "pageEtudiants");
        conteneurPages.add(pagePromotions, "pagePromotions");
        conteneurPages.add(pageEnseignants, "pageEnseignants");
        conteneurPages.add(pageSalles, "pageSalles");
        conteneurPages.add(pageAdmin, "pageAdmin");
        
        //Test du type d'utilisateur
        testDroitsUser();
        
        //Ajout du conteneur des pages et affichage fenetre
        this.setContentPane(conteneurPages);
        this.setVisible(true);
    }
    
    /**
     * Ajout des listener sur le Menu
     */
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
     * Test les 'droits' de l'utilisateur pour afficher les menus et pages en fonction
     */
    public final void testDroitsUser()
    {
        //User = Référent pédagogique
        if(user.getDroit() == 2)
        {
            //L'admin n'a pas d'EDT 
            menuBar.getCours().setVisible(false);
        }
        //User = Administrateur
        if(user.getDroit() == 1)
        {
            //Le référent n'a pas le droit de modif les données
            menuBar.getAdmin().setVisible(false);
        }
        //User = Enseignant
        if(user.getDroit() == 3)
        {
            //Prof ne peut voir que son EDT
            menuBar.getAdmin().setVisible(false);
            menuBar.getPromotions().setVisible(false);
            menuBar.getSalles().setVisible(false);
            menuBar.getEtudiants().setVisible(false);
            menuBar.getEnseignants().setVisible(false);
        }
        //User = Etudiant
        if(user.getDroit() == 4)
        {
            //Etudiant ne peux voir que son EDT
            menuBar.getAdmin().setVisible(false);
            menuBar.getPromotions().setVisible(false);
            menuBar.getSalles().setVisible(false);
            menuBar.getEtudiants().setVisible(false);
            menuBar.getEnseignants().setVisible(false);
        }
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
