public class KolorowyTekst {

    public static void wypisz(String tekst, String kolor) {
        String kodKoloru = switch (kolor.toLowerCase()) {
            case "czarny" -> "\u001B[30m";
            case "czerwony" -> "\u001B[31m";
            case "zielony" -> "\u001B[32m";
            case "żółty" -> "\u001B[33m";
            case "niebieski" -> "\u001B[34m";
            case "fioletowy" -> "\u001B[35m";
            case "jasnoniebieski" -> "\u001B[36m";
            case "biały" -> "\u001B[37m";
            default -> "\u001B[0m"; // brak koloru lub nieznany
        };

        final String RESET = "\u001B[0m";
        System.out.println(kodKoloru + tekst + RESET);
    }
}