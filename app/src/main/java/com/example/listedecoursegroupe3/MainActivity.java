package com.example.listedecoursegroupe3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
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
    private ScrollView grille;
    private Button Liste;
    private Button AddProduit;
    private Button Recette;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grille=findViewById(R.id.layout);
        Liste = findViewById(R.id.liste);
        AddProduit = findViewById(R.id.Add_Product);
        Recette = findViewById(R.id.Recette);
        DataBaseLinker linker = new DataBaseLinker(this);
        try
        {
            Dao<Produit, Integer> daoProduit = linker.getDao( Produit.class );
            List<Produit> produit = daoProduit.queryForAll();
            LinearLayout firstLinearLayout = new LinearLayout(this);
            firstLinearLayout.setOrientation(LinearLayout.VERTICAL);
            for(Produit Produits: produit)
            {

                LinearLayout.LayoutParams paramButton = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        0.3f
                );
                LinearLayout produitLinearLayout =  new LinearLayout(this);
                produitLinearLayout.setGravity(Gravity.CENTER_VERTICAL);
                TextView newText = new TextView(this);
                newText.setText(Produits.getLibelleProduit());
                newText.setLayoutParams(paramButton);
                newText.setTextSize(15.0F);
                newText.setTypeface(Typeface.DEFAULT_BOLD);
                ImageButton Modifier = new ImageButton(this);
                Modifier.setLayoutParams(paramButton);
                Modifier.setBackground(null);
                Modifier.setImageResource(R.mipmap.ic_edit);
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
                SupprimerProduit.setImageResource(R.mipmap.ic_clear);
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
                        ((ViewGroup) produitLinearLayout.getParent()).removeView(produitLinearLayout);
                    }
                });

                ImageButton Plus = new ImageButton(this);
                Plus.setImageResource(R.drawable.ic_baseline_add_24);
                Plus.setBackground(null);
                TextView quantité = new TextView(this);
                quantité.setText("1");
                ImageButton Moins = new ImageButton(this);
                Moins.setImageResource(R.drawable.ic_baseline_remove_24);
                Moins.setBackground(null);
                produitLinearLayout.addView(newText);
                produitLinearLayout.addView(Moins);
                produitLinearLayout.addView(quantité);
                produitLinearLayout.addView(Plus);
                produitLinearLayout.addView(Modifier);
                produitLinearLayout.addView(SupprimerProduit);
                firstLinearLayout.addView(produitLinearLayout);
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
                        if (Integer.parseInt((String) quantité.getText())!=1)
                        {
                            quantité.setText(Integer.toString(Integer.parseInt((String) quantité.getText()) - 1));
                        }
                    }
                });
            }
            grille.addView(firstLinearLayout);
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
            Liste.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent monIntent = new Intent(MainActivity.this, ViewList.class);
                    startActivity(monIntent);
                }
            });
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }
}