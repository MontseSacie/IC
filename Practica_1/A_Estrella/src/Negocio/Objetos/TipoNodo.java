package Negocio.Objetos;

public enum TipoNodo {
	INICIO, META, LIBRE, PROHIBIDO, PELIGROSO, CAMINO, WAYPOINT, CAMINO_PELIGROSO
}
//CAMINO -> Para pintar la solución
//WAY_POINT -> son puntos que forman siempre parte del camino
//CAMINO_PELIGROSO -> Tipo auxiliar para poder pintar nodos "Peligrosos" que forman parte del camino