package com.example.listedecoursegroupe3.entites;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
@DatabaseTable(tableName = "Produit")
public class Produit {

    @DatabaseField( columnName = "idProduit", generatedId = true )
    private int idProduit;

    @DatabaseField( columnName="libelle")
    private String libelleProduit;

    @DatabaseField( columnName="quantite")
    private int quantite;

    public Produit(String libelleProduit, int quantite) {
        this.libelleProduit = libelleProduit;
        this.quantite = quantite;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Produit() {
    }

    public int getIdProduit() {
        return idProduit;
    }

    public String getLibelleProduit() {
        return libelleProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public void setLibelleProduit(String libelleProduit) {
        this.libelleProduit = libelleProduit;
    }

}
