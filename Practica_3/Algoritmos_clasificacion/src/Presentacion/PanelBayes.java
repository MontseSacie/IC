package Presentacion;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

public class PanelBayes extends Fondo {
	private PanelClasificar panelClasificar;
	private JPanel panelClasesResult;
	private PanelDatos panelDatos;
	private JTextArea textAreaClases;
	private PanelMCovarianzas panelmCovarianzas;

	/**
	 * Create the panel.
	 */
	public PanelBayes() {
		super("./imagenes/fondo3.jpg");
		setForeground(new Color(0, 0, 0));
		setBackground(new Color(230, 230, 250));
		
		panelDatos = new PanelDatos();
		panelDatos.setBackground(new Color(240, 248, 255));
		
		JLabel lblDatos = new JLabel("Datos");
		lblDatos.setForeground(Color.WHITE);
		lblDatos.setFont(new Font("Sitka Text", Font.BOLD, 22));
		
		JLabel lblCentrosObtenidos = new JLabel("Centros obtenidos");
		lblCentrosObtenidos.setForeground(Color.WHITE);
		lblCentrosObtenidos.setFont(new Font("Sitka Text", Font.BOLD, 22));
		
		JTextArea textAreaCentros = new JTextArea();
		JScrollPane jsp2 = new JScrollPane(textAreaCentros);
		textAreaCentros.setEditable(false);
		textAreaCentros.setBackground(new Color(240, 248, 255));
		
		JButton btnCalcularCentros = new JButton("Ejecutar algoritmo");
		btnCalcularCentros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Controlador.getInstance().getBayes() == null) {
					JOptionPane.showMessageDialog(null, "Primero debe cargar el fichero de ejemplos en la pestaña de Inicio", "ERROR",
				            JOptionPane.ERROR_MESSAGE);
				}
				else {
					Controlador.getInstance().reiniciar();
					Controlador.getInstance().getBayes().execute();
					ArrayList<double[]> centrosSol = Controlador.getInstance().getBayes().getCentros();
					String text = Auxiliar.centros2String(centrosSol);
					textAreaCentros.setEditable(true);
					textAreaCentros.setText(text);
					textAreaCentros.setFont(new Font("Arial", Font.PLAIN, 18));
					textAreaCentros.setEditable(false);
					refresh();
				}
			}
		});
		btnCalcularCentros.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		btnCalcularCentros.setBackground(new Color(255, 255, 224));
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		
		panelClasificar = new PanelClasificar();
		panelClasificar.setForeground(new Color(0, 0, 0));
		panelClasificar.setBackground(new Color(240, 255, 255));
		
		panelClasesResult = new JPanel();
		panelClasesResult.setBackground(new Color(240, 248, 255));
		
		JLabel labelNuevosEje = new JLabel("Clasificar Nuevos ejemplos");
		labelNuevosEje.setForeground(Color.WHITE);
		labelNuevosEje.setFont(new Font("Sitka Text", Font.BOLD, 22));
		
		JLabel lblMatricesDeCovarianza = new JLabel("Matrices de Covarianza");
		lblMatricesDeCovarianza.setForeground(Color.WHITE);
		lblMatricesDeCovarianza.setFont(new Font("Sitka Text", Font.BOLD, 22));
		
		panelmCovarianzas = new PanelMCovarianzas();
		panelmCovarianzas.setBackground(new Color(240, 248, 255));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(166)
							.addComponent(lblCentrosObtenidos, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
							.addGap(161)
							.addComponent(lblMatricesDeCovarianza, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(120)
									.addComponent(panelDatos, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(249)
									.addComponent(lblDatos))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(156)
									.addComponent(jsp2, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(48)
											.addComponent(separator, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(112)
											.addComponent(panelClasificar, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(panelClasesResult, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)))
									.addGap(237))
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addGap(33)
									.addComponent(panelmCovarianzas, GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
									.addGap(70)
									.addComponent(btnCalcularCentros)
									.addGap(308))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(258)
									.addComponent(labelNuevosEje, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblCentrosObtenidos)
							.addComponent(lblMatricesDeCovarianza, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(43)
							.addComponent(btnCalcularCentros))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panelmCovarianzas, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(17)
							.addComponent(jsp2, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelNuevosEje, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDatos))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelDatos, GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
						.addComponent(panelClasesResult, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
						.addComponent(panelClasificar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE))
					.addContainerGap(29, GroupLayout.PREFERRED_SIZE))
		);
		
		textAreaClases = new JTextArea();
		textAreaClases.setFont(new Font("Arial", Font.PLAIN, 18));
		textAreaClases.setBorder(new LineBorder(new Color(0, 0, 0)));
		textAreaClases.setEditable(false);
		
		JButton btnClasificarEjemplos = new JButton("Clasificar Ejemplos");
		btnClasificarEjemplos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if(Controlador.getInstance().getBayes().getCentros() == null || Controlador.getInstance().getBayes().getmCovarianzas().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Primero debe ejecutar el algoritmo para obtener los centros y matrices de Covarianza", "ERROR",
				            JOptionPane.ERROR_MESSAGE);
					}
					else {
						ArrayList<String> clases = new ArrayList<String>();
						for(double[] ejemplo: Controlador.getInstance().getEjemplos()) {
							String i = Controlador.getInstance().getBayes().clasificarNuevo(ejemplo);
							clases.add(i);
						}
						String text = Auxiliar.getInstance().clases2String(clases);
						textAreaClases.setEditable(true);
						textAreaClases.setText(text);
						textAreaClases.setEditable(false);
						Controlador.getInstance().setClasesEjemplos(clases);
						refresh();
					}
				}catch(NullPointerException e3) {
					JOptionPane.showMessageDialog(null, "Primero debe cargar el fichero de ejemplos en la pestaña de Inicio", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				
				}
			}
		});
		btnClasificarEjemplos.setForeground(Color.WHITE);
		btnClasificarEjemplos.setBackground(Color.BLACK);
		btnClasificarEjemplos.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		GroupLayout gl_panelClasesResult = new GroupLayout(panelClasesResult);
		gl_panelClasesResult.setHorizontalGroup(
			gl_panelClasesResult.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelClasesResult.createSequentialGroup()
					.addContainerGap(25, Short.MAX_VALUE)
					.addGroup(gl_panelClasesResult.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panelClasesResult.createSequentialGroup()
							.addComponent(textAreaClases, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
							.addGap(23))
						.addGroup(Alignment.TRAILING, gl_panelClasesResult.createSequentialGroup()
							.addComponent(btnClasificarEjemplos)
							.addGap(49))))
		);
		gl_panelClasesResult.setVerticalGroup(
			gl_panelClasesResult.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelClasesResult.createSequentialGroup()
					.addContainerGap()
					.addComponent(textAreaClases, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnClasificarEjemplos)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelClasesResult.setLayout(gl_panelClasesResult);
		setLayout(groupLayout);
		
		
	}

	public void refresh() {
		panelClasificar.refresh();
		panelDatos.refresh();
		panelmCovarianzas.refresh();
		textAreaClases.setText(Auxiliar.clases2String(Controlador.getInstance().getClasesEjemplos()));
		revalidate();
      	repaint();
	}
}
