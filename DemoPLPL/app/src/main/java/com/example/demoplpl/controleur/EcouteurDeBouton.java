package com.example.demoplpl.controleur;

import android.util.Log;
import android.view.View;

import com.example.demoplpl.MainActivity;
import com.example.demoplpl.R;
import com.example.demoplpl.reseau.Connexion;
import com.example.demoplpl.vue.Vue;

import constantes.NET;
import io.socket.client.Socket;

public class EcouteurDeBouton implements View.OnClickListener {

    private final Connexion mSocket;
    private final Vue vue;



    public EcouteurDeBouton(Vue v, Connexion mSocket) {
        this.vue = v;
        this.mSocket = mSocket;
    }

    @Override
    public void onClick(View v) {
        /*
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
           */

        mSocket.envoyer(NET.AJOUTER);

    }



}
