import java.io.*;
import java.util.ArrayList;

public class StanProgramu implements Serializable {
    private int ostatnieId;
    private ArrayList<Klient> klienci;

    public StanProgramu(int ostatnieID, ArrayList<Klient> klienci) {
        this.ostatnieId = ostatnieID;
        this.klienci = klienci;
    }

    public static void zapiszStanDoPliku(String nazwaPliku, StanProgramu stan) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nazwaPliku))) {
            oos.writeObject(stan);
            System.out.println("Stan programu zapisany.");
        } catch (IOException e) {
            System.out.println("Błąd zapisu: " + e.getMessage());
        }
    }

    public static StanProgramu wczytajStanZPliku(String nazwaPliku) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nazwaPliku))) {
            StanProgramu stan = (StanProgramu) ois.readObject();
            System.out.println("Stan programu wczytany.");
            return stan;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Błąd odczytu: " + e.getMessage());
            return null;
        }
    }



    public int getOstatnieID() {
        return ostatnieId;
    }

    public ArrayList<Klient> getKlienci() {
        return klienci;
    }

    public void setOstatnieID(int ostatnieID) {
        this.ostatnieId = ostatnieID;
    }

    public void setKlienci(ArrayList<Klient> klienci) {
        this.klienci = klienci;
    }
}
