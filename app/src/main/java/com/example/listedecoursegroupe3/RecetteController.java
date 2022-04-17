package com.example.listedecoursegroupe3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listedecoursegroupe3.entites.Produit;
import com.example.listedecoursegroupe3.entites.Recette;
import com.example.listedecoursegroupe3.entites.Recette_Contient;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class RecetteController extends AppCompatActivity
{
    private TableLayout grille;
    private Button AjoutRecette;
    private ImageButton Retour;
    private EditText name;
    private LinearLayout texte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addproduit);
        grille = findViewById(R.id.layout);
        AjoutRecette = findViewById(R.id.Valider);
        Retour = findViewById(R.id.imageView);
        texte = findViewById(R.id.first_layout);
        texte.setVisibility(View.GONE);
        name = findViewById(R.id.name);
        name.setVisibility(View.GONE);
        DataBaseLinker linker = new DataBaseLinker(this);
        Intent intent = this.getIntent();
        try
        {
            Dao<Recette, Integer> daoRecette = linker.getDao(Recette.class);
            List<Recette> Recette = daoRecette.queryForAll();
            for(Recette recette: Recette)
            {
                TableRow.LayoutParams paramButton = new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                );
                TableRow newRow = new TableRow(this);
                newRow.setGravity(Gravity.CENTER_VERTICAL);
                TextView newText = new TextView(this);
                newText.setText(recette.getLibelle());
                ImageButton Modifier = new ImageButton(this);
                Modifier.setLayoutParams(paramButton);
                Modifier.setBackground(null);
                Modifier.setImageResource(R.mipmap.ic_edit);
                Modifier.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        Intent monIntent = new Intent(RecetteController.this, AlterRecette.class);
                        monIntent.putExtra("id", recette.getIdRecette());
                        startActivity(monIntent);
                    }
                });
                ImageButton Supprimer = new ImageButton(this);
                Supprimer.setLayoutParams(paramButton);
                Supprimer.setImageResource(R.mipmap.ic_clear);
                Supprimer.setBackground(null);
                Supprimer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            daoRecette.delete(recette);
                        }
                        catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        ((ViewGroup) newRow.getParent()).removeView(newRow);
                    }
                });
                ImageButton AddCart = new ImageButton(this);
                AddCart.setLayoutParams(paramButton);
                AddCart.setImageResource(R.mipmap.ic_add);
                AddCart.setBackground(null);
                AddCart.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        try
                        {
                            Dao<Recette_Contient, Integer> daoRecette_Contient = linker.getDao(Recette_Contient.class);
                            Dao<Produit, Integer> daoProduit = linker.getDao(Produit.class);
                            List<Recette_Contient> contient = daoRecette_Contient.queryForAll();
                            List<Produit> produits = daoProduit.queryForAll();
                            for (Recette_Contient Rcontient:contient)
                            {
                                if (Rcontient.getRecette().getIdRecette()==recette.getIdRecette())
                                {
                                    Produit produit=new Produit(Rcontient.getProduit().getLibelleProduit(),Rcontient.getQuantite());
                                    Boolean Tocreate=true;
                                    for (Produit Produits: produits)
                                    {
                                        if(Produits.getLibelleProduit().equals(produit.getLibelleProduit()))
                                        {
                                            Log.e("bouton", "ici");
                                            Produits.setQuantite(produit.getQuantite()+Produits.getQuantite());
                                            daoProduit.update(Produits);
                                            Tocreate=false;
                                        }
                                    }
                                    if (Tocreate) {
                                        daoProduit.create(produit);
                                    }
                                    Intent monIntent = new Intent(RecetteController.this, MainActivity.class);
                                    startActivity(monIntent);
                                }
                            }
                        }
                        catch (SQLException throwables)
                        {
                            throwables.printStackTrace();
                        }
                        ((ViewGroup) newRow.getParent()).removeView(newRow);
                    }
                });
                newRow.addView(newText);
                newRow.addView(Modifier);
                newRow.addView(Supprimer);
                newRow.addView(AddCart);
                grille.addView(newRow);
            }
            AjoutRecette.setText("Creer une recette");
            AjoutRecette.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent monIntent = new Intent(RecetteController.this, AlterRecette.class);
                    startActivity(monIntent);
                }
            });
            Retour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent monIntent = new Intent(RecetteController.this, MainActivity.class);
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
