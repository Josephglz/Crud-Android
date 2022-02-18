package com.example.practica4;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
    TextView txtcont;
    ArrayList<Platillo> listaPlatillos = new ArrayList<Platillo>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_view);
        txtcont = findViewById(R.id.txt_rContent);
        loadPlatillos();
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
            String contenido = "";
            while(sc.hasNext())
            {
                contenido = sc.nextLine() + "\n";
                String elementos[] = contenido.split(",");
                listaPlatillos.add(new Platillo(listaPlatillos.size(), elementos[0],
                        Float.parseFloat(elementos[1]),
                        elementos[2],
                        elementos[3],
                        elementos[4]));
            }
            for(Platillo platillo : listaPlatillos)
            {
                datos = datos + "<b>ID:</b> " + platillo.getId() +
                        "<br><b>Platillo:</b> " + platillo.getNombre() +
                        "<br><b>Precio:</b> $" + platillo.getPrecio() +
                        "<br><b>Categoria:</b> " + platillo.getCategoria() +
                        "<br><b>Descripción:</b> " + platillo.getDescripcion() +
                        "<br><b>URL Foto:</b> " + platillo.getFoto() +
                        "<br>____________________________________<br>";
                txtcont.setText(Html.fromHtml(datos));
            }
        } catch (Exception e)
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Error al cargar los Platillos.", Toast.LENGTH_SHORT);
            toast.show();
            e.printStackTrace();
        }
    }
}