package com.example.practica4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class DeleteView extends AppCompatActivity {
    ArrayList<Platillo> listaPlatillos = new ArrayList<Platillo>();
    EditText txtid;
    Button btnsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_view);
        loadPlatillos();
        txtid = findViewById(R.id.txt_dID);
        btnsearch = findViewById(R.id.btn_dSearch);
    }

    public void deletePlatillo(View v) {
        int id = Integer.parseInt(txtid.getText().toString());
        for (int i = 0; i < listaPlatillos.size(); i++) {
            if (listaPlatillos.get(i).getId() == id) {
                listaPlatillos.remove(id);
                saveData();
                Toast toast = Toast.makeText(getApplicationContext(), "El platillo se ha eliminado correctamente.", Toast.LENGTH_SHORT);
                toast.show();
                break;
            }
        }
    }

    public void saveData()
    {
        try
        {
            String fName = "platillos.txt";
            String content = "";
            for(int i = 0; i < listaPlatillos.size(); i++)
            {
                content = content + listaPlatillos.get(i).getNombre() + "," + listaPlatillos.get(i).getPrecio() +
                        "," + listaPlatillos.get(i).getCategoria() + "," + listaPlatillos.get(i).getDescripcion() +
                        "," + listaPlatillos.get(i).getFoto() + "\n";
            }
            FileOutputStream outputStream;
            outputStream = openFileOutput(fName, Context.MODE_PRIVATE);
            outputStream.write(content.getBytes());
        }
        catch(Exception e)
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Error al Actualizar.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void loadPlatillos() {

        listaPlatillos.clear();
        String fName = "platillos.txt";
        String datos = "";
        FileInputStream inputStream;
        try {
            inputStream = openFileInput(fName);
            Scanner sc = new Scanner(inputStream);
            String contenido = "";
            while (sc.hasNext()) {
                contenido = sc.nextLine() + "\n";
                String elementos[] = contenido.split(",");
                listaPlatillos.add(new Platillo(listaPlatillos.size(), elementos[0],
                        Float.parseFloat(elementos[1]),
                        elementos[2],
                        elementos[3],
                        elementos[4]));
            }
        } catch (Exception e) {
            Toast toast = Toast.makeText(getApplicationContext(), "Error al cargar los Platillos.", Toast.LENGTH_SHORT);
            toast.show();
            System.out.println(e);
        }
    }
}