public class Klient {
    private int idKlienta;
    private String imie;
    private String nazwisko;
    private double saldoKonta;
    private double oprocentowanie;

    public Klient(int idKlienta, String imie, String nazwisko, double saldoKonta, double oprocentowanie){
        this.idKlienta = idKlienta;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.saldoKonta = saldoKonta;
        if (oprocentowanie < 0){
            this.oprocentowanie = 0;
        } else{
            this.oprocentowanie = oprocentowanie;
        }

    }
    public Klient(int idKlienta, String imie, String nazwisko){
        this.idKlienta = idKlienta;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.saldoKonta = 0;
        this.oprocentowanie = 0;
    }
    public String toString(){
        return "Klient ID:" + idKlienta +
                ", Imię:" + imie +
                ", Nazwisko:" + nazwisko +
                ", Saldo:" + saldoKonta +
                ", Oprocentowanie:" + oprocentowanie;
    }
    public int getIdKlienta() {
        return idKlienta;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public double getSaldoKonta() {
        return saldoKonta;
    }

    public double getOprocentowanie() {
        return oprocentowanie;
    }

    public void setIdKlienta(int idKlienta) {
        this.idKlienta = idKlienta;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setSaldoKonta(double saldoKonta) {
        this.saldoKonta = saldoKonta;
    }

    public void setOprocentowanie(double oprocentowanie) {
        this.oprocentowanie = oprocentowanie;
    }
}
