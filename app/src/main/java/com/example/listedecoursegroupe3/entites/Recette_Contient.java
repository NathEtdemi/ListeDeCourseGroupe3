package com.example.listedecoursegroupe3.entites;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
@DatabaseTable(tableName = "Recette_Contient")
public class Recette_Contient {
    @DatabaseField( canBeNull = false, foreign = true, foreignColumnName = "idRecette", foreignAutoCreate = true )
    private Recette recette;
    @DatabaseField( canBeNull = false, foreign = true, foreignColumnName = "idProduit", foreignAutoCreate = true )
    private Produit produit;
    @DatabaseField( columnName="quantite")
    private String quantite;

    public Recette_Contient(Recette recette, Produit produit, String quantite) {
        this.recette = recette;
        this.produit = produit;
        this.quantite = quantite;
    }

    public Recette getRecette() {
        return recette;
    }

    public void setRecette(Recette recette) {
        this.recette = recette;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public Recette_Contient() {
    }
}
