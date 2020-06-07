/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Kozlow
 * 
 * Création du Menu de la fenêtre
 */
public class Menu extends JMenuBar{
    //Différents menus
    private JMenu cours = new JMenu("Cours");
    private JMenu etudiants = new JMenu("Etudiants");
    private JMenu promotions = new JMenu("Promotions");
    private JMenu enseignants = new JMenu("Enseignants");
    private JMenu salles = new JMenu("Salles");
    private JMenu admin = new JMenu("Admin");
   
    //Item Menu 'Cours
    private JMenuItem mesCours = new JMenuItem("> Mes cours");
    //Items Menu 'Etudiants'
    private JMenuItem emploiDuTempsE = new JMenuItem("> Emploi du temps");
    private JMenuItem recapCoursE = new JMenuItem("> Récapitulatif des cours");
    private JMenuItem coursAnnulesE = new JMenuItem("> Cours annulés");
    //Items Menu 'Enseignants'
    private JMenuItem emploiDuTempsP = new JMenuItem("> Emploi du temps");
    private JMenuItem recapCoursP = new JMenuItem("> Récapitulatif des cours");
    private JMenuItem coursAnnulesP = new JMenuItem("> Cours annulés");
    //Item Menu 'Promotions'
    private JMenuItem chercherPromotion = new JMenuItem("> Rechercher par promotion");
    //Item Menu 'Salles'
    private JMenuItem chercherSalle = new JMenuItem("> Rechercher une salle");
    //Item Menu 'Admin'
    private JMenuItem pageAdmin = new JMenuItem("> Page administration");
    
    
    /**
     * Constructeur du menu
     */
    public Menu()
    {
        //Initialisation Menu Cours
        cours.add(mesCours);
        
        //Initialisation Menu Etudiants
        etudiants.add(emploiDuTempsE);
        etudiants.add(recapCoursE);
        etudiants.add(coursAnnulesE);
        
        //Initialisation Menu Enseignants
        enseignants.add(emploiDuTempsP);
        enseignants.add(recapCoursP);
        enseignants.add(coursAnnulesP);
        
        //Initialisation Menu Promotions
        promotions.add(chercherPromotion);
        
        //Initialisaiton Menu Salles
        salles.add(chercherSalle);
        
        //Initialisation Menu Admin
        admin.add(pageAdmin);
        
        //Ajouts à la barre de menu
        this.add(cours);
        this.add(etudiants);
        this.add(promotions);
        this.add(enseignants);
        this.add(salles);
        this.add(admin);
    }
    
    //GETTERS Menu
    /**
     *
     * @return cours
     */
    public JMenu getCours(){
        return cours;
    }
    /**
     *
     * @return etudiants
     */
    public JMenu getEtudiants(){
        return etudiants;
    }
    /**
     *
     * @return enseignants
     */
    public JMenu getEnseignants(){
        return enseignants;
    }
    /**
     *
     * @return promotions
     */
    public JMenu getPromotions(){
        return promotions;
    }
    /**
     *
     * @return salles
     */
    public JMenu getSalles(){
        return salles;
    }
    /**
     *
     * @return admin
     */
    public JMenu getAdmin(){
        return admin;
    }
    
    
    //GETTERS MenuItems 
    /**
     *
     * @return mesCours
     */
    public JMenuItem getMesCours(){
        return mesCours;
    }
    /**
     *
     * @return emploiDuTempsE
     */
    public JMenuItem getEmploiDuTempsE(){
        return emploiDuTempsE;
    }
    /**
     *
     * @return recapCoursE
     */
    public JMenuItem getRecapCoursE(){
        return recapCoursE;
    }
    /**
     *
     * @return coursAnnulesE
     */
    public JMenuItem getCoursAnnulesE(){
        return coursAnnulesE;
    }
    /**
     *
     * @return emploiDuTempsE
     */
    public JMenuItem getEmploiDuTempsP(){
        return emploiDuTempsP;
    }
    /**
     *
     * @return recapCoursP
     */
    public JMenuItem getRecapCoursP(){
        return recapCoursP;
    }
    /**
     *
     * @return coursAnnulesP
     */
    public JMenuItem getCoursAnnulesP(){
        return coursAnnulesP;
    }
    /**
     *
     * @return chercherPromotion
     */
    public JMenuItem getChercherPromotion(){
        return chercherPromotion;
    }
    /**
     *
     * @return chercherSalle
     */
    public JMenuItem getChercherSalle(){
        return chercherSalle;
    }
    /**
     *
     * @return pageAdmin
     */
    public JMenuItem getPageAdmin(){
        return pageAdmin;
    }
    
}
