package com.example.listedecoursegroupe3.entites;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
@DatabaseTable(tableName = "Recette")
public class Recette {

    @DatabaseField( columnName = "idRecette", generatedId = true )
    private int idRecette;

    @DatabaseField( columnName = "libelle" )
    private String libelle;

    public int getIdRecette() {
        return idRecette;
    }

    public Recette(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Recette() {
    }
}
