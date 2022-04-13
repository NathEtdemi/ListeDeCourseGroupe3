package com.example.listedecoursegroupe3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.listedecoursegroupe3.R;
import com.example.listedecoursegroupe3.entites.Produit;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;


public class AjoutProduitFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        DataBaseLinker linker = new DataBaseLinker(getContext());
        View rootView = inflater.inflate(R.layout.fragment_ajout_produit, container, false);
        Button Valider = (Button) rootView.findViewById(R.id.Valider);
        EditText name =(EditText) rootView.findViewById(R.id.name);
        Valider.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Valider.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        if (String.valueOf(name.getText()) != "")
                        {
                            try
                            {
                                Dao<Produit, Integer> dao = linker.getDao(Produit.class);
                                Produit produit = new Produit(String.valueOf(name.getText()),1);
                                dao.create(produit);
                            }
                            catch (SQLException throwables)
                            {
                                throwables.printStackTrace();
                            }
                        }
                    }
                });
            }

            });
        return rootView;
    }
}