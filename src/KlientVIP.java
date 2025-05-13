public class KlientVIP extends Klient{
    private Double dodatkoweOprocentowanie;

    public KlientVIP(int idKlienta, String imie, String nazwisko, double saldoKonta, double oprocentowanie, Double dodatkoweOprocentowanie) {
        super(idKlienta, imie, nazwisko, saldoKonta, oprocentowanie);
        this.dodatkoweOprocentowanie = dodatkoweOprocentowanie;
    }

    public KlientVIP(int idKlienta, String imie, String nazwisko, Double dodatkoweOprocentowanie) {
        super(idKlienta, imie, nazwisko);
        this.dodatkoweOprocentowanie = dodatkoweOprocentowanie;
    }
}
