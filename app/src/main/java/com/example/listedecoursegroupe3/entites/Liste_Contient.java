package com.example.listedecoursegroupe3.entites;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
@DatabaseTable(tableName = "Liste_Contient")
public class Liste_Contient {
    @DatabaseField( canBeNull = false, foreign = true, foreignColumnName = "idListe", foreignAutoCreate = true )
    private Liste liste;
    @DatabaseField( canBeNull = false, foreign = true, foreignColumnName = "idProduit", foreignAutoCreate = true )
    private Produit produit;
    @DatabaseField( columnName="quantite")
    private String quantite;

    public Liste_Contient(Liste liste, Produit produit, String quantite) {
        this.liste = liste;
        this.produit = produit;
        this.quantite = quantite;
    }

    public Liste getListe() {
        return liste;
    }

    public void setListe(Liste liste) {
        this.liste = liste;
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

    public Liste_Contient() {
    }
}
