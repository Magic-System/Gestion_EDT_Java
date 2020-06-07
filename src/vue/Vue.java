package vue;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author gesli
 */

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
 
public class Vue {
   
  public static void main(String[] args){
      
        try { 
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel"); 
        } catch(ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ignored){}
        
        Connection fenetre_co = new Connection ();
        fenetre_co.setVisible(true);
        
        FenetreEDT fenetreEDT = new FenetreEDT();
  }       
}
