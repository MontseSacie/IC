package Negocio.Objetos;

import java.util.ArrayList;

public class ListaOrdenada {
	ArrayList<Nodo> list;
	
	public ListaOrdenada() {
		list = new ArrayList<Nodo>();
	}
	
	public void limpiar() {
		list.clear();
	}
	public void añadir(Nodo n) {
		list.add(n);
	}
	
	public void eliminar(Nodo n) {
		int i = 0;
		boolean encontrado = false;
		while(i < list.size() && encontrado == false) {
			if (list.get(i).equals(n)){
				encontrado = true;
				list.remove(i);
			}
			else {
				i++;
			}
		}
	}
	public ArrayList<Nodo> getList() {
		return list;
	}

	public void setList(ArrayList<Nodo> list) {
		this.list = list;
	}
	
	public Nodo sacarNodo() {
		int imejor= 0;
		double fmejor = list.get(imejor).costeF();
		int i = 1;
		while (i < list.size()) {
			if(list.get(i).costeF() < fmejor) {
				imejor = i;
				fmejor = list.get(i).costeF();
			}
			i++;
		}
		Nodo n = list.get(imejor);
		list.remove(imejor);
		return n;
	}
	
	public boolean esta(Nodo n) {
		return list.contains(n);
	}

	public boolean vacia() {
		return list.isEmpty();
	}
	
	public Nodo getNodo(Nodo n) {
		boolean encontrado = false;
		int i = 0;
		while(i < list.size() && encontrado == false) {
			if (list.get(i).equals(n)){
				encontrado = true;
				return list.get(i);
			}
			else {
				i++;
			}
		}
		return null;
	}
}
