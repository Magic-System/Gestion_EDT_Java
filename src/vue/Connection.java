package vue;

import controler.RechercheDonnees;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import modele.Utilisateur;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gesli
 */
public class Connection extends JFrame implements ActionListener{

    Conteneur_co Affichage;
    Color fond = new Color(255, 255, 255);
    
    /**
     * CONSTRUCTEUR
     */
    public Connection() {
        super();
        propConnection();
    }

    /**
     * Défini les propriétés de la fenêtre de connection
     */
    private void propConnection() {
        this.setSize(650, 450);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setAlwaysOnTop(true);
        Affichage = new Conteneur_co();
        Affichage.getValidation().addActionListener(this);
        Affichage.setBackground(fond);
        this.setContentPane(Affichage);
        this.setVisible(true);
    }
    
    /**
     * Gestion des évènements de la connection
     * Test si les données rentrées par le user sont bonnes
     * 
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Affichage.getValidation()) {
            RechercheDonnees loginBdd = new RechercheDonnees();
            Utilisateur user = loginBdd.login(Affichage.getAdresse_email().getText(), Affichage.getMot_de_passe().getText());
            if (user != null) {
                System.out.println("Login bon !");
                //On ferme la fenetre de connection
                this.dispose();
                //On créé la fenetre d'EDT
                FenetreEDT fenetre = new FenetreEDT(user);
            } 
            else {
                System.out.println("-> Addresse email ou mot de passe incorrects.");
                Affichage.getError_mdp().setVisible(true);
                Affichage.getError_email().setVisible(true);
            }
        }
    }

}
