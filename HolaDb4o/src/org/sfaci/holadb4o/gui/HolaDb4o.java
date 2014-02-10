package org.sfaci.holadb4o.gui;

import static org.sfaci.holadb4o.util.Constantes.CENTRO_COMERCIAL;
import static org.sfaci.holadb4o.util.Constantes.TIENDA;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.sfaci.holadb4o.base.Tienda;
import org.sfaci.holadb4o.util.Constantes;
import org.sfaci.holadb4o.util.Util;
import org.sfaci.holadb4o.util.Util.Accion;

import com.db4o.Db4oEmbedded;

/**
 * Ejemplo que pruebas las principales características de db4o
 * @author Santiago Faci
 * @version 1.0
 *
 */
public class HolaDb4o {

	private JFrame frmHolaDbo;
	private JLabel lbEstado;
	private JMenuBar menuBar;
	private JToolBar toolBar;
	private JTabbedPane tab;
	private JPanel panelTiendas;
	private JPanel panelCentros;
	private JMenu mnPrograma;
	private JMenu mnHerramientas;
	private JMenu mnAyuda;
	private JButton btAlta;
	private JButton btModificar;
	private JButton btEliminar;
	
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JTablaTiendas tablaTiendas;
	private JTable tablaCentros;
	private JButton tfBuscar;
	private JTextField tfFiltro;
	private JComboBox<String> cbCampos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HolaDb4o window = new HolaDb4o();
					window.frmHolaDbo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HolaDb4o() {
		
		initialize();
		inicializar();
	}
	
	/**
	 * Inicializa la aplicación
	 */
	private void inicializar() {
		
		conectar();
		tablaTiendas.listar();
	}
	
	/**
	 * Conecta con la Base de Datos
	 */
	private void conectar() {
		
		// Conecta con la Base de Datos (si el fichero no existe lo creará)
		Util.db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), Constantes.DATABASE_FILENAME);
	}
	
	/**
	 * Cierra la aplicación
	 */
	private void cerrar() {
		
		Util.db.close();
		System.exit(0);
	}
	
	/**
	 * TODO Da de alta Tiendas o Centros Comerciales
	 */
	private void darDeAlta() {
		
		switch (tab.getSelectedIndex()) {
		case TIENDA:
			JTienda jTienda = new JTienda();
			if (jTienda.mostrarDialogo() == Accion.CANCELAR)
				return;
			
			Tienda tienda = jTienda.getTienda();
			Util.db.store(tienda);
			Util.db.commit();
			
			tablaTiendas.listar();
		case CENTRO_COMERCIAL:
			// TODO
		default:
		}
	}
	
	/**
	 * TODO Modifica Tiendas o Centros Comerciales
	 */
	private void modificar() {
		
		switch (tab.getSelectedIndex()) {
		case TIENDA:
			JTienda jTienda = new JTienda();
			jTienda.setTienda(tablaTiendas.getTiendaSeleccionada());
			if (jTienda.mostrarDialogo() == Accion.CANCELAR)
				return;
			
			Tienda tienda = jTienda.getTienda();
			Util.db.store(tienda);
			Util.db.commit();
			
			tablaTiendas.listar();
		case CENTRO_COMERCIAL:
			// TODO
		default:
		}
	}
	
	/**
	 * TODO Elimina Tiendas o Centros Comercial
	 */
	private void eliminar() {
	
		switch (tab.getSelectedIndex()) {
		case TIENDA:
			Tienda tienda = tablaTiendas.getTiendaSeleccionada();
			Util.db.delete(tienda);
			Util.db.commit();
			
			tablaTiendas.listar();
		case CENTRO_COMERCIAL:
			// TODO
		default:
		}
	}
	
	/**
	 * TODO Busca en Tiendas o Centros Comerciales
	 */
	private void buscar() {
	
		int campo = cbCampos.getSelectedIndex();
		tablaTiendas.listar(tfFiltro.getText(), campo);
	}
	
	/**
	 * TODO Cambia de pestaña. Hay que recargar el combo de campos
	 */
	private void cambiarPestana() {
		
		switch (tab.getSelectedIndex()) {
		case TIENDA:
			cbCampos.removeAllItems();
			cbCampos.addItem("<Todos>");
			cbCampos.addItem(Constantes.NOMBRE);
			cbCampos.addItem(Constantes.DESCRIPCION);
			cbCampos.addItem(Constantes.NUMERO_LOCAL);
			cbCampos.addItem(Constantes.FECHA_APERTURA);
			break;
		case CENTRO_COMERCIAL:
			cbCampos.removeAllItems();
			// TODO
			break;
		default:
		}
	}
	
	/**
	 * TODO Selecciona alguna tienda de la tabla
	 */
	private void seleccionarTiendas() {
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHolaDbo = new JFrame();
		frmHolaDbo.setTitle("Hola db4o");
		frmHolaDbo.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				cerrar();
			}
		});
		frmHolaDbo.setBounds(100, 100, 450, 300);
		frmHolaDbo.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmHolaDbo.getContentPane().add(getTfEstado(), BorderLayout.SOUTH);
		frmHolaDbo.getContentPane().add(getToolBar(), BorderLayout.NORTH);
		frmHolaDbo.getContentPane().add(getTab(), BorderLayout.CENTER);
		frmHolaDbo.setJMenuBar(getMenuBar());
		frmHolaDbo.setLocationRelativeTo(null);
	}

	public JLabel getTfEstado() {
		if (lbEstado == null) {
			lbEstado = new JLabel();
			lbEstado.setText("Grancasas v1.0");
		}
		return lbEstado;
	}
	public JMenuBar getMenuBar() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnPrograma());
			menuBar.add(getMnHerramientas());
			menuBar.add(getMnAyuda());
		}
		return menuBar;
	}
	public JToolBar getToolBar() {
		if (toolBar == null) {
			toolBar = new JToolBar();
			toolBar.add(getBtAlta());
			toolBar.add(getBtModificar());
			toolBar.add(getBtEliminar());
			toolBar.add(getTfFiltro());
			toolBar.add(getCbCampos());
			toolBar.add(getTfBuscar());
		}
		return toolBar;
	}
	public JTabbedPane getTab() {
		if (tab == null) {
			tab = new JTabbedPane(JTabbedPane.TOP);
			tab.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					cambiarPestana();
				}
			});
			tab.addTab("Tiendas", null, getPanelTiendas(), null);
			tab.addTab("Centros Comerciales", null, getPanelCentros(), null);
		}
		return tab;
	}
	public JPanel getPanelTiendas() {
		if (panelTiendas == null) {
			panelTiendas = new JPanel();
			panelTiendas.setLayout(new BorderLayout(0, 0));
			panelTiendas.add(getScrollPane(), BorderLayout.CENTER);
		}
		return panelTiendas;
	}
	public JPanel getPanelCentros() {
		if (panelCentros == null) {
			panelCentros = new JPanel();
			panelCentros.setLayout(new BorderLayout(0, 0));
			panelCentros.add(getScrollPane_1(), BorderLayout.CENTER);
		}
		return panelCentros;
	}
	public JMenu getMnPrograma() {
		if (mnPrograma == null) {
			mnPrograma = new JMenu("Fichero");
		}
		return mnPrograma;
	}
	public JMenu getMnHerramientas() {
		if (mnHerramientas == null) {
			mnHerramientas = new JMenu("Herramientas");
		}
		return mnHerramientas;
	}
	public JMenu getMnAyuda() {
		if (mnAyuda == null) {
			mnAyuda = new JMenu("Ayuda");
		}
		return mnAyuda;
	}
	public JButton getBtAlta() {
		if (btAlta == null) {
			btAlta = new JButton("Alta");
			btAlta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					darDeAlta();
				}
			});
		}
		return btAlta;
	}
	public JButton getBtModificar() {
		if (btModificar == null) {
			btModificar = new JButton("Modificar");
			btModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modificar();
				}
			});
		}
		return btModificar;
	}
	public JButton getBtEliminar() {
		if (btEliminar == null) {
			btEliminar = new JButton("Eliminar");
			btEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					eliminar();
				}
			});
		}
		return btEliminar;
	}
	public JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTablaTiendas());
		}
		return scrollPane;
	}
	public JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setViewportView(getTablaCentros());
		}
		return scrollPane_1;
	}
	public JTable getTablaTiendas() {
		if (tablaTiendas == null) {
			tablaTiendas = new JTablaTiendas();
			tablaTiendas.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					seleccionarTiendas();
				}
			});
		}
		return tablaTiendas;
	}
	public JTable getTablaCentros() {
		if (tablaCentros == null) {
			tablaCentros = new JTable();
		}
		return tablaCentros;
	}
	public JButton getTfBuscar() {
		if (tfBuscar == null) {
			tfBuscar = new JButton("Buscar");
			tfBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buscar();
				}
			});
		}
		return tfBuscar;
	}
	public JTextField getTfFiltro() {
		if (tfFiltro == null) {
			tfFiltro = new JTextField();
			tfFiltro.setColumns(10);
		}
		return tfFiltro;
	}
	public JComboBox<String> getCbCampos() {
		if (cbCampos == null) {
			cbCampos = new JComboBox<String>();
			cbCampos.setPreferredSize(new Dimension(90, 20));
		}
		return cbCampos;
	}
}
