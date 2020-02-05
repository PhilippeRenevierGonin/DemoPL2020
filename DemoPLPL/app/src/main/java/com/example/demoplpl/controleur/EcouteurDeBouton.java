package com.example.demoplpl.controleur;

import android.util.Log;
import android.view.View;

import com.example.demoplpl.R;
import com.example.demoplpl.vue.Vue;
import com.example.demoplpl.modele.Compteur;

public class EcouteurDeBouton implements View.OnClickListener {

    private final Compteur modèle;
    private final Vue vue;

    public EcouteurDeBouton(Vue v, Compteur m) {
        this.modèle = m;
        this.vue = v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                Log.d("POUR MONTRER", "on a cliqué");
                modèle.ajouter();
                break;
            case R.id.text:
                modèle.raz();
                break;
        }

        vue.mettreÀJourLAffichageDuCompteur(modèle.getCpt());


    }



}
