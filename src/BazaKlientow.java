import java.util.ArrayList;

public class BazaKlientow {

    private int ostatnieId = 0;
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

    public void naliczOprocentowanieDlaWszystkich(){
        for(Klient k : klienci) {
            double saldo = k.getSaldoKonta();
            saldo *= 1 + (k.getOprocentowanie() / 100.0);
            if (k instanceof KlientVIP vip) {
                saldo *= 1 + (vip.getDodatkoweOprocentowanie() / 100.0);
            }
            k.setSaldoKonta(saldo);
        }
    }

    public int generujNoweId() {
        ostatnieId++;
        return ostatnieId;
    }

    public void dodajKlienta(Klient klient) {

        klienci.add(klient);
    }
    public boolean usunKlineta(int id){
        Klient doUsuniecia = znajdzPoId(id);
        if (doUsuniecia!=null){
            klienci.remove(doUsuniecia);
            return true;
        }
        return false;
    }

    // Formatowanie wierszy:
    // %-5d  -> liczba całkowita (ID), 5 znaków, wyrównana do lewej
    // %-10s -> tekst (Imie), 10 znaków, wyrównany do lewej
    // %-15s -> tekst (Nazwisko), 15 znaków, wyrównany do lewej
    // %-10.2f -> liczba zmiennoprzecinkowa (Saldo), 10 znaków, 2 miejsca po przecinku, wyrównanie do lewej
    // %-15.2f -> liczba zmiennoprzecinkowa (Oprocentowanie), 15 znaków, 2 miejsca po przecinku, wyrównanie do lewej
    public void wyswietlKlientow(){
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_RESET = "\u001B[0m";
        System.out.printf(ANSI_YELLOW + "%-5s %-10s %-15s %-10s %-15s %-15s" + ANSI_RESET + "%n", "Id", "Imie", "Nazwisko", "Saldo", "Oprocentowanie", "Dodatkowe %");
        for (Klient k : klienci){
            if (k instanceof KlientVIP vip) {
                System.out.printf(ANSI_CYAN + "%-5d %-10s %-15s %-10.2f %-15.2f %-15.2f" + ANSI_RESET + "%n", vip.getIdKlienta(), vip.getImie(), vip.getNazwisko(), vip.getSaldoKonta(), vip.getOprocentowanie(), vip.getDodatkoweOprocentowanie());
            } else {
                System.out.printf("%-5d %-10s %-15s %-10.2f %-15.2f %-15s%n", k.getIdKlienta(), k.getImie(), k.getNazwisko(), k.getSaldoKonta(), k.getOprocentowanie(), "-");
            }
        }
    }

    public void setKlienci(ArrayList<Klient> klienci) {
        this.klienci = klienci;
    }

    public void setOstatnieID(int id) {
        this.ostatnieId = id;
    }

    public void zapiszStanDoPliku(String nazwaPliku) {
        StanProgramu stan = new StanProgramu(ostatnieId, klienci);
        StanProgramu.zapiszStanDoPliku(nazwaPliku, stan);
    }

}
