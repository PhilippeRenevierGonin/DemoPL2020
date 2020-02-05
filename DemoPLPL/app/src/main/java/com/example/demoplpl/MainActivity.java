package com.example.demoplpl;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.demoplpl.controleur.EcouteurDeBouton;
import com.example.demoplpl.controleur.EcouteurDeReseau;
import com.example.demoplpl.modele.Compteur;
import com.example.demoplpl.modele.Identité;
import com.example.demoplpl.vue.Vue;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class MainActivity extends Activity implements Vue {

    private TextView texte;
    private Button bouton;

    private Socket mSocket;

    private Identité monIdentité ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // création de l'interface graphique
        setContentView(R.layout.activity_main);

        monIdentité = new Identité("Super Appli");
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

        mSocket.disconnect();
    }


    @Override
    protected void onResume() {
        super.onResume();
        try {
            Socket mSocket = IO.socket("http://10.0.2.2:10101");

            EcouteurDeReseau net = new EcouteurDeReseau(this);

            mSocket.on("valeur", net);


            texte = findViewById(R.id.text);
            bouton = findViewById(R.id.button);


            // création de l'écouteur (le controleur)
            EcouteurDeBouton ecouteur = new EcouteurDeBouton(this, mSocket);
            bouton.setOnClickListener(ecouteur);

            texte.setOnClickListener(ecouteur);

            mSocket.connect();


            JSONObject identité = new JSONObject();
            try {
                identité.put("nom", monIdentité.getNom());
            } catch (JSONException e) {
                e.printStackTrace();}
            mSocket.emit("je me connecte", identité);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
