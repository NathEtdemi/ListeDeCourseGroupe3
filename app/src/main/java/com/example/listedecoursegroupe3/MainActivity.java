package com.example.listedecoursegroupe3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.listedecoursegroupe3.entites.Produit;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private TableLayout grille;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBaseLinker linker = new DataBaseLinker(this);
        try
        {
            Dao<Produit, Integer> daoProduit = linker.getDao( Produit.class );
            List<Produit> produit = daoProduit.queryForAll();
            Log.i("bouton", "On me clique dessus !");
            for(Produit Produits: produit)
            {
                Log.i("bouton", "On me clique dessus !");
                TableRow newRow = new TableRow(this);
                TextView newText = new TextView(this);
                newText.setText(Produits.getLibelleProduit());
                Button ajouter = new Button(this);
                ajouter.setText("Ajouter au panier");
                Button Plus = new Button(this);
                TextView quantité = new TextView(this);
                Button Moins = new Button(this);
                Plus.setText("Plus");
                Moins.setText("Moins");
                newRow.addView(newText);
                newRow.addView(ajouter);
                grille=findViewById(R.id.layout);
                grille.addView(newRow);
                ajouter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        newRow.removeView(ajouter);
                        newRow.addView(Moins);
                        newRow.addView(quantité);
                        newRow.removeView(Plus);
                    }
                });
            }
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        linker.close();
    }
}