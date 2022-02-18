package com.example.practica4;

public class Platillo
{
    private int id;
    private String nombre;
    private float precio;
    private String categoria;
    private String descripcion;
    private String foto;

    //---------     CONSTRUCTORS     ---------

    public Platillo(){}

    public Platillo(int id, String nombre, float precio, String categoria, String descripcion, String foto)
    {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.foto = foto;
    }

    //---------     GETTERS     ---------

    public int getId()
    {
        return id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public float getPrecio()
    {
        return precio;
    }

    public String getCategoria()
    {
        return categoria;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public String getFoto()
    {
        return foto;
    }

    //---------     SETTERS     ---------

    public void setId(int id)
    {
        this.id = id;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setPrecio(float precio)
    {
        this.precio = precio;
    }

    public void setCategoria(String categoria)
    {
        this.categoria = categoria;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public void setFoto(String foto)
    {
        this.foto = foto;
    }

    //---------     OVERRIDE     ---------

    @Override
    public String toString()
    {
        return "Platillo  " +
                "id = " + id + '\'' +
                "nombre ='" + nombre + '\'' +
                "precio =" + precio +
                "categoria ='" + categoria + '\'' +
                "descripcion ='" + descripcion + '\'' +
                "foto ='" + foto;
    }
}
