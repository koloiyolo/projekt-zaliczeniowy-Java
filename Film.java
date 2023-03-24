package com.company;

import java.io.Serializable;

public class Film implements Serializable {
    private int id;
    private String nazwa;
    private double cena;
    private int rok;
    private String typ;
    private boolean dostepne;

    public Film(int id, String nazwa, double cena, int rok, String typ, boolean dostepne ) {
        this.id = id;
        this.nazwa = nazwa;
        this.cena =cena;
        this.rok = rok;
        this.typ = typ;
        this.dostepne =dostepne;
    }
    //gettery
    public int getId(){return id;}
    public String getNazwa(){return nazwa;}
    public double getCena(){return cena;}
    public int getRok(){return rok;}
    public String getTyp(){return typ;}
    public boolean isDostepne(){
        return dostepne;
    }
    public String getDostepne(){if(isDostepne()){return "dostepny";}else{return"niedostepny";}}
    //settery
    public void setId(int id){this.id=id;}
    public void setRok(int rok){this.rok=rok;}
    public void setNazwa(String nazwa){this.nazwa=nazwa;}
    public void setTyp(String typ){this.typ=typ;}
    public void setCena(double cena){this.cena=cena;}
    public void setDostepne(boolean dostepne){this.dostepne=dostepne;}

}