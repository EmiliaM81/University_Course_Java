
public abstract class ProduktMuzyczny implements Sprzedawalny {
    private int ileSztuk;
    protected double cenaBazowa;
    protected String nazwa;

    public ProduktMuzyczny(String nazwa, double cenaBazowa, int ileSztuk)
    {
        this.nazwa = nazwa;
        this.cenaBazowa = cenaBazowa;
        this.ileSztuk = ileSztuk;
    }
    
    public abstract double obliczCene();

    public int getIloscSztuk()
    {
        return ileSztuk;
    }


    public void zmniejszIloscSztuk(int ile)
    {
        ileSztuk-=ile;
    }
}
