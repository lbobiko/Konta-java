public class Main {
    public static void main(String[] args) {
        BazaKlientow baza = new BazaKlientow();
        baza.dodajKlienta(new Klient(1, "Lukasz", "Bobinski"));
        baza.dodajKlienta(new Klient(2, "Monika", "Bobinska"));

        Klient k = baza.znajdzPoId(1);
        if (k != null) {
            System.out.println(k);
        } else {
            System.out.println("Nie znaleziono klienta.");
        }
    }
}