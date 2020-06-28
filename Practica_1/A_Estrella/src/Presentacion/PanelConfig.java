package Presentacion;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.border.SoftBevelBorder;

import Negocio.Algoritmo.Algoritmo;
import Negocio.Objetos.Nodo;
import Negocio.Objetos.Tablero;

import javax.swing.border.BevelBorder;
import java.awt.Rectangle;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Point;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class PanelConfig extends JPanel {
	private JTextField textNumFilas;
	private JTextField textNumColumnas;

	/**
	 * Create the panel.
	 */
	public PanelConfig() {
		setBorder(null);
		setOpaque(false);
		setBackground(new Color(245, 255, 250));
		setForeground(new Color(255, 255, 255));
		setBounds(new Rectangle(2, 2, 2, 2));
		
		JLabel lblfilas = new JLabel("Filas:");
		lblfilas.setFont(new Font("Papyrus", Font.BOLD, 18));
		
		JLabel lblCoumnas = new JLabel("Columnas:");
		lblCoumnas.setFont(new Font("Papyrus", Font.BOLD, 18));
		
		JLabel lblTablero = new JLabel("Tablero");
		lblTablero.setForeground(new Color(0, 0, 0));
		lblTablero.setFont(new Font("Papyrus", Font.BOLD, 20));
		
		textNumFilas = new JTextField("5");
		textNumFilas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textNumFilas.setForeground(new Color(255, 255, 255));
		textNumFilas.setCaretColor(new Color(255, 255, 255));
		textNumFilas.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textNumFilas.setAlignmentX(Component.RIGHT_ALIGNMENT);
		textNumFilas.setBackground(new Color(102, 153, 255));
		textNumFilas.setColumns(10);
		
		
		textNumColumnas = new JTextField("5");
		textNumColumnas.setForeground(Color.WHITE);
		textNumColumnas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textNumColumnas.setColumns(10);
		textNumColumnas.setCaretColor(Color.WHITE);
		textNumColumnas.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textNumColumnas.setBackground(new Color(102, 153, 255));
		textNumColumnas.setAlignmentX(1.0f);
		
		JButton btnGenerarTab = new JButton("Generar");
		btnGenerarTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					int filas = Integer.valueOf(textNumFilas.getText());
					int columnas = Integer.valueOf(textNumColumnas.getText());
					if(filas == 0 || columnas == 0)
						throw new NumberFormatException();
					Tablero tablero = new Tablero(filas,columnas);
					Controlador.getInstance().setTablero(tablero);
					Controlador.getInstance().refresh();
				} catch(NumberFormatException nF){
					JOptionPane.showMessageDialog(null, "Los campos filas y columnas deben contener números mayores que 0.");
				}
			}
		});
		btnGenerarTab.setBackground(new Color(135, 206, 250));
		btnGenerarTab.setFont(new Font("Tahoma", Font.PLAIN, 19));

		
		JLabel lblElementos = new JLabel("Coloca en el tablero  : \r\n");
		lblElementos.setForeground(Color.BLACK);
		lblElementos.setFont(new Font("Papyrus", Font.BOLD, 20));
		JLabel lblInicio = new JLabel("Inicio");
		lblInicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblInicio.setFont(new Font("Papyrus", Font.BOLD, 18));
		
		JLabel lblMeta = new JLabel("Meta");
		lblMeta.setHorizontalAlignment(SwingConstants.CENTER);
		lblMeta.setFont(new Font("Papyrus", Font.BOLD, 18));
		
		JLabel lblObstaculo = new JLabel("Obstaculo");
		lblObstaculo.setHorizontalAlignment(SwingConstants.CENTER);
		lblObstaculo.setFont(new Font("Papyrus", Font.BOLD, 18));
		
		JLabel lblWayPoint = new JLabel("Way Point");
		lblWayPoint.setHorizontalAlignment(SwingConstants.CENTER);
		lblWayPoint.setFont(new Font("Papyrus", Font.BOLD, 18));
		
		JLabel lblLibre = new JLabel("Peligroso");
		lblLibre.setForeground(new Color(240, 255, 255));
		lblLibre.setHorizontalAlignment(SwingConstants.CENTER);
		lblLibre.setFont(new Font("Papyrus", Font.BOLD, 18));
		
		JButton btnComenzar = new JButton("Comenzar");
		btnComenzar.setBackground(new Color(135, 206, 250));
		btnComenzar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnComenzar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(Controlador.getInstance().getTablero().getInicio() == null) {
					JOptionPane.showMessageDialog(null, "¡Debes colocar un nodo Inicio!", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(Controlador.getInstance().getTablero().getMeta() == null){
					JOptionPane.showMessageDialog(null, "¡Debes colocar un nodo Meta!", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					Controlador.getInstance().getTablero().borrarCamino(); //por si acaso
					Controlador.getInstance().refresh();
					Algoritmo a = new Algoritmo(Controlador.getInstance().getTablero());
					ArrayList<Nodo> solucion;
					if(Controlador.getInstance().getTablero().getWayPoints().size() > 0) {
						solucion = a.executeConWayPoints();
					}
					else {
						solucion = a.execute();
					}
					if(solucion == null){
						JOptionPane.showMessageDialog(null, "No se puede llegar al nodo Meta", "No hay camino", JOptionPane.INFORMATION_MESSAGE);
					}
					else{
						Controlador.getInstance().setTablero(a.getTablero());
						Controlador.getInstance().pintarCamino(solucion);
						Controlador.getInstance().refresh();
						Controlador.getInstance().getTablero().actualizaPeligrosos(); //para que los nodos de tipo CAMINO_PELIGROSO vuelvan a ser PELIGROSO solo
					}
				}
				
			}
			
		});
		JButton btnReset = new JButton("Reset");
		btnReset.setBackground(new Color(135, 206, 250));
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Color lightBlue=Color.blue.brighter();
		
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try{
					Controlador.getInstance().getTablero().borrarCamino();
					Controlador.getInstance().refresh();
				} catch(NumberFormatException nF){
					JOptionPane.showMessageDialog(null, "Los campos filas y columnas deben contener números mayores que 0.");
				}
			}
			
		});
		JSeparator separator = new JSeparator();
		
		JButton btnColocarProhibido = new JButton("");
		btnColocarProhibido.setIcon(new ImageIcon(PanelConfig.class.getResource("/Presentacion/prohibido.jpg")));
		btnColocarProhibido.setVerticalAlignment(SwingConstants.TOP);
		btnColocarProhibido.setBackground(Color.BLUE.brighter());
		try {
			Image image = ImageIO.read(getClass().getResource("prohibido.jpg"));
			ImageIcon icon = new ImageIcon(image);
			btnColocarProhibido.setIcon(icon);
			Auxiliar.ajustarImagen(btnColocarProhibido, image);
			btnColocarProhibido.addComponentListener(new ComponentAdapter() {

                @Override
                public void componentResized(ComponentEvent e) {
                    JButton btn = (JButton) e.getComponent();
                    Auxiliar.ajustarImagen(btn, image);
                }

            });
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		btnColocarProhibido.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Controlador.getInstance().setBtnProhibido(true);

			}
			
		});
		JButton btnColocarWayPoint = new JButton("");
		btnColocarWayPoint.setIcon(new ImageIcon(PanelConfig.class.getResource("/Presentacion/wayPoint.png")));
		btnColocarWayPoint.setVerticalAlignment(SwingConstants.TOP);
		btnColocarWayPoint.setBackground(Color.BLUE.brighter());
		try {
			Image image = ImageIO.read(getClass().getResource("wayPoint.png"));
			ImageIcon icon = new ImageIcon(image);
			btnColocarWayPoint.setIcon(icon);
			Auxiliar.ajustarImagen(btnColocarWayPoint, image);
			btnColocarWayPoint.addComponentListener(new ComponentAdapter() {

                @Override
                public void componentResized(ComponentEvent e) {
                    JButton btn = (JButton) e.getComponent();
                    Auxiliar.ajustarImagen(btn, image);
                }

            });
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		btnColocarWayPoint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Controlador.getInstance().setBtnWayPoint(true);
			}
			
		});
		JButton btnColocarPeligroso = new JButton("");
		btnColocarPeligroso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controlador.getInstance().setBtnPeligroso(true);

			}
		});
		btnColocarPeligroso.setIcon(new ImageIcon(PanelConfig.class.getResource("/Presentacion/peligroso.jpg")));
		btnColocarPeligroso.setVerticalAlignment(SwingConstants.TOP);
		btnColocarPeligroso.setBackground(Color.BLUE.brighter());
		try {
			Image image = ImageIO.read(getClass().getResource("peligroso.jpg"));
			ImageIcon icon = new ImageIcon(image);
			btnColocarPeligroso.setIcon(icon);
			Auxiliar.ajustarImagen(btnColocarPeligroso, image);
			btnColocarPeligroso.addComponentListener(new ComponentAdapter() {

                @Override
                public void componentResized(ComponentEvent e) {
                    JButton btn = (JButton) e.getComponent();
                    Auxiliar.ajustarImagen(btn, image);
                }

            });
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		JButton btnColocarLibre = new JButton("");
		btnColocarLibre.setVerticalAlignment(SwingConstants.TOP);
		btnColocarLibre.setBackground(new Color(0, 0, 255));
		btnColocarLibre.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Controlador.getInstance().setBtnLibre(true);

				
			}
			
		});
		JLabel lblLibre_1 = new JLabel("Eliminar");
		lblLibre_1.setForeground(new Color(240, 255, 255));
		lblLibre_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblLibre_1.setFont(new Font("Papyrus", Font.BOLD, 18));
		
		JButton btnColocarInicio = new JButton("");
		btnColocarInicio.setIcon(new ImageIcon(PanelConfig.class.getResource("/Presentacion/inicio.png")));
		btnColocarInicio.setVerticalAlignment(SwingConstants.TOP);
		btnColocarInicio.setBackground(Color.BLUE.brighter());
		try {
			Image image = ImageIO.read(getClass().getResource("inicio.png"));
			ImageIcon icon = new ImageIcon(image);
			btnColocarInicio.setIcon(icon);
			Auxiliar.ajustarImagen(btnColocarInicio, image);
			btnColocarInicio.addComponentListener(new ComponentAdapter() {

                @Override
                public void componentResized(ComponentEvent e) {
                    JButton btn = (JButton) e.getComponent();
                    Auxiliar.ajustarImagen(btn, image);
                }

            });
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		btnColocarInicio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(Controlador.getInstance().getTablero().getInicio() == null){
					Controlador.getInstance().setBtnInicio(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "¡Ya existe un nodo Inicio! Pero puedes eliminarlo y colocarlo de nuevo");

				}
			}
			
		});
		JButton btnColocarMeta = new JButton("");
		btnColocarMeta.setIcon(new ImageIcon(PanelConfig.class.getResource("/Presentacion/meta.png")));
		btnColocarMeta.setVerticalAlignment(SwingConstants.TOP);
		btnColocarMeta.setBackground(Color.BLUE.brighter());
		try {
			Image image = ImageIO.read(getClass().getResource("meta.png"));
			ImageIcon icon = new ImageIcon(image);
			btnColocarMeta.setIcon(icon);
			Auxiliar.ajustarImagen(btnColocarMeta, image);
			btnColocarMeta.addComponentListener(new ComponentAdapter() {

                @Override
                public void componentResized(ComponentEvent e) {
                    JButton btn = (JButton) e.getComponent();
                    Auxiliar.ajustarImagen(btn, image);
                }

            });
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		btnColocarMeta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(Controlador.getInstance().getTablero().getMeta() == null){
					Controlador.getInstance().setBtnMeta(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "¡Ya existe un nodo Meta! Pero puedes eliminarlo y colocarlo de nuevo");
					}
				}			
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(136)
							.addComponent(lblTablero))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(43)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCoumnas, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblfilas, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
							.addGap(38)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(textNumColumnas, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
								.addComponent(textNumFilas, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(btnGenerarTab))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(72)
								.addComponent(lblElementos, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addContainerGap()
										.addComponent(btnComenzar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(53)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
											.addComponent(lblLibre, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(btnColocarPeligroso, 0, 0, Short.MAX_VALUE)
											.addComponent(btnColocarProhibido, 0, 0, Short.MAX_VALUE)
											.addComponent(lblObstaculo, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
											.addComponent(lblInicio, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(btnColocarInicio, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))))
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(btnColocarMeta, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(lblMeta, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
											.addComponent(lblWayPoint, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addGroup(groupLayout.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(lblLibre_1, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
											.addComponent(btnColocarWayPoint, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
											.addComponent(btnColocarLibre, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))
										.addGap(65))
									.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTablero)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(34)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblfilas, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
										.addComponent(textNumFilas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCoumnas, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(textNumColumnas, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(48)
							.addComponent(btnGenerarTab)))
					.addGap(19)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblElementos, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnColocarInicio, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnColocarMeta, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInicio)
						.addComponent(lblMeta, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnColocarProhibido, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblObstaculo, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnColocarWayPoint, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblWayPoint, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnColocarPeligroso, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnColocarLibre, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLibre)
						.addComponent(lblLibre_1, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnComenzar)
						.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(21))
		);
		setLayout(groupLayout);
	}
}
