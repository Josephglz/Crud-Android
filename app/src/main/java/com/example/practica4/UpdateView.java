package com.example.practica4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class UpdateView extends AppCompatActivity
{
    ArrayList<Platillo> listaPlatillos = new ArrayList<Platillo>();
    EditText txtname, txtprice, txtdesc, txtcategory, txturl, txtid;
    Button btnmodify, btncancel, btnsearch;
    int platillo = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_view);
        txtname = findViewById(R.id.txt_uName);
        txtprice = findViewById(R.id.txt_uPrice);
        txtcategory = findViewById(R.id.txt_uCategory);
        txtdesc = findViewById(R.id.txt_uDescription);
        txturl = findViewById(R.id.txt_uPicture);
        txtid = findViewById(R.id.txt_uID);
        btnmodify = findViewById(R.id.btn_umodify);
        btncancel = findViewById(R.id.btn_uCancel);
        btnsearch = findViewById(R.id.btn_uSearch);
        loadPlatillos();
    }

    public void searchPlatillo(View v)
    {
        int idx = SeqSearch(Integer.parseInt(txtid.getText().toString()));
        if(idx > -1)
        {
            platillo = idx;
            showFields();
            txtname.setText(listaPlatillos.get(idx).getNombre());
            txtprice.setText(String.valueOf(listaPlatillos.get(idx).getPrecio()));
            txtcategory.setText(listaPlatillos.get(idx).getCategoria());
            txtdesc.setText(listaPlatillos.get(idx).getDescripcion());
            txturl.setText(listaPlatillos.get(idx).getFoto());
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(), "No se encuentra el Platillo!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void modifyPlatillo(View v)
    {
        if(platillo > -1)
        {
            try
            {
                listaPlatillos.get(platillo).setNombre(txtname.getText().toString());
                listaPlatillos.get(platillo).setPrecio(Float.parseFloat(txtprice.getText().toString()));
                listaPlatillos.get(platillo).setCategoria(txtcategory.getText().toString());
                listaPlatillos.get(platillo).setDescripcion(txtdesc.getText().toString());
                listaPlatillos.get(platillo).setFoto(txturl.getText().toString());
                hideFields(null);
                platillo = -1;
                saveData();
                Toast toast = Toast.makeText(getApplicationContext(), "Los datos se actualizaron!", Toast.LENGTH_SHORT);
                toast.show();
            }
            catch(Exception e)
            {
                Toast toast = Toast.makeText(getApplicationContext(), "Error al introducir los datos.", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Ocurrió un error interno!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public int SeqSearch(int id)
    {
        for(int i = 0; i < listaPlatillos.size(); i++)
        {
            if(listaPlatillos.get(i).getId() == id)
            {
                return i;
            }
        }
        return -1;
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
        } catch (Exception e)
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Error al cargar los Platillos.", Toast.LENGTH_SHORT);
            toast.show();
            System.out.println(e);
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

    public void showFields()
    {
        txtname.setVisibility(View.VISIBLE);
        txtprice.setVisibility(View.VISIBLE);
        txtcategory.setVisibility(View.VISIBLE);
        txtdesc.setVisibility(View.VISIBLE);
        txturl.setVisibility(View.VISIBLE);
        btnmodify.setVisibility(View.VISIBLE);
        btncancel.setVisibility(View.VISIBLE);
        txtid.setVisibility(View.INVISIBLE);
        btnsearch.setVisibility(View.INVISIBLE);
    }

    public void hideFields(View v)
    {
        txtname.setVisibility(View.INVISIBLE);
        txtprice.setVisibility(View.INVISIBLE);
        txtcategory.setVisibility(View.INVISIBLE);
        txtdesc.setVisibility(View.INVISIBLE);
        txturl.setVisibility(View.INVISIBLE);
        btnmodify.setVisibility(View.INVISIBLE);
        btncancel.setVisibility(View.INVISIBLE);
        txtid.setVisibility(View.VISIBLE);
        btnsearch.setVisibility(View.VISIBLE);
    }
}