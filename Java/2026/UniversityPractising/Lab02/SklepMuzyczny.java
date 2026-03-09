import java.util.ArrayList;

public class SklepMuzyczny {
    private ArrayList<ProduktMuzyczny> list;
    private int ile;

    SklepMuzyczny(int ile)
    {
        this.ile = ile;
        list = new ArrayList<>();
    }

    public void zwrocInstrument(InstrumentDoWyporzyczenia instrument)
    {
         if (!instrument.czyDostepny())
        {
            instrument.zwroc();
        } else {
            System.out.println("Błąd: Instrument nie jest wyporzyczony");
        }       
    }

    public void dodajProdukt(ProduktMuzyczny produkt)
    {
        list.add(produkt);
    }

    public void sprzedajProdukt(int val1 , int val2)
    {
        System.out.println("Sprzedano: " + list.get(val1).getInfo());
        list.get(val1).zmniejszIloscSztuk(val2);
    }

    public void wyswietlWszystkieProdukty()
    {
        for (ProduktMuzyczny produkt: list)
        {
            System.out.println(produkt.getInfo());
        }
    }


    public void wypozyczInstrument(InstrumentDoWyporzyczenia instrument, int ileDni)
    {
        if (instrument.czyDostepny())
        {
            instrument.wypozycz();
            System.out.println("Koszt wypożyczenia na " + ileDni + " dni: " + instrument.obliczKosztWypozyczenia(ileDni) + "zl");
        } else {
            System.out.println("Błąd: Instrument nie jest dostępny do wypożyczenia");
        }

    }

}
