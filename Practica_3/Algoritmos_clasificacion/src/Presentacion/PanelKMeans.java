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

import Jama.Matrix;
import Negocio.Auxiliar.AuxiliarNegocio;

public class PanelKMeans extends Fondo {

	private JTextField textTolerancia;
	private JTextField textB;
	private PanelClasificar panelClasificar;
	private JPanel panelClasesResult;
	private PanelDatos panelDatos;
	private JTextArea textAreaClases;

	/**
	 * Create the panel.
	 */
	public PanelKMeans() {
		super("./imagenes/fondo4.jpg");
		setForeground(new Color(0, 0, 0));
		setBackground(new Color(230, 230, 250));
		
		panelDatos = new PanelDatos();
		panelDatos.setBackground(new Color(255, 255, 240));
		
		JLabel lblDatos = new JLabel("Datos");
		lblDatos.setForeground(Color.WHITE);
		lblDatos.setFont(new Font("Sitka Text", Font.BOLD, 22));
		
		JLabel lblParmetros = new JLabel("Par\u00E1metros\r\n");
		lblParmetros.setForeground(Color.WHITE);
		lblParmetros.setFont(new Font("Sitka Text", Font.BOLD, 22));
		
		JLabel lblTolerancia = new JLabel("Tolerancia : ");
		lblTolerancia.setForeground(new Color(255, 255, 224));
		lblTolerancia.setFont(new Font("Sitka Text", Font.BOLD, 19));
		
		textTolerancia = new JTextField();
		textTolerancia.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textTolerancia.setBackground(new Color(255, 255, 240));
		textTolerancia.setText("0.01");
		textTolerancia.setEditable(false);
		textTolerancia.setColumns(10);
		
		JLabel lblRaznDeAprendizaje = new JLabel("Peso exponencial");
		lblRaznDeAprendizaje.setForeground(new Color(255, 255, 224));
		lblRaznDeAprendizaje.setFont(new Font("Sitka Text", Font.BOLD, 19));
		
		textB = new JTextField();
		textB.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textB.setBackground(new Color(255, 255, 240));
		textB.setText("2");
		textB.setEditable(false);
		textB.setColumns(10);
		
		JButton btnPorDefecto = new JButton("Por defecto");
		btnPorDefecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Controlador.getInstance().getKmeans() == null) {
					JOptionPane.showMessageDialog(null, "Primero debe cargar el fichero de ejemplos en la pestaña de Inicio", "ERROR",
				            JOptionPane.ERROR_MESSAGE);
				}
				else {
					Controlador.getInstance().reiniciar();
					Controlador.getInstance().getKmeans().setTolerancia(0.01);
					Controlador.getInstance().getKmeans().setB(2);
					textTolerancia.setEditable(true);
					textTolerancia.setText(String.valueOf(0.1));
					textTolerancia.setEditable(false);
					textB.setEditable(true);
					textB.setText("2");
					textB.setEditable(false);
					refresh();
				}
			}
		});
		btnPorDefecto.setForeground(new Color(255, 255, 255));
		btnPorDefecto.setBackground(new Color(220, 20, 60));
		btnPorDefecto.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		
		JButton btnAleatorio = new JButton("Aleatorio\r\n");
		btnAleatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Controlador.getInstance().getLloyd() == null) {
					JOptionPane.showMessageDialog(null, "Primero debe cargar el fichero de ejemplos en la pestaña de Inicio", "ERROR",
				            JOptionPane.ERROR_MESSAGE);
				}
				else {
					double tolerancia  = Auxiliar.roundDecimals(Math.random(),4)* 2 + 0.1;
					double b = Auxiliar.roundDecimals(Math.random()* 5 + 1.1 ,4);
					Controlador.getInstance().reiniciar();
					Controlador.getInstance().getKmeans().setTolerancia(tolerancia);
					Controlador.getInstance().getKmeans().setB(b);
					textTolerancia.setEditable(true);
					textTolerancia.setText(String.valueOf(tolerancia));
					textTolerancia.setEditable(false);
					textB.setEditable(true);
					textB.setText(String.valueOf(b));
					textB.setEditable(false);
					refresh();
				}
			}
		});
		btnAleatorio.setForeground(Color.WHITE);
		btnAleatorio.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		btnAleatorio.setBackground(new Color(220, 20, 60));
		
		JLabel lblCentrosObtenidos = new JLabel("Centros obtenidos");
		lblCentrosObtenidos.setForeground(Color.WHITE);
		lblCentrosObtenidos.setFont(new Font("Sitka Text", Font.BOLD, 22));
		
		JTextArea textAreaCentros = new JTextArea();
		JScrollPane jsp2 = new JScrollPane(textAreaCentros);
		textAreaCentros.setEditable(false);
		textAreaCentros.setBackground(new Color(255, 255, 240));
		
		JButton btnCalcularCentros = new JButton("Ejecutar algoritmo");
		btnCalcularCentros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Controlador.getInstance().getKmeans() == null) {
					JOptionPane.showMessageDialog(null, "Primero debe cargar el fichero de ejemplos en la pestaña de Inicio", "ERROR",
				            JOptionPane.ERROR_MESSAGE);
				}
				else {
					Controlador.getInstance().getKmeans().setCentros(Controlador.getInstance().getCentrosIni());
					Controlador.getInstance().getKmeans().execute();
					ArrayList<double[]> centrosSol = Controlador.getInstance().getKmeans().getCentros();
					double [][] U = Controlador.getInstance().getKmeans().getU();
					String textU = Auxiliar.matrix2String(Matrix.constructWithCopy(U));
					String text = Auxiliar.centros2String(centrosSol);
					textAreaCentros.setEditable(true);
					textAreaCentros.setText(text);
					textAreaCentros.setFont(new Font("Arial", Font.PLAIN, 18));
					textAreaCentros.setEditable(false);
					refresh();
					JOptionPane.showMessageDialog(null,textU , "Matriz de grados de pertenencia, U",
				            JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnCalcularCentros.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		btnCalcularCentros.setBackground(new Color(255, 255, 224));
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		
		panelClasificar = new PanelClasificar();
		panelClasificar.setBackground(new Color(255, 255, 240));
		
		panelClasesResult = new JPanel();
		panelClasesResult.setBackground(new Color(255, 255, 240));
		
		JLabel labelNuevosEje = new JLabel("Clasificar Nuevos ejemplos");
		labelNuevosEje.setForeground(Color.WHITE);
		labelNuevosEje.setFont(new Font("Sitka Text", Font.BOLD, 22));
		
		JLabel labelCentrosIni = new JLabel("Centros iniciales");
		labelCentrosIni.setForeground(Color.WHITE);
		labelCentrosIni.setFont(new Font("Sitka Text", Font.BOLD, 22));
		
		JTextArea textAreaCentrosIni = new JTextArea();
		textAreaCentrosIni.setEditable(false);
		JScrollPane jsp = new JScrollPane(textAreaCentrosIni);
		textAreaCentrosIni.setFont(new Font("Arial", Font.PLAIN, 18));
		textAreaCentrosIni.setText("1. [4.6, 3.0, 4.0, 0.0]\r\n2. [6.8, 3.4, 4.6, 0.7]");
		textAreaCentrosIni.setBackground(new Color(255, 255, 240));
		
		JButton btnCentrosPorDefecto = new JButton("Por defecto");
		btnCentrosPorDefecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Controlador.getInstance().getLloyd() == null) {
					JOptionPane.showMessageDialog(null, "Primero debe cargar el fichero de ejemplos en la pestaña de Inicio", "ERROR",
				            JOptionPane.ERROR_MESSAGE);
				}
				else {
					Controlador.getInstance().reiniciar();
					ArrayList<double[]> aux = new ArrayList<double[]>();
					aux.add(new double[]{4.6, 3.0, 4.0, 0.0});
					aux.add(new double[]{6.8, 3.4, 4.6, 0.7});
					Controlador.getInstance().setCentrosIni(aux);
					textAreaCentrosIni.setEditable(true);
					textAreaCentrosIni.setText(Auxiliar.centrosIni2String(aux));
					textAreaCentrosIni.setEditable(false);
					refresh();
				}
				
			}
		});
		btnCentrosPorDefecto.setForeground(Color.WHITE);
		btnCentrosPorDefecto.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		btnCentrosPorDefecto.setBackground(new Color(220, 20, 60));
		
		JButton btnCentrosAleatorios = new JButton("Aleatorio\r\n");
		btnCentrosAleatorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Controlador.getInstance().getLloyd() == null) {
					JOptionPane.showMessageDialog(null, "Primero debe cargar el fichero de ejemplos en la pestaña de Inicio", "ERROR",
				            JOptionPane.ERROR_MESSAGE);
				}
				else {
					Controlador.getInstance().reiniciar();
					int numCentros = Controlador.getInstance().getCentrosIni().size();
					int numCoordenadas = Controlador.getInstance().getCentrosIni().get(0).length;
					ArrayList<double[]> aux = new ArrayList<double[]>();
					for(int i = 0; i <numCentros; i++) {
						aux.add(i,new double[numCoordenadas]);
						for(int j = 0; j < numCoordenadas; j++) {
							aux.get(i)[j] = Auxiliar.roundDecimals(Math.random()* 10, 2);
						}
					}
					Controlador.getInstance().setCentrosIni(aux);
					textAreaCentrosIni.setEditable(true);
					textAreaCentrosIni.setText(Auxiliar.centros2String(aux));
					textAreaCentrosIni.setEditable(false);
					refresh();
				}
			}
		});
		btnCentrosAleatorios.setForeground(Color.WHITE);
		btnCentrosAleatorios.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		btnCentrosAleatorios.setBackground(new Color(220, 20, 60));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(267)
							.addComponent(lblDatos))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(135)
							.addComponent(panelDatos, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(233)
							.addComponent(lblParmetros, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(135)
							.addComponent(lblTolerancia, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
							.addGap(86)
							.addComponent(textTolerancia, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(135)
							.addComponent(lblRaznDeAprendizaje, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
							.addGap(36)
							.addComponent(textB, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(161)
							.addComponent(btnPorDefecto)
							.addGap(51)
							.addComponent(btnAleatorio, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(jsp, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
												.addGap(105))
											.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
													.addComponent(btnCentrosAleatorios, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
													.addComponent(labelCentrosIni, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE))
												.addGap(86))))
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(99)
										.addComponent(btnCentrosPorDefecto)
										.addGap(242)))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblCentrosObtenidos, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
									.addComponent(jsp2, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(25)
										.addComponent(btnCalcularCentros)))
								.addGap(289)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(266)
							.addComponent(labelNuevosEje, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(19)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDatos)
								.addComponent(lblCentrosObtenidos)
								.addComponent(labelCentrosIni, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
							.addGap(7)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(panelDatos, GroupLayout.PREFERRED_SIZE, 477, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblParmetros, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblTolerancia, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
										.addComponent(textTolerancia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(7)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblRaznDeAprendizaje, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(12)
											.addComponent(textB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addGap(7)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnPorDefecto)
										.addComponent(btnAleatorio)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(jsp, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
										.addComponent(jsp2, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnCentrosPorDefecto, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnCentrosAleatorios, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnCalcularCentros))
									.addGap(18)
									.addComponent(labelNuevosEje, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(panelClasesResult, GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
										.addComponent(panelClasificar, GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE))))))
					.addContainerGap(32, GroupLayout.PREFERRED_SIZE))
		);
		
		textAreaClases = new JTextArea();
		textAreaClases.setFont(new Font("Arial", Font.PLAIN, 18));
		textAreaClases.setBorder(new LineBorder(new Color(0, 0, 0)));
		textAreaClases.setEditable(false);
		
		JButton btnClasificarEjemplos = new JButton("Clasificar Ejemplos");
		btnClasificarEjemplos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if(Controlador.getInstance().getKmeans().getU() == null || Controlador.getInstance().getKmeans().getU().length == 0) {
					JOptionPane.showMessageDialog(null, "Primero debe ejecutar el algoritmo para obtener los centros y la matriz de grados de pertenencia", "ERROR",
				            JOptionPane.ERROR_MESSAGE);
					}
					else {
						ArrayList<String> clases = new ArrayList<String>();
						for(double[] ejemplo: Controlador.getInstance().getEjemplos()) {
							int i = Controlador.getInstance().getKmeans().clasificarNuevo(ejemplo);
							clases.add(Controlador.getInstance().getClases().get(i));
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
		textAreaClases.setText(Auxiliar.clases2String(Controlador.getInstance().getClasesEjemplos()));
		revalidate();
      	repaint();
	}
}
