package com.example.listedecoursegroupe3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
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

public class ViewList extends AppCompatActivity
{
    private ScrollView grille;
    private ImageButton Retour;
    private Button deleteall;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listeview);
        grille=findViewById(R.id.layout);
        Retour = findViewById(R.id.imageView);
        deleteall = findViewById(R.id.deleteall);
        DataBaseLinker linker = new DataBaseLinker(this);
        try {
            Dao<Produit, Integer> daoProduit = linker.getDao(Produit.class);
            List<Produit> produit = daoProduit.queryForAll();
            LinearLayout firstLinearLayout = new LinearLayout(this);
            firstLinearLayout.setOrientation(LinearLayout.VERTICAL);
            for (Produit Produits : produit) {
                LinearLayout produitLinearLayout =  new LinearLayout(this);
                produitLinearLayout.setGravity(Gravity.CENTER_VERTICAL);
                CheckBox checkBox=new CheckBox(this);
                TextView newText = new TextView(this);
                newText.setTextSize(25.0F);
                newText.setTypeface(Typeface.DEFAULT_BOLD);
                newText.setText(String.valueOf(Produits.getQuantite())+" x "+Produits.getLibelleProduit());
                produitLinearLayout.addView(newText);
                produitLinearLayout.addView(checkBox);
                firstLinearLayout.addView(produitLinearLayout);
                if (Produits.getIscheck()==1)
                {
                    checkBox.setChecked(true);
                    newText.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                }
                checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (newText.getPaintFlags()!=16)
                        {
                            newText.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                            Produits.setIscheck(1);
                            try {

                                daoProduit.update(Produits);
                            }
                            catch (SQLException throwables)
                            {
                                throwables.printStackTrace();
                            }
                        }
                        else
                        {
                            newText.setPaintFlags(0);
                            Produits.setIscheck(0);
                            try
                            {
                                daoProduit.update(Produits);
                            }
                            catch (SQLException throwables)
                            {
                                throwables.printStackTrace();
                            }
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
                deleteall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (Produit Produits : produit) {
                            try
                            {
                                daoProduit.delete(Produits);
                            }
                            catch (SQLException throwables)
                            {
                                throwables.printStackTrace();
                            }
                        }
                        Intent monIntent = new Intent(ViewList.this, MainActivity.class);
                        startActivity(monIntent);
                    }
                });
            }
            grille.addView(firstLinearLayout);
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }
}