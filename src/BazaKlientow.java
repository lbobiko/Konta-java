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
    // Formatowanie wierszy:
    // %-5d  -> liczba całkowita (ID), 5 znaków, wyrównana do lewej
    // %-10s -> tekst (Imie), 10 znaków, wyrównany do lewej
    // %-15s -> tekst (Nazwisko), 15 znaków, wyrównany do lewej
    // %-10.2f -> liczba zmiennoprzecinkowa (Saldo), 10 znaków, 2 miejsca po przecinku, wyrównanie do lewej
    // %-15.2f -> liczba zmiennoprzecinkowa (Oprocentowanie), 15 znaków, 2 miejsca po przecinku, wyrównanie do lewej
    public void wyswietlKlientow(){
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_RESET = "\u001B[0m";
        System.out.printf(ANSI_YELLOW + "%-5s %-10s %-15s %-10s %-15s" + ANSI_RESET + "%n", "Id", "Imie", "Nazwisko", "Saldo", "Oprocentowanie");
        for (Klient k : klienci){
            System.out.printf("%-5d %-10s %-15s %-10.2f %-15.2f%n", k.getIdKlienta(), k.getImie(), k.getNazwisko(), k.getSaldoKonta(), k.getOprocentowanie());
        }
    }

}
