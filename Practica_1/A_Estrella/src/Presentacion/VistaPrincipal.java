package Presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.SoftBevelBorder;

import Negocio.Objetos.Nodo;
import Negocio.Objetos.Tablero;

import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class VistaPrincipal extends JFrame {

	private Fondo contentPane;
	private static VistaPrincipal instance;
	private PanelTablero panelTablero;
	private PanelConfig panelConfig;
	public static VistaPrincipal getInstance() {
		if(instance == null)
			instance = new VistaPrincipal();
		return instance;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaPrincipal frame = VistaPrincipal.getInstance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public VistaPrincipal() {
		super("Algoritmo A*");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new Fondo("fondo.jpg");
		contentPane.setBackground(new Color(204, 255, 255));
		setContentPane(contentPane);
		
		panelTablero = new PanelTablero();
		JLabel lblBienvenidoALa = new JLabel("Bienvenido a la Traves\u00EDa A*");
		lblBienvenidoALa.setFont(new Font("Papyrus", Font.BOLD, 40));
		
		JLabel lblMontserratSacieAlcazar = new JLabel("Montserrat Sacie Alcazar");
		lblMontserratSacieAlcazar.setForeground(new Color(204, 204, 204));
		lblMontserratSacieAlcazar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		panelConfig = new PanelConfig();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(55)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panelTablero, GroupLayout.PREFERRED_SIZE, 573, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMontserratSacieAlcazar)
						.addComponent(lblBienvenidoALa))
					.addPreferredGap(ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
					.addComponent(panelConfig, GroupLayout.PREFERRED_SIZE, 403, GroupLayout.PREFERRED_SIZE)
					.addGap(106))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(71)
							.addComponent(lblBienvenidoALa)
							.addGap(52)
							.addComponent(panelTablero, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE)
							.addGap(67)
							.addComponent(lblMontserratSacieAlcazar))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(47)
							.addComponent(panelConfig, GroupLayout.PREFERRED_SIZE, 667, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(96, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		Tablero tablero = new Tablero(5,5);
		Controlador.getInstance().setTablero(tablero);
		
		this.setMinimumSize(new Dimension(1050, 744));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		
	}
	public void refresh() {
		this.panelTablero.refresh();
	}
	public void pintarCamino(ArrayList<Nodo> solucion) {
		//Para dibujar la sucesión de nodos por consola
		int fila;
		int columna;
		int numFilas = Controlador.getInstance().getTablero().getFilas();
		System.out.println("Nodos del camino solución:");
		System.out.println("----------------------------");
		for(int i = 0; i < solucion.size(); i++) {
			fila = ( numFilas - solucion.get(i).getFila());
			columna = solucion.get(i).getColumna() + 1;
			System.out.println("( " + fila + ", " + columna +" )");
		}
	}
}
