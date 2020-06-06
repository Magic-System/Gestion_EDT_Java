package modele;

public class Seance_Enseignants {

    private Seance seance;
    private Enseignant prof;

    public Seance_Enseignants() {
    }

    public Seance_Enseignants(Seance seance, Enseignant prof) {
        this.prof = new Enseignant(prof);
        this.seance = new Seance(seance);
    }

    public Enseignant getProf() {
        return prof;
    }

    public void setProf(Enseignant prof) {
        this.prof = new Enseignant(prof);
    }

    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance seance) {
        this.seance = new Seance(seance);
    }

    @Override
    public String toString() {
        return "Seance_Enseignants{" +
                "prof=" + prof +
                ", seance=" + seance +
                '}';
    }
}
