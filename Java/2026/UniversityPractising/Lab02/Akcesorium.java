public class Akcesorium extends ProduktMuzyczny{

    private String rodzaj;
    private boolean czyProfesjonalne;
    
    public Akcesorium(String nazwa, double cenaBazowa, int ileSztuk, String rodzaj, boolean czyProfesjonalne)
    {
        super(nazwa, cenaBazowa, ileSztuk);
        this.rodzaj = rodzaj;
        this.rodzaj = rodzaj;
        this.czyProfesjonalne = czyProfesjonalne;
    }


    public String getInfo()
    {
        return "Akcesorium: " + nazwa + ", Kategoria: " + rodzaj + (czyProfesjonalne ? ", profesjonalne" : ", amatorskie") + ", Cena: " + obliczCene() + "zl";
    }

    public double obliczCene() {
    // Profesjonalne akcesoria są droższe o 25%
    return czyProfesjonalne ? cenaBazowa * 1.25 : cenaBazowa;
    }
}