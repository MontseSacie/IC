package Presentacion;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import Negocio.Algoritmo.Algoritmo;
import Negocio.Auxiliar.LecturaDatos;
import Negocio.Objetos.Ejemplo;
import Negocio.Objetos.Nodo;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class VistaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static VistaPrincipal instance;
	private JPanel contentPane;
	private JTextField rutaEjemplos;
	private JTextField rutaAtributos;
	private PanelArbol panelArbol;
	private PanelReglas panelReglas;
	private Algoritmo a;

	public static VistaPrincipal getInstance() {
		if (instance == null)
			instance = new VistaPrincipal();
		return instance;
	}

	/**
	 * Create the frame.
	 */
	public VistaPrincipal() {
		super("Algoritmo ID3");
		initGui(null, null);
	}

	public void initGui(Nodo raiz, ArrayList<ArrayList<String>> reglas) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1363, 941);
		contentPane = new Fondo("fondo.jpg");
		setContentPane(contentPane);
		contentPane.setBackground(new Color(124, 252, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		a = new Algoritmo();
		JLabel lblNewLabel = new JLabel("ALGORITMO ID3");
		lblNewLabel.setFont(new Font("Sylfaen", Font.BOLD, 28));

		JLabel lblMontserratSacieAlczar = new JLabel("Montserrat Sacie Alc\u00E1zar");
		lblMontserratSacieAlczar.setFont(new Font("Sylfaen", Font.BOLD, 17));

		JPanel panelConfig = new JPanel();
		panelConfig.setBackground(SystemColor.info);
		panelConfig.setBorder(new LineBorder(new Color(139, 69, 19)));

		JButton btnEjecutarAlgoritmo = new JButton("Ejecutar algoritmo");
		btnEjecutarAlgoritmo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String fileEjemplos = rutaEjemplos.getText();
					String fileAtributos = rutaAtributos.getText();
					ArrayList<String> listaAtributos = LecturaDatos.readAtributos(fileAtributos);
					ArrayList<Ejemplo> listaEjemplos = LecturaDatos.readEjemplos(fileEjemplos, listaAtributos);
					a = new Algoritmo();
					Controlador.getInstance().setAlgoritmo(a);
					Nodo arbolSolucion = a.iniciarAlgoritmo(listaEjemplos, listaAtributos);
					if (arbolSolucion == null) {
						JOptionPane.showMessageDialog(null, "El algoritmo no ha alcanzado una solución",
								"No se ha alcanzado arbol de decisión", JOptionPane.INFORMATION_MESSAGE);
					} else {
						Controlador.getInstance().setArbol(arbolSolucion);
						ArrayList<ArrayList<String>> reglas = a.calcularReglas(arbolSolucion);
						if (reglas.isEmpty() == false) {
							Controlador.getInstance().setReglas(reglas);
						}
						Controlador.getInstance().pintarArbolyReglas();
						Controlador.getInstance().escribeMeritos(arbolSolucion, 0,arbolSolucion.getAtributoMejor().getNombre());
					}
				} catch (NullPointerException e2) {
					JOptionPane.showMessageDialog(null,
							"Debe seleccionar los ficheros que contienen una lista de ejemplos y una lista de atributos válidas");
				}
				catch(IndexOutOfBoundsException e3) {
					JOptionPane.showMessageDialog(null,
							"La lista de ejemplos y de atributos no es válida, seleccione de nuevo los archivos");
				}
			}
		});
		btnEjecutarAlgoritmo.setForeground(new Color(0, 0, 0));
		btnEjecutarAlgoritmo.setBackground(new Color(255, 182, 193));
		btnEjecutarAlgoritmo.setFont(new Font("Sylfaen", Font.PLAIN, 17));
		panelArbol = new PanelArbol(raiz);
		panelArbol.setBorder(new LineBorder(new Color(139, 69, 19)));
		panelArbol.setBackground(new Color(255, 250, 240));

		JPanel panelNuevoEjemplo = new PanelNuevoEjemplo();

		panelReglas = new PanelReglas(reglas);
		panelReglas.setBorder(new LineBorder(new Color(139, 69, 19)));
		panelReglas.setBackground(new Color(255, 250, 240));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(25)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 633, Short.MAX_VALUE)
								.addComponent(lblMontserratSacieAlczar, GroupLayout.PREFERRED_SIZE, 227,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
								.createParallelGroup(Alignment.LEADING, false)
								.addComponent(panelConfig, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(panelReglas, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(panelArbol, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														645, Short.MAX_VALUE))
										.addGap(53).addComponent(panelNuevoEjemplo, GroupLayout.PREFERRED_SIZE, 293,
												GroupLayout.PREFERRED_SIZE)))
								.addGap(29).addComponent(btnEjecutarAlgoritmo, GroupLayout.PREFERRED_SIZE, 200,
										GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panelConfig, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(panelArbol,
														GroupLayout.PREFERRED_SIZE, 480, GroupLayout.PREFERRED_SIZE))
										.addComponent(panelNuevoEjemplo, GroupLayout.PREFERRED_SIZE, 355,
												GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblMontserratSacieAlczar)
								.addGap(72).addComponent(btnEjecutarAlgoritmo)))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(panelReglas, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(15, Short.MAX_VALUE)));

		rutaEjemplos = new JTextField();
		rutaEjemplos.setBackground(new Color(255, 255, 255));
		rutaEjemplos.setEditable(false);
		rutaEjemplos.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		rutaEjemplos.setColumns(10);

		JButton btnAbrir = new JButton("Abrir");
		btnAbrir.setFont(new Font("Sylfaen", Font.PLAIN, 17));
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jf = new JFileChooser();
				jf.showOpenDialog(jf);
				File archivo = jf.getSelectedFile();
				if (archivo != null) {
					rutaEjemplos.setEditable(true);
					rutaEjemplos.setText(archivo.getAbsolutePath());
					rutaEjemplos.setEditable(false);
				}
			}
		});

		rutaAtributos = new JTextField();
		rutaAtributos.setBackground(new Color(255, 255, 255));
		rutaAtributos.setEditable(false);
		rutaAtributos.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		rutaAtributos.setColumns(10);

		JButton btnAbrir2 = new JButton("Abrir");
		btnAbrir2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jf = new JFileChooser();
				jf.showOpenDialog(jf);
				File archivo = jf.getSelectedFile();
				if (archivo != null) {
					rutaEjemplos.setEditable(true);
					rutaAtributos.setText(archivo.getAbsolutePath());
					rutaEjemplos.setEditable(false);

				}
			}
		});
		btnAbrir2.setFont(new Font("Sylfaen", Font.PLAIN, 17));

		JLabel lblListaEjemplos = new JLabel("Selecciona el fichero  con la lista de ejemplos");
		lblListaEjemplos.setFont(new Font("Sylfaen", Font.BOLD, 17));

		JLabel lblSeleccionaElFichero = new JLabel("Selecciona el fichero  con la lista de atributos");
		lblSeleccionaElFichero.setFont(new Font("Sylfaen", Font.BOLD, 17));
		GroupLayout gl_panelConfig = new GroupLayout(panelConfig);
		gl_panelConfig.setHorizontalGroup(gl_panelConfig.createParallelGroup(Alignment.LEADING).addGroup(gl_panelConfig
				.createSequentialGroup().addGap(18)
				.addGroup(gl_panelConfig.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelConfig.createSequentialGroup()
								.addComponent(rutaEjemplos, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnAbrir, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblListaEjemplos, GroupLayout.PREFERRED_SIZE, 394, GroupLayout.PREFERRED_SIZE))
				.addGap(78)
				.addGroup(gl_panelConfig.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSeleccionaElFichero, GroupLayout.PREFERRED_SIZE, 394,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelConfig.createSequentialGroup()
								.addComponent(rutaAtributos, GroupLayout.PREFERRED_SIZE, 257,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnAbrir2, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(18, Short.MAX_VALUE)));
		gl_panelConfig
				.setVerticalGroup(gl_panelConfig.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelConfig.createSequentialGroup().addContainerGap()
								.addGroup(gl_panelConfig
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblListaEjemplos, GroupLayout.PREFERRED_SIZE, 24,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(
												lblSeleccionaElFichero, GroupLayout.PREFERRED_SIZE, 24,
												GroupLayout.PREFERRED_SIZE))
								.addGap(7)
								.addGroup(gl_panelConfig.createParallelGroup(Alignment.BASELINE)
										.addComponent(rutaEjemplos, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnAbrir, GroupLayout.PREFERRED_SIZE, 27,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(rutaAtributos, GroupLayout.PREFERRED_SIZE, 28,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnAbrir2, GroupLayout.PREFERRED_SIZE, 29,
												GroupLayout.PREFERRED_SIZE))
								.addContainerGap(22, Short.MAX_VALUE)));
		panelConfig.setLayout(gl_panelConfig);
		contentPane.setLayout(gl_contentPane);
		this.setVisible(true);
		this.setResizable(false);
	}

	public void pintar(Nodo arbol, ArrayList<ArrayList<String>> reglas) {
		// TODO Auto-generated method stub
		initGui(arbol, reglas);

	}
	public Algoritmo getA() {
		return a;
	}
}
