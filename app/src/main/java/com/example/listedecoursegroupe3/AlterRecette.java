package com.example.listedecoursegroupe3;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listedecoursegroupe3.entites.Produit;
import com.example.listedecoursegroupe3.entites.Produit_Recette;
import com.example.listedecoursegroupe3.entites.Recette;
import com.example.listedecoursegroupe3.entites.Recette_Contient;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class AlterRecette extends AppCompatActivity
{
    private TableLayout grille;
    private Button Valider;
    private ImageButton AddIngredient;
    private ImageButton Retour;
    private EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alterrecette);
        grille = findViewById(R.id.layout);
        Valider = findViewById(R.id.Valider);
        AddIngredient = findViewById(R.id.imageButton2);
        Retour = findViewById(R.id.imageView);
        name = findViewById(R.id.name);
        DataBaseLinker linker = new DataBaseLinker(this);
        Intent intent = this.getIntent();
        TableRow.LayoutParams paramButton = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT,
                1f
        );

        try {
            Dao<Recette, Integer> dao = linker.getDao(Recette.class);
            Dao<Produit_Recette, Integer> daoProduit_Recette = linker.getDao(Produit_Recette.class);
            Dao<Recette_Contient, Integer> daoRecette_Contient = linker.getDao(Recette_Contient.class);
            Recette recette = dao.queryForId(intent.getIntExtra("id", 0));
            if (recette == null) {
                Valider.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (String.valueOf(name.getText()) != "") {
                            try
                            {
                                Recette recettes = new Recette(String.valueOf(name.getText()));
                                dao.create(recettes);
                                List<Recette> nbrecette = dao.queryForAll();
                                int idRecette=nbrecette.get(nbrecette.size()-1).getIdRecette();
                                for (int x = 3; x < grille.getChildCount(); x++)
                                {
                                    Log.i("bouton", String.valueOf(x));
                                    TableRow myrow= (TableRow) grille.getChildAt(x);
                                    EditText nom = (EditText) myrow.getChildAt(0);
                                    TextView quantité = (TextView) myrow.getChildAt(2);
                                    Produit_Recette produit=new Produit_Recette(String.valueOf(nom.getText()));
                                    Recette_Contient contient= new Recette_Contient(nbrecette.get(nbrecette.size()-1), produit,Integer.parseInt(String.valueOf(quantité.getText())));
                                    daoProduit_Recette.create(produit);
                                    daoRecette_Contient.create(contient);
                                }
                                Intent monIntent = new Intent(AlterRecette.this, RecetteController.class);
                                startActivity(monIntent);
                            }
                            catch (SQLException throwables)
                            {
                                throwables.printStackTrace();
                            }
                        }
                    }
                });
                AddIngredient.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        TableRow newRow = new TableRow(getApplicationContext());
                        newRow.setGravity(Gravity.CENTER_VERTICAL);
                        EditText text = new EditText(getApplicationContext());
                        ImageButton Plus = new ImageButton(getApplicationContext());
                        Plus.setImageResource(R.drawable.ic_baseline_add_24);
                        Plus.setBackground(null);
                        Plus.setLayoutParams(paramButton);
                        ImageButton Moins = new ImageButton(getApplicationContext());
                        Moins.setImageResource(R.drawable.ic_baseline_remove_24);
                        Moins.setBackground(null);
                        Moins.setLayoutParams(paramButton);
                        TextView quantité = new TextView(getApplicationContext());
                        quantité.setText("1");
                        newRow.addView(text);
                        newRow.addView(Moins);
                        newRow.addView(quantité);
                        newRow.addView(Plus);
                        ImageButton Supprimer = new ImageButton(getApplicationContext());
                        Supprimer.setBackground(null);
                        Supprimer.setImageResource(R.mipmap.ic_clear);
                        Supprimer.setLayoutParams(paramButton);
                        newRow.addView(Supprimer);
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
                                if (Integer.parseInt((String) quantité.getText())!=1)
                                {
                                    quantité.setText(Integer.toString(Integer.parseInt((String) quantité.getText()) - 1));
                                }
                            }
                        });
                        Supprimer.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v)
                            {
                                grille.removeView(newRow);
                            }
                        });
                    }
                });
            }
            else
            {
                name.setText(recette.getLibelle());
                List<Recette_Contient> contient = daoRecette_Contient.queryForAll();
                for (int x = 0; x < contient.size(); x++)
                {
                    if (contient.get(x).getRecette().getIdRecette()==recette.getIdRecette())
                    {
                        Recette_Contient contients=contient.get(x);
                        TableRow newRow = new TableRow(getApplicationContext());
                        newRow.setGravity(Gravity.CENTER_VERTICAL);
                        EditText text = new EditText(getApplicationContext());
                        ImageButton Plus = new ImageButton(getApplicationContext());
                        Plus.setImageResource(R.drawable.ic_baseline_add_24);
                        Plus.setBackground(null);
                        ImageButton Moins = new ImageButton(getApplicationContext());
                        Moins.setImageResource(R.drawable.ic_baseline_remove_24);
                        Moins.setBackground(null);
                        TextView quantité = new TextView(getApplicationContext());
                        text.setText(contient.get(x).getProduit().getLibelleProduit());
                        quantité.setText(String.valueOf(contient.get(x).getQuantite()));
                        newRow.addView(text);
                        newRow.addView(Moins);
                        newRow.addView(quantité);
                        newRow.addView(Plus);
                        ImageButton Supprimer = new ImageButton(getApplicationContext());
                        Supprimer.setBackground(null);
                        Supprimer.setImageResource(R.mipmap.ic_clear);
                        Supprimer.setLayoutParams(paramButton);
                        newRow.addView(Supprimer);
                        grille.addView(newRow);
                        Plus.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v)
                            {
                                quantité.setText(Integer.toString(Integer.parseInt((String) quantité.getText())+1));
                                contients.setQuantite(contients.getQuantite()+1);
                                try
                                {
                                    daoRecette_Contient.update(contients);
                                }
                                catch (SQLException throwables)
                                {
                                    throwables.printStackTrace();
                                }
                            }
                        });
                        Moins.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v)
                            {
                                if (Integer.parseInt((String) quantité.getText())!=1)
                                {
                                    quantité.setText(Integer.toString(Integer.parseInt((String) quantité.getText()) - 1));
                                    contients.setQuantite(contients.getQuantite()-1);
                                    try
                                    {
                                        daoRecette_Contient.update(contients);
                                    }
                                    catch (SQLException throwables)
                                    {
                                        throwables.printStackTrace();
                                    }
                                }
                            }
                        });
                        Supprimer.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v)
                            {
                                grille.removeView(newRow);
                                contient.remove(contients);
                                try
                                {
                                    daoRecette_Contient.delete(contients);
                                }
                                catch (SQLException throwables)
                                {
                                    throwables.printStackTrace();
                                }
                            }
                        });
                    }
                    else{
                        contient.remove(x);
                    }
                }
                Valider.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            for (int x = 3; x < contient.size()+3; x++)
                            {
                                TableRow myrow= (TableRow) grille.getChildAt(x);
                                EditText nom = (EditText) myrow.getChildAt(0);
                                TextView quantité = (TextView) myrow.getChildAt(2);
                                Produit_Recette produit = daoProduit_Recette.queryForId(contient.get(x-3).getProduit().getIdProduit_Recette());
                                produit.setLibelleProduit(String.valueOf(nom.getText()));
                                contient.get(x-3).setQuantite(Integer.parseInt(String.valueOf(quantité.getText())));
                                contient.get(x-3).setProduit(produit);
                                daoProduit_Recette.update(produit);
                                daoRecette_Contient.update(contient.get(x-3));
                            }
                            for (int x = contient.size()+3; x < grille.getChildCount(); x++)
                            {
                                Log.i("bouton", String.valueOf(x));
                                Log.i("bouton", String.valueOf(contient.size()));
                                TableRow myrow= (TableRow) grille.getChildAt(x);
                                EditText nom = (EditText) myrow.getChildAt(0);
                                TextView quantité = (TextView) myrow.getChildAt(2);
                                Produit_Recette produit=new Produit_Recette(String.valueOf(nom.getText()));
                                Recette_Contient contients=new Recette_Contient(recette,produit,Integer.parseInt(String.valueOf(quantité.getText())));
                                daoProduit_Recette.create(produit);
                                daoRecette_Contient.create(contients);
                            }
                            recette.setLibelle(String.valueOf(name.getText()));
                            dao.update(recette);
                            Intent monIntent = new Intent(AlterRecette.this, RecetteController.class);
                            startActivity(monIntent);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                });
                AddIngredient.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        TableRow newRow = new TableRow(getApplicationContext());
                        newRow.setGravity(Gravity.CENTER_VERTICAL);
                        EditText text = new EditText(getApplicationContext());
                        ImageButton Plus = new ImageButton(getApplicationContext());
                        Plus.setImageResource(R.drawable.ic_baseline_add_24);
                        Plus.setBackground(null);
                        ImageButton Moins = new ImageButton(getApplicationContext());
                        Moins.setImageResource(R.drawable.ic_baseline_remove_24);
                        Moins.setBackground(null);
                        TextView quantité = new TextView(getApplicationContext());
                        quantité.setText("1");
                        newRow.addView(text);
                        newRow.addView(Moins);
                        newRow.addView(quantité);
                        newRow.addView(Plus);
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
                                if (Integer.parseInt((String) quantité.getText())!=1)
                                {
                                    quantité.setText(Integer.toString(Integer.parseInt((String) quantité.getText()) - 1));
                                }
                            }
                        });
                    }
                });
            }
            Retour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent monIntent = new Intent(AlterRecette.this, RecetteController.class);
                    startActivity(monIntent);
                }
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
