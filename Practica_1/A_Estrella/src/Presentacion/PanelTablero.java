package Presentacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import Negocio.Objetos.Nodo;
import Negocio.Objetos.Tablero;
import Negocio.Objetos.TipoNodo;

import javax.swing.JLabel;
import javax.swing.JComboBox;

public class PanelTablero extends JPanel{

	/**
	 * Create the panel.
	 */
	private JButton[][] casillas;
	
	private int numFilas;
	private int numColumnas;

	private ArrayList<Nodo> solucion;
	
	public PanelTablero() {
			initGui();
	}
	
	public void initGui(){
		inicializar();
		ajustar();
	}

	public void inicializar() {	
		this.removeAll();
		Tablero tablero = Controlador.getInstance().getTablero();
		numFilas = tablero.getFilas();
		numColumnas = tablero.getColumnas();
		this.setBackground(Color.CYAN);
		setLayout(new GridLayout(numFilas, numColumnas));
		casillas = new JButton[numFilas][numColumnas];
		
		for(int i = 0; i < numFilas; i++) {
			for(int j = 0; j < numColumnas; j++) {
				casillas[i][j] = new JButton();
				colorearBoton(i,j);
				casillas[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				casillas[i][j].addActionListener(new ActionListenerImp(i, j));
				this.add(casillas[i][j]);
			}
		}
		this.revalidate();
		this.repaint();
	}


	private void colorearProhibido(int i, int j) {
		// TODO Auto-generated method stub
		try {
			Image image = ImageIO.read(getClass().getResource("prohibido.jpg"));
			ImageIcon icon = new ImageIcon(image);
			casillas[i][j].setIcon(icon);
			casillas[i][j].setBackground(Color.WHITE);
			Auxiliar.getInstance().ajustarImagen(casillas[i][j], image);
			casillas[i][j].addComponentListener(new ComponentAdapter() {

                @Override //cuando se cambie el tamaño del botón, cambiará el tamaño del icono del btn
                public void componentResized(ComponentEvent e) {
                    JButton btn = (JButton) e.getComponent();
                    Auxiliar.getInstance().ajustarImagen(btn, image);
                }

            });
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void colorearInicio(int i, int j) {
		// TODO Auto-generated method stub
		try {
			Image image = ImageIO.read(getClass().getResource("inicio.png"));
			//BufferedImage img = Auxiliar.getInstance().getBufferedImage((BufferedImage) image, "");
			ImageIcon icon = new ImageIcon(image);
			casillas[i][j].setIcon(icon);
			casillas[i][j].setBackground(Color.WHITE);
			Auxiliar.getInstance().ajustarImagen(casillas[i][j], image);
			casillas[i][j].addComponentListener(new ComponentAdapter() {

                @Override //cuando se cambie el tamaño del botón, cambiará el tamaño del icono del btn
                public void componentResized(ComponentEvent e) {
                    JButton btn = (JButton) e.getComponent();
                    Auxiliar.getInstance().ajustarImagen(btn, image);
                }

            });
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void colorearMeta(int i, int j) {
		// TODO Auto-generated method stub
		try {
			Image image = ImageIO.read(getClass().getResource("meta.png"));
			ImageIcon icon = new ImageIcon(image);
			casillas[i][j].setIcon(icon);
			casillas[i][j].setBackground(Color.BLUE);
			Auxiliar.getInstance().ajustarImagen(casillas[i][j], image);
			casillas[i][j].addComponentListener(new ComponentAdapter() {

                @Override
                public void componentResized(ComponentEvent e) {
                	 JButton btn = (JButton) e.getComponent();
                     Auxiliar.getInstance().ajustarImagen(btn, image);
                }

            });
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void colorearWayPoint(int i, int j) {
		// TODO Auto-generated method stub
		try {
			Image image = ImageIO.read(getClass().getResource("wayPoint.png"));
			ImageIcon icon = new ImageIcon(image);
			casillas[i][j].setIcon(icon);
			casillas[i][j].setBackground(Color.BLUE);
			Auxiliar.getInstance().ajustarImagen(casillas[i][j], image);
			casillas[i][j].addComponentListener(new ComponentAdapter() {

                @Override
                public void componentResized(ComponentEvent e) {
                	 JButton btn = (JButton) e.getComponent();
                     Auxiliar.getInstance().ajustarImagen(btn, image);
                }

            });
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void colorearPeligroso(int i, int j) {
		// TODO Auto-generated method stub
		try {
			Image image = ImageIO.read(getClass().getResource("peligroso.jpg"));
			ImageIcon icon = new ImageIcon(image);
			casillas[i][j].setIcon(icon);
			casillas[i][j].setBackground(Color.BLUE);
			Auxiliar.getInstance().ajustarImagen(casillas[i][j], image);
			casillas[i][j].addComponentListener(new ComponentAdapter() {

                @Override
                public void componentResized(ComponentEvent e) {
                	 JButton btn = (JButton) e.getComponent();
                     Auxiliar.getInstance().ajustarImagen(btn, image);
                }

            });
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void colorearBoton(int i, int j) {
		switch(Controlador.getInstance().getTablero().getNodo(i, j).getTipoNodo()) {
		case LIBRE: 
			casillas[i][j].setBackground((Color.BLUE.brighter()));
			casillas[i][j].setIcon(null);
			break;
		case META:
			colorearMeta(i,j);
			break;
		case INICIO:
			colorearInicio(i,j);
			break;
		case PROHIBIDO:
			colorearProhibido(i,j);
			break;
		case PELIGROSO:
			colorearPeligroso(i,j);
			break;
		case WAYPOINT:
			colorearWayPoint(i,j);
			break;
		case CAMINO:
			casillas[i][j].setBackground(Color.ORANGE);
			break;
		case CAMINO_PELIGROSO:
			colorearPeligroso(i,j);
			casillas[i][j].setBackground(Color.ORANGE);
			break;
		default:
			casillas[i][j].setBackground(Color.BLUE);
			break;
		}
	}

	public int getNumFilas() {
		return this.numFilas;
	}
	
	public int getNumColumnas() {
		return this.numColumnas;
	}
	public void setNumFilas(int m) {
		this.numFilas = m;
	}
	
	public void setNumColumnas(int n) {
		this.numColumnas = n;
	}
	public void ajustar() {
		int ancho = this.getWidth();
		int alto = this.getHeight();
		
		int anchoBtn = ancho /numColumnas;
		int altoBtn = alto /numFilas;
		
		for(int i = 0; i < numFilas; i++) {
			for (int j = 0; j < numColumnas; j++) {
				casillas[i][j].setBounds((j -1) * anchoBtn, (i-1)*altoBtn, anchoBtn, altoBtn);
			}
		}
		
		
	}
	private class ActionListenerImp implements ActionListener{
		private int i;
		private int j;
		
		public ActionListenerImp(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Nodo n = new Nodo(i,j);
			
			if(Controlador.getInstance().isBtnInicio()) {
				n.setTipoNodo(TipoNodo.INICIO);
				Controlador.getInstance().getTablero().borrarCasillaExistente(i, j);
				Controlador.getInstance().getTablero().setInicio(n);
	        	Controlador.getInstance().setBtnInicio(false);
			}
			else if(Controlador.getInstance().isBtnMeta()) {
				n.setTipoNodo(TipoNodo.META);
				Controlador.getInstance().getTablero().borrarCasillaExistente(i, j);
				Controlador.getInstance().getTablero().setMeta(n);
	        	Controlador.getInstance().setBtnMeta(false);
			}
			else if(Controlador.getInstance().isBtnProhibido()) {
				n.setTipoNodo(TipoNodo.PROHIBIDO);
				Controlador.getInstance().getTablero().borrarCasillaExistente(i, j);
				Controlador.getInstance().getTablero().setProhibido(n);
	        	Controlador.getInstance().setBtnProhibido(false);
			}
			else if(Controlador.getInstance().isBtnWayPoint()) {
				n.setTipoNodo(TipoNodo.WAYPOINT);
				Controlador.getInstance().getTablero().borrarCasillaExistente(i, j);
				Controlador.getInstance().getTablero().añadirWayPoint(n);
	        	Controlador.getInstance().setBtnWayPoint(false);
			}
			else if(Controlador.getInstance().isBtnPeligroso()) {
				n.setTipoNodo(TipoNodo.PELIGROSO);
				Controlador.getInstance().getTablero().borrarCasillaExistente(i, j);
				Controlador.getInstance().getTablero().añadirPeligroso(n);
	        	Controlador.getInstance().setBtnPeligroso(false);
			}
			else if(Controlador.getInstance().isBtnCamino()) {
				n.setTipoNodo(TipoNodo.CAMINO);
				Controlador.getInstance().getTablero().borrarCasillaExistente(i, j); //si es que había ya marcado esta casilla
				Controlador.getInstance().getTablero().añadirCamino(n);
	        	Controlador.getInstance().setBtnCamino(false);
			}
			else {
				Controlador.getInstance().getTablero().borrarCasillaExistente(i, j);
				Controlador.getInstance().setBtnLibre(false);
			}
			colorearBoton(i,j);
		}
		
	}
	public void refresh() {
		// TODO Auto-generated method stub
		this.initGui();
	}
}
