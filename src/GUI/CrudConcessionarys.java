package GUI;

import CONTROL.Branch;
import CONTROL.Brand;
import CONTROL.City;
import ESTRUCTURAS.MultiLista;
import ESTRUCTURAS.Nodo2;
import ESTRUCTURAS.Nodo25;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import poo.Archivos;

/**
 *
 * @author alefdelgado
 */
public class CrudConcessionarys extends JPanel
{

    JComboBox<Brand> marcaBox;
    public Window ventana;

    City[] ciudades =
    {
        new City("Todas"),
        new City("Toluca"),
        new City("Otra")
    };

    Brand[] marcas =
    {
        new Brand("Todas"),
        new Brand("Ford"),
        new Brand("VolksWagen"),
        new Brand("Mercedes Benz"),
        new Brand("Toyota"),
    };

    public CrudConcessionarys(Window ventana)
    {
        this.ventana = ventana;
        setLayout(new BorderLayout());
        setBackground(Window.mist);

        JPanel bar = createMenuBar();
        JPanel newSucursal = crearSucursal();
        JPanel contenedor = createPanelSucursales();
        JScrollPane scroll = new JScrollPane(contenedor);
        scroll.getVerticalScrollBar().setUnitIncrement(8);

        add(bar, BorderLayout.NORTH);
        add(newSucursal, BorderLayout.SOUTH);
        add(scroll, BorderLayout.CENTER);

    }

    public JPanel createMenuBar()
    {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(0, 40));
        panel.setBackground(Window.steel);

        panel.add(new JLabel("Ciudad: "));

        JComboBox<City> ciudadesBox = new JComboBox<>(ciudades);
        panel.add(ciudadesBox);

        JLabel especifiqueCiudad = new JLabel("Especifique: ");
        especifiqueCiudad.setVisible(false);
        panel.add(especifiqueCiudad);

        JTextField nuevaCiudadField = new JTextField(10);
        nuevaCiudadField.setVisible(false);
        panel.add(nuevaCiudadField);

        JButton addCityButton = new JButton("Agregar");
        addCityButton.setBackground(Window.midnigth);
        addCityButton.setForeground(Window.mist);
        addCityButton.setOpaque(true);
        addCityButton.setBorderPainted(false);
        addCityButton.setFocusPainted(false);
        addCityButton.setVisible(false);
        panel.add(addCityButton);
        panel.add(new JSeparator());

        ciudadesBox.addActionListener(e ->
        {
            City selection = (City) ciudadesBox.getSelectedItem();
            boolean esOtro = selection.getNom().equals("Otra");

            especifiqueCiudad.setVisible(esOtro);
            nuevaCiudadField.setVisible(esOtro);
            addCityButton.setVisible(esOtro);

            ciudadesBox.getParent().revalidate();
            ciudadesBox.getParent().repaint();
        });

        panel.add(new JLabel("Marca: "));

        marcaBox = new JComboBox<>(marcas);
        panel.add(marcaBox);
        return panel;
    }

    public JPanel crearSucursal()
    {
        JPanel panel = new JPanel();
        panel.setBackground(Window.steel);
        panel.setPreferredSize(new Dimension(0, 40));

        panel.add(new JLabel("Nombre: "));

        JTextField nombreField = new JTextField(20);
        panel.add(nombreField);

        panel.add(new JLabel("Ciudad: "));

        DefaultComboBoxModel<City> modeloCiudades = new DefaultComboBoxModel<>();
        for (City b : ciudades)
        {
            if (!b.getNom().equals("Otra") && !b.getNom().equals("Todas"))
            {
                modeloCiudades.addElement(b);
            }
        }

        JComboBox<City> ciudadBox = new JComboBox<>(modeloCiudades);
        panel.add(ciudadBox);

        JButton addSucursalButton = new JButton("Agregar");
        addSucursalButton.setBackground(Window.midnigth);
        addSucursalButton.setForeground(Window.mist);
        addSucursalButton.setOpaque(true);
        addSucursalButton.setBorderPainted(false);
        addSucursalButton.setFocusPainted(false);
        panel.add(addSucursalButton);
        panel.add(new JSeparator());
        Path ruta = Paths.get("dataBase.dat");
        MultiLista ml = new MultiLista();
        String concesionarios[] =
        {
            "Ciudad", "Marca", "Sucursal", "Servicio", "Carro"
        };
        if (Files.exists(ruta) && !Files.isDirectory(ruta))
        {
            ml = (ESTRUCTURAS.MultiLista) Archivos.carga("dataBase.dat");
            System.out.println("");
        } else
        {
            Nodo25 root = new Nodo25(ciudades[1].toString(), ciudades[1]);
            //ml.setR(ml.inserta(root, concesionarios, 3, null));
            for (City ciudad : ciudades)
            {
                if (!ciudad.getNom().equals("Otra") && !ciudad.getNom().equals("Todas"))
                {
                    Nodo25 city = (Nodo25) ml.inserta(new Nodo25(ciudad.getNom(), ciudad), concesionarios, 4, (Nodo25) ml.getR());
                    ml.setR(city);
                    for (Brand marca : marcas)
                    {
                        if (!marca.getNom().equals("Otra") && !marca.getNom().equals("Todas"))
                        {
                            ml.inserta(new Nodo25(marca.getNom(), marca), concesionarios, 3, city);
                        }
                    }
                }
            }
            new CONTROL.DataBase(ml);
        }
        addSucursalButton.addActionListener(e ->
        {
            CONTROL.Branch sucursal = new Branch(nombreField.getText(), ciudadBox.getSelectedItem().toString(), "Diego García");
            ESTRUCTURAS.MultiLista multilista = (ESTRUCTURAS.MultiLista) Archivos.carga("dataBase.dat");
            Nodo25 ciudadEncontrada = multilista.busca((Nodo25) multilista.getR(), ciudadBox.getSelectedItem().toString());
            Nodo25 marcaEncontrada = multilista.busca(ciudadEncontrada.getAbj(), marcaBox.getSelectedItem().toString());
            String marcaEnEspecifico[]={"Marca","Sucursal","Servicio","Carro"};
            multilista.inserta(new Nodo25(sucursal.getNom(),sucursal), marcaEnEspecifico, 2, marcaEncontrada);
            System.out.println("");
            new CONTROL.DataBase(multilista);
        });
        return panel;
    }

    public JPanel createPanelSucursales()
    {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Window.mist);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        int fila = 0;

        for (int i = 0; i < 50; i++)
        {
            fila++;
            gbc.gridx = 0;
            gbc.gridy = fila;
            String texto = (String.valueOf(i + 1));
            JButton btn1 = new JButton(texto);
            btn1.setPreferredSize(new Dimension(250, 250));
            panel.add(btn1, gbc);

            gbc.gridx = 2;
            gbc.gridy = fila;
            JButton btn2 = new JButton(texto);
            btn2.setPreferredSize(new Dimension(250, 250));
            panel.add(btn2, gbc);

            gbc.gridx = 3;
            gbc.gridy = fila;
            JButton btn3 = new JButton(texto);
            btn3.setPreferredSize(new Dimension(250, 250));
            panel.add(btn3, gbc);

            gbc.gridx = 4;
            gbc.gridy = fila;
            JButton btn4 = new JButton(texto);
            btn4.setPreferredSize(new Dimension(250, 250));
            panel.add(btn4, gbc);
        }

        return panel;
    }

}
