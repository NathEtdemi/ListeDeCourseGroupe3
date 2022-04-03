package com.example.listedecoursegroupe3.entites;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
@DatabaseTable(tableName = "Produit")
public class Produit {

    @DatabaseField( columnName = "idProduit", generatedId = true )
    private int idProduit;

    @DatabaseField
    private String libelleProduit;

    @DatabaseField
    private int quantiteProduit;

    @DatabaseField
    private float prixProduit;

    public Produit() {
    }

    public Produit(int idProduit, String libelleProduit, int quantiteProduit, float prixProduit) {
        this.idProduit = idProduit;
        this.libelleProduit = libelleProduit;
        this.quantiteProduit = quantiteProduit;
        this.prixProduit = prixProduit;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public String getLibelleProduit() {
        return libelleProduit;
    }

    public int getQuantiteProduit() {
        return quantiteProduit;
    }

    public float getPrixProduit() {
        return prixProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public void setLibelleProduit(String libelleProduit) {
        this.libelleProduit = libelleProduit;
    }

    public void setQuantiteProduit(int quantiteProduit) {
        this.quantiteProduit = quantiteProduit;
    }

    public void setPrixProduit(float prixProduit) {
        this.prixProduit = prixProduit;
    }
}
