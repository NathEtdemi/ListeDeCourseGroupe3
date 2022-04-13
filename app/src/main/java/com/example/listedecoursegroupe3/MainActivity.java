package com.example.listedecoursegroupe3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ImageButton;

import com.example.listedecoursegroupe3.entites.Produit;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private TableLayout grille;
    private Button Liste;
    private Button AddProduit;
    private Button Recette;

    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    AjoutProduitFragment ajoutProduitFragment = new AjoutProduitFragment();
    RecetteFragment recetteFragment = new RecetteFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.liste:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                        return true;
                    case R.id.AjoutProduit:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, ajoutProduitFragment).commit();
                        Intent monIntent = new Intent(MainActivity.this, AlterProduit.class);
                        startActivity(monIntent);
                        return true;
                    case R.id.Receipt:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, recetteFragment).commit();
                        Intent uneIntent = new Intent(MainActivity.this, RecetteController.class);
                        startActivity(uneIntent);
                        return true;
                }
                return false;
            }
        });

        grille=findViewById(R.id.layout);
        Liste = findViewById(R.id.listeProduit);
        AddProduit = findViewById(R.id.Add_Product);
        Recette = findViewById(R.id.Recette);
        DataBaseLinker linker = new DataBaseLinker(this);
        try
        {
            Dao<Produit, Integer> daoProduit = linker.getDao( Produit.class );
            List<Produit> produit = daoProduit.queryForAll();
            for(Produit Produits: produit)
            {
                TableRow.LayoutParams paramButton = new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                );

                TableRow newRow = new TableRow(this);
                TextView newText = new TextView(this);
                newText.setText(Produits.getLibelleProduit());
                ImageButton Modifier = new ImageButton(this);
                Modifier.setLayoutParams(paramButton);
                Modifier.setBackground(null);
                Modifier.setImageResource(R.drawable.edit);
                Modifier.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        Intent monIntent = new Intent(MainActivity.this, AlterProduit.class);
                        monIntent.putExtra("id", Produits.getIdProduit());
                        startActivity(monIntent);
                    }
                });

                ImageButton SupprimerProduit = new ImageButton(this);
                SupprimerProduit.setLayoutParams(paramButton);
                SupprimerProduit.setImageResource(R.drawable.delete);
                SupprimerProduit.setBackground(null);
                SupprimerProduit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            daoProduit.delete(Produits);
                        }
                        catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        ((ViewGroup) newRow.getParent()).removeView(newRow);
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
                newRow.addView(SupprimerProduit);
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
            AddProduit.setOnClickListener(new View.OnClickListener()
            {
              @Override
              public void onClick(View v)
              {
                  Intent monIntent = new Intent(MainActivity.this, AlterProduit.class);
                  startActivity(monIntent);
              }
            });
            Recette.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent monIntent = new Intent(MainActivity.this, RecetteController.class);
                    startActivity(monIntent);
                }
            });
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        linker.close();
    }
}