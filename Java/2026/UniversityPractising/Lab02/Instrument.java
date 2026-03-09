public class Instrument extends ProduktMuzyczny{

    private String rodzaj;
    private String marka;
    private boolean akustyczny;
    
    public Instrument(String nazwa, double cenaBazowa, int ileSztuk, String rodzaj, String marka, boolean akustyczny)
    {
        super(nazwa, cenaBazowa, ileSztuk);
        this.rodzaj = rodzaj;
        this.marka = marka;
        this.akustyczny = akustyczny;
    }


    public String getInfo()
    {
        return "Instrument: " + nazwa + ", Marka: " + marka + ", Rodzaj: "+ rodzaj + (akustyczny ? ", akustyczny" : ", elektryczny") + ", Cena: " +obliczCene() + "zl";
    }

    public double obliczCene() {
    // Instrumenty akustyczne są droższe o 15%
    return akustyczny ? cenaBazowa * 1.15 : cenaBazowa;
    }
}
