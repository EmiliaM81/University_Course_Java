public interface Wyporzyczalny {
    boolean czyDostepny();
    double obliczKosztWypozyczenia(int dni);
    void zwroc();
    void wypozycz();
}
