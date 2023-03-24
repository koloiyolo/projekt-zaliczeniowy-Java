package com.company;

import java.io.Serializable;

public class Klient implements Serializable {
    private int id;
    private String imie;
    private String nazwisko;
    private int[] wypozyczone;
    private int ileWypozyczonych;
    private double saldo;
    public Klient(int id, String imie, String nazwisko){
        this.id=id;
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.ileWypozyczonych=0;
        this.wypozyczone = new int[]{0,0,0};
        this.saldo = 60;
    }
    public Klient(int id, String imie, String nazwisko, int ile, int[] wypozyczone, double saldo){
        this.id=id;
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.ileWypozyczonych=ile;
        this.wypozyczone = wypozyczone;
        this.saldo=saldo;

    }

    public int getId(){return id;}
    public String getImie(){return imie;}
    public String getNazwisko() {return nazwisko;}
    public int getIleWypozyczonych(){return ileWypozyczonych;}
    public int getWypozyczone(int i){return wypozyczone[i];}

    public void setWypozyczone(int j, int i){
        wypozyczone[j]=i;
    }
    public double getSaldo(){return saldo;}
    public void setSaldo(double saldo){this.saldo=saldo;}
    public void ileWypozyczonychplus(){
        this.ileWypozyczonych++;
    }
    public void ileWypozyczonychminus(){
        this.ileWypozyczonych--;
    }
}
