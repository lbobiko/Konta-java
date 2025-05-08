import java.util.ArrayList;

public class BazaKlientow {

    private ArrayList<Klient> klienci;

    public BazaKlientow() {
        klienci = new ArrayList<Klient>();
    }

    public Klient znajdzPoId(int id) {
        for (Klient k : klienci) {
            if (k.getIdKlienta() == id) {
                return k;
            }
        }
        return null;
    }
    public void dodajKlienta(Klient klient) {
        klienci.add(klient);
    }

}
