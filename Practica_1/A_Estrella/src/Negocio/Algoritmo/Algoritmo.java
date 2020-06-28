package Negocio.Algoritmo;

import java.util.ArrayList;
import java.util.Stack;

import Negocio.Objetos.*;

public class Algoritmo {
	private static final String Instance = null;
	private Tablero tablero;
	private Tablero tableroAux;
	private ListaOrdenada abierta;
	private ArrayList<Nodo> cerrada;
	
	public Algoritmo(Tablero tab) {
		tablero = tab;
		tableroAux = new Tablero(tab);
		abierta = new ListaOrdenada();
		cerrada = new ArrayList<Nodo>();
	}
	
	public Tablero getTablero() {
		return tablero;
	}

	public ArrayList<Nodo> execute() {
		abierta.limpiar();
		cerrada.clear();
		Nodo r = tableroAux.getInicio();
		r.setG(0);
		abierta.añadir(r);
		Nodo m;
		
		while (abierta.vacia() == false) {
			m = abierta.sacarNodo();
			cerrada.add(m);
			
			if(m.equals((tableroAux.getMeta()))) {
				return construyeSolucion(m);
			}
			
			//Expansión de m
			for(Nodo nPrima: tableroAux.getAdyacentes(m)) {
				if(nPrima.getTipoNodo() == TipoNodo.PROHIBIDO) {
					cerrada.add(nPrima);
				}
				else if(cerrada.contains(nPrima) == false){
					nPrima.setPadre(m);
					nPrima.setG(m.getG() + nPrima.costeANodo(m));
					nPrima.setH(nPrima.costeANodo(tableroAux.getMeta()));
					
					if(abierta.esta(nPrima)) {
						Nodo n = abierta.getNodo(nPrima);
						
						if (nPrima.getG() < n.getG()) { //Redireccionar puntero de n a m
							abierta.eliminar(n);
							n.setPadre(m);
							n.setG(nPrima.getG()); //actualizo G
							abierta.añadir(n);
						}
					}
					else {
						abierta.añadir(nPrima);
					}
				}
			}
		}
		return null; //si llega aquí no hay solución
	}
	
	
	private ArrayList<Nodo> construyeSolucion(Nodo m) {
		Stack<Nodo> pila = new Stack<Nodo>();
		ArrayList<Nodo> sol = new ArrayList<Nodo>();
		pila.push(m);
		m = m.getPadre();
		while(m.equals(tableroAux.getInicio()) == false){ //Actualiza el tablero
			if(m.getTipoNodo() != TipoNodo.WAYPOINT && m.getTipoNodo() != TipoNodo.PELIGROSO) //El wayPoint es parte del camino siempre
				m.setTipoNodo(TipoNodo.CAMINO);
			else if(m.getTipoNodo() == TipoNodo.PELIGROSO) {
				m.setTipoNodo(TipoNodo.CAMINO_PELIGROSO); // Esto es solo para pintar la interfaz,
			}
			tablero.setNodo(m);
			pila.push(m);
			m = m.getPadre();
		}
		pila.push(m); //metes inicio
		while(!pila.empty()) {
			sol.add(pila.pop());
		}
		return sol;
	}
	
	public ArrayList<Nodo> executeConWayPoints(){ //Invoca a execute() varias veces (Desde I -> WayPoint1, Waypoint1 -> WaypOint2,..., WayPoint-> Meta)
		ArrayList<Nodo> wayPoints = new ArrayList<Nodo>(tablero.getWayPoints()); //constructor por valor
		ArrayList<Nodo> solucion = new ArrayList<Nodo>();
		ArrayList<Nodo> aux = new ArrayList<Nodo>();
		
		Nodo meta = siguienteWayPoint(wayPoints,tablero.getInicio());
		tableroAux.setMeta(meta);
		wayPoints.remove(meta);
		aux = execute();
		if (aux == null) {
			return null;
		}
		solucion.addAll(solucion.size(),aux);
		Nodo inicio;
		boolean fin  = false;
		while(wayPoints.size() >= 0 && fin == false) {
			inicio = meta;
			if(wayPoints.size() == 0) {
				meta = tablero.getMeta();
				fin = true;
			}
			else {
				meta = siguienteWayPoint(wayPoints,inicio);
				wayPoints.remove(meta);
			}
			tableroAux.setInicio(inicio);
			tableroAux.setMeta(meta);
			aux = execute();
			if(aux != null) {
				aux.remove(0);
				solucion.addAll(solucion.size(),aux);
			}
			else {
				return null;
			}
		}
		return solucion;
	}
	
	public Nodo siguienteWayPoint(ArrayList<Nodo> wayPoints, Nodo inicio) {
		if(wayPoints.size() > 0) {
			double distanciaMenor = wayPoints.get(0).costeANodo(inicio);
			int iMenor = 0;
			int i = 1;
			while (i < wayPoints.size()) {
				if (distanciaMenor > wayPoints.get(i).costeANodo(inicio)) {
					distanciaMenor = wayPoints.get(i).costeANodo(inicio);
					iMenor = i;
				}
				i++;
			}
			return wayPoints.get(iMenor);
		}
		return null;
	}
	
}
