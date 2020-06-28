package Negocio.Auxiliar;

import java.util.ArrayList;

import Jama.Matrix;

public class AuxiliarNegocio {
	private static AuxiliarNegocio instance = null;
	
	public static AuxiliarNegocio getInstance() {
		if (instance == null) {
			instance = new AuxiliarNegocio();
		}
		return instance;
	}
	
	public static Matrix getMatrix(double [] dif) { //matriz columna
		double [][] mDif = new double[dif.length][1];
		for(int i = 0; i < dif.length; i++) {
			mDif[i][0] = dif[i];
		}
		return Matrix.constructWithCopy(mDif);
	}
	public static Matrix inicializaMatriz(int length) {
		double [][] aux =  new double [length][length];
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < length; j++) {
				aux[i][j] = 0;
			}
			
		}
		return Matrix.constructWithCopy(aux);
	}
	
	public static ArrayList<Matrix> centros2Matrix(ArrayList<double[]> centros){
		ArrayList<Matrix> sol =  new ArrayList<Matrix> ();
		for(double[] c: centros) {
			sol.add(getMatrix(c));
		}
		return sol;
	}
	
	public static ArrayList<double[]> matrix2centros(ArrayList<Matrix> centros){
		ArrayList<double[]> sol =  new ArrayList<double[]> ();
		for(Matrix c: centros) {
			sol.add(c.getRowPackedCopy());
		}
		return sol;
	}
	
	
}
