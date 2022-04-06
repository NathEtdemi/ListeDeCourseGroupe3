package com.example.listedecoursegroupe3.entites;

import com.j256.ormlite.field.DatabaseField;

public class Produit_Recette
{
    @DatabaseField( columnName = "idProduit_Recette", generatedId = true )
    private int idProduit_Recette;

    @DatabaseField( columnName="libelle")
    private String libelleProduit;

    @DatabaseField( columnName="quantite")
    private int quantite;

    public Produit_Recette(String libelleProduit, int quantite) {
        this.libelleProduit = libelleProduit;
        this.quantite = quantite;
    }

    public Produit_Recette() {
    }

    public int getIdProduit_Recette() {
        return idProduit_Recette;
    }

    public String getLibelleProduit() {
        return libelleProduit;
    }

    public void setLibelleProduit(String libelleProduit) {
        this.libelleProduit = libelleProduit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
