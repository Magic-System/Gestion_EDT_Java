package vue;

import java.awt.FlowLayout;

import java.awt.Color;

import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author gesli
 */
public class Conteneur_co extends JPanel {

    private JLabel accueil;
    private JLabel email;
    private JTextField adresse_email;
    private JLabel mdp;
    private JPasswordField mot_de_passe;
    private JButton validation;
    private JLabel error_email;
    private JLabel error_mdp;
    private JLabel image_ece;
    private ImageIcon image_ece1;

    public Conteneur_co() {
        super();
        setLayout (new FlowLayout());
        this.prop_conteneur();
    }

    public final void prop_conteneur() {
        this.setLayout(null);
        prop_accueil_texte();
        prop_adresse_email();
        prop_mot_de_passe();
        prop_boutton();
        prop_error_mdp();
        prop_error_email();
        prop_image();
        prop_fond ();
    }

    public final void prop_image() {
        image_ece = new JLabel();
        image_ece1 = new ImageIcon("img/ece.jpg");
        image_ece.setBounds(25, 325,400, 100);
        image_ece.setIcon(image_ece1);
        this.add(image_ece);
    }
    
    public final void prop_fond() {
        image_ece = new JLabel();
        image_ece1 = new ImageIcon("img/effeil.jpg");
        image_ece.setBounds(0, 0,400, 450);
        image_ece.setIcon(image_ece1);
        this.add(image_ece);
    }

    public final void prop_accueil_texte() {
        accueil = new JLabel();
        accueil.setBounds(420, 10, 400, 50);
        accueil.setText("Bienvenue sur votre EDT");
        this.add(accueil);
    }

    public final void prop_adresse_email() {
        email = new JLabel();
        email.setBounds(420, 80, 100, 20);
        email.setText("Adresse e-mail :");
        this.add(email);
        adresse_email = new JTextField();
        adresse_email.setBounds(420, 100, 200, 28);
        this.add(adresse_email);
    }

    public final void prop_mot_de_passe() {
        mdp = new JLabel();
        mdp.setBounds(420, 160, 100, 20);
        mdp.setText("Mot de passe :");
        this.add(mdp);
        mot_de_passe = new JPasswordField();
        mot_de_passe.setBounds(420, 180, 200, 28);
        this.add(mot_de_passe);
    }

    public final void prop_boutton() {
        validation = new JButton();
        validation.setText("Valider");
        validation.setBounds(440, 240, 100, 30);
        this.add(validation);
    }

    public final void prop_error_email() {
        error_email = new JLabel();
        error_email.setBounds(440, 130, 200, 20);
        error_email.setText("Adresse invalide.");
        error_email.setForeground(Color.red);
        error_email.setVisible(false);
        this.add(error_email);
    }

    public final void prop_error_mdp() {
        error_mdp = new JLabel();
        error_mdp.setBounds(440, 210, 200, 20);
        error_mdp.setText("Mot de passe invalide.");
        error_mdp.setForeground(Color.red);
        error_mdp.setVisible(false);
        this.add(error_mdp);
    }
    
    //GETTERS
    /**
     * Getter bouton validation
     * @return validation
     */
    public JButton getValidation()
    {
        return validation;
    }
    /**
     * Getter addresse email
     * @return adresse_email
     */
    public JTextField getAdresse_email()
    {
        return adresse_email;
    }
    /**
     * Getter mot de passe
     * @return mot_de_passe
     */
    public JPasswordField getMot_de_passe()
    {
        return mot_de_passe;
    }
    /**
     * Getter label erreur de mot de passe
     * @return error_mdp
     */
    public JLabel getError_mdp()
    {
        return error_mdp;
    }
    /**
     * Getter label erreur de mail
     * @return error_email
     */
    public JLabel getError_email()
    {
        return error_email;
    }

}
