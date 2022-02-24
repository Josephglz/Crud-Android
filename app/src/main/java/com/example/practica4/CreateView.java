package com.example.practica4;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CreateView extends AppCompatActivity
{
    EditText txtname, txtprice, txtcategory, txtdesc, txturl;
    JSONObject jArray = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_view);
        txtname = findViewById(R.id.txt_cName);
        txtprice = findViewById(R.id.txt_cPrice);
        txtcategory = findViewById(R.id.txt_cCategory);
        txtdesc = findViewById(R.id.txt_cDescription);
        txturl = findViewById(R.id.txt_cPicture);

        try {
            JSONObject miPlatillo = new JSONObject(getIntent().getStringExtra("PlatillosJSON"));

            txtname.setText(miPlatillo.getJSONObject("Platillos").getString("Nombre"));
            txtprice.setText(miPlatillo.getJSONObject("Platillos").getString("Precio"));
            txtcategory.setText(miPlatillo.getJSONObject("Platillos").getString("Categoria"));
            txtdesc.setText(miPlatillo.getJSONObject("Platillos").getString("Descripcion"));
            txturl.setText(miPlatillo.getJSONObject("Platillos").getString("Foto"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void clearFields(View v)
    {
        txtname.setText("");
        txtprice.setText("");
        txtcategory.setText("");
        txtdesc.setText("");
        txturl.setText("");
        Toast toast = Toast.makeText(getApplicationContext(), "Campos Limpiados!", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void createPlatillo(View v)
    {
        try
        {
            if(!TextUtils.isEmpty(txtname.getText().toString()) || !TextUtils.isEmpty(txtprice.getText().toString()) ||
                    !TextUtils.isEmpty(txtcategory.getText().toString()) || !TextUtils.isEmpty(txtdesc.getText().toString()) ||
                    !TextUtils.isEmpty(txturl.getText().toString()))
            {
                String fName = "platillos.txt";
                String content = txtname.getText().toString() + "," + txtprice.getText().toString() + "," +
                        txtcategory.getText().toString() + "," + txtdesc.getText().toString() + "," +
                        txturl.getText().toString() + "\n";
                FileOutputStream outputStream;
                outputStream = openFileOutput(fName, Context.MODE_APPEND);
                outputStream.write(content.getBytes());

                Toast toast = Toast.makeText(getApplicationContext(), "Platillo Añadido!", Toast.LENGTH_SHORT);
                toast.show();
                clearFields(null);
            }
            else
            {
                Toast toast = Toast.makeText(getApplicationContext(), "Los campos no deben estar vacíos.", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        catch(Exception e)
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Error al Insertar.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}