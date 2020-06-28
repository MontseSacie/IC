package Negocio.Objetos;

import java.util.ArrayList;

public class Tablero{
	private Nodo [][] Nodos;
	private Nodo inicio;
	private Nodo meta;
	private ArrayList<Nodo> wayPoints;
	private int numFilas;
	private int numColumnas;
	
	public Tablero(int m, int n) {
		this.numFilas = m;
		this.numColumnas = n;
		Nodos = new Nodo[m][n];
		this.inicializar();
		
	}
	
	public Tablero(Tablero tab) {
		//Constructor por copia
		this.numFilas = tab.numFilas;
		this.numColumnas = tab.numColumnas;
		this.Nodos = new Nodo[this.numFilas][this.numColumnas];
		this.wayPoints = new ArrayList<Nodo>();
		
		for(int i = 0; i < this.numFilas; i++){
			for(int j = 0; j < this.numColumnas; j++){				
				Nodo nodo = new Nodo(tab.getNodo(i, j).getFila(), tab.getNodo(i, j).getColumna());
				nodo.setTipoNodo(tab.getNodo(i, j).getTipoNodo());
				this.Nodos[i][j] = nodo;
				switch(nodo.getTipoNodo()) {
				case INICIO: this.inicio = nodo;break;
				case META: this.meta = nodo; break;
				case WAYPOINT: this.wayPoints.add(nodo); break;
				default: break;
				}
			}
		}
	}
	public Nodo[][] getNodos() {
		return Nodos;
	}
	
	public void setNodos(Nodo[][] Nodos) {
		this.Nodos = Nodos;
		
	}
	public void setNodo(Nodo m) {
		switch(m.getTipoNodo()) {
		case INICIO: this.inicio = m; break;
		case META: this.meta = m; break;
		case WAYPOINT: añadirWayPoint(m); break;
		default: break;
		}
		Nodos[m.getFila()][m.getColumna()] = m;
	}
	public ArrayList<Nodo> getWayPoints() {
		return wayPoints;
	}
	
	public void añadirWayPoint(Nodo m) {
		this.wayPoints.add(m);
		Nodos[m.getFila()][m.getColumna()] = m;
	}
	public void borrarWayPoint(int i, int j) {
		this.wayPoints.remove(Nodos[i][j]);
		Nodos[i][j].setTipoNodo(TipoNodo.LIBRE);
	}
	
	public Nodo getInicio() {
		return inicio;
	}
	
	public void setInicio(Nodo i) {
		this.inicio = i;
		Nodos[i.getFila()][i.getColumna()] = i;
	}
	public void borrarInicio() {
		if(this.inicio != null) {
			Nodos[this.inicio.getFila()][this.inicio.getColumna()].setTipoNodo(TipoNodo.LIBRE);
			this.inicio = null;
		}
	}
	public Nodo getMeta() {
		return meta;
	}
	
	public void setMeta(Nodo m) {
		this.meta = m;
		Nodos[m.getFila()][m.getColumna()] = m;
	}
	
	public void borrarMeta() {
		if(this.meta != null) {
			Nodos[this.meta.getFila()][this.meta.getColumna()].setTipoNodo(TipoNodo.LIBRE);
			this.meta = null;
		}
	}
	public int getFilas() {
		return numFilas;
	}

	public void setNumFilas(int filas) {
		this.numFilas = filas;
	}
	public void setProhibido(Nodo m) {
		Nodos[m.getFila()][m.getColumna()] = m;
	}
	public void borrarProhibida(int i, int j) {
		Nodos[i][j].setTipoNodo(TipoNodo.LIBRE);
	}
	public int getColumnas() {
		return numColumnas;
	}
	public void setNumColumnas(int columnas) {
		this.numColumnas = columnas;
	}
	public Nodo getNodo(int i, int j) {
		return Nodos[i][j];
	}
	public ArrayList<Nodo> getAdyacentes(Nodo n) {
		ArrayList<Nodo> adyacentes = new ArrayList<Nodo>();
		int i = n.getFila();
		int j = n.getColumna();
		if (i == 0) {
			if(j == 0) {
				adyacentes.add(Nodos[i+1][j]);
				adyacentes.add(Nodos[i+1][j+1]);
				adyacentes.add(Nodos[i][j+1]);
			}
			else if(j == numColumnas - 1) {
				adyacentes.add(Nodos[i][j-1]);
				adyacentes.add(Nodos[i+1][j-1]);
				adyacentes.add(Nodos[i+1][j]);
			}
			else {
				adyacentes.add(Nodos[i][j-1]);
				adyacentes.add(Nodos[i+1][j-1]);
				adyacentes.add(Nodos[i+1][j]);
				adyacentes.add(Nodos[i+1][j+1]);
				adyacentes.add(Nodos[i][j+1]);

			}
		} 
		else if ( i == numFilas -1) {
			if(j == 0) {
				adyacentes.add(Nodos[i-1][j]);
				adyacentes.add(Nodos[i-1][j+1]);
				adyacentes.add(Nodos[i][j+1]);
			}
			else if(j == numColumnas - 1) {
				adyacentes.add(Nodos[i][j-1]);
				adyacentes.add(Nodos[i-1][j-1]);
				adyacentes.add(Nodos[i-1][j]);
			}
			else {
				adyacentes.add(Nodos[i][j-1]);
				adyacentes.add(Nodos[i-1][j-1]);
				adyacentes.add(Nodos[i-1][j]);
				adyacentes.add(Nodos[i-1][j+1]);
				adyacentes.add(Nodos[i][j+1]);

			}
		}
		else {
			if(j == 0) {
				adyacentes.add(Nodos[i-1][j]);
				adyacentes.add(Nodos[i-1][j+1]);
				adyacentes.add(Nodos[i][j+1]);
				adyacentes.add(Nodos[i+1][j+1]);
				adyacentes.add(Nodos[i+1][j]);

			}
			else if(j == numColumnas - 1) {
				adyacentes.add(Nodos[i-1][j]);
				adyacentes.add(Nodos[i-1][j-1]);
				adyacentes.add(Nodos[i][j-1]);
				adyacentes.add(Nodos[i+1][j-1]);
				adyacentes.add(Nodos[i+1][j]);
			}
			else {
				adyacentes.add(Nodos[i-1][j+1]);
				adyacentes.add(Nodos[i][j+1]);
				adyacentes.add(Nodos[i+1][j+1]);
				adyacentes.add(Nodos[i+1][j]);
				adyacentes.add(Nodos[i+1][j-1]);
				adyacentes.add(Nodos[i][j-1]);
				adyacentes.add(Nodos[i-1][j-1]);
				adyacentes.add(Nodos[i-1][j]);

			}
		}
		
		Nodo padre = n.getPadre();
		adyacentes.remove(padre);
		return adyacentes;
		
	}
	
	public void inicializar() {
		wayPoints = new ArrayList<Nodo>();
		for (int i = 0; i < numFilas; i++) {
			for(int j = 0; j < numColumnas; j++) {
				Nodos[i][j] = new Nodo(i, j);
			}
		}
	}
	public void añadirPeligroso(Nodo n) {
		Nodos[n.getFila()][n.getColumna()] = n;
	}
	public void añadirCamino(Nodo n) {
		Nodos[n.getFila()][n.getColumna()] = n;
	}
	public void borrarCasillaExistente(int i, int j) {
		// TODO Auto-generated method stub
		switch (Nodos[i][j].getTipoNodo()) {
		case LIBRE: break;
		case PROHIBIDO: break;
		case WAYPOINT: this.wayPoints.remove(Nodos[i][j]); break;
		case CAMINO: break;
		case INICIO: this.inicio = null;break;
		case META: this.meta = null; break;
		case PELIGROSO: break;
		}
		Nodos[i][j] = new Nodo(i,j);
	}
	public void borrarCamino() {
		// TODO Auto-generated method stub
		for(int i = 0; i < this.numFilas; i++){
			for(int j = 0; j < this.numColumnas; j++){
				if(this.Nodos[i][j].getTipoNodo() == TipoNodo.CAMINO) { 
					this.Nodos[i][j] = new Nodo(i, j);
				}
			}
		}
	}
	public void setWayPoints(ArrayList<Nodo> wayPoints2) {
		for(int i = 0; i < wayPoints.size(); i++) {
			this.borrarWayPoint(wayPoints.get(i).getFila(),wayPoints.get(i).getColumna());
		}
		this.wayPoints = new ArrayList<Nodo>();
		for(int i = 0; i < wayPoints.size(); i++) {
			this.añadirWayPoint(wayPoints.get(i));
		}
		
	}

	public void actualizaPeligrosos() {
		for(int i = 0; i < this.numFilas; i++) {
			for (int j = 0; j < this.numColumnas; j++) {
				if(Nodos[i][j].getTipoNodo() == TipoNodo.CAMINO_PELIGROSO) {
					Nodos[i][j].setTipoNodo(TipoNodo.PELIGROSO);
				}
			}
		}
		
	}
}
		
