public class Plyta extends ProduktMuzyczny{

    private String wykonawca;
    private int rokWydania;
    private String gatunek;
    
    public Plyta(String nazwa, double cenaBazowa, int ileSztuk, String wykonawca, int rokWydania, String gatunek)
    {
        super(nazwa, cenaBazowa, ileSztuk);
        this.wykonawca = wykonawca;
        this.rokWydania = rokWydania;
        this.gatunek = gatunek;
    }


    public String getInfo()
    {
        return "Płyta: " + nazwa + ", Wykonawca: "+ wykonawca + ", Rok: " + rokWydania + ", Gatunek: " + gatunek +", Cena: " + obliczCene() + "zl";
    }

    public double obliczCene() {
    // Nowsze płyty (ostatnie 5 lat) są droższe o 10%
    int aktualnyRok = 2025;
    return (aktualnyRok - rokWydania <= 5) ? cenaBazowa * 1.1 : cenaBazowa;
    }
}


