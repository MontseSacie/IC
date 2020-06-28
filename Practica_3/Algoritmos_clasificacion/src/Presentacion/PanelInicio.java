package Presentacion;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.Font;
import javax.swing.UIManager;

import Negocio.Auxiliar.LecturaDatos;

import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PanelInicio extends Fondo {
	private JTextField textFichero;

	/**
	 * Create the panel.
	 */
	public PanelInicio() {
		super("./imagenes/fondoIni.jpg");
		
		JLabel lblNewLabel = new JLabel("Algoritmos de clasificaci\u00F3n\r\n");
		lblNewLabel.setToolTipText("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Sitka Text", Font.BOLD, 36));
		lblNewLabel.setForeground(UIManager.getColor("Button.highlight"));
		
		JLabel lblSeleccionaFicheroCon = new JLabel("Selecciona fichero con ejemplos de entrenamiento");
		lblSeleccionaFicheroCon.setForeground(UIManager.getColor("Button.highlight"));
		lblSeleccionaFicheroCon.setFont(new Font("Sitka Text", Font.BOLD, 18));
		
		textFichero = new JTextField();
		textFichero.setEditable(false);
		textFichero.setBackground(SystemColor.inactiveCaptionBorder);
		textFichero.setFont(new Font("Sitka Text", Font.PLAIN, 18));
		textFichero.setColumns(10);
		
		JButton btnAbrir = new JButton("Abrir");
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				JFileChooser jf = new JFileChooser();
				jf.showOpenDialog(jf);
				File archivo = jf.getSelectedFile();
				if (archivo != null) {
					textFichero.setEditable(true);
					textFichero.setText(archivo.getAbsolutePath());
					textFichero.setEditable(false);
					Controlador.getInstance().setRutaFichero(textFichero.getText());
					ArrayList<double[]> datos = LecturaDatos.readDatos(textFichero.getText());
					
					Controlador.getInstance().inicializaDatos(datos, LecturaDatos.getClasesDeDatos());
					Controlador.getInstance().inicializaAlgoritmos();
				} 
				}catch(Exception p ) {
					JOptionPane.showMessageDialog(null, "Formato incorrecto del contenido del fichero", "ERROR",
				            JOptionPane.ERROR_MESSAGE);
					Controlador.getInstance().setRutaFichero(null);
					textFichero.setText("");
				}
			}
		});
		btnAbrir.setBackground(new Color(175, 238, 238));
		btnAbrir.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(432)
					.addComponent(textFichero, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
					.addGap(37)
					.addComponent(btnAbrir)
					.addContainerGap(428, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(384, Short.MAX_VALUE)
					.addComponent(lblSeleccionaFicheroCon)
					.addGap(368))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(282)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 636, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(282, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(315)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblSeleccionaFicheroCon)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAbrir)
						.addComponent(textFichero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(284, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
}
