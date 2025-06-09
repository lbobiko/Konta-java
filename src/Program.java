import java.util.Scanner;

public class Program {
    //private BazaKlientow baza = new BazaKlientow();
    //private Scanner scanner = new Scanner(System.in);
    public void uruchom() {
        wyczyscEkran();
        BazaKlientow baza = new BazaKlientow();
        // Wczytanie danych z pliku binarnego po utworzeniu bazy
        StanProgramu stan = StanProgramu.wczytajStanZPliku("stan_programu.dat");
        if (stan != null) {
            baza.setKlienci(stan.getKlienci());
            baza.setOstatnieID(stan.getOstatnieID());
        }
        Scanner scanner = new Scanner(System.in);
        boolean dziala = true;


        while (dziala) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Dodaj klienta                        |  2. Dodaj klienta VIP");
            System.out.println("3. Wyświetl wszystkich klientów         |  4. Wyswietl wybranego klienta");
            System.out.println("5. Zasilenie konta klienta              |  6. Wypłata z konta klienta");
            System.out.println("7. Przelew pomiędzy kontami             |  8. Naliczenie oprocentowania");
            System.out.println("9. Zapis zmian i zakończenie programu   |  0. Usuniecie klienta");
            System.out.print("Wybierz opcję: ");
            int menu = scanner.nextInt();
            scanner.nextLine(); // pochłania Enter

            switch (menu) {
                case 1 -> {
                    wyczyscEkran();
                    System.out.println("Dodawanie klientow...");
                    System.out.println("Podaj imię klienta: ");
                    String imie = scanner.nextLine();
                    System.out.println("Podaj nazwisko klienta: ");
                    String nazwisko = scanner.nextLine();
                    int noweId = baza.generujNoweId();
                    baza.dodajKlienta(new Klient(noweId, imie, nazwisko));
                    System.out.println("Klient dodany, wcisnij dowolny klawisz...");
                    scanner.nextLine();
                }
                case 2 -> {
                    wyczyscEkran();
                    System.out.println("Dodawanie klientow VIP...");
                    System.out.println("Podaj imię klienta: ");
                    String imie = scanner.nextLine();
                    System.out.println("Podaj nazwisko klienta: ");
                    String nazwisko = scanner.nextLine();
                    int noweId = baza.generujNoweId();
                    baza.dodajKlienta(new KlientVIP(noweId, imie, nazwisko));
                    System.out.println("Klient dodany, wcisnij dowolny klawisz...");
                    scanner.nextLine();
                }
                case 3 -> {
                    wyczyscEkran();
                    System.out.println("Lista klientów:");
                    baza.wyswietlKlientow();
                }
                case 4 -> {
                    wyczyscEkran();
                    System.out.println("Wyświetlenie danych klienta...");
                    Integer pokazID = pobierzIdKlienta(scanner);
                    if (pokazID == null) {
                        System.out.println("Anulowano. Wciśnij Enter, aby wrócić do menu...");
                        scanner.nextLine();
                        break;
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
                case 5 -> {
                    wyczyscEkran();
                    System.out.println("Zasilenie konta...");
                    Integer pokazID = pobierzIdKlienta(scanner);
                    if (pokazID == null) {
                        System.out.println("Anulowano. Wciśnij Enter, aby wrócić do menu...");
                        scanner.nextLine();
                        break;
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
                case 6 -> {
                    wyczyscEkran();
                    System.out.println("Wypłata z konta...");
                    Integer pokazID = pobierzIdKlienta(scanner);
                    if (pokazID == null) {
                        System.out.println("Anulowano. Wciśnij Enter, aby wrócić do menu...");
                        scanner.nextLine();
                        break;
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
                    // scanner.nextLine(); // pochłania Enter po nextDouble -- USUNIĘTO NA TWOJĄ PROŚBĘ
                    scanner.nextLine(); // oczekuje na Enter od użytkownika
                }
                case 7 -> {
                    wyczyscEkran();
                    System.out.println("Przelew pomiędzy kontami...");
                    System.out.println("Podaj ID klienta źródłowego:");
                    int odId = scanner.nextInt();
                    scanner.nextLine(); // pochłania Enter po nextInt
                    Klient klientZrodlowy = baza.znajdzPoId(odId);
                    if (klientZrodlowy==null){
                        System.out.println("Nie znaleziono klienta o takim ID. Powrót do menu.");
                        System.out.println("Wciśnij Enter");
                        scanner.nextLine();
                        break;
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
                        break;
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
                case 8 -> {
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
                case 0 -> {
                    wyczyscEkran();
                    System.out.println("Usuwanie klienta...");
                    Integer usunID = pobierzIdKlienta(scanner);
                    if (usunID == null) {
                        System.out.println("Anulowano. Wciśnij Enter...");
                        scanner.nextLine();
                        break;
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
                case 9 -> {
                    // Zapisz dane do pliku przed zakończeniem programu
                    baza.zapiszStanDoPliku("stan_programu.dat");
                    System.out.println("Zamykam program, do zobaczenia!");
                    dziala = false;
                }
                default -> {
                    System.out.println("Nieprawidłowa opcja!");
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
                System.out.println("Niepoprawny format. Wprowadź numer ID lub 'z'.");
            }
        }
    }

}
