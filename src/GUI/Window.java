package GUI;

import CONTROL.DataBase;
import cjb.ci.Mensajes;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.spi.LocaleServiceProvider;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.SeparatorUI;

/**
 *
 * @author alefdelgado
 */
public class Window extends JFrame
{
    // Paleta de colores (De canva, Mauro, no de la IA)

    // Las de Alberto
    public static final Color AZUL_PRINCIPAL = new Color(13, 71, 161);     // Azul oscuro
    public static final Color AZUL_SECUNDARIO = new Color(25, 118, 210);   // Azul medio
    public static final Color AZUL_CLARO = new Color(66, 165, 245);        // Azul claro
    public static final Color AZUL_MUY_CLARO = new Color(227, 242, 253);   // Azul muy claro
    public static final Color VERDE_EXITO = new Color(46, 125, 50);        // Verde
    public static final Color ROJO_PELIGRO = new Color(198, 40, 40);       // Rojo
    public static final Color GRIS_TEXTO = new Color(66, 66, 66);          // Gris oscuro
    public static final Color GRIS_CLARO = new Color(245, 245, 245);       // Gris muy claro

    // Clara:
    public static final Color midnigth = Color.decode("#0A1628");
    public static final Color deepNavy = Color.decode("#132238");
    public static final Color ocean = Color.decode("#1A3A5C");
    public static final Color ice = Color.decode("#F0F4FA");
    public static final Color mist = Color.decode("#D4DCE8");

    public static final Color cloud = Color.decode("#E8EDF5");
    public static final Color ink = Color.decode("#0A1628");
    public static final Color slate = Color.decode("#5A6A80");
    public static final Color steel = Color.decode("#8A9AB0");
    public static final Color action = Color.decode("#3A7BD5");
    public static final Color alert = Color.decode("#E8A830");
    public static final Color alertHover = Color.decode("#CF901A");

    //Alef
    public static Color appBg = Color.decode("#0D1926");
    public static Color surface = Color.decode("#111E2E");
    public static Color rootBg = Color.decode("#080F1A");
    public static Color border = Color.decode("#1E2D3D");
    public static Color textPrim = Color.decode("#EAF0F8");
    public static Color textMuted = Color.decode("#5A7090");
    public static Color accent = Color.decode("#3A8FE8");
    public static Color gain = Color.decode("#2AAF7F");
    public static Color loss = Color.decode("#E05545");

    private static JLabel titulo;
    private CardLayout cards = new CardLayout();
    private JPanel container = new JPanel(cards);

    public Window(String title)
    {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        container.add(welcome(), "Welcome");
        container.add(new BrandsCRUD(this), "Marcas");
        container.add(new CitysCRUD(this), "Ciudades");
        container.add(new CampusCRUD(this), "Sucursales");
//        container.add(new VehiclesCRUD(this), "Vehiculos");

        JMenuBar bar = createMenuBar();
        setJMenuBar(bar);

        JPanel header = createHeader();
        JPanel buttons = botonera();
        JPanel footer = createFooter();

        add(header, BorderLayout.NORTH);
        add(buttons, BorderLayout.WEST);
        add(container, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
    }

    public JMenuBar createMenuBar()
    {
        JMenuBar barra = new JMenuBar();

        JMenu app = new JMenu("App");

        JMenuItem acercaDe = new JMenuItem("Acerca de");
        acercaDe.addActionListener(e ->
        {
            mostrarDialogoPersonalizado("KarmannGhia", "Sistema de Gestión Vehicular", JOptionPane.INFORMATION_MESSAGE);
        });

        app.add(acercaDe);

        JMenu exportar = new JMenu("Exportar");
        JMenuItem marcas = new JMenuItem("Marcas");
        JMenuItem ciudades = new JMenuItem("Ciudades");
        JMenuItem sucursales = new JMenuItem("Sucursales");
        JMenuItem servicios = new JMenuItem("Servicios");
        JMenuItem autos = new JMenuItem("Autos");

        exportar.add(marcas);
        exportar.add(ciudades);
        exportar.add(sucursales);
        exportar.add(servicios);
        exportar.add(autos);

        JMenu compararPrecios = new JMenu("Comparar Precios");

        barra.add(app);
        barra.add(exportar);
        barra.add(compararPrecios);

        return barra;
    }

    public JPanel createHeader()
    {
        JPanel header = new JPanel();
        header.setBackground(surface);
        header.setLayout(new BorderLayout());
        header.setPreferredSize(new Dimension(0, 80));

        titulo = new JLabel("Bienvenido", SwingConstants.CENTER);
        titulo.setForeground(mist);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));

        header.add(titulo, BorderLayout.CENTER);

        return header;
    }

    public JPanel botonera()
    {

        JPanel botones = new JPanel();
        botones.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 25));
        botones.setBackground(rootBg);
        botones.setPreferredSize(new Dimension(250, 0));

        JButton marcasButton = new JButton("Marcas");
        marcasButton.setBackground(border);
        marcasButton.setForeground(mist);
        marcasButton.setOpaque(true);
        marcasButton.setBorderPainted(false);
        marcasButton.setFocusPainted(false);
        marcasButton.setPreferredSize(new Dimension(200, 90));
        marcasButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        marcasButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addHoverEffect(marcasButton, 200, 90, 230, 100);
        addClickEffect(marcasButton, 230, 100, 170, 80);
        marcasButton.addActionListener(e ->
        {
            mostrar("Marcas");
        });

        JButton ciudadesButton = new JButton("Ciudades");
        ciudadesButton.setBackground(border);
        ciudadesButton.setForeground(mist);
        ciudadesButton.setOpaque(true);
        ciudadesButton.setBorderPainted(false);
        ciudadesButton.setFocusPainted(false);
        ciudadesButton.setPreferredSize(new Dimension(200, 90));
        ciudadesButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        ciudadesButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        addHoverEffect(ciudadesButton, 200, 90, 230, 100);
        addClickEffect(ciudadesButton, 230, 100, 170, 80);
        ciudadesButton.addActionListener(e ->
        {
            if (DataBase.marcas.length > 0 )
            {
                mostrar("Ciudades");
            } else
            {
                Mensajes.error(this, "No existe nunguna marca");
                
            }
            
            
        });

        JButton sucursalesButton = new JButton("Sucursales");
        sucursalesButton.setBackground(border);
        sucursalesButton.setForeground(mist);
        sucursalesButton.setOpaque(true);
        sucursalesButton.setBorderPainted(false);
        sucursalesButton.setFocusPainted(false);
        sucursalesButton.setPreferredSize(new Dimension(200, 90));
        sucursalesButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        sucursalesButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addHoverEffect(sucursalesButton, 200, 90, 230, 100);
        addClickEffect(sucursalesButton, 230, 100, 170, 80);
        sucursalesButton.addActionListener(e ->
        {
            if (DataBase.ciudades.length > 0)
            {
                mostrar("Sucursales");
            } else
            {
                Mensajes.error(this, "Primero deberia tener alguna ciudad");
            }
        });

        JButton ServiciosButton = new JButton("Servícios");
        ServiciosButton.setBackground(border);
        ServiciosButton.setForeground(mist);
        ServiciosButton.setOpaque(true);
        ServiciosButton.setBorderPainted(false);
        ServiciosButton.setFocusPainted(false);
        ServiciosButton.setPreferredSize(new Dimension(200, 90));
        ServiciosButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        ServiciosButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addHoverEffect(ServiciosButton, 200, 90, 230, 100);
        addClickEffect(ServiciosButton, 230, 100, 170, 80);
        ServiciosButton.addActionListener(e ->
        {
            if (DataBase.sucursales.length > 0)
            {
                mostrar("Servicios");
            } else
            {
                Mensajes.error(this, "No existen sucursales disponibles");
            }
        });

        JButton vehicles = new JButton("Vehículos");
        vehicles.setBackground(border);
        vehicles.setForeground(mist);
        vehicles.setOpaque(true);
        vehicles.setBorderPainted(false);
        vehicles.setFocusPainted(false);
        vehicles.setPreferredSize(new Dimension(200, 90));
        addHoverEffect(vehicles, 200, 90, 230, 100);
        addClickEffect(vehicles, 230, 100, 170, 80);
        vehicles.setFont(new Font("Segoe UI", Font.BOLD, 16));
        vehicles.setCursor(new Cursor(Cursor.HAND_CURSOR));
        vehicles.addActionListener(e ->
        {
            mostrar("Vehiculos");
        });

        botones.add(marcasButton, BorderLayout.CENTER);
        botones.add(ciudadesButton, BorderLayout.CENTER);
        botones.add(sucursalesButton, BorderLayout.CENTER);
        botones.add(ServiciosButton, BorderLayout.CENTER);
        botones.add(vehicles, BorderLayout.CENTER);

        return botones;
    }

    /**
     *
     * @return
     */
    public static JPanel welcome()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 150));
        panel.setBackground(appBg);
        panel.setPreferredSize(new Dimension(450, 600));

        ImageIcon logo = new ImageIcon("Logo.png");
        panel.add(new JLabel(logo));

        return panel;
    }

    public JPanel createFooter()
    {
        JPanel header = new JPanel();
        header.setLayout(new GridLayout());
        header.setBackground(surface);
        header.setLayout(new BorderLayout());
        header.setPreferredSize(new Dimension(0, 80));

        JLabel txt = new JLabel("Estructura de Datos - UAEMex 2026", SwingConstants.CENTER);
        txt.setForeground(mist);
        txt.setFont(new Font("Arial", Font.PLAIN, 12));

        header.add(txt, BorderLayout.CENTER);

        return header;
    }

    public void mostrar(String name)
    {
        cards.show(container, name);
        switch (name)
        {
            case "Marcas":
                titulo.setText("Marcas");
                break;
            case "Ciudades":
                titulo.setText("Ciudades");
                break;
            case "Sucursales":
                titulo.setText("Sucursales");
                break;
            case "Servicios":
                titulo.setText("Servicios");
                break;
            case "Vehiculos":
                titulo.setText("Vehiculos");
                break;
            default:
                break;
        }
    }

    public void addHoverEffect(JButton button, int normalW, int normalH, int hoverW, int hoverH)
    {
        Dimension normal = new Dimension(normalW, normalH);
        Dimension hover = new Dimension(hoverW, hoverH);

        button.setPreferredSize(normal);

        final javax.swing.Timer[] timer = new javax.swing.Timer[1];

        button.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e)
            {
                animateButton(button, hover, timer);
                button.setBackground(surface);
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                animateButton(button, normal, timer);
                button.setBackground(border);
            }
        });
    }

    public void addClickEffect(JButton button, int hoverW, int hoverH, int pressedW, int pressedH)
    {
        Dimension hover = new Dimension(hoverW, hoverH);
        Dimension pressed = new Dimension(pressedW, pressedH);

        final javax.swing.Timer[] timer = new javax.swing.Timer[1];

        button.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                animateButton(button, pressed, timer);
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
                if (button.contains(e.getPoint()))
                {
                    // vuelve al tamaño hover
                    animateButton(button, hover, timer);
                }
            }
        });
    }

    private void animateButton(JButton button, Dimension target, javax.swing.Timer[] timer)
    {
        if (timer[0] != null)
        {
            timer[0].stop();
        }

        timer[0] = new javax.swing.Timer(5, null);

        timer[0].addActionListener(e ->
        {
            Dimension current = button.getPreferredSize();

            int w = current.width;
            int h = current.height;

            if (w < target.width)
            {
                w += 2;
            }
            if (w > target.width)
            {
                w -= 2;
            }

            if (h < target.height)
            {
                h += 2;
            }
            if (h > target.height)
            {
                h -= 2;
            }

            button.setPreferredSize(new Dimension(w, h));

            button.revalidate();

            if (Math.abs(w - target.width) <= 2
                    && Math.abs(h - target.height) <= 2)
            {
                button.setPreferredSize(target);
                button.revalidate();
                timer[0].stop();
            }
        });

        timer[0].start();
    }

    public static JButton crearBotonPrimario(String texto)
    {
        JButton boton = new JButton(texto);
        boton.setBackground(AZUL_SECUNDARIO);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                boton.setBackground(AZUL_CLARO);
            }

            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                boton.setBackground(AZUL_SECUNDARIO);
            }
        });

        return boton;
    }

    public static JButton crearBotonSecundario(String texto)
    {
        JButton boton = new JButton(texto);
        boton.setBackground(GRIS_CLARO);
        boton.setForeground(GRIS_TEXTO);
        boton.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(8, 18, 8, 18)
        ));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                boton.setBackground(new Color(230, 230, 230));
            }

            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                boton.setBackground(GRIS_CLARO);
            }
        });

        return boton;
    }

    public static JButton crearBotonPeligro(String texto)
    {
        JButton boton = new JButton(texto);
        boton.setBackground(ROJO_PELIGRO);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                boton.setBackground(new Color(220, 60, 60));
            }

            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                boton.setBackground(ROJO_PELIGRO);
            }
        });

        return boton;
    }

    public static JButton crearBotonWarning(String texto)
    {
        JButton boton = new JButton(texto);
        boton.setBackground(alert);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                boton.setBackground(alertHover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                boton.setBackground(alert);
            }
        });

        return boton;
    }

    public static JButton crearBotonExito(String texto)
    {
        JButton boton = new JButton(texto);
        boton.setBackground(VERDE_EXITO);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                boton.setBackground(new Color(56, 142, 60));
            }

            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                boton.setBackground(VERDE_EXITO);
            }
        });

        return boton;
    }

    public void mostrarDialogoPersonalizado(String titulo, String mensaje, int tipo)
    {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }
}
