package vue;

import java.awt.Color;
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
public class Connection extends JFrame {

    Conteneur_co Affichage;
    Color fond = new Color(255, 255, 255);

    
    
    public Connection() {
        super();
        propConnection();

    }

    private void propConnection() {
        this.setSize(650, 450);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setAlwaysOnTop(true);
        Affichage = new Conteneur_co();
        Affichage.setBackground(fond);
        this.setContentPane(Affichage);

    }

}
