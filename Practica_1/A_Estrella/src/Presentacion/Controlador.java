package Presentacion;

import java.util.ArrayList;

import Negocio.Objetos.Nodo;
import Negocio.Objetos.Tablero;

public class Controlador {
	
	private static Controlador instance = null;
	private boolean btnInicio;
	private boolean btnMeta;
	private boolean btnProhibido;
	private boolean btnLibre;
	private boolean btnWayPoint;
	private boolean btnPeligroso;
	private boolean btnCamino;
	private Tablero tablero; 
	
	public static Controlador getInstance() {
		if(instance == null){
			instance = new Controlador();
		}
		
		return instance;
	}
	public Controlador() {
		btnInicio = false;
		btnMeta = false;
		btnProhibido = false;
		btnLibre = false;
		btnWayPoint = false;
	}
	
	public boolean isBtnInicio() {
		return btnInicio;
	}

	public boolean isBtnMeta() {
		return btnMeta;
	}

	public boolean isBtnProhibido() {
		return btnProhibido;
	}

	public boolean isBtnLibre() {
		return btnLibre;
	}

	public boolean isBtnWayPoint() {
		return btnWayPoint;
	}

	public Tablero getTablero() {
		if(tablero == null) {
			tablero =  new Tablero(5,5);
		}
		return tablero;
	}

	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}

	public void setBtnInicio(boolean btnInicio) {
		this.btnInicio = btnInicio;
		btnMeta = false;
		btnProhibido = false;
		btnLibre = false;
		btnWayPoint = false;
		btnPeligroso = false;
		btnCamino = false;
	}
	
	public void setBtnMeta(boolean btnMeta) {
		btnInicio = false;
		this.btnMeta = btnMeta;
		btnProhibido = false;
		btnLibre = false;
		btnWayPoint = false;
		btnPeligroso = false;
		btnCamino = false;
	}
	
	public void setBtnProhibido(boolean btnProhibido) {
		btnInicio = false;
		btnMeta = false;
		this.btnProhibido = btnProhibido;
		btnLibre = false;
		btnWayPoint = false;
		btnPeligroso = false;
		btnCamino = false;
	}
	
	public void setBtnLibre(boolean btnLibre) {
		btnInicio = false;
		btnMeta = false;
		btnProhibido = false;
		this.btnLibre = btnLibre;
		btnWayPoint = false;
		btnPeligroso = false;
		btnCamino = false;
	}
	
	public void setBtnWayPoint(boolean btnWayPoint) {
		btnInicio = false;
		btnMeta = false;
		btnProhibido = false;
		btnLibre = false;
		this.btnWayPoint = btnWayPoint;
		btnPeligroso = false;
		btnCamino = false;
	}
	
	public boolean isBtnPeligroso() {
		return btnPeligroso;
	}
	public void setBtnPeligroso(boolean btnPeligroso) {
		this.btnPeligroso = btnPeligroso;
		btnInicio = false;
		btnMeta = false;
		btnProhibido = false;
		btnLibre = false;
		btnWayPoint = false;
		btnCamino = false;
	}
	
	public boolean isBtnCamino() {
		return btnCamino;
	}
	public void setBtnCamino(boolean btnCamino) {
		this.btnCamino = btnCamino;
		btnInicio = false;
		btnMeta = false;
		btnProhibido = false;
		btnLibre = false;
		btnWayPoint = false;
		btnPeligroso = false;
		
	}
	public void refresh() {
		VistaPrincipal.getInstance().refresh();
	}
	
	public void pintarCamino(ArrayList<Nodo> solucion) {
		VistaPrincipal.getInstance().pintarCamino(solucion);
	}
}
