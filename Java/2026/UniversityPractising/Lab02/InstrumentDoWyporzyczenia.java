public class InstrumentDoWyporzyczenia extends Instrument implements Wyporzyczalny {

    private double stawkaZaDzien;
    private boolean czyDostepny;

    InstrumentDoWyporzyczenia(String nazwa, double cenaBazowa, int ileSztuk, String rodzaj, String marka, boolean akustyczny, double stawkaZaDzien)
    {
        super(nazwa, cenaBazowa, ileSztuk, rodzaj, marka, akustyczny);
        this.stawkaZaDzien = stawkaZaDzien;
        czyDostepny = true;
    }

    public boolean czyDostepny()
    {
        return czyDostepny;
    }

    public double obliczKosztWypozyczenia(int liczbaDni) {
    // Zniżka przy dłuższym okresie wypożyczenia
    if (liczbaDni > 30) {
        return liczbaDni * stawkaZaDzien * 0.8; // 20% zniżki
    } else if (liczbaDni > 7) {
        return liczbaDni * stawkaZaDzien * 0.9; // 10% zniżki
    }
    return liczbaDni * stawkaZaDzien;
    }

    public void zwroc()
    {
        if (czyDostepny)
        {
            return;
        }
        czyDostepny = true;    
        System.out.println("Zwrocono: " + this.getInfo());
        
    }

    public void wypozycz()
    {
        if (!czyDostepny)
        {
            return;
        }
        
        czyDostepny = false;
        System.out.println("Wyporzyczono: " + this.getInfo());
        
    }

    public String getInfo()
    {
        return super.getInfo() + ", Stawka dzienna: " + stawkaZaDzien + "zl" +(czyDostepny ? ", Dostępny" : ", Niedostępny");
    }
}
