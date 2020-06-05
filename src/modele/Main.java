package modele;
import dao.*;
import dao.service.EnseignantDaoServiceImpl;
import dao.service.EtudiantDaoServiceImpl;
import dao.service.GroupeDaoServiceImpl;
import dao.service.SeanceDaoServiceImpl;

import java.security.acl.Group;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here

        SeanceDaoServiceImpl test = new SeanceDaoServiceImpl();

        Seance grp = null;
        try {
            grp = test.getById(4);
            ArrayList<Seance> ls = new ArrayList<Seance>(test.getAll());
            for (Seance e : ls) {
                System.out.println(e.toString());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(grp.toString());

    }
}
