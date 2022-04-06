package com.example.listedecoursegroupe3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.listedecoursegroupe3.entites.Produit;
import com.example.listedecoursegroupe3.entites.Produit_Recette;
import com.example.listedecoursegroupe3.entites.Recette;
import com.example.listedecoursegroupe3.entites.Recette_Contient;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DataBaseLinker extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "bdd.db";
    private static final int DATABASE_VERSION = 2;

    public DataBaseLinker( Context context ) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try
        {
            TableUtils.createTable( connectionSource, Recette.class );
            TableUtils.createTable( connectionSource, Produit.class );
            TableUtils.createTable( connectionSource, Recette_Contient.class );
            TableUtils.createTable( connectionSource, Produit_Recette.class );
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