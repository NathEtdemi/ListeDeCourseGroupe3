package com.example.listedecoursegroupe3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addproduit);
        grille = findViewById(R.id.layout);
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
            TableRow row =new TableRow(this);
            Button add=new Button(this);
            Button Return=new Button(this);
            add.setText("Ajouter Recette");
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent monIntent = new Intent(RecetteController.this, AlterRecette.class);
                    startActivity(monIntent);
                }
            });
            Return.setText("Retour");
            Return.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent monIntent = new Intent(RecetteController.this, MainActivity.class);
                    startActivity(monIntent);
                }
            });
            row.addView(add);
            row.addView(Return);
            grille.addView(row);
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }
}
