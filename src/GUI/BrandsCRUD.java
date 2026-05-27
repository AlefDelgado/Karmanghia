/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import CONTROL.Ciudad;
import CONTROL.DataBase;
import CONTROL.Marca;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alefdelgado
 */
public class BrandsCRUD extends JPanel
{

    Window ventana;
    DefaultTableModel marcasTableModel;
    JTable marcasTable;
    JTextField nombreMarcaField;
    JButton editarButton, eliminarButton;
    int filaSeleccionada = -1;
    
    public static DefaultComboBoxModel<Marca> marcaComboBoxModel;

    public BrandsCRUD(Window ventana)
    {
        this.ventana = ventana;
        setBackground(Window.appBg);
        setLayout(new BorderLayout());
        
        add(createPanelAdd());
    }

    public JPanel createPanelAdd()
    {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Window.appBg);

        
        JScrollPane scroll = CrearTablaScroll();
        JPanel campos = crearCampos();
        
        panel.add(scroll, BorderLayout.WEST);
        panel.add(campos, BorderLayout.CENTER);
        
        return panel;
    }
    
    public JScrollPane CrearTablaScroll()
    {
        String[] columnas =
        {
            "Marca"
        };
        marcasTableModel = new DefaultTableModel(columnas, 0)
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };

        for (Marca m : DataBase.marcas)
        {
            marcasTableModel.addRow(new Object[]
            {
                m.getNom()
            });
        }

        marcasTable = new JTable(marcasTableModel);
        marcasTable.setFont(new Font("Segoe UI", Font.BOLD, 12));
        marcasTable.setBackground(Window.border);
        marcasTable.setForeground(Window.mist);
        marcasTable.setGridColor(Window.surface);
        marcasTable.getTableHeader().setBackground(Window.rootBg);
        marcasTable.getTableHeader().setForeground(Window.mist);
        marcasTable.setFillsViewportHeight(true);
        marcasTable.setRowHeight(50);
        marcasTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        marcasTable.getSelectionModel().addListSelectionListener(e ->
        {
            if (!e.getValueIsAdjusting())
            {
                filaSeleccionada = marcasTable.getSelectedRow();
                if (filaSeleccionada >= 0)
                {
                    String nombre = (String) marcasTableModel.getValueAt(filaSeleccionada, 0);
                    nombreMarcaField.setText(nombre);
                    editarButton.setEnabled(true);
                    eliminarButton.setEnabled(true);
                }
            }
        });

        JScrollPane scroll = new JScrollPane(marcasTable);
        scroll.setPreferredSize(new Dimension(800, 600));
        
        return scroll;
    }
    
    public JPanel crearCampos()
    {
        JPanel campos = new JPanel(new GridBagLayout());
        campos.setBackground(Window.appBg);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        int fila = 0;

        gbc.gridx = 0;
        gbc.gridy = fila;
        JLabel labelNombre = new JLabel("Nombre: ");
        labelNombre.setForeground(Window.mist);
        campos.add(labelNombre, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        nombreMarcaField = new JTextField(15);
        campos.add(nombreMarcaField, gbc);

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
            String nombre = nombreMarcaField.getText().trim();
            if (nombre.isEmpty())
            {
                return;
            }

            DataBase.marcas = DataBase.insertarMarca(new Marca(nombre));

            marcasTableModel.addRow(new Object[]
            {
                nombre
            });
            nombreMarcaField.setText("");
        });
        campos.add(registrarButton, gbc);

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
            String nuevoNombre = nombreMarcaField.getText().trim();
            if (nuevoNombre.isEmpty())
            {
                return;
            }

            DataBase.marcas = DataBase.editarMarca(filaSeleccionada, new Marca(nuevoNombre));

            marcasTableModel.setValueAt(nuevoNombre, filaSeleccionada, 0);
            nombreMarcaField.setText("");
            marcasTable.clearSelection();
            filaSeleccionada = -1;
            editarButton.setEnabled(false);
            eliminarButton.setEnabled(false);
        });
        campos.add(editarButton, gbc);

        fila++;
        gbc.gridy = fila;
        eliminarButton = Window.crearBotonPeligro("ELIMINAR");
        eliminarButton.setOpaque(true);
        eliminarButton.setBorderPainted(false);
        eliminarButton.setFocusPainted(false);
        eliminarButton.setEnabled(false);
        eliminarButton.addActionListener(e ->
        {
            if (filaSeleccionada < 0)
            {
                return;
            }

            DataBase.marcas = DataBase.eliminarMarca(filaSeleccionada);

            marcasTableModel.removeRow(filaSeleccionada);
            nombreMarcaField.setText("");
            marcasTable.clearSelection();
            filaSeleccionada = -1;
            editarButton.setEnabled(false);
            eliminarButton.setEnabled(false);
        });
        campos.add(eliminarButton, gbc);
        return campos;
    }
}
