import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BazaKlientow baza = new BazaKlientow();
        Scanner scanner = new Scanner(System.in);
        boolean dziala = true;

        /*
        baza.dodajKlienta(new Klient(1, "Lukasz", "Bobinski"));
        baza.dodajKlienta(new Klient(2, "Monika", "Bobinska"));
        baza.dodajKlienta(new KlientVIP(3, "Iga", "Bobinska", 1000, 10.0, 10.0));
        baza.dodajKlienta(new KlientVIP(4, "Hanna", "Bobinska", 1000, 10.0, 10.0));

        baza.wyswietlKlientow();*/

        while (dziala) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Dodaj klienta");
            System.out.println("2. Wyświetl wszystkich klientów");
            System.out.println("3. Zakończ program");
            System.out.print("Wybierz opcję: ");
            int menu = scanner.nextInt();
            scanner.nextLine(); // pochłania Enter

            switch (menu) {
                case 1 -> {
                    wyczyscEkran();
                    System.out.println("Dodawanie klientow....");
                }
                case 2 -> {
                    wyczyscEkran();
                    System.out.println("Lista klientów:");
                    baza.wyswietlKlientow();
                }
                case 3 -> {
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
}