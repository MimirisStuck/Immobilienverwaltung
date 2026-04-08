package com.example.immobilienverwaltung.model;

public class Immobilie {

    private Long id;
    private String bezeichnung;

    private String strasse;
    private String hausnummer;
    private String plz;
    private String ort;

    public Immobilie() {}

    public Immobilie(Long id, String bezeichnung, String strasse, String hausnummer, String plz, String ort) {
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.plz = plz;
        this.ort = ort;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getBezeichnung() { return bezeichnung; }
    public void setBezeichnung(String bezeichnung) { this.bezeichnung = bezeichnung; }

    public String getStrasse() { return strasse; }
    public void setStrasse(String strasse) { this.strasse = strasse; }

    public String getHausnummer() { return hausnummer; }
    public void setHausnummer(String hausnummer) { this.hausnummer = hausnummer; }

    public String getPlz() { return plz; }
    public void setPlz(String plz) { this.plz = plz; }

    public String getOrt() { return ort; }
    public void setOrt(String ort) { this.ort = ort; }
}