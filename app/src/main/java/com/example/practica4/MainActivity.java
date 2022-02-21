package com.example.practica4;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showInsertView(View v)
    {
        Intent vCreate = new Intent(getApplicationContext(), CreateView.class);
        vCreate.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(vCreate);
    }

    public void showReadView(View v)
    {
        Intent vRead = new Intent(getApplicationContext(), ReadView.class);
        vRead.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(vRead);
    }

    public void showUpdateView(View v)
    {
        Intent vUpdate = new Intent(getApplicationContext(), UpdateView.class);
        vUpdate.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(vUpdate);
    }

    public void showDeleteView(View v)
    {
        Intent vDelete = new Intent(getApplicationContext(), DeleteView.class);
        vDelete.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(vDelete);
    }
}