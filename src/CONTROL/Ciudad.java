/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROL;

import java.io.Serializable;

/**
 *
 * @author alefdelgado
 */
public class Ciudad implements Serializable
{
    String nom;

    public Ciudad(String nom)
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
