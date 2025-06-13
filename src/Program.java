import java.util.Scanner;

public class Program {
    //private BazaKlientow baza = new BazaKlientow();
    //private Scanner scanner = new Scanner(System.in);
    public void uruchom() {
        wyczyscEkran();
        BazaKlientow baza = new BazaKlientow();
        StanProgramu stan = StanProgramu.wczytajStanZPliku("stan_programu.dat");  // Wczytanie danych z pliku binarnego po utworzeniu bazy
        if (stan != null) {
            baza.setKlienci(stan.getKlienci());
            baza.setOstatnieID(stan.getOstatnieID());
        }
        Scanner scanner = new Scanner(System.in);
        boolean dziala = true;


        while (dziala) {
            //System.out.println("\n--- MENU ---");
            KolorowyTekst.wypisz("                             MENU GŁÓWNE:", "zielony");
            KolorowyTekst.wypisz("----------------------------------------------------------------------", "zielony");
            System.out.println("1. Dodaj klienta                        |  2. Wyświetl wybranego klienta");
            System.out.println("3. Wyświetl wszystkich klientów         |  4. Zmiana oprocentowania konta");
            System.out.println("5. Zasilenie konta klienta              |  6. Wypłata z konta klienta");
            System.out.println("7. Przelew pomiędzy kontami             |  8. Naliczenie oprocentowania");
            System.out.println("9. Zapis zmian i zakończenie programu   |  0. Usuniecie klienta");
            KolorowyTekst.wypisz("Wybierz opcję:", "zielony");
            String input = scanner.nextLine();
            int menu;
            //scanner.nextLine(); // pochłania Enter
            try {
                menu = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                KolorowyTekst.wypisz("Nieprawidłowy wybór. Wpisz liczbę.", "czerwony");
                continue; // wraca do pętli i wyświetla menu jeszcze raz
            }
            switch (menu) {
                case 1 -> dodajKlienta(baza, scanner);
                case 2 -> wyswietlKlienta(baza, scanner);
                case 3 -> wyswietlKlientow(baza, scanner);
                case 4 -> zmianaOprocentowania(baza, scanner);
                case 5 -> zailenieKonta(baza, scanner);
                case 6 -> wyplataZKonta(baza, scanner);
                case 7 -> przelewPomiedzyKontami(baza, scanner);
                case 8 -> naliczenieOprocentowania(baza, scanner);
                case 0 -> usunKlienta(baza, scanner);
                case 9 -> {
                    // Zapisz dane do pliku przed zakończeniem programu
                    baza.zapiszStanDoPliku("stan_programu.dat");
                    System.out.println("Zamykam program, do zobaczenia!");
                    dziala = false;
                }
                default -> {
                    KolorowyTekst.wypisz("Nieprawidłowa opcja.", "czerwony");
                }
            }
        }

    }
    public static void wyczyscEkran() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static Integer pobierzIdKlienta(Scanner scanner) {
        while (true) {
            System.out.println("Podaj ID klienta (lub wpisz 'z', aby wrócić):");
            String wprowadzonyZnak = scanner.nextLine();
            if (wprowadzonyZnak.equalsIgnoreCase("z")) {
                return null;  // użytkownik anulował
            }
            try {
                return Integer.parseInt(wprowadzonyZnak);
            } catch (NumberFormatException e) {
                KolorowyTekst.wypisz("Niepoprawny format. Wprowadź numer ID lub 'z'.", "czerwony");
            }
        }
    }

    // Dodaj klienta
    private void dodajKlienta(BazaKlientow baza, Scanner scanner) {
        wyczyscEkran();
        System.out.println("Dodawanie klientow...");
        System.out.println("Podaj imię klienta: ");
        String imie = scanner.nextLine();
        System.out.println("Podaj nazwisko klienta: ");
        String nazwisko = scanner.nextLine();
        System.out.println("Czy jest to klient VIP?");
        String wyborVip = "0";
        while(!wyborVip.equals("1") && !wyborVip.equals("2") && !wyborVip.equalsIgnoreCase("z")){
            System.out.println("1 - klient zwykły");
            System.out.println("2 - klient VIP");
            System.out.println("z - Powrót do menu");
            wyborVip = scanner.nextLine();
            if(wyborVip.equals("1")){
                int noweId = baza.generujNoweId();
                baza.dodajKlienta(new Klient(noweId, imie, nazwisko));
                System.out.println("Klient dodany, wcisnij dowolny klawisz...");
                scanner.nextLine();
            } else if (wyborVip.equals("2")) {
                int noweId = baza.generujNoweId();
                baza.dodajKlienta(new KlientVIP(noweId, imie, nazwisko));
                System.out.println("Klient VIP dodany, wcisnij dowolny klawisz...");
                scanner.nextLine();
            } else if (wyborVip.equalsIgnoreCase("z")) {
                System.out.println("Dodawanie przerwane. Wciśnij Enter...");
                scanner.nextLine();
                wyczyscEkran();
            }else{
                KolorowyTekst.wypisz("Proszę wybrać odpowiednią opcję. Wciśnij Enter...", "czerwony");

            }
        }
    }
    private void wyswietlKlientow(BazaKlientow baza, Scanner scanner){
        wyczyscEkran();
        System.out.println("Lista klientów:");
        baza.wyswietlKlientow();
    }
    private void wyswietlKlienta(BazaKlientow baza, Scanner scanner){
        wyczyscEkran();
        System.out.println("Wyświetlenie danych klienta...");
        Integer pokazID = pobierzIdKlienta(scanner);
        if (pokazID == null) {
            System.out.println("Anulowano. Wciśnij Enter, aby wrócić do menu...");
            scanner.nextLine();
            return;
        }
        Klient znaleziony = baza.znajdzPoId(pokazID);
        if (znaleziony != null) {
            System.out.println("Dane klienta:");
            System.out.println(znaleziony);
        } else {
            System.out.println("Nie znaleziono klienta o ID: " + pokazID);
        }
        System.out.println("\nWciśnij Enter, aby kontynuować...");
        scanner.nextLine();
    }
    private void zailenieKonta(BazaKlientow baza, Scanner scanner){
        wyczyscEkran();
        System.out.println("Zasilenie konta...");
        Integer pokazID = pobierzIdKlienta(scanner);
        if (pokazID == null) {
            System.out.println("Anulowano. Wciśnij Enter, aby wrócić do menu...");
            scanner.nextLine();
            return;
        }
        Klient znaleziony = baza.znajdzPoId(pokazID);
        if (znaleziony != null) {
            System.out.println("Podaj kwotę: ");
            double zasilenie = scanner.nextDouble();
            while (zasilenie < 0) {
                System.out.println("Kwota nie może być mniejsza od 0. Wprowadź jeszcze raz.");
                zasilenie = scanner.nextDouble();
            }
            double noweSaldo = znaleziony.getSaldoKonta() + zasilenie;
            znaleziony.setSaldoKonta(noweSaldo);
            System.out.printf("Zasilono konto. Nowe saldo: %.2f%n", noweSaldo);

            scanner.nextLine();
        } else {
            System.out.println("Nie znaleziono klienta o ID: " + pokazID);
        }

        System.out.println("\nWciśnij Enter, aby kontynuować...");
        scanner.nextLine();
    }
    private void zmianaOprocentowania(BazaKlientow baza, Scanner scanner){
        wyczyscEkran();
        System.out.println("Zmiana oprocentowania...");
        Integer zmienProcent = pobierzIdKlienta(scanner);
        if (zmienProcent == null){
            System.out.println("Anulowano. Wciśnij Enter, aby wrócić do menu...");
            scanner.nextLine();
            return;
        }
        Klient znaleziony = baza.znajdzPoId(zmienProcent);

        if (znaleziony instanceof KlientVIP vip){
            System.out.println("Aktualny stan konta klienta VIP:");
            System.out.println(vip);

            double procentBazowy = -1;
            while (procentBazowy <= 0){
                System.out.println("Podaj nową wartość oprocentowania bazowego (liczba > 0):");
                String input = scanner.nextLine();
                try {
                    procentBazowy = Double.parseDouble(input);
                    if (procentBazowy <= 0 ){
                        System.out.println("Oprocentowanie musi być większe od zera.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("To nie jest poprawna liczba. Wprowadź jeszcze raz.");
                }
            }

            double procentDodatkowy = -1;
            while (procentDodatkowy <= 0){
                System.out.println("Podaj nową wartość dodatkowego oprocentowania VIP (liczba > 0):");
                String input = scanner.nextLine();
                try {
                    procentDodatkowy = Double.parseDouble(input);
                    if (procentDodatkowy <= 0 ){
                        System.out.println("Oprocentowanie musi być większe od zera.");
                    }
                } catch (NumberFormatException e) {
                    KolorowyTekst.wypisz("To nie jest poprawna liczba. Wprowadź jeszcze raz.", "czerwony");
                }
            }
            vip.setOprocentowanie(procentBazowy);
            vip.setDodatkoweOprocentowanie(procentDodatkowy);
            System.out.println("Zmieniono oprocentowanie klienta VIP.");

        } else if (znaleziony != null) {
            System.out.println("Aktualny stan konta klienta:");
            System.out.println(znaleziony);
            int procent = -1;
            while (procent <= 0){
                System.out.println("Podaj nową wartość oprocentowania (liczba całkowita > 0):");
                String input = scanner.nextLine();
                try {
                    procent = Integer.parseInt(input);
                    if (procent <= 0 ){
                        System.out.println("Oprocentowanie musi być większe od zera");
                    }
                } catch (NumberFormatException e) {
                    KolorowyTekst.wypisz("To nie jest poprawna liczba. Wprowadź jeszcze raz.", "czerwony");
                }
            }
            znaleziony.setOprocentowanie(procent);
            System.out.println("Zmieniono oprocentowanie klienta.");
        } else {
            System.out.println("Nie znaleziono klienta o ID: " + zmienProcent);
        }

        System.out.println("Wciśnij Enter, aby kontynuować...");
        scanner.nextLine();
    }
    private void wyplataZKonta(BazaKlientow baza, Scanner scanner){
        wyczyscEkran();
        System.out.println("Wypłata z konta...");
        Integer pokazID = pobierzIdKlienta(scanner);
        if (pokazID == null) {
            System.out.println("Anulowano. Wciśnij Enter, aby wrócić do menu...");
            scanner.nextLine();
            return;
        }
        Klient znaleziony = baza.znajdzPoId(pokazID);
        System.out.println("Podaj kwotę do wypłaty:");
        if (znaleziony != null) {
            double wyplata = scanner.nextDouble();
            while (wyplata <= 0) {
                System.out.println("Kwota musi być większa od zera. Wprowadź jeszcze raz:");
                wyplata = scanner.nextDouble();
            }
            if (znaleziony.getSaldoKonta() < wyplata) {
                System.out.printf("Kwota przewyższa stan konta! Stan: %.2f%n", znaleziony.getSaldoKonta());
            } else {
                double noweSaldo = znaleziony.getSaldoKonta() - wyplata;
                znaleziony.setSaldoKonta(noweSaldo);
                System.out.printf("Wypłata zakończona. Aktualne saldo: %.2f%n", noweSaldo);
            }
        } else {
            System.out.println("Nie znaleziono klienta o ID: " + pokazID);
        }

        System.out.println("\nWciśnij Enter, aby kontynuować...");
        scanner.nextLine();

    }
    private void przelewPomiedzyKontami(BazaKlientow baza, Scanner scanner){
        wyczyscEkran();
        System.out.println("Przelew pomiędzy kontami...");
        System.out.println("Klient źródłowy:");
        Integer odId = pobierzIdKlienta(scanner);
        Klient klientZrodlowy = baza.znajdzPoId(odId);
        if (klientZrodlowy==null){
            System.out.println("Nie znaleziono klienta o takim ID. Powrót do menu.");
            System.out.println("Wciśnij Enter");
            scanner.nextLine();
            return;
        }
        Klient klientDocelowy = null;
        while (klientDocelowy==null){
            System.out.println("Podaj ID klienta docelowego lub 'z' aby anulować:");
            String wybor = scanner.nextLine();
            if (wybor.equalsIgnoreCase("z")){
                System.out.println("Anulowano, wciśnij Enter aby wrócić do menu");
                scanner.nextLine();
                break;
            }
            try {
                int doId = Integer.parseInt(wybor);
                klientDocelowy = baza.znajdzPoId(doId);
                if (klientDocelowy == null) {
                    System.out.println("Nie znaleziono klienta o takim ID.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Wpisz poprawny numer ID lub 'z' aby anulować.");
            }
        }
        if (klientDocelowy == null) {
            return;
        }
        System.out.println("Podaj kwotę przelewu:");
        Double przelew = scanner.nextDouble();
        scanner.nextLine();
        if (przelew < 0) {
            System.out.println("Kwota nie może być ujemna.");
        } else if (klientZrodlowy.getSaldoKonta() < przelew) {
            System.out.printf("Brak środków na koncie źródłowym (stan: %.2f).%n", klientZrodlowy.getSaldoKonta());
        } else {
            klientZrodlowy.setSaldoKonta(klientZrodlowy.getSaldoKonta() - przelew);
            klientDocelowy.setSaldoKonta(klientDocelowy.getSaldoKonta() + przelew);
            System.out.printf("Przelew zakończony. Nowe saldo: %.2f (źródło), %.2f (cel)%n", klientZrodlowy.getSaldoKonta(), klientDocelowy.getSaldoKonta());
        }
        System.out.println("Wciśnij Enter, aby kontynuować...");
        scanner.nextLine();
    }
    private void naliczenieOprocentowania(BazaKlientow baza, Scanner scanner){
        wyczyscEkran();
        System.out.println("Naliczanie oprocentowania. Wybierz jedną z opcji:");
        String naliczanie = "0";
        while (!naliczanie.equals("1") && !naliczanie.equals("2") && !naliczanie.equalsIgnoreCase("z")){
            wyczyscEkran();
            System.out.println("1 - naliczanie dla wybranego klienta");
            System.out.println("2 - naliczanie dla wszystkich klientów");
            System.out.println("z - Powrót do menu");
            naliczanie = scanner.nextLine();
            if (naliczanie.equals("1")){
                wyczyscEkran();
                Integer procentId = pobierzIdKlienta(scanner);
                if (procentId == null) {
                    System.out.println("Anulowano. Wciśnij Enter...");
                    scanner.nextLine();
                    break;
                }
                Klient znaleziony = baza.znajdzPoId(procentId);
                if (znaleziony != null) {
                    wyczyscEkran();
                    System.out.println("Naliczam oprocentowanie dla klienta:");
                    System.out.println("Dane klienta o ID :" + procentId + ":");
                    System.out.println(znaleziony);
                    double saldo = znaleziony.getSaldoKonta();
                    saldo *= 1 + (znaleziony.getOprocentowanie() / 100.0);
                    if (znaleziony instanceof KlientVIP vip) {
                        saldo *= 1 + (vip.getDodatkoweOprocentowanie() / 100.0);
                    }
                    znaleziony.setSaldoKonta(saldo);
                    System.out.println("\nStan konta klienta po naliczeniu:");
                    System.out.println(znaleziony);
                } else {
                    System.out.println("Nie znaleziono klienta o ID: " + procentId);
                }

                System.out.println("\nWciśnij Enter, aby kontynuować...");
                scanner.nextLine();

            } else if (naliczanie.equals("2")) {
                wyczyscEkran();
                System.out.println("Jesteś pewny, że chcesz naliczyć oprocentowanie dla WSZYSTKICH?");
                System.out.println("Potwierdź wpisując t, lub anuluj wpisując dowolny znak:");
                String potwierdzenie = scanner.nextLine();
                if (potwierdzenie.equalsIgnoreCase("t")){
                    baza.naliczOprocentowanieDlaWszystkich();
                    System.out.println("Naliczono oprocentowanie dla wszystkich klientów.");
                    System.out.println("Wciśnij Enter...");
                    scanner.nextLine();
                } else{
                    System.out.println("Powrót do menu. Wciśnij Enter...");
                    scanner.nextLine();
                }
            }
        }
    }
    private void usunKlienta(BazaKlientow baza, Scanner scanner){
        wyczyscEkran();
        System.out.println("Usuwanie klienta...");
        Integer usunID = pobierzIdKlienta(scanner);
        if (usunID == null) {
            System.out.println("Anulowano. Wciśnij Enter...");
            scanner.nextLine();
            return;
        }
        Klient znaleziony = baza.znajdzPoId(usunID);
        if (znaleziony!=null){
            System.out.println("UWAGA! Próbujesz usunąć klienta:");
            System.out.println(znaleziony);
            System.out.println("Jestes pewny? Wpisz: t - tak, inny znak - nie:");
            String potwierdzenie = scanner.nextLine();
            if (potwierdzenie.equalsIgnoreCase("t")){
                baza.usunKlineta(usunID);
                System.out.println("Klinet o ID " + usunID + " został usunięty.");
            } else {
                System.out.println("Usunięcie anulowane. Wciśnij Enter...");
                scanner.nextLine();
            }
        } else{
            System.out.println("Nie znaleziono klienta o ID: " + usunID);
        }
    }

}