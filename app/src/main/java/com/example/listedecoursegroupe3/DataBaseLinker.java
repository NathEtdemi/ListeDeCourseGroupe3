package com.example.listedecoursegroupe3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.listedecoursegroupe3.entites.Liste;
import com.example.listedecoursegroupe3.entites.Liste_Contient;
import com.example.listedecoursegroupe3.entites.Produit;
import com.example.listedecoursegroupe3.entites.Recette;
import com.example.listedecoursegroupe3.entites.Recette_Contient;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DataBaseLinker extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "bdd.db";
    private static final int DATABASE_VERSION = 1;

    public DataBaseLinker( Context context ) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try
        {
            TableUtils.dropTable( connectionSource, Produit.class, true );
            TableUtils.createTable( connectionSource, Liste.class );
            TableUtils.createTable( connectionSource, Recette.class );
            TableUtils.createTable( connectionSource, Produit.class );
            TableUtils.createTable( connectionSource, Liste_Contient.class );
            TableUtils.createTable( connectionSource, Recette_Contient.class );

            Dao<Liste, Integer> daoListe = this.getDao( Liste.class );
            Dao<Recette, Integer> daoRecette = this.getDao( Recette.class );
            Dao<Produit, Integer> daoProduit = this.getDao( Produit.class );
            Dao<Liste_Contient, Integer> daoListe_Contient = this.getDao( Liste_Contient.class );
            Dao<Recette_Contient, Integer> daoRecette_Contient = this.getDao( Recette_Contient.class );

            Produit produit1=new Produit("Farine");
            Produit produit2=new Produit("Lait");
            Produit produit3=new Produit("Oeuf");
            Produit produit4=new Produit("Sucre");
            Produit produit5=new Produit("Pomme");

            daoProduit.create(produit1);
            daoProduit.create(produit2);
            daoProduit.create(produit3);
            daoProduit.create(produit4);
            daoProduit.create(produit5);

            Log.i( "DATABASE", "onCreate invoked" );
        }
        catch( Exception exception )
        {
            Log.e( "DATABASE", "Can't create Database", exception );
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try
        {
            Log.i( "DATABASE", "onUpgrade invoked" );
        }
        catch( Exception exception )
        {
            Log.e( "DATABASE", "Can't upgrade Database", exception );
        }
    }
}