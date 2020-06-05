package modele;

public class Seance_Groupes {

    private Seance seance;
    private Groupe grp;

    public Seance_Groupes() {
    }

    public Seance_Groupes(Seance seance, Groupe grp) {

        this.seance = seance;
        this.grp = grp;
    }

    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

    public Groupe getGroupe() {
        return grp;
    }

    public void setGroupe(Groupe grp) {
        this.grp = grp;
    }

    @Override
    public String toString() {
        return "Seance_Groupes{" +
                "seance=" + seance +
                ", grp=" + grp +
                '}';
    }
}
