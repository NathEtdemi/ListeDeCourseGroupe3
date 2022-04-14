package com.example.listedecoursegroupe3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listedecoursegroupe3.entites.Produit;
import com.example.listedecoursegroupe3.entites.Recette;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class AlterRecette extends AppCompatActivity
{
    private TableLayout grille;
    private Button Valider;
    private Button Supprimer;
    private ImageButton AddIngredient;
    private EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alterrecette);
        grille = findViewById(R.id.layout);
        Valider = findViewById(R.id.Valider);
        Supprimer = findViewById(R.id.Supprimer);
        AddIngredient = findViewById(R.id.imageButton2);
        name = findViewById(R.id.name);
        DataBaseLinker linker = new DataBaseLinker(this);
        Intent intent = this.getIntent();
        try {
            Dao<Recette, Integer> dao = linker.getDao(Recette.class);
            Recette recette = dao.queryForId(intent.getIntExtra("id", 0));
            if (recette == null) {
                Supprimer.setText("Retour");
                Valider.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (String.valueOf(name.getText()) != "") {
                            try
                            {
                                Log.i("bouton", String.valueOf(findViewById(R.id.5)));
                                Recette recettes = new Recette(String.valueOf(name.getText()));
                                dao.create(recettes);
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
                Supprimer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            dao.delete(recette);
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
                        EditText text = new EditText(getApplicationContext());
                        Button Plus = new Button(getApplicationContext());
                        Button Moins = new Button(getApplicationContext());
                        TextView quantité = new TextView(getApplicationContext());
                        Plus.setText("+");
                        Moins.setText("-");
                        quantité.setText("1");
                        newRow.addView(text);
                        newRow.addView(Plus);
                        newRow.addView(quantité);
                        newRow.addView(Moins);
                        newRow.setId(5);
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
            else
            {
                name.setText(recette.getLibelle());
                Supprimer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            dao.delete(recette);
                            Intent monIntent = new Intent(AlterRecette.this, RecetteController.class);
                            startActivity(monIntent);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                });
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
