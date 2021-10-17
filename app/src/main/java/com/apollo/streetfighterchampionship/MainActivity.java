package com.apollo.streetfighterchampionship;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int balrog, ed, bison;
    Button btnBalrogWon, btnBalrogLose, btnEdWon, btnEdLose, btnBisonWon, btnBisonLose, btnWhoWon,
            btnResetResults, btnShowPlacement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout layout = findViewById(R.id.parent_layout);
        Drawable drawable = getDrawable(R.mipmap.street_fighter_bg_app);
        drawable.setAlpha(60);
        layout.setBackground(drawable);

        initializeComponents();
        initializeButtonsListener();
    }

    private void initializeComponents() {
        // balrog buttons
        btnBalrogWon = findViewById(R.id.btnBalrogWon);
        btnBalrogLose = findViewById(R.id.btnBalrogLose);

        // ed buttons
        btnEdWon = findViewById(R.id.btnEdWon);
        btnEdLose = findViewById(R.id.btnEdLose);

        // bison buttons
        btnBisonWon = findViewById(R.id.btnBisonWon);
        btnBisonLose = findViewById(R.id.btnBisonLose);

        btnWhoWon = findViewById(R.id.btnWhoWon);
        btnResetResults = findViewById(R.id.btnResetResults);
        btnShowPlacement = findViewById(R.id.btnShowPlacement);
    }

    private void initializeButtonsListener() {
        btnBalrogWon.setOnClickListener(View -> balrog++); // balrog increase points
        btnBalrogLose.setOnClickListener(View -> balrog--); // balrog decrease points

        btnEdWon.setOnClickListener(View -> ed++); // ed increase points
        btnEdLose.setOnClickListener(View -> ed--); // ed decrease points

        btnBisonWon.setOnClickListener(View -> bison++); // bison increase points
        btnBisonLose.setOnClickListener(View -> bison--); // bison decrease points

        btnWhoWon.setOnClickListener(View -> {
            String nome_personagem = "";
            String message;

            if (balrog > ed && balrog > bison) {
                nome_personagem = "Balrog";
            } else if (ed > balrog && ed > bison) {
                nome_personagem = "Ed";
            } else if (bison > balrog && bison > ed) {
                nome_personagem = "Bison";
            }

            if (nome_personagem.isEmpty()) {
                message = "There's a tie";
            } else {
                message = String.format("The winner's %s", nome_personagem);
            }

            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        });
        btnResetResults.setOnClickListener(View -> {
            balrog = 0;
            ed = 0;
            bison = 0;
        });
        btnShowPlacement.setOnClickListener(View -> {
            String[] names = new String[]{"Balrog", "Ed", "Bison"};
            Integer[] all_points = new Integer[]{balrog, ed, bison};
            Arrays.sort(all_points);
            Collections.reverse(Arrays.asList(all_points));

            int index = 1;
            List<String> new_names = new ArrayList<>();

            StringBuilder sb = new StringBuilder();
            sb.append("Results");

            List<String> characters = new ArrayList<>();

            for (int point : all_points){
                String character = "";

                if(point == balrog && !characters.contains("Balrog")){
                    character = "Balrog";
                    characters.add("Balrog");
                }else if(point == ed && !characters.contains("Ed")){
                    character = "Ed";
                    characters.add("Ed");
                }else if(point == bison && !characters.contains("Bison")){
                    character = "Bison";
                    characters.add("Bison");
                }
                sb.append(String.format("\n%s - %s", String.valueOf(index),character));
                index++;
            }
            Toast.makeText(getApplicationContext(), sb.toString(), Toast.LENGTH_LONG).show();
        });
    }

}