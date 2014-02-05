package org.sfaci.holadb4o.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.sfaci.holadb4o.base.Tienda;
import org.sfaci.holadb4o.util.Constantes;
import org.sfaci.holadb4o.util.Util;
import org.sfaci.holadb4o.util.Util.Accion;

import static org.sfaci.holadb4o.util.Constantes.TIENDA;
import static org.sfaci.holadb4o.util.Constantes.CENTRO_COMERCIAL;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextField;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		case CENTRO_COMERCIAL:
			// TODO
		default:
		}
	}
	
	/**
	 * TODO Modifica Tiendas o Centros Comerciales
	 */
	private void modificar() {
		
	}
	
	/**
	 * TODO Elimina Tiendas o Centros Comercial
	 */
	private void eliminar() {
		
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
		}
		return toolBar;
	}
	public JTabbedPane getTab() {
		if (tab == null) {
			tab = new JTabbedPane(JTabbedPane.TOP);
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
		}
		return tablaTiendas;
	}
	public JTable getTablaCentros() {
		if (tablaCentros == null) {
			tablaCentros = new JTable();
		}
		return tablaCentros;
	}
}
