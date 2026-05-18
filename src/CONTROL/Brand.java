package CONTROL;

/**
 *
 * @author alefdelgado
 */
public class Brand implements java.io.Serializable
{
    String nom;

    public Brand(String nom)
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
