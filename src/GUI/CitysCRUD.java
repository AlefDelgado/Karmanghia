/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import CONTROL.Ciudad;
import CONTROL.DataBase;
import CONTROL.Marca;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Collection;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alefdelgado
 */
public class CitysCRUD extends JPanel
{
    Window ventana;
    static String[] columnas =
        {
            "Marca",
            "Ciudad"
        };
    public static DefaultTableModel ciudadesTableModel = new DefaultTableModel(columnas, 0)
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
    
    JTable ciudadesTable;
    JTextField nombreCiudadField;
    JButton editarButton, eliminarButton;
    int filaSeleccionada = -1;
    JComboBox<Marca> marcasComboBox = new JComboBox<>(DataBase.marcaComboBoxModel);
    
    String marcaSeleccionada;
    String ciudadSeleccionada;
    
    public CitysCRUD(Window ventana)
    {
        
        this.ventana = ventana;
        setLayout(new BorderLayout());
        JScrollPane tablaCiudades = CrearTablaScroll();
        JPanel campos = crearCampos();
        
        add(tablaCiudades, BorderLayout.WEST);
        add(campos, BorderLayout.CENTER);
        
        
    }
    
    public JScrollPane CrearTablaScroll()
    {
        
        

        ciudadesTable = new JTable(ciudadesTableModel);
        ciudadesTable.setFont(new Font("Segoe UI", Font.BOLD, 12));
        ciudadesTable.setBackground(Window.border);
        ciudadesTable.setForeground(Window.mist);
        ciudadesTable.setGridColor(Window.surface);
        ciudadesTable.getTableHeader().setBackground(Window.rootBg);
        ciudadesTable.getTableHeader().setForeground(Window.mist);
        ciudadesTable.setFillsViewportHeight(true);
        ciudadesTable.setRowHeight(50);
        ciudadesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ciudadesTable.getSelectionModel().addListSelectionListener(e ->
        {
            if (!e.getValueIsAdjusting())
            {
                filaSeleccionada = ciudadesTable.getSelectedRow();
                if (filaSeleccionada >= 0)
                {
                    
                    marcaSeleccionada = (String) ciudadesTableModel.getValueAt(filaSeleccionada, 0);
                    ciudadSeleccionada = (String) ciudadesTableModel.getValueAt(filaSeleccionada, 1);
                    for (int i = 0; i < marcasComboBox.getItemCount(); i++) {
                        Marca eqq = marcasComboBox.getItemAt(i);
                        
                        if (eqq.getNom().equals(marcaSeleccionada)) {
                            marcasComboBox.setSelectedItem(eqq);
                            break;
                        }
                    }
                    
                    nombreCiudadField.setText(ciudadSeleccionada);
                    
                    editarButton.setEnabled(true);
                    eliminarButton.setEnabled(true);
                }
            }
        });

        JScrollPane scroll = new JScrollPane(ciudadesTable);
        scroll.setPreferredSize(new Dimension(800, 600));
        
        return scroll;
    }
    
    public JPanel crearCampos()
    {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Window.appBg);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        int fila = 0;
        gbc.gridx = 0;
        gbc.gridy = fila;
        JLabel ciudadLbl = new JLabel("Marca: ");
        ciudadLbl.setForeground(Window.mist);
        panel.add(ciudadLbl, gbc);
        
        gbc.gridx = 1;
        
        
        marcasComboBox.setRenderer(new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,int index, boolean isSelected, boolean cellHasFocus) {

                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (isSelected) {
                    setBackground(Window.surface); // azul al hover
                    setForeground(Window.mist);
                    setOpaque(true);
                } else {
                    setBackground(Window.border);
                    setForeground(Window.mist);
                    setOpaque(true);
                }

                setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // padding
                return this;
            }
        });
        marcasComboBox.setPreferredSize(new Dimension(160, 20));
        panel.add(marcasComboBox, gbc);
        
        fila++;
        gbc.gridx = 0;
        gbc.gridy = fila;
        JLabel labelNombre = new JLabel("Nombre: ");
        labelNombre.setForeground(Window.mist);
        panel.add(labelNombre, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        nombreCiudadField = new JTextField(15);
        panel.add(nombreCiudadField, gbc);

        fila++;
        gbc.gridx = 0;
        gbc.gridy = fila;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton registrarButton = Window.crearBotonExito("REGISTRAR");
        registrarButton.setOpaque(true);
        registrarButton.setBorderPainted(false);
        registrarButton.setFocusPainted(false);
        registrarButton.addActionListener(e ->
        {
            String nombreCiudad = nombreCiudadField.getText().trim();
            String selectedMarca =  marcasComboBox.getSelectedItem().toString();
            if (nombreCiudad.isEmpty())
            {
                return;
            }

            DataBase.ciudades = DataBase.insertarCiudad(selectedMarca, new Ciudad(nombreCiudad));
            
            ciudadesTableModel.addRow(new Object[]{selectedMarca, nombreCiudad});
            nombreCiudadField.setText("");
        });
        panel.add(registrarButton, gbc);

        fila++;
        gbc.gridy = fila;
        editarButton = Window.crearBotonSecundario("EDITAR");
        editarButton.setOpaque(true);
        editarButton.setBorderPainted(false);
        editarButton.setFocusPainted(false);
        editarButton.setEnabled(false);
        editarButton.addActionListener(e ->
        {
            if (filaSeleccionada < 0)
            {
                return;
            }
            String nuevoNombre = nombreCiudadField.getText().trim();
            if (nuevoNombre.isEmpty())
            {
                return;
            }
            
            // marcaSeleccionada y ciudadSeleccionada se poblan al seleccionar la fila
            DataBase.ciudades = DataBase.editarCiudad(filaSeleccionada, new Ciudad(nuevoNombre), marcaSeleccionada, ciudadSeleccionada);

            ciudadesTableModel.setValueAt(nuevoNombre, filaSeleccionada, 1); // columna 1 = Ciudad
            nombreCiudadField.setText("");
            ciudadesTable.clearSelection();
            filaSeleccionada = -1;
            editarButton.setEnabled(false);
            eliminarButton.setEnabled(false);
        });
        panel.add(editarButton, gbc);

        fila++;
        gbc.gridy = fila;
        eliminarButton = Window.crearBotonPeligro("ELIMINAR");
        eliminarButton.setOpaque(true);
        eliminarButton.setBorderPainted(false);
        eliminarButton.setFocusPainted(false);
        eliminarButton.setEnabled(false);
        eliminarButton.addActionListener(e ->
        {
            String nombreCiudad = nombreCiudadField.getText().trim();
            String selectedMarca =  marcasComboBox.getSelectedItem().toString();
            if (filaSeleccionada < 0)
            {
                return;
            }

            DataBase.ciudades = DataBase.eliminarCiudad(filaSeleccionada, nombreCiudad, selectedMarca);

            ciudadesTableModel.removeRow(filaSeleccionada);
            nombreCiudadField.setText("");
            ciudadesTable.clearSelection();
            filaSeleccionada = -1;
            editarButton.setEnabled(false);
            eliminarButton.setEnabled(false);
        });
        panel.add(eliminarButton, gbc);
        return panel;
    }
    
    
}
