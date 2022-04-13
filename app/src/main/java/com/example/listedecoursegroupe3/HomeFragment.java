package com.example.listedecoursegroupe3;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.listedecoursegroupe3.entites.Produit;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class HomeFragment extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DataBaseLinker linker = new DataBaseLinker(getContext());
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        TableLayout grille =(TableLayout) rootView.findViewById(R.id.grille);
        try
        {
            Dao<Produit, Integer> daoProduit = linker.getDao( Produit.class );
            List<Produit> produit = daoProduit.queryForAll();
            for(Produit Produits: produit)
            {
                TableRow.LayoutParams paramButton = new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f
                );
                Log.i("bouton", Produits.getLibelleProduit());
                TableRow newRow = new TableRow(getContext());
                TextView newText = new TextView(getContext());
                newText.setText(Produits.getLibelleProduit());
                ImageButton Modifier = new ImageButton(getContext());
                Modifier.setLayoutParams(paramButton);
                Modifier.setBackground(null);
                Modifier.setImageResource(R.drawable.edit);
                ImageButton SupprimerProduit = new ImageButton(getContext());
                SupprimerProduit.setLayoutParams(paramButton);
                SupprimerProduit.setImageResource(R.drawable.delete);
                SupprimerProduit.setBackground(null);
                SupprimerProduit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            daoProduit.delete(Produits);
                        }
                        catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        ((ViewGroup) newRow.getParent()).removeView(newRow);
                    }
                });

                Button Plus = new Button(getContext());
                TextView quantité = new TextView(getContext());
                quantité.setText("1");
                Button Moins = new Button(getContext());
                Plus.setText("+");
                Moins.setText("-");
                newRow.addView(newText);
                newRow.addView(Moins);
                newRow.addView(quantité);
                newRow.addView(Plus);
                newRow.addView(Modifier);
                newRow.addView(SupprimerProduit);
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
                        Log.i("bouton", String.valueOf(Integer.parseInt((String) quantité.getText())));
                        if (Integer.parseInt((String) quantité.getText())!=1)
                        {
                            quantité.setText(Integer.toString(Integer.parseInt((String) quantité.getText()) - 1));
                        }
                    }
                });
            }
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}