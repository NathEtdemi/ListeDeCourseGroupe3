package com.example.listedecoursegroupe3.entites;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
@DatabaseTable(tableName = "Recette_Contient")
public class Recette_Contient {
    @DatabaseField( columnName = "idRecette_Contient", generatedId = true )
    private int idRecette_Contient;
    @DatabaseField( canBeNull = false, foreign = true, foreignColumnName = "idRecette", foreignAutoCreate = true )
    private Recette recette;
    @DatabaseField( canBeNull = false, foreign = true, foreignColumnName = "idProduit_Recette", foreignAutoCreate = true )
    private Produit_Recette produit;
    @DatabaseField( columnName="quantite")
    private int quantite;

    public Recette_Contient(Recette recette, Produit_Recette produit, int quantite) {
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

    public Produit_Recette getProduit() {
        return produit;
    }

    public void setProduit(Produit_Recette produit) {
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Recette_Contient() {
    }
}
