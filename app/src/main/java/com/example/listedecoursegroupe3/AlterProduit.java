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
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;


public class AlterProduit extends AppCompatActivity
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
            Dao<Produit, Integer> dao = linker.getDao(Produit.class);
            Produit produit = dao.queryForId(intent.getIntExtra("id", 0));
            if (produit == null)
            {
                Supprimer.setText("Retour");
                Valider.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        if (String.valueOf(name.getText()) != "")
                        {
                            try {
                                Produit produit = new Produit(String.valueOf(name.getText()),1);
                                dao.create(produit);
                                Intent monIntent = new Intent(AlterProduit.this, MainActivity.class);
                                startActivity(monIntent);
                            }
                            catch (SQLException throwables)
                            {
                                throwables.printStackTrace();
                            }
                        }
                    }
                });
                Supprimer.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent monIntent = new Intent(AlterProduit.this, MainActivity.class);
                        startActivity(monIntent);
                    }
                });
            }
            else
            {
                name.setText(produit.getLibelleProduit());
                Supprimer.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        try
                        {
                            dao.delete(produit);
                            Intent monIntent = new Intent(AlterProduit.this, MainActivity.class);
                            startActivity(monIntent);
                        }
                        catch (SQLException throwables)
                        {
                            throwables.printStackTrace();
                        }
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
