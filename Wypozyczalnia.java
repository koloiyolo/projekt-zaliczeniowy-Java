package com.company;


import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Wypozyczalnia {
    public Film[] filmy;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private int ileDodanych;
    private int maksRozmiar;
    public Klient[] klienci;
    private int iluKlientow;

    //inicjowanie podstawowych zmiennych
    public Wypozyczalnia() {
        this.iluKlientow = 0;
        this.ileDodanych = 0;
        this.maksRozmiar = 20;
        filmy = new Film[maksRozmiar];
        klienci = new Klient[maksRozmiar];
    }

    public int getIleDodanych() {
        return ileDodanych;
    }

    public int getIluKlientow() {
        return iluKlientow;
    }

    public void usunFilm(int indeks) {
        if (filmy[indeks].getDostepne() == "dostepny") {
            for (int i = (indeks); i < ileDodanych; i++) {
                filmy[i] = filmy[i + 1];
            }
            System.out.println("Film usuniety pomyslnie.");
            ileDodanych--;
        } else {
            System.out.println("Nie mozna usunac niedostepnej pozycji.");
        }
    }

    //inicjowanie/wyciaganie danych z tablic
    public void zaladujKlientow() {
        klienci[0] = new Klient(1, "Jakub", "Kolodziej");
        klienci[1] = new Klient(2, "Mariusz", "Pudzianowski");
        klienci[2] = new Klient(3, "Janusz", "Korwin", 1, new int[]{5, 0, 0}, (60 - 49.99));
        klienci[3] = new Klient(4, "Jan", "Kowalski");
        klienci[4] = new Klient(5, "Stefan", "Duda", 2, new int[]{2, 8, 0}, (60 - 14.99 - 29.99));
        iluKlientow = 5;
    }

    public String getKlient(int i) {
        return (klienci[i].getImie() + "/" + klienci[i].getNazwisko() + "/" + klienci[i].getIleWypozyczonych() + "/" + klienci[i].getWypozyczone(0) + "/" + klienci[i].getWypozyczone(1) + "/" + klienci[i].getWypozyczone(2) + "/" + klienci[i].getSaldo());
    }//do zapisu do pliku tekstowego

    public Klient[] getKlienci() {
        return klienci;
    }

    public void dodajK(int i, String imie, String nazwisko, int ile, int x, int y, int z, double saldo) {
        klienci[iluKlientow] = new Klient(i, imie, nazwisko, ile, new int[]{x, y, z}, saldo);
        iluKlientow++;
    }

    public void dK(Klient[] K) {
        iluKlientow=0;
        for (int i = 0; i < K.length; i++) {
            klienci[i]=K[i];
            if(K[i]!=null){
                iluKlientow++;
            }
        }
    }

    public String toSKlient(int i) {
        return (klienci[i].getId() + ". " + klienci[i].getImie() + " " + klienci[i].getNazwisko() + "\n wypozyczone: " + klienci[i].getIleWypozyczonych() + " pozycje\n saldo: " + df.format(klienci[i].getSaldo()) + "zl");
    }

    public void zaladujFilmy() {
        filmy[0] = new Film(1, "Hobbit", (45.99), 2012, "przygodowy", true);
        filmy[1] = new Film(2, "Wladca Pierscieni", (29.99), 2001, "przygodowy", false);
        filmy[2] = new Film(3, "Hitman", (20.99), 2007, "akcji", true);
        filmy[3] = new Film(4, "Transporter", (20.99), 2010, "akcji", true);
        filmy[4] = new Film(5, "Matrix", (49.99), 1997, "akcji", false);
        filmy[5] = new Film(6, "Transformers", (25.99), 2005, "akcji", true);
        filmy[6] = new Film(7, "Terminator", (45.99), 1999, "akcji", true);
        filmy[7] = new Film(8, "Mgla", (14.99), 2010, "horror", false);
        filmy[8] = new Film(9, "Pila", (14.99), 2004, "horror", true);
        filmy[9] = new Film(10,"They look like people", (16.99), 2015, "horror", true );
        filmy[10] = new Film(11, "Skyfall", 25.99, 2012, "akcjii", true);
        ileDodanych = 11;
    }

    public String getFilm(int i) {
        return (filmy[i].getNazwa() + "/" + filmy[i].getCena() + "/" + filmy[i].getRok() + "/" + filmy[i].getTyp() + "/" + filmy[i].isDostepne());
    }//do zapisu do pliku tekstowego

    public Film[] getFilmy() {
        return filmy;
    }

    public void dodajF(int i, String nazwa, double cena, int rok, String typ, boolean dostepne) {
        filmy[ileDodanych] = new Film(i, nazwa, cena, rok, typ, dostepne);
        ileDodanych++;
    }

    public void dF(Film[] f) {
        ileDodanych=0;
        for (int i = 0; i < f.length; i++) {
            filmy[i]=f[i];
            if(f[i]!=null){
            ileDodanych++;
            }
        }
    }

    public String toSFilm(int i) {
        return (filmy[i].getId() + ". " + filmy[i].getNazwa() + "\n rok produkcji: " + filmy[i].getRok() + " typ: " + filmy[i].getTyp() + "\n cena: " + df.format(filmy[i].getCena()) + " dostepnosc: " + filmy[i].getDostepne());
    }

    //zarzadzanie filmami
    public void dodajFilm() {
        Scanner sn = new Scanner(System.in);
        ileDodanych++;
        System.out.println("Film numer: " + (ileDodanych));
        System.out.print("Podaj nazwę nowego filmu: ");
        String nazwa = sn.nextLine();
        System.out.print("Podaj cene nowego filmu: ");
        double cena = sn.nextDouble();
        System.out.print("Podaj rok wydania filmu: ");
        int rok = sn.nextInt();
        sn.nextLine();
        System.out.print("Co to za film?: ");
        String typ = sn.nextLine();
        System.out.println("Czy film jest dostepny (tak/nie): ");
        String dos = sn.nextLine();
        filmy[ileDodanych - 1] = new Film((ileDodanych), nazwa, cena, rok, typ, ((dos.equalsIgnoreCase("tak")) || (dos.equalsIgnoreCase("t"))));
        System.out.println("Film: " + filmy[ileDodanych - 1].getNazwa() + " zostal dodany pomyslnie!");
    }

    public void edytujFilm(int id) {
        Scanner sn = new Scanner(System.in);
        System.out.print("Podaj nowa nazwe filmu: ");
        String nazwa = sn.nextLine();
        filmy[id - 1].setNazwa(nazwa);
        System.out.print("Podaj nowa cene filmu: ");
        double cena = sn.nextDouble();
        filmy[id - 1].setCena(cena);
        System.out.print("Podaj nowy rok wydania filmu: ");
        int rok = sn.nextInt();
        filmy[id - 1].setRok(rok);
        System.out.print("Co to za film?: ");
        String typ = sn.nextLine();
        filmy[id - 1].setTyp(typ);
        System.out.println("Czy film jest dostepny (tak/nie): ");
        String dos = sn.nextLine();
        filmy[id - 1].setDostepne(dos == "tak");
        System.out.println("Edycja zakonczona pomyslnie");
    }

    public void wyswietlFilm(int i) {
        System.out.println(filmy[i].getId() + ". " + filmy[i].getNazwa() + " " + filmy[i].getRok() + " typ: " + filmy[i].getTyp() + " kosztuje: " + df.format(filmy[i].getCena()) + " zl, oraz jest " + filmy[i].getDostepne());
    }

    public void wyswietlListe() {
        for (int i = 0; i < (ileDodanych); i++) {
            System.out.println(filmy[i].getId() + ". " + filmy[i].getNazwa() + " jest " + filmy[i].getDostepne());
        }
        System.out.println("");
    }

    //zarzadzanie klientami
    public void dodajKlienta() {
        System.out.println("Dodawanie klienta");
        Scanner sn = new Scanner(System.in);
        int id = iluKlientow+1;
        System.out.print("Podaj imie: ");
        String imie = sn.nextLine();
        System.out.print("Podaj nazwisko: ");
        String nazwisko = sn.nextLine();
        klienci[iluKlientow] = new Klient(id, imie, nazwisko);
        System.out.println("Operacja zakonczona sukcesem");
        iluKlientow++;
    }

    public void usunKlienta(int id) {
        if (klienci[id - 1].getIleWypozyczonych() == 0) {
            for (int i = (id - 1); i < (iluKlientow - 1); i++) {
                klienci[i] = klienci[i + 1];
            }
            iluKlientow--;
            System.out.println("Operacja zakonczona sukcesem");
        } else {
            System.out.println("Klient jeszcze nie zwrocil wszystkich filmow.\n Operacja niepowiodla sie");
        }
    }

    public void wyswietlKlienta(int j) {
        System.out.println("Klient " + klienci[j].getId() + ". " + klienci[j].getImie() + " " + klienci[j].getNazwisko() + ", saldo klienta wynosi: " + df.format(klienci[j].getSaldo()) + "zl, oraz ma wypozyczone " + klienci[j].getIleWypozyczonych() + " pozycje. \n Sa to:");
        if (klienci[j].getIleWypozyczonych() != 0) {
            for (int i = 0; i < 3; i++) {
                if (klienci[j].getWypozyczone(i) != 0) {
                    wyswietlFilm(klienci[j].getWypozyczone(i) - 1);
                }
            }
        } else {
            System.out.println("Nie ma żadnych pozycji.");
        }
        System.out.println("");
    }

    public void wyswietlKlientow() {
        for (int i = 0; i < iluKlientow; i++) {
            System.out.println(klienci[i].getId() + ". " + klienci[i].getImie() + " " + klienci[i].getNazwisko());
        }
    }

    //dzialanie wypozyczalni
    public void wypozyczenie(int id, int wybor) {
        if(!filmy[wybor-1].isDostepne()){
            System.out.println("Operacja zakonczona niepowodzeniem, film jest niedostepny");
            return;
        }
        if ((klienci[id].getIleWypozyczonych() < 3) && (klienci[id].getSaldo() >= filmy[wybor - 1].getCena())) {
            klienci[id].setWypozyczone(klienci[id].getIleWypozyczonych(), wybor);
            klienci[id].ileWypozyczonychplus();
            klienci[id].setSaldo((klienci[id].getSaldo() - filmy[wybor - 1].getCena()));
            filmy[wybor - 1].setDostepne(false);
            System.out.println("Operacja zakonczona sukcesem.");
        } else if ((klienci[id].getIleWypozyczonych() >= 3) && (klienci[id].getSaldo() > filmy[wybor].getCena())) {
            System.out.println("Operacja zakonczona niepowodzeniem, osiagnieto limit wypozyczonych filmow.");
        } else if ((klienci[id].getIleWypozyczonych() < 3) && (klienci[id].getSaldo() < filmy[wybor].getCena())) {
            System.out.println("Operacja zakonczona niepowodzeniem, zbyt niskie saldo.");
        } else if ((klienci[id].getIleWypozyczonych() >= 3) && (klienci[id].getSaldo() < filmy[wybor].getCena())) {
            System.out.println("Operacja zakonczona niepowodzeniem, zaden z warunkow nie zostal spelniony.");
        }
    }

    public void zwrot(int id, int wybor) {
        for (int i = 0; i < 3; i++) {
            if (klienci[id].getWypozyczone(i) == filmy[wybor - 1].getId()) {
                klienci[id].setSaldo(klienci[id].getSaldo() + filmy[wybor - 1].getCena());
                klienci[id].setWypozyczone(i, 0);
                klienci[id].ileWypozyczonychminus();
                filmy[wybor - 1].setDostepne(true);
                System.out.println("Operacja zakonczona sukcesem");
            }
        }
    }

    //sortowanie i filtry
    public void filtry(int wybor) {
        Scanner sn = new Scanner(System.in);
        if (wybor == 1) {
            System.out.print("Podaj maksymalna cene: ");
            double cena = sn.nextDouble();
            System.out.println("Filmy odpowiadajace kryteriom to: ");
            for (int i = 0; i < ileDodanych; i++) {
                if (filmy[i].getCena() < cena)
                    wyswietlFilm(i);
            }
        } else if (wybor == 3) {
            System.out.print("Podaj rok z ktorego chcesz wyswietlic filmy: ");
            int rok = sn.nextInt();
            System.out.println("Filmy odpowiadajace kryteriom to: ");
            for (int i = 0; i < ileDodanych; i++) {
                if (filmy[i].getRok() == rok)
                    wyswietlFilm(i);
            }
            System.out.println("");
        } else if (wybor == 4) {
            System.out.print("Podaj rok przed ktorym powstaly filmy chcesz wyswietlic ");
            int rok = sn.nextInt();
            System.out.println("Filmy odpowiadajace kryteriom to: ");
            for (int i = 0; i < ileDodanych; i++) {
                if (filmy[i].getRok() < rok)
                    wyswietlFilm(i);
            }
            System.out.println("");
        } else if (wybor == 5) {
            System.out.print("Podaj rok po którym powstaly filmy ktore chcesz wyswietlic ");
            int rok = sn.nextInt();
            System.out.println("Filmy odpowiadajace kryteriom to: ");
            for (int i = 0; i < ileDodanych; i++) {
                if (filmy[i].getRok() > rok)
                    wyswietlFilm(i);
            }
            System.out.println("");
        } else if (wybor == 2) {
            System.out.print("Podaj minimalna cene: ");
            double cena = sn.nextDouble();
            System.out.println("Filmy odpowiadajace kryteriom to: ");
            for (int i = 0; i < ileDodanych; i++) {
                if (filmy[i].getCena() > cena)
                    wyswietlFilm(i);
            }
        }
    }

    public void wyszukajFilm(String szukany) {
        szukany.toLowerCase();
        System.out.println("Wyniki wyszukiwania: ");
        System.out.println("");
        if ((szukany.equals("przygodowy")) || (szukany.equals("akcji")) || (szukany.equals("horror"))) {
            for (int i = 0; i < (ileDodanych); i++) {
                if (szukany.equals(filmy[i].getTyp())) {
                    wyswietlFilm(i);
                }
            }
        } else if (szukany.length() == 1) {
            for (int i = 0; i < (ileDodanych); i++) {
                if (szukany.charAt(0) == filmy[i].getNazwa().toLowerCase().charAt(0)) {
                    wyswietlFilm(i);
                }
            }
        } else if (szukany.length() > 1) {
            for (int i = 0; i < (ileDodanych); i++) {
                int x = 0;
                do {
                    if (szukany.charAt(x) != filmy[i].getNazwa().toLowerCase().charAt(x)) {
                        break;
                    }
                    x++;
                    if (x == szukany.length()) {
                        wyswietlFilm(i);
                    }
                } while (x < szukany.length());
            }
        }
    }

    public void sortujFilmy(int i) {
        if (i == 1) {
            System.out.println("Od najstarszych:");
            int y = 1950;
            do {
                for (int j = 0; j < (ileDodanych); j++) {
                    if (filmy[j].getRok() == y) {
                        wyswietlFilm(j);
                    }
                }
                y++;
            } while (y < 2022);
        }
        if (i == 2) {
            System.out.println("Od najmlodszych:");
            int y = 2022;
            do {
                for (int j = 0; j < (ileDodanych); j++) {
                    if (filmy[j].getRok() == y) {
                        wyswietlFilm(j);
                    }

                }
                y--;
            } while (y > 1950);
        }
        if (i == 3) {
            System.out.println("Od najdrozszych:");
            Film[] posortowane = new Film[ileDodanych];
            for (int j = 0; j < (ileDodanych); j++) {
                Film f = new Film(0, "", 50, 0, "", false);
                posortowane[j] = f;
            }
            for (int j = 0; j < (ileDodanych); j++) {
                for (int k = 0; k < posortowane.length; k++) {
                    if ((posortowane[k].getCena() < filmy[j].getCena()) && (posortowane[k]).getCena() != 50) {
                        for (int l = 0; l < ((posortowane.length - 1) - k); l++) {
                            posortowane[(posortowane.length - 1) - l] = posortowane[(posortowane.length - 1) - l - 1];
                        }
                        posortowane[k] = filmy[j];
                        break;
                    }
                    if (posortowane[k].getCena() == 50) {
                        posortowane[k] = filmy[j];
                        break;
                    }
                }

            }
            for (int k = 0; k < (ileDodanych); k++) {
                System.out.println(posortowane[k].getId() + ". " + posortowane[k].getNazwa() + " " + posortowane[k].getRok() + " typ: " + posortowane[k].getTyp() + " kosztuje: " + posortowane[k].getCena() + " zl, oraz jest " + posortowane[k].getDostepne());
            }

        }
        if (i == 4) {
            System.out.println("Od najtanszych:");
            Film[] posortowane = new Film[ileDodanych];
            for (int j = 0; j < (ileDodanych); j++) {
                Film f = new Film(0, "", 0, 0, "", false);
                posortowane[j] = f;

            }
            for (int j = 0; j < (ileDodanych); j++) {
                for (int k = 0; k < posortowane.length; k++) {
                    if ((posortowane[k].getCena() > filmy[j].getCena()) && (posortowane[k]).getCena() != 0) {
                        for (int l = 0; l < ((posortowane.length - 1) - k); l++) {
                            posortowane[(posortowane.length - 1) - l] = posortowane[(posortowane.length - 1) - l - 1];
                        }
                        posortowane[k] = filmy[j];
                        break;
                    }
                    if (posortowane[k].getCena() == 0) {
                        posortowane[k] = filmy[j];
                        break;
                    }
                }

            }
            for (int k = 0; k < (ileDodanych); k++) {
                System.out.println(posortowane[k].getId() + ". " + posortowane[k].getNazwa() + " " + posortowane[k].getRok() + " typ: " + posortowane[k].getTyp() + " kosztuje: " + posortowane[k].getCena() + " zl, oraz jest " + posortowane[k].getDostepne());
            }

        }
    }

    public void panelKlienta() {
        Scanner sn = new Scanner(System.in);
        int x;
        int selection = 20;
        System.out.println("\nDostępne dla Panstwa opcje to:");
        System.out.println("\n1. Wyswietl wszystkie filmy\n2. Wyswietl konkretny film\n3. Wyszukaj film \n4. Sortowanie filmow\n5. Filtry\n");
        while (selection != 0) {
            if (selection == 0) {
                return;
            }
            try {
                System.out.println("");
                System.out.print("Inna dowolna liczba, wyswietl opcje jeszcze raz\n0. Powrot do glownego menu\nCo chciałbys zrobic?: ");
                selection = sn.nextInt();
                switch (selection) {
                    case 1:
                        wyswietlListe();
                        break;
                    case 2:
                        System.out.println("Ktory film chcesz wyswietlic?(Podaj indeks): ");
                        x = sn.nextInt();
                        wyswietlFilm((x - 1));
                        break;
                    case 3:
                        System.out.println("Wyszukaj film:");
                        sn.nextLine();
                        String szukany = sn.nextLine();
                        wyszukajFilm(szukany);
                        break;
                    case 4:
                        System.out.println("Sortowanie: od najstarszych(1), od najmlodszych(2), od najdrozszych(3), od najtanszych(4)");
                        System.out.println("Proszę wybrać tryb sortowania: ");
                        x = sn.nextInt();
                        sortujFilmy(x);
                        break;
                    case 5:
                        System.out.print("Wybierz opcje filtrowania\ncena: 1.ponizej 2.powyzej\nrok: 3. konkretny rok 4. powstale przed 5. powstale po\nTwoj wybor:");
                        x = sn.nextInt();
                        filtry(x);
                        break;
                    default:
                        System.out.println("Opcje:\n1. Wyswietl wszystkie filmy\n2. Wyswietl konkretny film\n3. Wyszukaj film \n4. Sortowanie filmow\n5. Filtry\n");
                        break;
                    case 0:
                        return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Bledne dane, powrot do menu");
                sn.next();
            }
        }
    }

    public void panelSprzedawcy() throws InterruptedException {
        System.out.println("Witamy w panelu obslugi klienta");
        System.out.println("Prosze sie zalogowac");
        System.out.print("login: ");
        Scanner s = new Scanner(System.in);
        String lgn = "Pracownik";
        int x;
        int y;
        int selection = 20;
        for (int i = 0; i < lgn.length(); i++) {
            TimeUnit.MILLISECONDS.sleep(350);
            System.out.print(lgn.charAt(i));
        }
        System.out.print("\nhaslo: ");
        for (int i = 0; i < 8; i++) {
            TimeUnit.MILLISECONDS.sleep(350);
            System.out.print("*");
        }
        System.out.println("\nZalogowano pomyslnie! ;)");
        System.out.println("Dostepne opcje to: ");
        System.out.println("\n1. Wyswietl wszystkich klientow\n2. Wyswietl konkretnego klienta\n3. Wypozyczenie \n4. Zwrot\n5. Dodanie filmu\n6. Edycja filmu\n7. Usuniecie filmu\n8. Dodanie klienta\n9. Usuniecie klienta\n");
        while (selection != 0) {
            if (selection == 0) {
                return;
            }
            try {
                System.out.print("Inna dowolna liczba, wyswietl wszystkie opcje jeszcze raz\n0. Powrot do glownego menu\nProsze wybrac opcje: ");
                selection = s.nextInt();
                switch (selection) {
                    case 1:
                        wyswietlKlientow();
                        break;
                    case 2:
                        System.out.println("Którego klienta chcesz wyswietlic?(Podaj indeks): ");
                        x = s.nextInt();
                        wyswietlKlienta((x - 1));
                        break;
                    case 3:
                        System.out.print("Ktory klient wypozycza?(Podaj indeks): ");
                        x = s.nextInt();
                        System.out.print("Ktory film jest wypozyczany?(Podaj indeks): ");
                        y = s.nextInt();
                        wypozyczenie((x - 1), (y));
                        break;
                    case 4:
                        System.out.print("Który klient zwraca?(Podaj indeks):");
                        x = s.nextInt();
                        System.out.print("Ktory film jest zwracany?(Podaj indeks):");
                        y = s.nextInt();
                        zwrot(x - 1, y);
                        break;
                    case 5:
                        dodajFilm();
                        break;
                    case 6:
                        System.out.println("Który film chcesz edytowac?(Podaj indeks): ");
                        x = s.nextInt();
                        edytujFilm(x);
                        break;
                    case 7:
                        System.out.print("Który film chcesz usunac?(Podaj indeks):");
                        x = s.nextInt();
                        usunFilm(x - 1);
                        break;
                    case 8:
                        dodajKlienta();
                        break;
                    case 9:
                        System.out.print("Ktorego klienta chcesz usunac?(Podaj indeks):");
                        x = s.nextInt();
                        usunKlienta(x);
                        break;
                    default:
                        System.out.println("Dostepne opcje to: ");
                        System.out.println("\n1. Wyswietl wszystkich klientow\n2. Wyswietl konkretnego klienta\n3. Wypozyczenie \n4. Zwrot\n5. Dodanie filmu\n6. Edycja filmu\n7. Usuniecie filmu\n8. Dodanie klienta\n9. Usuniecie klienta\n");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Bledne dane, powrot do panelu\n");
                s.next();
            }
        }
    }
}
