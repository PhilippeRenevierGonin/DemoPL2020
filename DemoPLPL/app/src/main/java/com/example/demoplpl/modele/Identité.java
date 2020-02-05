package com.example.demoplpl.modele;

public class Identité {
    public String getNom() {
        return nom;
    }

    private final String nom;

    public Identité() {
        this("nom par défaut");
    }
    public Identité(String nom) {
        this.nom = nom;
    }
}
