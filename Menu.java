package com.company;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public void menu() {
        Scanner s = new Scanner(System.in);
        Wypozyczalnia f = new Wypozyczalnia();
        //deserializacja
        String plik = "klienci.bin";
        File file1 = new File(plik);
        if (file1.exists()) {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("klienci.bin"))) {
                Klient[] desKlient = (Klient[]) inputStream.readObject();
                f.dK(desKlient);
            } catch (Exception e) {
                System.out.println("Blad wczytywania klientow" + e);
                f.zaladujKlientow();
            }
        } else {
            f.zaladujKlientow();
        }
        plik = "filmy.bin";
        File file2 = new File(plik);
        if (file2.exists()) {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("filmy.bin"))) {
                Film[] des = (Film[]) inputStream.readObject();
                f.dF(des);
            } catch (Exception e) {
                System.out.println("Blad wczytywania filmow");
                f.zaladujFilmy();
            }
        } else {
            f.zaladujFilmy();
        }
        //koniec deserializacji
        //wczytywanie z pliku tekstowego
//        File file1 = new File("filmy.txt");
//        File file2 = new File("klienci.txt");
//        if(file1.exists()){
//            try{
//                Scanner sn = new Scanner(file1);
//                String line ="";
//                int i=1;
//                while(sn.hasNextLine()){
//                    line=sn.nextLine();
//                    String[] tab=line.split("/");
//                    f.dodajF(i,tab[0],Double.parseDouble(tab[1]), Integer.parseInt(tab[2]), tab[3], Boolean.parseBoolean(tab[4]) );
//                    i++;
//                    }
//            }catch(Exception e){
//                    System.out.println("Wczytywaanie filmow z pliku nie powiodlo sie"+e);
//            }
//        }else{
//            f.zaladujFilmy();
//        }
//        if(file2.exists()){
//            try{
//                Scanner sn = new Scanner(file2);
//                String line ="";
//                int i=1;
//                while(sn.hasNextLine()) {
//                    line = sn.nextLine();
//                    String[] tab = line.split("/");
//                    if(tab.length==2){
//                        f.dodajK(tab[0],tab[1]);
//                    }else{
//                    f.dodajK(i, tab[0],tab[1],Integer.parseInt(tab[2]),Integer.parseInt(tab[3]),Integer.parseInt(tab[4]),Integer.parseInt(tab[5]),Double.parseDouble(tab[6]));
//                    i++;
//                    }
//                }
//            }catch(Exception e){
//                System.out.println("Wczytywanie klientow z pliku nie powiodlo sie"+e);
//            }
//        }else {
//            f.zaladujKlientow();
//        }
//      //koniec wczytywania z pliku tekstowego
        int selection = 20;
        System.out.println("           Witamy w wypożyczalni filmow");
        System.out.println("      Co moglibysmy dla Panstwa zrobic?\n");
        System.out.println("Opcje:\n1. Panel klienta\n2. Panel administracji oraz obslugi klienta\n0. Wyjdz oraz zapisz zmiany\nCo chciałbys zrobic?:");
        while (selection != 0) {
            try {
                selection = s.nextInt();
                switch (selection) {
                    case 1:
                        f.panelKlienta();
                        System.out.println("\nWitamy z powrotem w menu glownym :D");
                        System.out.println("Dostepne opcje:\n1. Panel klienta\n2. Panel administracji oraz obslugi klienta\n0. Wyjdz oraz zapisz zmiany\nCo chciałbys zrobic?: ");
                        break;
                    case 2:
                        try {
                            f.panelSprzedawcy();
                        } catch (InterruptedException e) {
                        }
                        System.out.println("\nWitamy z powrotem w menu glownym :D");
                        System.out.println("Dostepne opcje:\n1. Panel klienta\n2. Panel administracji oraz obslugi klienta\n0. Wyjdz oraz zapisz zmiany\nCo chciałbys zrobic?: ");
                        break;
                    default:
                        System.out.println("Dostepne opcje:\n1. Panel klienta\n2. Panel administracji oraz obslugi klienta\n0. Wyjdz oraz zapisz zmiany\nCo chciałbys zrobic?: ");
                        break;
                    case 0:
                        System.out.println("\nDziekujemy za skorzystanie z naszego serwisu");
                        System.out.println("                Do zobaczenia!");
                        //zapis do pliku tekstowego
                        try {
                            File file = new File("PodsumowanieSesji.txt");
                            if (file.exists()) {
                                file.delete();
                                file.createNewFile();
                            } else {
                                file.createNewFile();
                            }
                            FileWriter fw = new FileWriter(file);
                            fw.write("             Podsumowanie sesji\n\n");
                            fw.write("Lista klientow:\n");

                            for (int i = 0; i < f.getIluKlientow(); i++) {
                                fw.write(f.toSKlient(i));
                                fw.write("\n");
                            }
                            fw.write("\nLista filmow:\n");
                            for (int i = 0; i < f.getIleDodanych(); i++) {
                                fw.write(f.toSFilm(i));
                                fw.write("\n");
                            }
                            fw.close();
                        } catch (Exception e) {
                            System.out.println("Wystapil blad " + e);
                        }
                        //zapis do pliku w formacie mozliwym do odczytania przez program
//                    try{
//                        if(file1.exists()){
//                            file1.delete();
//                            file1.createNewFile();
//                        }else{
//                            file1.createNewFile();
//                        }
//                        if(file2.exists()){
//                            file2.delete();
//                            file2.createNewFile();
//                        }else{
//                            file2.createNewFile();
//                        }
//                        FileWriter fw1 = new FileWriter(file1,true);
//                        FileWriter fw2 = new FileWriter(file2,true);
//                        for (int i = 0; i < (f.getIluKlientow()); i++) {
//                            fw2.write(f.getKlient(i)+"\n");
//                        }
//                        for (int i = 0; i < (f.getIleDodanych()); i++) {
//                            fw1.write(f.getFilm(i)+"\n");
//                        }
//                        fw1.close();
//                        fw2.close();
//                        System.out.println("Dane zapisane pomyslnie");
//                    }catch(Exception e){
//                        System.out.println("Wystapil blad");
//                        file1.delete();
//                        file2.delete();
//                    }
                        //zakonczenie zapisu
                        //serializacja
                        Klient[] serKlient = f.getKlienci();
                        serKlient[3].getImie();
                        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("klienci.bin"))) {
                            outputStream.writeObject(serKlient);
                            outputStream.flush();
                        } catch (Exception e) {
                            System.out.println("zapis klientow nie powiodl sie");
                        }
                        Film[] ser = f.getFilmy();
                        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("filmy.bin"))) {
                            outputStream.writeObject(ser);
                            outputStream.flush();
                        } catch (Exception e) {
                            System.out.println("zapis filmow nie powiodl sie");
                        }
                        //koniec serializacji
                }

            } catch (InputMismatchException e) {
                System.out.println("Bledne dane, prosze sprobowac ponownie");
                s.next();
            }
        }

    }
}
