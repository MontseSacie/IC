package Presentacion;


import java.util.ArrayList;

import javax.swing.JPanel;
import java.util.HashMap;
import java.util.Map;
import Negocio.Objetos.Nodo;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;

import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;
import java.awt.Color;


import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;

public class PanelArbol extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private mxGraph grafo;
	private ArrayList<Object> vertices;
	private int x;
	private int y;

	/**
	 * Create the panel.
	 */
	public PanelArbol(Nodo raiz) {
		initGui(raiz);
	}

	private void initGui(Nodo raiz) {
		this.removeAll();
		setBackground(new Color(255, 250, 240));

		grafo = new mxGraph();

		Map<String, Object> edgeStyle = new HashMap<String, Object>();
		edgeStyle.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CONNECTOR);
		edgeStyle.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_CLASSIC);
		edgeStyle.put(mxConstants.STYLE_STROKECOLOR, "a33600");
		edgeStyle.put(mxConstants.STYLE_FONTCOLOR, "#000000");
		edgeStyle.put(mxConstants.STYLE_FONTSIZE, 15);
		edgeStyle.put(mxConstants.STYLE_LABEL_BACKGROUNDCOLOR, "#EEEEEE");

		Map<String, Object> vertexStyle = new HashMap<String, Object>();
		vertexStyle.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CYLINDER);
		vertexStyle.put(mxConstants.STYLE_FONTSIZE, 20);
		vertexStyle.put(mxConstants.STYLE_FILLCOLOR, "#FFFFBF");
		vertexStyle.put(mxConstants.STYLE_FONTFAMILY, mxConstants.FONT_BOLD);

		mxStylesheet stylesheet = new mxStylesheet();
		stylesheet.setDefaultEdgeStyle(edgeStyle);
		stylesheet.setDefaultVertexStyle(vertexStyle);
		grafo.setStylesheet(stylesheet);

		grafo.getModel().beginUpdate();

		vertices = new ArrayList<Object>();
		try {

			// posición inicial para la raiz
			x = 20;
			y = 80;
			if (raiz != null) {
				Object parent = grafo.getDefaultParent();
				Object v1 = grafo.insertVertex(parent, null, raiz.getAtributoMejor().getNombre(), 230, 20, 150, 40,
						"rounded");
				vertices.add(v1);
				pintarArbol(raiz, parent, 0);
			}
		} finally {
			grafo.getModel().endUpdate();
		}

		mxGraphComponent graphComponent = new mxGraphComponent(grafo);//
		graphComponent.setGridColor(new Color(255, 250, 240));
		graphComponent.setPageShadowColor(new Color(255, 250, 240));
		graphComponent.setBackground(new Color(255, 250, 240));
		graphComponent.setPageBackgroundColor(new Color(255, 250, 240));
		graphComponent.getGraphControl().setBackground(new Color(255, 250, 240));
		graphComponent.setEnabled(false);

		JLabel lblrbolDeDecisin = new JLabel("\u00C1rbol de decisi\u00F3n");
		lblrbolDeDecisin.setForeground(new Color(102, 0, 0));
		lblrbolDeDecisin.setFont(new Font("Sylfaen", Font.BOLD, 18));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addContainerGap()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(graphComponent, GroupLayout.DEFAULT_SIZE, 621,
														Short.MAX_VALUE)
												.addComponent(lblrbolDeDecisin))
										.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				groupLayout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblrbolDeDecisin).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(graphComponent, GroupLayout.PREFERRED_SIZE, 413, GroupLayout.PREFERRED_SIZE)
						.addGap(21)));
		setLayout(groupLayout);
		this.validate();
		this.repaint();

	}

	private void pintarArbol(Nodo raiz, Object parent, int pos) {
		y += 130;
		for (int i = 0; i < raiz.getHijos().size(); i++) {
			if (i > 0)
				x += 130;
			Object vertice = null;
			if (raiz.getHijos().get(i).isHoja()) {
				if (raiz.getHijos().get(i).getValor().equals("si")) {
					vertice = grafo.insertVertex(parent, null, raiz.getHijos().get(i).getValor(), x, y, 50, 40,
							"rounded;fillColor=lightgreen");
				} else if (raiz.getHijos().get(i).getValor().equals("no")) {
					vertice = grafo.insertVertex(parent, null, raiz.getHijos().get(i).getValor(), x, y, 50, 40,
							"rounded;fillColor=#FF8888");
				}
			} else {
				vertice = grafo.insertVertex(parent, null, raiz.getHijos().get(i).getAtributoMejor().getNombre(), x, y,
						150, 40, "rounded");
			}
			vertices.add(vertice);
			int indice = vertices.indexOf(vertice);
			grafo.insertEdge(parent, null, raiz.getHijos().get(i).getValorPadre(), vertices.get(pos),
					vertices.get(indice));

			pintarArbol(raiz.getHijos().get(i), parent, indice);
			y -= 130;
		}
	}
}
