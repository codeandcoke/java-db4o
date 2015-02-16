package org.sfaci.holadb4o.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.sfaci.holadb4o.base.Tienda;
import org.sfaci.holadb4o.util.Util.Accion;

import com.toedter.calendar.JDateChooser;

/**
 * Ventana para recogida de datos de Tiendas
 * @author Santiago Faci
 * @version curso 2014-2015
 *
 */
public class JTienda extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField tfNombre;
	private JTextField tfDescripcion;
	private JTextField tfNumeroLocal;
	private JDateChooser dcFechaApertura;
	
	private Tienda tienda;
	private Accion accion;
	private boolean esNueva = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JTienda dialog = new JTienda();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Accion mostrarDialogo() {
		
		setVisible(true);
		
		return accion;
	}
	
	public Tienda getTienda() {
		return tienda;
	}
	
	public void setTienda(Tienda tienda) {
		
		esNueva = false;
		this.tienda = tienda;
		
		tfNombre.setText(tienda.getNombre());
		tfDescripcion.setText(tienda.getDescripcion());
		tfNumeroLocal.setText(String.valueOf(tienda.getNumeroLocal()));
		dcFechaApertura.setDate(tienda.getFechaApertura());
	}
	
	private void aceptar() {
		
		if (esNueva)
			tienda = new Tienda();
		
		tienda.setNombre(tfNombre.getText());
		tienda.setDescripcion(tfDescripcion.getText());
		if (tfNumeroLocal.getText().equals(""))
			tfNumeroLocal.setText("0");
		tienda.setNumeroLocal(Integer.parseInt(tfNumeroLocal.getText()));
		tienda.setFechaApertura(dcFechaApertura.getDate());
		
		accion = Accion.ACEPTAR;
		setVisible(false);
	}
	
	private void cancelar() {
		
		tienda = null;
		accion = Accion.CANCELAR;
		
		setVisible(false);
	}

	/**
	 * Create the dialog.
	 */
	public JTienda() {
		setModal(true);
		setTitle("Tienda");
		setBounds(100, 100, 357, 269);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getLblNewLabel());
		contentPanel.add(getLblNewLabel_1());
		contentPanel.add(getLblNewLabel_2());
		contentPanel.add(getLblNewLabel_3());
		contentPanel.add(getTfNombre());
		contentPanel.add(getTfDescripcion());
		contentPanel.add(getTfNumeroLocal());
		contentPanel.add(getDcFechaApertura());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						aceptar();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancelar();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setLocationRelativeTo(null);
	}
	public JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Nombre");
			lblNewLabel.setBounds(10, 41, 46, 14);
		}
		return lblNewLabel;
	}
	public JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Descripción");
			lblNewLabel_1.setBounds(10, 75, 72, 14);
		}
		return lblNewLabel_1;
	}
	public JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("Núm. Local");
			lblNewLabel_2.setBounds(10, 112, 72, 14);
		}
		return lblNewLabel_2;
	}
	public JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("Fecha Apertura");
			lblNewLabel_3.setBounds(10, 152, 104, 14);
		}
		return lblNewLabel_3;
	}
	public JTextField getTfNombre() {
		if (tfNombre == null) {
			tfNombre = new JTextField();
			tfNombre.setBounds(110, 41, 141, 20);
			tfNombre.setColumns(10);
		}
		return tfNombre;
	}
	public JTextField getTfDescripcion() {
		if (tfDescripcion == null) {
			tfDescripcion = new JTextField();
			tfDescripcion.setBounds(110, 75, 199, 20);
			tfDescripcion.setColumns(10);
		}
		return tfDescripcion;
	}
	public JTextField getTfNumeroLocal() {
		if (tfNumeroLocal == null) {
			tfNumeroLocal = new JTextField();
			tfNumeroLocal.setBounds(110, 112, 72, 20);
			tfNumeroLocal.setColumns(10);
		}
		return tfNumeroLocal;
	}
	public JDateChooser getDcFechaApertura() {
		if (dcFechaApertura == null) {
			dcFechaApertura = new JDateChooser();
			dcFechaApertura.setBounds(110, 149, 95, 20);
		}
		return dcFechaApertura;
	}
}
