package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reifen")
public class Reifen {
    @Id
    private String id;
    private String name;
    private double breite;
    private double hoehe;
    private double zoll;
    private String beschreibung;

    public Reifen () {

    }

    public Reifen (String name, double breite, double hoehe, double zoll, String beschreibung) {
        this.name = name;
        this.breite = breite;
        this.hoehe = hoehe;
        this.zoll = zoll;
        this.beschreibung = beschreibung;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBreite() {
        return breite;
    }

    public void setBreite(double breite) {
        this.breite = breite;
    }

    public double getHoehe() {
        return hoehe;
    }

    public void setHoehe(double hoehe) {
        this.hoehe = hoehe;
    }

    public double getZoll() {
        return zoll;
    }

    public void setZoll(double zoll) {
        this.zoll = zoll;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    @Override
    public String toString() {
        return "Reifen{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", breite=" + breite +
                ", hoehe=" + hoehe +
                ", zoll=" + zoll +
                ", beschreibung='" + beschreibung + '\'' +
                '}';
    }
}


