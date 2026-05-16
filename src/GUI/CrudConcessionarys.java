/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import CONTROL.City;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 *
 * @author alefdelgado
 */
public class CrudConcessionarys extends JPanel
{
    public Window ventana;
    
    City[] ciudades =
    {
        new City("Toluca"),
        new City("Otro")
    };

    public CrudConcessionarys(Window ventana)
    {
        this.ventana = ventana;
        setLayout(new BorderLayout());
        setBackground(Window.mist);
        
        JPanel bar = createMenuBar();
        
        add(bar, BorderLayout.NORTH);
    }
    
    public JPanel createMenuBar()
    {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(0, 40));
        panel.setBackground(Window.steel);
        
        panel.add(new JLabel("Ciudad: "));
        
        JComboBox<City> ciudadesBox = new JComboBox<>(ciudades);
        panel.add(ciudadesBox);
        
        JLabel especifiqueCiudad = new JLabel();
        especifiqueCiudad.setVisible(false);
        panel.add(especifiqueCiudad);
        
        JTextField nuevaCiudadField = new JTextField(20);
        nuevaCiudadField.setVisible(false);
        panel.add(nuevaCiudadField);
        
        ciudadesBox.addActionListener(e -> 
        {
            City selection = (City) ciudadesBox.getSelectedItem();
            boolean esOtro = selection.getNom().equals("Otro");
            
            especifiqueCiudad.setVisible(esOtro);
            nuevaCiudadField.setVisible(esOtro);
            
            ciudadesBox.getParent().revalidate();
            ciudadesBox.getParent().repaint();
        });
        
        
        return panel;
    }
    
}
