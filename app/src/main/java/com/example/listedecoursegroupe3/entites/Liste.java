package com.example.listedecoursegroupe3.entites;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
@DatabaseTable(tableName = "Liste")
public class Liste {
    @DatabaseField( columnName = "idListe", generatedId = true )
    private int idListe;

    @DatabaseField( columnName = "libelle" )
    private String libelle;

    public Liste() {
    }

    public Liste(String libelle) {
        this.libelle = libelle;
    }

    public int getIdListe() {
        return idListe;
    }

    public void setIdListe(int idListe) {
        this.idListe = idListe;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
