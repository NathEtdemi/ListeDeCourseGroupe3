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
import android.widget.EditText;
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
        DataBaseLinker linker = new DataBaseLinker(this);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.liste:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                        return true;
                    case R.id.AjoutProduit:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, ajoutProduitFragment).commit();
                        return true;
                    case R.id.Recettes:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, recetteFragment).commit();
                        return true;
                }
                return false;
            }
        });

        grille=findViewById(R.id.layout);
        Liste = findViewById(R.id.listeProduit);
        AddProduit = findViewById(R.id.Add_Product);
        Recette = findViewById(R.id.Recette);
        linker.close();
    }
}