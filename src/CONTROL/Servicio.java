package CONTROL;

import java.io.Serializable;

/**
 *
 * @author alefdelgado
 */
public class Servicio implements Serializable
{
    String nom;
    double precio;
    int capacidad;

    public Servicio()
    {
    }

    public Servicio(String nom, double precio, int capacidad)
    {
        this.nom = nom;
        this.precio = precio;
        this.capacidad = capacidad;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public double getPrecio()
    {
        return precio;
    }

    public void setPrecio(double precio)
    {
        this.precio = precio;
    }

    public int getCapacidad()
    {
        return capacidad;
    }

    public void setCapacidad(int capacidad)
    {
        this.capacidad = capacidad;
    }

    @Override
    public String toString()
    {
        return nom;
    }
    
}
