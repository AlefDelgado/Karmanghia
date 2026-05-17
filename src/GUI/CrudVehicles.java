package GUI;

import CONTROL.Brand;
import CONTROL.Services;
import ESTRUCTURAS.Lista;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import GUI.Window;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import poo.Archivos;

/**
 *
 * @author alefdelgado
 */
public class CrudVehicles extends JPanel
{
    Brand[] marcas =
    {
        new Brand("Ford"),
        new Brand("VolksWagen"),
        new Brand("Mercedes Benz"),
        new Brand("Toyota"),
        new Brand("Otra")
    };
        
    Services[] servicios =
    {
        new Services("Pintura", 8500.75, 200),
        new Services("Otro", 0, 0)
    };    

    private final Window ventana;

    public CrudVehicles(Window ventana)
    {
        this.ventana = ventana;
        setLayout(new BorderLayout());
        setBackground(Window.mist);

        JTabbedPane menu = new JTabbedPane();
        menu.addTab("Nuevo", createPanelAdd());
        menu.addTab("Eliminar", createPanelDelete());
        menu.addTab("Localizar", createPanelFind());
        menu.addTab("Editar", createPanelEdit());

        add(menu, BorderLayout.CENTER);
    }

    public JPanel createPanelAdd()
    {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Window.ice);
        //setBackground(Window.mist);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        int fila = 0;
        gbc.gridx = 0;
        gbc.gridy = fila;
        panel.add(new JLabel("Marca: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = fila;
        JComboBox<Brand> marca = new JComboBox<>(marcas);
        marca.setPreferredSize(new Dimension(200, 20));
        panel.add(marca, gbc);
        
        fila++;
        gbc.gridx = 0;
        gbc.gridy = fila;
        JLabel otroLbl = new JLabel("Especifique: ");
        otroLbl.setVisible(false);
        panel.add(otroLbl, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = fila;
        JTextField newBrand = new JTextField(20);
        newBrand.setVisible(false);
        panel.add(newBrand, gbc);

        marca.addActionListener(e ->
        {
            Brand selection = (Brand) marca.getSelectedItem();
            boolean esOtro = selection.getNom().equals("Otra");
            
            otroLbl.setVisible(esOtro);
            newBrand.setVisible(esOtro);
            
            marca.getParent().revalidate();
            marca.getParent().repaint();
        });

        fila++;
        gbc.gridx = 0;
        gbc.gridy = fila;
        panel.add(new JLabel("Modelo: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = fila;
        JTextField Modelo = new JTextField(20);
        panel.add(Modelo, gbc);
        
        fila++;
        gbc.gridx = 0;
        gbc.gridy = fila;
        panel.add(new JLabel("Año: "), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = fila;
        JSpinner anio = new JSpinner(new SpinnerNumberModel(2026, 1886, 2027, 1));
        panel.add(anio, gbc);

        fila++;
        gbc.gridx = 0;
        gbc.gridy = fila;
        panel.add(new JLabel("Placas: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = fila;
        JTextField placas = new JTextField(20);
        panel.add(placas, gbc);
        
        fila++;
        gbc.gridx = 0;
        gbc.gridy = fila;
        panel.add(new JLabel("Servicio: "), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = fila;
        JComboBox<Services> servicio = new JComboBox<>(servicios);
        servicio.setPreferredSize(new Dimension(200, 20));
        panel.add(servicio, gbc);
        
        fila++;
        gbc.gridx = 0;
        gbc.gridy = fila;
        JLabel insertService = new JLabel("Especifique: ");
        insertService.setVisible(false);
        panel.add(insertService, gbc);
        
        gbc.gridx = 1;
        gbc.gridy =  fila;
        JTextField newService = new JTextField(20);
        newService.setVisible(false);
        panel.add(newService, gbc);
        
        
        servicio.addActionListener(e ->
        {
            Services selected = (Services) servicio.getSelectedItem();
            boolean esOtro = selected.getNom().equals("Otro");
            
            insertService.setVisible(esOtro);
            newService.setVisible(esOtro);
            
            servicio.getParent().revalidate();
            servicio.getParent().repaint();
            
        });
        
        
        fila++;
        gbc.gridx = 0;
        gbc.gridy = fila;
        panel.add(new JLabel("Fecha de ingreso: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = fila;
        SpinnerDateModel fechador = new SpinnerDateModel(
                new Date(2026, 05, 15), // Valor inicial
                null, // Fecha mínima (null = sin límite)
                null, // Fecha máxima (null = sin límite)
                Calendar.DAY_OF_MONTH // Campo que incrementa al girar
        );

        JSpinner fecha = new JSpinner(fechador);
        panel.add(fecha, gbc);
        
        fila++;
        gbc.gridx = 0;
        gbc.gridy = fila;
        JButton registrar = new JButton("Registrar");
        registrar.setBackground(Window.deepNavy);
        registrar.setOpaque(true);
        registrar.setBorderPainted(false);
        registrar.setFocusPainted(false);
        registrar.setForeground(Window.mist);
        panel.add(registrar, gbc);
        
        return panel;
    }
    
    public JPanel createPanelDelete()
    {
        JPanel panel = new JPanel();
        panel.setBackground(Window.ice);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        int fila = 0;
        gbc.gridx = 0;
        gbc.gridy =  fila;
        panel.add(new JLabel("Placas: "));
        
        gbc.gridx = 1;
        JTextField placa = new JTextField(20);
        panel.add(placa, gbc);
        
        fila++;
        gbc.gridx = 0;
        gbc.gridy = fila;
        JButton eliminar = new JButton("Eliminar");
        eliminar.setBackground(Window.deepNavy);
        eliminar.setOpaque(true);
        eliminar.setBorderPainted(false);
        eliminar.setFocusPainted(false);
        eliminar.setForeground(Window.mist);
        panel.add(eliminar, gbc);
        
        
        return panel;
    }
    
    public JPanel createPanelFind()
    {
        JPanel panel = new JPanel();
        panel.setBackground(Window.ice);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        int fila = 0;
        gbc.gridx = 0;
        gbc.gridy =  fila;
        panel.add(new JLabel("Placas: "));
        
        gbc.gridx = 1;
        JTextField placa = new JTextField(20);
        panel.add(placa, gbc);
        
        fila++;
        gbc.gridx = 0;
        gbc.gridy = fila;
        JButton eliminar = new JButton("Encontrar");
        eliminar.setBackground(Window.deepNavy);
        eliminar.setOpaque(true);
        eliminar.setBorderPainted(false);
        eliminar.setFocusPainted(false);
        eliminar.setForeground(Window.mist);
        panel.add(eliminar, gbc);
        
        
        return panel;
    }
    
    public JPanel createPanelEdit()
    {
        JPanel panel = new JPanel();
        panel.setBackground(Window.ice);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        int fila = 0;
        gbc.gridx = 0;
        gbc.gridy =  fila;
        panel.add(new JLabel("Placas: "));
        
        gbc.gridx = 1;
        JTextField placa = new JTextField(20);
        panel.add(placa, gbc);
        
        fila++;
        gbc.gridx = 0;
        gbc.gridy = fila;
        JButton eliminar = new JButton("Modificar");
        eliminar.setBackground(Window.deepNavy);
        eliminar.setOpaque(true);
        eliminar.setBorderPainted(false);
        eliminar.setFocusPainted(false);
        eliminar.setForeground(Window.mist);
        panel.add(eliminar, gbc);
        
        
        return panel;
    }

}
