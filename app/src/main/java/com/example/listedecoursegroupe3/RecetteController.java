package com.example.listedecoursegroupe3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listedecoursegroupe3.entites.Recette;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class RecetteController extends AppCompatActivity
{
    private TableLayout grille;
    private Button AjoutRecette;
    private Button Retour;
    private EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addproduit);
        grille = findViewById(R.id.layout);
        AjoutRecette = findViewById(R.id.Valider);
        Retour = findViewById(R.id.Supprimer);
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
                TableRow newRow = new TableRow(this);
                TextView newText = new TextView(this);
                newText.setText(recette.getLibelle());
                Button Modifier = new Button(this);
                Modifier.setText("Modifier Recette");
                Modifier.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        Intent monIntent = new Intent(RecetteController.this, AlterRecette.class);
                        monIntent.putExtra("id", recette.getIdRecette());
                        startActivity(monIntent);
                    }
                });
                newRow.addView(newText);
                newRow.addView(Modifier);
                grille.addView(newRow);
            }
            AjoutRecette.setText("Ajouter Recette");
            AjoutRecette.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent monIntent = new Intent(RecetteController.this, AlterRecette.class);
                    startActivity(monIntent);
                }
            });
            Retour.setText("Retour");
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
