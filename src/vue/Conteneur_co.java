package vue;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controler.RechercheDonnees;
import javafx.scene.paint.Color;
import modele.Utilisateur;

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
public class Conteneur_co extends JPanel implements ActionListener {

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

    private void prop_conteneur() {
        this.setLayout(null);
        this.prop_accueil_texte();
        this.prop_adresse_email();
        this.prop_mot_de_passe();
        this.prop_boutton();
        this.validation.addActionListener(this);
        this.prop_error_mdp();
        this.prop_error_email();
        this.prop_image();
        this.prop_fond ();

    }

    private void prop_image() {
        image_ece = new JLabel();
        image_ece1 = new ImageIcon("img/ece.jpg");
        this.image_ece.setBounds(25, 325,400, 100);
        this.image_ece.setIcon(image_ece1);
        this.add(image_ece);

    }
    
        private void prop_fond() {
        image_ece = new JLabel();
        image_ece1 = new ImageIcon("img/effeil.jpg");
        this.image_ece.setBounds(0, 0,400, 450);
        this.image_ece.setIcon(image_ece1);
        this.add(image_ece);

    }

    private void prop_accueil_texte() {
        accueil = new JLabel();
        this.accueil.setBounds(420, 10, 400, 50);
        this.accueil.setText("Bienvenue sur votre EDT");
        this.add(accueil);
    }

    private void prop_adresse_email() {
        email = new JLabel();
        this.email.setBounds(420, 80, 100, 20);
        this.email.setText("Adresse e-mail :");
        this.add(email);
        adresse_email = new JTextField();
        this.adresse_email.setBounds(420, 100, 200, 20);
        this.add(adresse_email);
    }

    private void prop_mot_de_passe() {
        mdp = new JLabel();
        this.mdp.setBounds(420, 160, 100, 20);
        this.mdp.setText("Mot de passe :");
        this.add(mdp);
        mot_de_passe = new JPasswordField();
        this.mot_de_passe.setBounds(420, 180, 200, 20);
        this.add(mot_de_passe);
    }

    private void prop_boutton() {
        validation = new JButton();
        this.validation.setText("Valider");
        this.validation.setBounds(440, 240, 100, 20);
        this.add(validation);
    }

    private void prop_error_email() {
        error_email = new JLabel();
        this.error_email.setBounds(440, 120, 200, 20);
        this.error_email.setText("Adresse invalide.");
        error_email.setVisible(false);
        this.add(error_email);
    }

    private void prop_error_mdp() {
        error_mdp = new JLabel();
        this.error_mdp.setBounds(440, 200, 200, 20);
        this.error_mdp.setText("Mot de passe invalide.");
        error_mdp.setVisible(false);
        this.add(error_mdp);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.validation) {
            RechercheDonnees loginBdd = new RechercheDonnees();
            Utilisateur user = loginBdd.login(adresse_email.getText(), mot_de_passe.getText());
            if (user != null) {
                System.out.println("login bon !");
            } else {
                error_mdp.setVisible(true);
                error_email.setVisible(true);
            }
        }
    }

    void setBackground(Color fond) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
