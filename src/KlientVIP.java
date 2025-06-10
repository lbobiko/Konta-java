import java.io.Serializable;

public class KlientVIP extends Klient implements Serializable {
    private double dodatkoweOprocentowanie;

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
        return String.format("[VIP] %s Dod.oprocentowanie: %.2f%%", super.toString(), dodatkoweOprocentowanie);
    }


    public double getDodatkoweOprocentowanie() {
        return dodatkoweOprocentowanie;
    }

    public void setDodatkoweOprocentowanie(double dodatkoweOprocentowanie) {
        this.dodatkoweOprocentowanie = dodatkoweOprocentowanie;
    }
}
