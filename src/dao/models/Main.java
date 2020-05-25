package modele;
import dao.*;
import dao.service.UtilisateurDaoServiceImpl;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here

        UtilisateurDaoServiceImpl test = new UtilisateurDaoServiceImpl();

        try {
            Utilisateur user = new Utilisateur();
            System.out.println(user.getId());
            user.setNom("test");
            user.setId(33);
            test.ajouter(user);

            ArrayList<Utilisateur> liste = test.getAll();

            for (Utilisateur u : liste)
                System.out.println(u.getNom());

            System.out.println(test.getById(user.getId()));
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
