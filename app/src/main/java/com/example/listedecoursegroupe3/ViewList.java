package com.example.listedecoursegroupe3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
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

public class ViewList extends AppCompatActivity
{
    private TableLayout grille;
    private Button Retour;
    private Button deleteall;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listeview);
        grille=findViewById(R.id.layout);
        Retour = findViewById(R.id.retour);
        deleteall = findViewById(R.id.deleteall);
        DataBaseLinker linker = new DataBaseLinker(this);
        try {
            Dao<Produit, Integer> daoProduit = linker.getDao(Produit.class);
            List<Produit> produit = daoProduit.queryForAll();
            for (Produit Produits : produit) {
                TableRow.LayoutParams paramButton = new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                );
                TableRow newRow = new TableRow(this);
                CheckBox checkBox=new CheckBox(this);
                TextView newText = new TextView(this);
                newText.setText(String.valueOf(Produits.getQuantite())+" x "+Produits.getLibelleProduit());
                newRow.addView(newText);
                newRow.addView(checkBox);
                grille.addView(newRow);
                checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (newText.getPaintFlags()!=16)
                        {
                            newText.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                        }
                        else
                        {
                            newText.setPaintFlags(0);
                        }
                    }
                });
                Retour.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent monIntent = new Intent(ViewList.this, MainActivity.class);
                        startActivity(monIntent);
                    }
                });
            }
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }
}