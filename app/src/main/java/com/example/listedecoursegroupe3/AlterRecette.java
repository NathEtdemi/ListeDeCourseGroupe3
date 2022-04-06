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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addproduit);
        grille = findViewById(R.id.layout);
        DataBaseLinker linker = new DataBaseLinker(this);
        Intent intent = this.getIntent();
        try {
            Dao<Recette, Integer> dao = linker.getDao(Recette.class);
            Recette recette = dao.queryForId(intent.getIntExtra("id", 0));
            if (recette == null) {
                EditText text = new EditText(this);
                Button button = new Button(this);
                button.setText("Valider");
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (String.valueOf(text.getText()) != "") {
                            try {
                                Recette recettes = new Recette(String.valueOf(text.getText()));
                                dao.create(recettes);
                                Intent monIntent = new Intent(AlterRecette.this, MainActivity.class);
                                startActivity(monIntent);
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        }
                    }
                });
                TableRow row = new TableRow(this);
                row.addView(text);
                row.addView(button);
                grille.addView(row);
            } else {
                EditText text = new EditText(this);
                Button buttonsuppr = new Button(this);
                Button buttonvali = new Button(this);
                text.setText(recette.getLibelle());
                buttonsuppr.setText("Supprimer Recette");
                buttonsuppr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            dao.delete(recette);
                            Intent monIntent = new Intent(AlterRecette.this, MainActivity.class);
                            startActivity(monIntent);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                });
                buttonvali.setText("Valider");
                TableRow row = new TableRow(this);
                row.addView(text);
                row.addView(buttonsuppr);
                row.addView(buttonvali);
                grille.addView(row);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
