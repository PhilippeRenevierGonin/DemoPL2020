package com.example.demoplpl;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.demoplpl.controleur.EcouteurDeBouton;
import com.example.demoplpl.controleur.EcouteurDeReseau;

import com.example.demoplpl.reseau.Connexion;
import com.example.demoplpl.vue.Vue;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import constantes.NET;
import io.socket.client.IO;
import io.socket.client.Socket;
import metier.Identité;

public class MainActivity extends Activity implements Vue {

    public static final String AUTOCONNECT = "AUTOCONNECT";

    private TextView texte;
    private Button bouton;


    private Identité monIdentité ;
    private boolean autoconnect =  true;

    public Connexion getConnexion() {
        return connexion;
    }

    public void setConnexion(Connexion connexion) {
        this.connexion = connexion;
        this.connexion.écouterRéseau();
    }

    private Connexion connexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // création de l'interface graphique
        setContentView(R.layout.activity_main);

        monIdentité = new Identité("Super Appli");

        autoconnect = getIntent().getBooleanExtra(AUTOCONNECT, true);
    }


    public void mettreÀJourLAffichageDuCompteur(final int cpt) {

        texte.post(new Runnable() {
            @Override
            public void run() {
                texte.setText("" + cpt);
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();

        if (connexion != null) connexion.disconnect();
    }




    @Override
    protected void onResume() {
        super.onResume();

            if (autoconnect) {
                setConnexion(new Connexion(this));
            }



            texte = findViewById(R.id.text);
            bouton = findViewById(R.id.button);




        if (autoconnect) {
            initVue();
        }




    }


    protected void initVue() {
        EcouteurDeBouton ecouteur = new EcouteurDeBouton(this, connexion);
        // création de l'écouteur (le controleur)
        bouton.setOnClickListener(ecouteur);
        texte.setOnClickListener(ecouteur);

        connexion.démarrerÉcoute();
        connexion.envoyerMessage(NET.CONNEXION, monIdentité);
    }
}
