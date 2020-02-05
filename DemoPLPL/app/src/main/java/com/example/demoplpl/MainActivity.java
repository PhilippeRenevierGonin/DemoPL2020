package com.example.demoplpl;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.demoplpl.controleur.EcouteurDeBouton;
import com.example.demoplpl.modele.Compteur;
import com.example.demoplpl.vue.Vue;

public class MainActivity extends Activity implements Vue {

    private TextView texte;
    private Button bouton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // création de l'interface graphique
        setContentView(R.layout.activity_main);

        texte = findViewById(R.id.text);
        bouton = findViewById(R.id.button);

        // création des objets métiers (ou modèle)
        final Compteur cpt = new Compteur();

        // création de l'écouteur (le controleur)
        final EcouteurDeBouton ecouteur = new EcouteurDeBouton(this, cpt);

        // pour montrer l'abonnement, ici avec 2 listeners dont un anonyme
        // car dans Android ce sont des set = un seul listener
        // pour faire simple, on aurait fait bouton.setOnClickListener(ecouteur);
        bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("POUR MONTRER", "C'est le listener anonyme");
                Log.d("POUR MONTRER", "je suis "+this);
                Log.d("POUR MONTRER", "l'outter est "+MainActivity.this);


                ecouteur.onClick(v);

            }
        });

        texte.setOnClickListener(ecouteur);
        // ou :
        /*
        texte.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                cpt.raz();
                mettreÀJourLAffichageDuCompteur(0);
            }
        }); */
    }


    public void mettreÀJourLAffichageDuCompteur(int cpt) {
        texte.setText(""+cpt);
    }



}
