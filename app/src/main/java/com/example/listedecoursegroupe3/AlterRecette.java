package com.example.listedecoursegroupe3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

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
    private EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addproduit);
        grille = findViewById(R.id.layout);
        Valider = findViewById(R.id.Valider);
        Supprimer = findViewById(R.id.Supprimer);
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
                            try {
                                Recette recettes = new Recette(String.valueOf(name.getText()));
                                dao.create(recettes);
                                Intent monIntent = new Intent(AlterRecette.this, RecetteController.class);
                                startActivity(monIntent);
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        }
                    }
                });
            } else {
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
