package com.example.practica4;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadView extends AppCompatActivity
{
    EditText txtsearch;
    ListView milista;
    ArrayList<Platillo> listaPlatillos = new ArrayList<>();
    MyCustomAdapter adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_view);

        loadPlatillos();
        milista = findViewById(R.id.miLista);
        MyCustomAdapter adaptador = new MyCustomAdapter(listaPlatillos, this);
        milista.setAdapter(adaptador);

        txtsearch = findViewById(R.id.txt_rSearch);
        txtsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adaptador.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    public void loadPlatillos() {
        listaPlatillos.clear();
        String fName = "platillos.txt";
        String datos = "";
        FileInputStream inputStream;
        try
        {
            inputStream = openFileInput(fName);
            Scanner sc = new Scanner(inputStream);
            while(sc.hasNext())
            {
                datos = sc.nextLine() + "\n";
                String[] elementos = datos.split(",");
                Platillo nPlatillo = new Platillo(listaPlatillos.size(), elementos[0],
                        Float.parseFloat(elementos[1]),
                        elementos[2],
                        elementos[3],
                        elementos[4]);
                listaPlatillos.add(nPlatillo);
            }
        } catch (Exception e)
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Error al cargar los Platillos.", Toast.LENGTH_SHORT);
            toast.show();
            e.printStackTrace();
        }
    }
}