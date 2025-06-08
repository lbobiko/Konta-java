import java.io.Serializable;

public class KlientVIP extends Klient implements Serializable {
    private Double dodatkoweOprocentowanie;

    public KlientVIP(int idKlienta, String imie, String nazwisko, double saldoKonta, double oprocentowanie, Double dodatkoweOprocentowanie) {
        super(idKlienta, imie, nazwisko, saldoKonta, oprocentowanie);
        this.dodatkoweOprocentowanie = dodatkoweOprocentowanie;
    }

    public KlientVIP(int idKlienta, String imie, String nazwisko) {
        super(idKlienta,imie, nazwisko);
        this.dodatkoweOprocentowanie = 2.0;
    }
    @Override
    public String toString() {
        return "[VIP] " + super.toString() + " dodatkowe oprocentowanie: " + dodatkoweOprocentowanie;
    }

    public Double getDodatkoweOprocentowanie() {
        return dodatkoweOprocentowanie;
    }
}
