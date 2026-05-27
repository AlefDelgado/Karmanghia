package CONTROL;

import java.io.Serializable;

/**
 *
 * @author alefdelgado
 */
public class Marca implements Serializable
{
    String nom;

    public Marca(String nom)
    {
        this.nom = nom;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    @Override
    public String toString()
    {
        return nom;
    }
    
    
}
