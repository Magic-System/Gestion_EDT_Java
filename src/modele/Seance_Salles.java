package modele;

public class Seance_Salles {

    private Seance seance;
    private Salle salle;

    public Seance_Salles() {
    }

    public Seance_Salles(Seance seance, Salle salle) {
        this.salle = salle;
        this.seance = seance;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = new Salle(salle);
    }

    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance seance) {
        this.seance = new Seance(seance);
    }

    @Override
    public String toString() {
        return "Seance_Salles{" +
                "salle=" + salle +
                ", seance=" + seance +
                '}';
    }
}
