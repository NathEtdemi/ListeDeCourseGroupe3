package com.example.listedecoursegroupe3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
        grille=findViewById(R.id.layout);
        DataBaseLinker linker = new DataBaseLinker(this);
        try
        {
            Dao<Produit, Integer> daoProduit = linker.getDao( Produit.class );
            List<Produit> produit = daoProduit.queryForAll();
            for(Produit Produits: produit)
            {
                TableRow newRow = new TableRow(this);
                TextView newText = new TextView(this);
                newText.setText(Produits.getLibelleProduit());
                Button Modifier = new Button(this);
                Modifier.setText("Modifier Produit");
                Modifier.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        Intent monIntent = new Intent(MainActivity.this, AlterProduit.class);
                        monIntent.putExtra("id", Produits.getIdProduit());
                        startActivity(monIntent);
                    }
                });
                Button Plus = new Button(this);
                TextView quantité = new TextView(this);
                quantité.setText("1");
                Button Moins = new Button(this);
                Plus.setText("+");
                Moins.setText("-");
                newRow.addView(newText);
                newRow.addView(Moins);
                newRow.addView(quantité);
                newRow.addView(Plus);
                newRow.addView(Modifier);
                grille.addView(newRow);
                Plus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        quantité.setText(Integer.toString(Integer.parseInt((String) quantité.getText())+1));
                    }
                });
                Moins.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        Log.i("bouton", String.valueOf(Integer.parseInt((String) quantité.getText())));
                        if (Integer.parseInt((String) quantité.getText())!=1)
                        {
                            quantité.setText(Integer.toString(Integer.parseInt((String) quantité.getText()) - 1));
                        }
                    }
                });
            }
            TableRow Row = new TableRow(this);
            Button Liste = new Button(this);
            Button AjouterProduit = new Button(this);
            Button Recette = new Button(this);
            Liste.setText("Voir liste");
            AjouterProduit.setText("Ajouter Produit");
            AjouterProduit.setOnClickListener(new View.OnClickListener()
            {
              @Override
              public void onClick(View v)
              {
                  Intent monIntent = new Intent(MainActivity.this, AlterProduit.class);
                  startActivity(monIntent);
              }
            });
            Recette.setText("Recette");
            Recette.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent monIntent = new Intent(MainActivity.this, RecetteController.class);
                    startActivity(monIntent);
                }
            });
            Row.addView(Liste);
            Row.addView(AjouterProduit);
            Row.addView(Recette);
            grille.addView(Row);
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        linker.close();
    }
}