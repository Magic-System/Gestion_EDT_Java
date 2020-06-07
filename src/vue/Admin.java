package vue;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gesli
 */
public class Admin extends JFrame {
   

    Conteneur_Admin Affichage;
    Color fond = new Color(221, 240, 255);

    
    
    public Admin() {
        super();
        propAdmin();

    }

    private void propAdmin() {
        this.setSize(1000, 600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setAlwaysOnTop(true);
        Affichage = new Conteneur_Admin();
        Affichage.setBackground(fond);
        this.setContentPane(Affichage);

    }

}

